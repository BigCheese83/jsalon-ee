package ru.bigcheese.jsalon.ee.web.jsp.servlet.admin;

import ru.bigcheese.jsalon.core.model.Service;
import ru.bigcheese.jsalon.ee.ejb.ServiceEJBLocal;
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
@WebServlet(name = "ServicesServlet", urlPatterns = {"/admin/services"})
public class ServicesServlet extends HttpServlet {

    @EJB
    private ServiceEJBLocal serviceEJB;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FindResult<Service> result = serviceEJB.getAllServices();
        request.setAttribute("errMessages", EJBUtils.getMessages(ActionResult.FATAL_ERROR, result));
        request.getSession().setAttribute("servicesList", result.getResult());
        request.getRequestDispatcher("/WEB-INF/jsp/admin/services.jsp").forward(request, response);
    }
}
