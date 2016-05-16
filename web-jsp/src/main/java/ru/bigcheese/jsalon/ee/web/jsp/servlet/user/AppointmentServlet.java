package ru.bigcheese.jsalon.ee.web.jsp.servlet.user;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by BigCheese on 26.04.16.
 */
@WebServlet(name = "AppointmentServlet", urlPatterns = {"/user/appointment"})
public class AppointmentServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/user/appointment.jsp").forward(request, response);
    }
}
