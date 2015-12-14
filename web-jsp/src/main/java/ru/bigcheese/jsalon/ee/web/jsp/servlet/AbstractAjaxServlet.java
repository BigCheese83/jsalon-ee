package ru.bigcheese.jsalon.ee.web.jsp.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by BigCHeese on 27.10.15.
 */
public abstract class AbstractAjaxServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String json = getJsonResponse(req);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");
        PrintWriter out = resp.getWriter();
        out.print(json);
        out.flush();
    }

    protected abstract String getJsonResponse(HttpServletRequest request);
}
