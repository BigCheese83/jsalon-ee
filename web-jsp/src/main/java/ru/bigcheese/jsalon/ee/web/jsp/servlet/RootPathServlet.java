package ru.bigcheese.jsalon.ee.web.jsp.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.bigcheese.jsalon.core.Constants;
import ru.bigcheese.jsalon.core.model.User;
import ru.bigcheese.jsalon.core.util.DBUtils;
import ru.bigcheese.jsalon.core.util.DateUtils;
import ru.bigcheese.jsalon.core.util.ExceptionUtils;
import ru.bigcheese.jsalon.core.util.NumberUtils;
import ru.bigcheese.jsalon.ee.ejb.UserEJBLocal;

import javax.ejb.EJB;
import javax.servlet.ServletContext;
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
    private static final Logger LOG = LoggerFactory.getLogger(RootPathServlet.class);

    @EJB
    private UserEJBLocal userEJB;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User)request.getSession().getAttribute("user");
        if (user == null) {
            try {
                LOG.info("========== JSalon app started ==========");
                user = userEJB.getUserByLogin(request.getRemoteUser());
                if (user == null) {
                    throw new SecurityException("Not find user by login.");
                }
                LOG.info("Login success. User {} ({}) authenticated.", user.getLogin(), user.getFullFIO());
                LOG.info("Workstation IP = {}", request.getRemoteHost());
            } catch (Exception e) {
                LOG.error("Login failed.", e);
                throw new ServletException("Unable get user info." + ExceptionUtils.parse(e), e);
            }
            request.getSession().setAttribute("user", user);
            putConstantsToContext(request.getSession().getServletContext());
        }

        String forwardPath;
        if (request.getServletPath().equals("/admin")) {
            forwardPath = "/admin/users";
            LOG.info("========== Admin console ==========");
        } else {
            forwardPath = "/user/clients";
        }
        request.getRequestDispatcher(forwardPath).forward(request, response);
    }

    private void putConstantsToContext(ServletContext context) {
        context.setAttribute("countriesList", Constants.ALL_COUNTRIES);
    }
}
