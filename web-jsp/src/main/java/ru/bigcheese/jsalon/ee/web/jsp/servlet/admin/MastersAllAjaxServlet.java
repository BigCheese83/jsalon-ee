package ru.bigcheese.jsalon.ee.web.jsp.servlet.admin;

import com.google.gson.GsonBuilder;
import org.apache.commons.lang3.StringUtils;
import ru.bigcheese.jsalon.core.Constants;
import ru.bigcheese.jsalon.core.model.Master;
import ru.bigcheese.jsalon.core.util.ExceptionUtils;
import ru.bigcheese.jsalon.ee.dao.QueryCriteria;
import ru.bigcheese.jsalon.ee.dao.QueryCriteriaFactory;
import ru.bigcheese.jsalon.ee.ejb.MasterEJBLocal;
import ru.bigcheese.jsalon.ee.web.jsp.servlet.AbstractAjaxServlet;
import ru.bigcheese.jsalon.ee.web.jsp.support.DatatablesRequest;
import ru.bigcheese.jsalon.ee.web.jsp.support.DatatablesResponse;
import ru.bigcheese.jsalon.ee.web.jsp.to.MasterTO;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by BigCheese on 20.01.16.
 */
@WebServlet(name = "MastersAllAjaxServlet", urlPatterns = {"/admin/masters/ajax"})
public class MastersAllAjaxServlet extends AbstractAjaxServlet {

    @EJB
    private MasterEJBLocal masterEJB;

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
            List<Master> masters = masterEJB.findLimitUsersByCriteria(req.getLength(), criteria);
            resp.setData(tranformData(masters));
            resp.setDraw(req.getDraw());
            resp.setRecordsTotal(masters.size());
            resp.setRecordsFiltered(masters.size());
        } catch (Exception e) {
            resp = DatatablesResponse.buildErrorResponse(req.getDraw(), ExceptionUtils.parse(e));
        }
        return new GsonBuilder()
                .setDateFormat(Constants.ISO_DATE_FORMAT)
                .create().toJson(resp);
    }

    private Object[] tranformData(List<Master> masters) {
        Object[] data = new Object[masters.size()];
        for (int i = 0; i < masters.size(); i++) {
            data[i] = new MasterTO(masters.get(i));
        }
        return data;
    }

    private int transformIndex(int index) {
        if (index < 6) {
            return index + 1;
        } else if (index == 6) {
            return index + 2;
        } else {
            return index + 3;
        }
    }
}
