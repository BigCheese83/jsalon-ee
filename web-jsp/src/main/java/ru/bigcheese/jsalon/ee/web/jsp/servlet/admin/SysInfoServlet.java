package ru.bigcheese.jsalon.ee.web.jsp.servlet.admin;

import ru.bigcheese.jsalon.core.support.SystemInfo;
import ru.bigcheese.jsalon.ee.ejb.SecurityEJB;

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
@WebServlet(name = "SysInfoServlet", urlPatterns = {"/admin/sysinfo"})
public class SysInfoServlet extends HttpServlet {

    @EJB
    private SecurityEJB securityEJB;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute("dbmetadata", securityEJB.getDatabaseMetaData());
        request.getSession().setAttribute("systeminfo", new SystemInfo());
        request.getRequestDispatcher("/WEB-INF/jsp/admin/sysinfo.jsp").forward(request, response);
    }
}
