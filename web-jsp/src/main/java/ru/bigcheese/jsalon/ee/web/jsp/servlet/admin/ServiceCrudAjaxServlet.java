package ru.bigcheese.jsalon.ee.web.jsp.servlet.admin;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.bigcheese.jsalon.core.exception.ValidationException;
import ru.bigcheese.jsalon.core.model.Service;
import ru.bigcheese.jsalon.core.util.NumberUtils;
import ru.bigcheese.jsalon.ee.ejb.ServiceEJBLocal;
import ru.bigcheese.jsalon.ee.ejb.result.CrudEntityResult;
import ru.bigcheese.jsalon.ee.web.jsp.servlet.AbstractAjaxServlet;
import ru.bigcheese.jsalon.ee.web.jsp.util.JsonUtils;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by BigCheese on 27.10.15.
 */
@WebServlet(name = "ServiceCrudAjaxServlet", urlPatterns = {"/admin/services/ajax"})
public class ServiceCrudAjaxServlet extends AbstractAjaxServlet {
    private static final Logger LOG = LoggerFactory.getLogger(ServiceCrudAjaxServlet.class);

    @EJB
    private ServiceEJBLocal serviceEJB;

    @Override
    protected String getJsonResponse(HttpServletRequest request) {
        String json;
        try {
            String radioId = request.getParameter("radioID");
            CrudEntityResult<Service> result;
            if ("newRadio".equals(radioId)) {
                Service service = parseRequest(request);
                service.validate();
                result = serviceEJB.createService(service);
            } else if ("editRadio".equals(radioId)) {
                Service service = parseRequest(request);
                service.validate();
                result = serviceEJB.updateService(service);
            } else if ("delRadio".equals(radioId)) {
                result = serviceEJB.deleteService(NumberUtils.toLong(request.getParameter("id")));
            } else {
                throw new Exception("Unknown operation");
            }
            json = JsonUtils.getGson().toJson(result);
        } catch (ValidationException e) {
            LOG.error("Validation failed. {}.", e.getMessage());
            json = JsonUtils.getJsonValidateErrors(e);
        } catch (Throwable e) {
            LOG.error("Error.", e);
            json = JsonUtils.getJsonException(e);
        }
        return json;
    }

    private Service parseRequest(HttpServletRequest request) {
        Service result = new Service();
        result.setId(NumberUtils.toLong(request.getParameter("id")));
        result.setName(StringUtils.stripToNull(request.getParameter("name")));
        result.setCost(NumberUtils.toBigDecimal(request.getParameter("cost")));
        result.setDuration(NumberUtils.toInteger(request.getParameter("duration")));
        result.setDescription(request.getParameter("description"));
        return result;
    }
}
