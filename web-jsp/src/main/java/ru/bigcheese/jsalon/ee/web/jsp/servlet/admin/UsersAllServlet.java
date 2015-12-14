package ru.bigcheese.jsalon.ee.web.jsp.servlet.admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by BigCheese on 19.08.15.
 */
@WebServlet(name = "UsersAllServlet", urlPatterns = {"/admin/users"})
public class UsersAllServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/admin/users_all.jsp").forward(request, response);
    }
}
