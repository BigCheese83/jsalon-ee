package ru.bigcheese.jsalon.ee.web.jsp.servlet.admin;

import org.apache.commons.lang3.StringUtils;
import ru.bigcheese.jsalon.core.exception.ValidationException;
import ru.bigcheese.jsalon.core.model.PostServiceBind;
import ru.bigcheese.jsalon.core.util.NumberUtils;
import ru.bigcheese.jsalon.ee.ejb.PostServiceEJB;
import ru.bigcheese.jsalon.ee.ejb.PostServiceEJBLocal;
import ru.bigcheese.jsalon.ee.ejb.result.CrudEntityResult;
import ru.bigcheese.jsalon.ee.web.jsp.servlet.AbstractAjaxServlet;
import ru.bigcheese.jsalon.ee.web.jsp.util.JsonUtils;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by BigCheese on 19.04.16.
 */
@WebServlet(name = "PostServiceAjaxServlet", urlPatterns = {"/admin/postservice/ajax"})
public class PostServiceAjaxServlet extends AbstractAjaxServlet {

    @EJB
    private PostServiceEJBLocal postServiceEJB;

    @Override
    protected String getJsonResponse(HttpServletRequest request) {
        String json;
        try {
            String radioId = request.getParameter("radioID");
            CrudEntityResult<PostServiceBind> result;
            if ("newRadio".equals(radioId)) {
                PostServiceBind bind = parseRequest(request);
                bind.validate();
                result = postServiceEJB.createPostServiceBind(bind);
            } else if ("editRadio".equals(radioId)) {
                PostServiceBind bind = parseRequest(request);
                bind.validate();
                result = postServiceEJB.updatePostServiceBind(bind);
            } else if ("delRadio".equals(radioId)) {
                result = postServiceEJB.deletePostServiceBind(NumberUtils.toLong(request.getParameter("id")));
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

    private PostServiceBind parseRequest(HttpServletRequest request) {
        PostServiceBind bind = new PostServiceBind();
        bind.setId(NumberUtils.toLong(request.getParameter("id")));
        bind.setPostId(NumberUtils.toLong(request.getParameter("postId")));
        bind.setPostName(StringUtils.stripToNull(request.getParameter("postName")));
        bind.setServiceId(NumberUtils.toLong(request.getParameter("serviceId")));
        bind.setServiceName(StringUtils.stripToNull(request.getParameter("serviceName")));
        return bind;
    }
}
