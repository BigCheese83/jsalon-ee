package ru.bigcheese.jsalon.ee.web.jsp.servlet.admin;

import ru.bigcheese.jsalon.core.model.Discount;
import ru.bigcheese.jsalon.ee.ejb.DiscountFacade;
import ru.bigcheese.jsalon.ee.ejb.result.ActionResult;
import ru.bigcheese.jsalon.ee.ejb.result.FindResult;
import ru.bigcheese.jsalon.ee.web.jsp.util.EJBUtils;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by BigCheese on 21.08.15.
 */
@WebServlet(name = "DiscountsServlet", urlPatterns = {"/admin/discounts"})
public class DiscountsServlet extends HttpServlet {

    @EJB
    private DiscountFacade discountFacade;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FindResult<Discount> result = discountFacade.getAllDiscounts();
        request.setAttribute("errMessages", EJBUtils.getMessages(ActionResult.FATAL_ERROR, result));
        request.getSession().setAttribute("discountsList", result.getResult());
        request.getRequestDispatcher("/WEB-INF/jsp/admin/discounts.jsp").forward(request, response);
    }
}
