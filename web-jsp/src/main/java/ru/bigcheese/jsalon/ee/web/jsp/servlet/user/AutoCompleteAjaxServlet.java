package ru.bigcheese.jsalon.ee.web.jsp.servlet.user;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.bigcheese.jsalon.ee.ejb.ClientEJBLocal;
import ru.bigcheese.jsalon.ee.ejb.MasterEJBLocal;
import ru.bigcheese.jsalon.ee.ejb.PostServiceEJBLocal;
import ru.bigcheese.jsalon.ee.ejb.ServiceEJBLocal;
import ru.bigcheese.jsalon.ee.web.jsp.servlet.AbstractAjaxServlet;
import ru.bigcheese.jsalon.ee.web.jsp.util.JsonUtils;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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
    private ClientEJBLocal clientEJB;
    @EJB
    private MasterEJBLocal masterEJB;
    @EJB
    private ServiceEJBLocal serviceEJB;
    @EJB
    private PostServiceEJBLocal postServiceEJB;

    @Override
    protected String getJsonResponse(HttpServletRequest request) {
        String json = "[]";
        String term = request.getParameter("term");
        if (StringUtils.isBlank(term)) {
            return json;
        }
        try {
            String id = request.getParameter("id");
            List<String> list;
            if (CLIENT_AC.equals(id)) {
                list = clientEJB.filterClientsByNames(request.getParameter("client"));
            } else if (MASTER_AC.equals(id)) {
                if ("true".equals(request.getParameter("service-valid"))) {
                    list = masterEJB.filterMastersByNamesAndService(request.getParameter("master"), request.getParameter("service"));
                } else {
                    list = masterEJB.filterMastersByNames(request.getParameter("master"));
                }
            } else if (SERVICE_AC.equals(id)) {
                if ("true".equals(request.getParameter("master-valid"))) {
                    list = serviceEJB.filterServicesByNameAndMaster(request.getParameter("service"), request.getParameter("master"));
                } else {
                    list = serviceEJB.filterServicesByName(request.getParameter("service"));
                }
            } else {
                throw new Exception("Unknown id autocomplete!");
            }
            json = JsonUtils.getGson().toJson(list);
        } catch (Throwable e) {
            LOG.error("Error.", e);
            json = JsonUtils.getJsonException(e);
        }
        return json;
    }
}
