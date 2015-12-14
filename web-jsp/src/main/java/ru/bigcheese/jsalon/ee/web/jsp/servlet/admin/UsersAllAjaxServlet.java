package ru.bigcheese.jsalon.ee.web.jsp.servlet.admin;

import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import ru.bigcheese.jsalon.core.model.User;
import ru.bigcheese.jsalon.core.util.ExceptionUtils;
import ru.bigcheese.jsalon.ee.dao.QueryCriteria;
import ru.bigcheese.jsalon.ee.dao.QueryCriteriaFactory;
import ru.bigcheese.jsalon.ee.ejb.UserEJBLocal;
import ru.bigcheese.jsalon.ee.web.jsp.servlet.AbstractAjaxServlet;
import ru.bigcheese.jsalon.ee.web.jsp.support.DatatablesRequest;
import ru.bigcheese.jsalon.ee.web.jsp.support.DatatablesResponse;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by BigCheese on 02.11.15.
 */
@WebServlet(name = "UsersAllAjaxServlet", urlPatterns = {"/admin/users/ajax"})
public class UsersAllAjaxServlet extends AbstractAjaxServlet {

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
            resp.setData(tranformData(users));
            resp.setDraw(req.getDraw());
            resp.setRecordsTotal(users.size());
            resp.setRecordsFiltered(users.size());
        } catch (Exception e) {
            resp = DatatablesResponse.buildErrorResponse(req.getDraw(), ExceptionUtils.parse(e));
        }
        return new Gson().toJson(resp);
    }

    private Object[][] tranformData(List<User> users) {
        Object[][] data = new Object[users.size()][5];
        for (int i = 0; i < users.size(); i++) {
            User u = users.get(i);
            data[i][0] = u.getLogin();
            data[i][1] = u.getLastName();
            data[i][2] = u.getFirstName();
            data[i][3] = u.getMiddleName();
            data[i][4] = u.getRole();
        }
        return data;
    }

    private int transformIndex(int index) {
        if (index < 4) {
            return index + 2;
        } else {
            return index + 3;
        }
    }
}
