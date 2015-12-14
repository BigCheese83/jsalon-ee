package ru.bigcheese.jsalon.ee.web.jsp.servlet.admin;

import org.apache.commons.lang3.StringUtils;
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

    @EJB
    private ServiceEJBLocal serviceEJB;

    @Override
    protected String getJsonResponse(HttpServletRequest request) {
        String json;
        try {
            String radioId = request.getParameter("radioID");
            CrudEntityResult result;
            if ("newRadio".equals(radioId)) {
                Service service = parseRequest(request);
                service.validate();
                result = serviceEJB.createService(service);
            } else if ("editRadio".equals(radioId)) {
                Service service = parseRequest(request);
                service.validate();
                result = serviceEJB.updateService(service);
            } else if ("delRadio".equals(radioId)) {
                result = serviceEJB.deleteService(NumberUtils.toLong(request.getParameter("serviceID")));
            } else {
                throw new Exception("Unknown operation");
            }
            json = JsonUtils.getJsonCrudEjbResult(result);
        } catch (ValidationException e) {
            json = JsonUtils.getJsonValidateErrors(e);
        } catch (Throwable e) {
            json = JsonUtils.getJsonError(e);
        }
        return json;
    }

    private Service parseRequest(HttpServletRequest request) {
        Service result = new Service();
        result.setId(NumberUtils.toLong(request.getParameter("serviceID")));
        result.setName(StringUtils.stripToNull(request.getParameter("serviceName")));
        result.setCost(NumberUtils.toBigDecimal(request.getParameter("serviceCost")));
        result.setDuration(NumberUtils.toInteger(request.getParameter("serviceDuration")));
        result.setDescription(request.getParameter("serviceDescription"));
        return result;
    }
}
