package ru.bigcheese.jsalon.ee.web.jsp.servlet.admin;

import org.apache.commons.lang3.StringUtils;
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

    @EJB
    private DiscountEJBLocal discountEJB;

    @Override
    protected String getJsonResponse(HttpServletRequest request) {
        String json;
        try {
            String radioId = request.getParameter("radioID");
            CrudEntityResult result;
            if ("newRadio".equals(radioId)) {
                Discount discount = parseRequest(request);
                discount.validate();
                result = discountEJB.createDiscount(discount);
            } else if ("editRadio".equals(radioId)) {
                Discount discount = parseRequest(request);
                discount.validate();
                result = discountEJB.updateDiscount(discount);
            } else if ("delRadio".equals(radioId)) {
                result = discountEJB.deleteDiscount(NumberUtils.toLong(request.getParameter("discountID")));
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

    private Discount parseRequest(HttpServletRequest request) {
        Long id = NumberUtils.toLong(request.getParameter("discountID"));
        String name = StringUtils.stripToNull(request.getParameter("discountName"));
        Integer value = NumberUtils.toInteger(request.getParameter("discountValue"));
        return new Discount(id, name, value);
    }
}
