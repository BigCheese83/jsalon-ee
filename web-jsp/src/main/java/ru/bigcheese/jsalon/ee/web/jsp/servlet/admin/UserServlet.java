package ru.bigcheese.jsalon.ee.web.jsp.servlet.admin;

import ru.bigcheese.jsalon.core.util.ExceptionUtils;
import ru.bigcheese.jsalon.ee.ejb.UserEJBLocal;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by aleksey.shulchenkov on 05.11.15.
 */
@WebServlet(name = "UserServlet", urlPatterns = {"/admin/user"})
public class UserServlet extends HttpServlet {

    @EJB
    private UserEJBLocal userEJB;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> roles = (List<String>) request.getSession().getAttribute("allRoles");
        if (roles == null) {
            try {
                request.getSession().setAttribute("allRoles", userEJB.getAllRoles());
            } catch (Exception e) {
                throw new ServletException("Unable get roles for user." + ExceptionUtils.parse(e), e);
            }
        }
        request.getRequestDispatcher("/WEB-INF/jsp/admin/user_crud.jsp").forward(request, response);
    }
}
