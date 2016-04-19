package ru.bigcheese.jsalon.ee.web.jsp.servlet.admin;

import ru.bigcheese.jsalon.core.model.Post;
import ru.bigcheese.jsalon.core.model.PostServiceBind;
import ru.bigcheese.jsalon.core.model.Service;
import ru.bigcheese.jsalon.ee.ejb.PostEJBLocal;
import ru.bigcheese.jsalon.ee.ejb.PostServiceEJBLocal;
import ru.bigcheese.jsalon.ee.ejb.ServiceEJBLocal;
import ru.bigcheese.jsalon.ee.ejb.result.ActionResult;
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

/**
 * Created by BigCheese on 18.04.16.
 */
@WebServlet(name = "PostServiceServlet", urlPatterns = {"/admin/postservice"})
public class PostServiceServlet extends HttpServlet {

    @EJB
    private PostServiceEJBLocal postServiceEJB;
    @EJB
    private PostEJBLocal postEJB;
    @EJB
    private ServiceEJBLocal serviceEJB;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FindResult<PostServiceBind> result = postServiceEJB.getAllPostServiceBinds();
        FindResult<Post> posts = postEJB.getAllPosts();
        FindResult<Service> services = serviceEJB.getAllServices();
        List<String> errors = new ArrayList<>();
        errors.addAll(EJBUtils.getMessages(ActionResult.FATAL_ERROR, result));
        errors.addAll(EJBUtils.getMessages(ActionResult.FATAL_ERROR, posts));
        errors.addAll(EJBUtils.getMessages(ActionResult.FATAL_ERROR, services));
        request.setAttribute("errMessages", errors);
        request.getSession().setAttribute("postserviceList", result.getResult());
        request.getSession().setAttribute("postsList", posts.getResult());
        request.getSession().setAttribute("servicesList", services.getResult());
        request.getRequestDispatcher("/WEB-INF/jsp/admin/postservice.jsp").forward(request, response);
    }
}
