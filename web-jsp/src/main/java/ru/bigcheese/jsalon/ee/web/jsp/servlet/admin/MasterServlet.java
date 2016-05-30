package ru.bigcheese.jsalon.ee.web.jsp.servlet.admin;

import ru.bigcheese.jsalon.ee.ejb.PostFacade;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by BigCheese on 22.12.15.
 */
@WebServlet(name = "MasterServlet", urlPatterns = {"/admin/master"})
public class MasterServlet extends HttpServlet {

    @EJB
    private PostFacade postFacade;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute("postsList", postFacade.getAllPosts().getResult());
        request.getRequestDispatcher("/WEB-INF/jsp/admin/master_crud.jsp").forward(request, response);
    }
}
