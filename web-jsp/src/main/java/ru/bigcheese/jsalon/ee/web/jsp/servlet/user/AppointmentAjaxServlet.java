package ru.bigcheese.jsalon.ee.web.jsp.servlet.user;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.bigcheese.jsalon.core.exception.ValidationException;
import ru.bigcheese.jsalon.core.model.Appointment;
import ru.bigcheese.jsalon.core.util.DateUtils;
import ru.bigcheese.jsalon.core.util.NumberUtils;
import ru.bigcheese.jsalon.ee.ejb.AppointmentEJBLocal;
import ru.bigcheese.jsalon.ee.ejb.result.CrudEntityResult;
import ru.bigcheese.jsalon.ee.web.jsp.servlet.AbstractAjaxServlet;
import ru.bigcheese.jsalon.ee.web.jsp.util.JsonUtils;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;

import static ru.bigcheese.jsalon.core.Constants.ISO_DATE_FORMAT;

/**
 * Created by BigCheese on 20.05.16.
 */
@WebServlet(name = "AppointmentAjaxServlet", urlPatterns = {"/user/appointment/ajax"})
public class AppointmentAjaxServlet extends AbstractAjaxServlet {
    private static final Logger LOG = LoggerFactory.getLogger(AppointmentAjaxServlet.class);

    @EJB
    private AppointmentEJBLocal appointmentEJB;

    @Override
    protected String getJsonResponse(HttpServletRequest request) {
        String json;
        try {
            Appointment appointment = parseRequest(request);
            appointment.validate();
            CrudEntityResult<Appointment> result = appointmentEJB.createAppointment(appointment);
            json = JsonUtils.getGson().toJson(result);
        } catch (ValidationException e) {
            LOG.error("Validation failed. {}.", e.getMessage());
            json = JsonUtils.getJsonValidateErrors(e);
        } catch (Throwable e) {
            LOG.error("", e);
            json = JsonUtils.getJsonException(e);
        }
        return json;
    }

    private Appointment parseRequest(HttpServletRequest request) {
        Appointment result = new Appointment();
        Date date = DateUtils.parse(request.getParameter("dateAppoint"), ISO_DATE_FORMAT);
        if (date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            Integer minutes = parseTime(request.getParameter("timeAppoint"));
            if (minutes != null) {
                DateUtils.addMinutes(calendar, minutes);
            }
            result.setAppointmentDate(calendar);
        }
        result.setMasterId(NumberUtils.toLong(request.getParameter("master-id")));
        result.setClientId(NumberUtils.toLong(request.getParameter("client-id")));
        result.setServiceId(NumberUtils.toLong(request.getParameter("service-id")));
        result.setNote(StringUtils.stripToNull(request.getParameter("note")));
        return result;
    }

    private Integer parseTime(String time) {
        if (StringUtils.isBlank(time)) {
            return null;
        }
        if ((time.trim().length() != 5) || (time.trim().charAt(2) != ':')) {
            LOG.error("Incorrect time format: {}. Must be HH:mm", time);
            return null;
        }
        try {
            int hours = Integer.parseInt(time.substring(0,2));
            int minutes = Integer.parseInt(time.substring(3));
            return hours*60 + minutes;
        } catch (NumberFormatException e) {
            LOG.error("Error parse time", e);
            return null;
        }
    }
}
