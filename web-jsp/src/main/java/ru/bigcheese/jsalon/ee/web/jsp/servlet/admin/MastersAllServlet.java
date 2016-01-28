package ru.bigcheese.jsalon.ee.web.jsp.servlet.admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by BigCheese on 22.12.15.
 */
@WebServlet(name = "MastersAllServlet", urlPatterns = {"/admin/masters"})
public class MastersAllServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/admin/masters_all.jsp").forward(request, response);
    }
}
