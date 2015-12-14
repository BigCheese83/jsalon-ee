package ru.bigcheese.jsalon.ee.web.jsp.servlet;

import ru.bigcheese.jsalon.core.model.User;
import ru.bigcheese.jsalon.core.util.ExceptionUtils;
import ru.bigcheese.jsalon.ee.ejb.UserEJBLocal;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by BigCheese on 13.08.15.
 */
@WebServlet(name = "RootPathServlet", urlPatterns = {"/user", "/admin"})
public class RootPathServlet extends HttpServlet {

    @EJB
    private UserEJBLocal userEJB;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User)request.getSession().getAttribute("user");
        if (user == null) {
            try {
                user = userEJB.getUserByLogin(request.getRemoteUser());
                if (user == null) throw new SecurityException("Not find user by login.");
            } catch (Exception e) {
                throw new ServletException("Unable get user info." + ExceptionUtils.parse(e), e);
            }
            request.getSession().setAttribute("user", user);
        }

        String forwardPath;
        if (request.getServletPath().equals("/admin")) {
            forwardPath = "/admin/users";
        } else {
            forwardPath = "/WEB-INF/jsp/index.jsp";
        }
        request.getRequestDispatcher(forwardPath).forward(request, response);
    }
}