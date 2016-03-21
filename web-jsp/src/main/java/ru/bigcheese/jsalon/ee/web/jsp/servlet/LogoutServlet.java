package ru.bigcheese.jsalon.ee.web.jsp.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.bigcheese.jsalon.core.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by BigCheese on 13.08.15.
 */
@WebServlet(name = "LogoutServlet", urlPatterns = {"/user/logout", "/admin/logout"})
public class LogoutServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(LogoutServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User)request.getSession().getAttribute("user");
        if (user != null) {
            LOG.info("Logout user {} ({})", user.getLogin(), user.getFullFIO());
            LOG.info("Workstation IP = {}", request.getRemoteHost());
        }
        request.getSession().invalidate();
        if ("/admin/logout".equals(request.getServletPath())) {
            response.sendRedirect(request.getContextPath() + "/admin");
        } else {
            response.sendRedirect(request.getContextPath());
        }
    }
}
