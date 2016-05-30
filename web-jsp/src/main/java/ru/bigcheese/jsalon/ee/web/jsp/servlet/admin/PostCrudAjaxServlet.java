package ru.bigcheese.jsalon.ee.web.jsp.servlet.admin;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.bigcheese.jsalon.core.exception.ValidationException;
import ru.bigcheese.jsalon.core.model.Post;
import ru.bigcheese.jsalon.core.util.NumberUtils;
import ru.bigcheese.jsalon.ee.ejb.PostFacade;
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
    private static final Logger LOG = LoggerFactory.getLogger(PostCrudAjaxServlet.class);

    @EJB
    private PostFacade postFacade;

    @Override
    protected String getJsonResponse(HttpServletRequest request) {
        String json;
        try {
            String radioId = request.getParameter("radioID");
            CrudEntityResult<Post> result;
            if ("newRadio".equals(radioId)) {
                Post post = parseRequest(request);
                post.validate();
                result = postFacade.createPost(post);
            } else if ("editRadio".equals(radioId)) {
                Post post = parseRequest(request);
                post.validate();
                result = postFacade.updatePost(post);
            } else if ("delRadio".equals(radioId)) {
                result = postFacade.deletePost(NumberUtils.toLong(request.getParameter("id")));
            } else {
                throw new Exception("Unknown operation");
            }
            json = JsonUtils.getGson().toJson(result);
        } catch (ValidationException e) {
            LOG.error("Validation failed. {}.", e.getMessage());
            json = JsonUtils.getJsonValidateErrors(e);
        } catch (Throwable e) {
            LOG.error("Error.", e);
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
