package ru.bigcheese.jsalon.ee.web.jsp.servlet.user;

import ru.bigcheese.jsalon.ee.ejb.DiscountEJBLocal;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by BigCheese on 12.02.16.
 */
@WebServlet(name = "ClientServlet", urlPatterns = {"/user/client"})
public class ClientServlet extends HttpServlet {

    @EJB
    private DiscountEJBLocal discountEJB;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute("discountsList", discountEJB.getAllDiscounts().getResult());
        request.getRequestDispatcher("/WEB-INF/jsp/user/client_crud.jsp").forward(request, response);
    }
}
