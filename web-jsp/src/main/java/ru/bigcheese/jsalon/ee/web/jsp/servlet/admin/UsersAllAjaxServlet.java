package ru.bigcheese.jsalon.ee.web.jsp.servlet.admin;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.bigcheese.jsalon.core.model.User;
import ru.bigcheese.jsalon.core.util.ExceptionUtils;
import ru.bigcheese.jsalon.ee.dao.QueryCriteria;
import ru.bigcheese.jsalon.ee.dao.QueryCriteriaFactory;
import ru.bigcheese.jsalon.ee.ejb.UserEJBLocal;
import ru.bigcheese.jsalon.ee.web.jsp.servlet.AbstractAjaxServlet;
import ru.bigcheese.jsalon.ee.web.jsp.support.DatatablesRequest;
import ru.bigcheese.jsalon.ee.web.jsp.support.DatatablesResponse;
import ru.bigcheese.jsalon.ee.web.jsp.util.JsonUtils;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by BigCheese on 02.11.15.
 */
@WebServlet(name = "UsersAllAjaxServlet", urlPatterns = {"/admin/users/ajax"})
public class UsersAllAjaxServlet extends AbstractAjaxServlet {
    private static final Logger LOG = LoggerFactory.getLogger(UsersAllAjaxServlet.class);

    @EJB
    private UserEJBLocal userEJB;

    @Override
    protected String getJsonResponse(HttpServletRequest request) {
        DatatablesRequest req = new DatatablesRequest(request);
        DatatablesResponse resp = new DatatablesResponse();
        try {
            QueryCriteria criteria = QueryCriteriaFactory.getInstance();
            if (StringUtils.isNotBlank(req.getSearchValue())) {
                criteria.where()
                        .like("username", req.getSearchValue(), "x%").or()
                        .like("lastname", req.getSearchValue(), "x%").or()
                        .like("firstname", req.getSearchValue(), "x%").or()
                        .like("middlename", req.getSearchValue(), "x%");
            }
            criteria.orderBy(transformIndex(req.getOrderColumn()), req.getOrderDir())
                    .limit(req.getLength(), req.getStart());
            List<User> users = userEJB.findLimitUsersByCriteria(req.getLength(), criteria);
            resp.setData(users.toArray());
            resp.setDraw(req.getDraw());
            resp.setRecordsTotal(users.size());
            resp.setRecordsFiltered(users.size());
        } catch (Exception e) {
            LOG.error("Error.", e);
            resp = DatatablesResponse.buildErrorResponse(req.getDraw(), ExceptionUtils.parse(e));
        }
        return JsonUtils.getGson().toJson(resp);
    }

    private int transformIndex(int index) {
        if (index < 4) {
            return index + 2;
        } else {
            return index + 3;
        }
    }
}
