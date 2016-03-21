package ru.bigcheese.jsalon.ee.web.jsp.servlet.admin;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.bigcheese.jsalon.core.exception.ValidationException;
import ru.bigcheese.jsalon.core.model.Discount;
import ru.bigcheese.jsalon.core.util.NumberUtils;
import ru.bigcheese.jsalon.ee.ejb.DiscountEJBLocal;
import ru.bigcheese.jsalon.ee.ejb.result.CrudEntityResult;
import ru.bigcheese.jsalon.ee.web.jsp.servlet.AbstractAjaxServlet;
import ru.bigcheese.jsalon.ee.web.jsp.util.JsonUtils;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by BigCheese on 25.08.15.
 */
@WebServlet(name = "DiscountCrudAjaxServlet", urlPatterns = {"/admin/discounts/ajax"})
public class DiscountCrudAjaxServlet extends AbstractAjaxServlet {
    private static final Logger LOG = LoggerFactory.getLogger(DiscountCrudAjaxServlet.class);

    @EJB
    private DiscountEJBLocal discountEJB;

    @Override
    protected String getJsonResponse(HttpServletRequest request) {
        String json;
        try {
            String radioId = request.getParameter("radioID");
            CrudEntityResult<Discount> result;
            if ("newRadio".equals(radioId)) {
                Discount discount = parseRequest(request);
                discount.validate();
                result = discountEJB.createDiscount(discount);
            } else if ("editRadio".equals(radioId)) {
                Discount discount = parseRequest(request);
                discount.validate();
                result = discountEJB.updateDiscount(discount);
            } else if ("delRadio".equals(radioId)) {
                result = discountEJB.deleteDiscount(NumberUtils.toLong(request.getParameter("id")));
            } else {
                throw new Exception("Unknown operation");
            }
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

    private Discount parseRequest(HttpServletRequest request) {
        Long id = NumberUtils.toLong(request.getParameter("id"));
        String name = StringUtils.stripToNull(request.getParameter("name"));
        Integer value = NumberUtils.toInteger(request.getParameter("value"));
        return new Discount(id, name, value);
    }
}
