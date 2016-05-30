package ru.bigcheese.jsalon.ee.web.jsp.servlet.admin;

import ru.bigcheese.jsalon.core.model.Post;
import ru.bigcheese.jsalon.core.model.Service;
import ru.bigcheese.jsalon.core.model.bind.PostServiceBind;
import ru.bigcheese.jsalon.ee.ejb.PostFacade;
import ru.bigcheese.jsalon.ee.ejb.PostServiceFacade;
import ru.bigcheese.jsalon.ee.ejb.ServiceFacade;
import ru.bigcheese.jsalon.ee.ejb.result.FindResult;
import ru.bigcheese.jsalon.ee.web.jsp.util.EJBUtils;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static ru.bigcheese.jsalon.ee.ejb.result.ActionResult.FATAL_ERROR;

/**
 * Created by BigCheese on 18.04.16.
 */
@WebServlet(name = "PostServiceServlet", urlPatterns = {"/admin/postservice"})
public class PostServiceServlet extends HttpServlet {

    @EJB
    private PostServiceFacade postServiceFacade;
    @EJB
    private PostFacade postFacade;
    @EJB
    private ServiceFacade serviceFacade;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FindResult<PostServiceBind> result = postServiceFacade.getAllPostServiceBinds();
        FindResult<Post> posts = postFacade.getAllPosts();
        FindResult<Service> services = serviceFacade.getAllServices();
        List<String> errors = new ArrayList<>();
        errors.addAll(EJBUtils.getMessages(FATAL_ERROR, result));
        errors.addAll(EJBUtils.getMessages(FATAL_ERROR, posts));
        errors.addAll(EJBUtils.getMessages(FATAL_ERROR, services));
        request.setAttribute("errMessages", errors);
        request.getSession().setAttribute("postserviceList", result.getResult());
        request.getSession().setAttribute("postsList", posts.getResult());
        request.getSession().setAttribute("servicesList", services.getResult());
        request.getRequestDispatcher("/WEB-INF/jsp/admin/postservice.jsp").forward(request, response);
    }
}
