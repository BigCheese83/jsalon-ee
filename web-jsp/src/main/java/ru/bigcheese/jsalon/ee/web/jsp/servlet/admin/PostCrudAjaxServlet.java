package ru.bigcheese.jsalon.ee.web.jsp.servlet.admin;

import org.apache.commons.lang3.StringUtils;
import ru.bigcheese.jsalon.core.exception.ValidationException;
import ru.bigcheese.jsalon.core.model.Post;
import ru.bigcheese.jsalon.core.util.NumberUtils;
import ru.bigcheese.jsalon.ee.ejb.PostEJBLocal;
import ru.bigcheese.jsalon.ee.ejb.result.CrudEntityResult;
import ru.bigcheese.jsalon.ee.web.jsp.servlet.AbstractAjaxServlet;
import ru.bigcheese.jsalon.ee.web.jsp.util.JsonUtils;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by BigCheese on 27.10.15.
 */
@WebServlet(name = "PostCrudAjaxServlet", urlPatterns = {"/admin/posts/ajax"})
public class PostCrudAjaxServlet extends AbstractAjaxServlet {

    @EJB
    private PostEJBLocal postEJB;

    @Override
    protected String getJsonResponse(HttpServletRequest request) {
        String json;
        try {
            String radioId = request.getParameter("radioID");
            CrudEntityResult<Post> result;
            if ("newRadio".equals(radioId)) {
                Post post = parseRequest(request);
                post.validate();
                result = postEJB.createPost(post);
            } else if ("editRadio".equals(radioId)) {
                Post post = parseRequest(request);
                post.validate();
                result = postEJB.updatePost(post);
            } else if ("delRadio".equals(radioId)) {
                result = postEJB.deletePost(NumberUtils.toLong(request.getParameter("id")));
            } else {
                throw new Exception("Unknown operation");
            }
            json = JsonUtils.getGson().toJson(result);
        } catch (ValidationException e) {
            json = JsonUtils.getJsonValidateErrors(e);
        } catch (Throwable e) {
            json = JsonUtils.getJsonException(e);
        }
        return json;
    }

    private Post parseRequest(HttpServletRequest request) {
        Long id = NumberUtils.toLong(request.getParameter("id"));
        String name = StringUtils.stripToNull(request.getParameter("name"));
        return new Post(id, name);
    }
}
