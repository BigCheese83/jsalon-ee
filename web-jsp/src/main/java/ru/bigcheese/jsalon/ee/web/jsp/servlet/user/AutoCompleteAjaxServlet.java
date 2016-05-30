package ru.bigcheese.jsalon.ee.web.jsp.servlet.user;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.bigcheese.jsalon.core.model.to.ModelTO;
import ru.bigcheese.jsalon.ee.ejb.ClientFacade;
import ru.bigcheese.jsalon.ee.ejb.MasterFacade;
import ru.bigcheese.jsalon.ee.ejb.PostServiceFacade;
import ru.bigcheese.jsalon.ee.ejb.ServiceFacade;
import ru.bigcheese.jsalon.ee.web.jsp.servlet.AbstractAjaxServlet;
import ru.bigcheese.jsalon.ee.web.jsp.util.JsonUtils;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by BigCheese on 27.04.16.
 */
@WebServlet(name = "AutoCompleteAjaxServlet", urlPatterns = {"/user/autocomplete/ajax"})
public class AutoCompleteAjaxServlet extends AbstractAjaxServlet {
    private static final Logger LOG = LoggerFactory.getLogger(AutoCompleteAjaxServlet.class);
    private static final String CLIENT_AC = "client-ac";
    private static final String MASTER_AC = "master-ac";
    private static final String SERVICE_AC = "service-ac";

    @EJB
    private ClientFacade clientFacade;
    @EJB
    private MasterFacade masterFacade;
    @EJB
    private ServiceFacade serviceFacade;
    @EJB
    private PostServiceFacade postServiceFacade;

    @Override
    protected String getJsonResponse(HttpServletRequest request) {
        String json = "[]";
        String term = request.getParameter("term");
        if (StringUtils.isBlank(term)) {
            return json;
        }
        try {
            String id = request.getParameter("id");
            List<ModelTO> list;
            if (CLIENT_AC.equals(id)) {
                list = clientFacade.filterClientsByNames(request.getParameter("client"));
            } else if (MASTER_AC.equals(id)) {
                if (NumberUtils.isNumber(request.getParameter("service-id"))) {
                    list = masterFacade.filterMastersByNamesAndService(request.getParameter("master"), request.getParameter("service"));
                } else {
                    list = masterFacade.filterMastersByNames(request.getParameter("master"));
                }
            } else if (SERVICE_AC.equals(id)) {
                if (NumberUtils.isNumber(request.getParameter("master-id"))) {
                    list = serviceFacade.filterServicesByNameAndMaster(request.getParameter("service"), request.getParameter("master"));
                } else {
                    list = serviceFacade.filterServicesByName(request.getParameter("service"));
                }
            } else {
                throw new Exception("Unknown id autocomplete!");
            }
            json = JsonUtils.getGsonBuilder()
                    .registerTypeAdapter(ModelTO.class, JsonUtils.MODEL_TO_SERIALIZER)
                    .create().toJson(list);
        } catch (Throwable e) {
            LOG.error("Error.", e);
            json = JsonUtils.getJsonException(e);
        }
        return json;
    }
}
