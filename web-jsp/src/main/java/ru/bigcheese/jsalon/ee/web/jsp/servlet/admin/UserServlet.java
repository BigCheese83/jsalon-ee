package ru.bigcheese.jsalon.ee.web.jsp.servlet.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.bigcheese.jsalon.core.util.ExceptionUtils;
import ru.bigcheese.jsalon.ee.ejb.UserFacade;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by BigCheese on 05.11.15.
 */
@WebServlet(name = "UserServlet", urlPatterns = {"/admin/user"})
public class UserServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(DiscountCrudAjaxServlet.class);

    @EJB
    private UserFacade userFacade;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> roles = (List<String>) request.getSession().getAttribute("allRoles");
        if (roles == null) {
            try {
                request.getSession().setAttribute("allRoles", userFacade.getAllRoles());
            } catch (Exception e) {
                LOG.error("Error.", e);
                throw new ServletException("Unable get roles for user." + ExceptionUtils.parse(e), e);
            }
        }
        request.getRequestDispatcher("/WEB-INF/jsp/admin/user_crud.jsp").forward(request, response);
    }
}
