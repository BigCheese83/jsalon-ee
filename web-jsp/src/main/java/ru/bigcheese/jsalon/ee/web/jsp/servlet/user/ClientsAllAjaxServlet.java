package ru.bigcheese.jsalon.ee.web.jsp.servlet.user;

import org.apache.commons.lang3.StringUtils;
import ru.bigcheese.jsalon.core.model.Client;
import ru.bigcheese.jsalon.core.util.ExceptionUtils;
import ru.bigcheese.jsalon.ee.dao.QueryCriteria;
import ru.bigcheese.jsalon.ee.dao.QueryCriteriaFactory;
import ru.bigcheese.jsalon.ee.ejb.ClientEJBLocal;
import ru.bigcheese.jsalon.ee.web.jsp.servlet.AbstractAjaxServlet;
import ru.bigcheese.jsalon.ee.web.jsp.support.DatatablesRequest;
import ru.bigcheese.jsalon.ee.web.jsp.support.DatatablesResponse;
import ru.bigcheese.jsalon.ee.web.jsp.to.ClientTO;
import ru.bigcheese.jsalon.ee.web.jsp.util.JsonUtils;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by BigCheese on 09.02.16.
 */
@WebServlet(name = "ClientsAllAjaxServlet", urlPatterns = {"/user/clients/ajax"})
public class ClientsAllAjaxServlet extends AbstractAjaxServlet {

    @EJB
    private ClientEJBLocal clientEJB;

    @Override
    protected String getJsonResponse(HttpServletRequest request) {
        DatatablesRequest req = new DatatablesRequest(request);
        DatatablesResponse resp = new DatatablesResponse();
        try {
            QueryCriteria criteria = QueryCriteriaFactory.getInstance();
            if (StringUtils.isNotBlank(req.getSearchValue())) {
                criteria.where()
                        .like("surname", req.getSearchValue(), "x%").or()
                        .like("name", req.getSearchValue(), "x%").or()
                        .like("patronymic", req.getSearchValue(), "x%");
            }
            criteria.orderBy(transformIndex(req.getOrderColumn()), req.getOrderDir())
                    .limit(req.getLength(), req.getStart());
            List<Client> clients = clientEJB.findLimitClientsByCriteria(req.getLength(), criteria);
            resp.setData(ClientTO.toList(clients).toArray());
            resp.setDraw(req.getDraw());
            resp.setRecordsTotal(clients.size());
            resp.setRecordsFiltered(clients.size());
        } catch (Exception e) {
            resp = DatatablesResponse.buildErrorResponse(req.getDraw(), ExceptionUtils.parse(e));
        }
        return JsonUtils.getGson().toJson(resp);
    }

    private int transformIndex(int index) {
        if (index < 6) {
            return index + 1;
        } else {
            return index + 5;
        }
    }
}