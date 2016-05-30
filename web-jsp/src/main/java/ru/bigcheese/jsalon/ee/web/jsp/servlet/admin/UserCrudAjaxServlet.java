package ru.bigcheese.jsalon.ee.web.jsp.servlet.admin;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.bigcheese.jsalon.core.exception.ValidationException;
import ru.bigcheese.jsalon.core.model.User;
import ru.bigcheese.jsalon.core.util.NumberUtils;
import ru.bigcheese.jsalon.ee.dao.QueryCriteria;
import ru.bigcheese.jsalon.ee.dao.QueryCriteriaFactory;
import ru.bigcheese.jsalon.ee.ejb.UserFacade;
import ru.bigcheese.jsalon.ee.ejb.result.CrudEntityResult;
import ru.bigcheese.jsalon.ee.web.jsp.servlet.AbstractAjaxServlet;
import ru.bigcheese.jsalon.ee.web.jsp.util.JsonUtils;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by BigCheese on 19.08.15.
 */
@WebServlet(name = "UserCrudAjaxServlet", urlPatterns = {"/admin/user/ajax"})
public class UserCrudAjaxServlet extends AbstractAjaxServlet {
    private static final Logger LOG = LoggerFactory.getLogger(UserCrudAjaxServlet.class);

    @EJB
    private UserFacade userFacade;

    @Override
    protected String getJsonResponse(HttpServletRequest request) {
        String json;
        try {
            if ("true".equals(request.getParameter("searchRequest"))) {
                List<User> users = userFacade.findUsersByCriteria(buildCriteria(request));
                json = JsonUtils.getGson().toJson(users);
            } else {
                String radioId = request.getParameter("radioID");
                CrudEntityResult<User> result;
                if ("newRadio".equals(radioId)) {
                    User user = parseRequest(request);
                    user.validate();
                    String newPassword = request.getParameter("newPassword");
                    String newPassword2 = request.getParameter("newPassword2");
                    validatePassword(newPassword, newPassword2);
                    result = userFacade.createUser(user, newPassword);
                } else if ("editRadio".equals(radioId)) {
                    User user = parseRequest(request);
                    user.validate();
                    String oldPassword = request.getParameter("oldPassword");
                    String newPassword = request.getParameter("newPassword");
                    String newPassword2 = request.getParameter("newPassword2");
                    validatePassword(newPassword, newPassword2);
                    result = userFacade.updateUser(user, oldPassword, newPassword);
                } else if ("delRadio".equals(radioId)) {
                    result = userFacade.deleteUser(NumberUtils.toLong(request.getParameter("id")));
                } else {
                    throw new Exception("Unknown operation");
                }
                json = JsonUtils.getGson().toJson(result);
            }
        } catch (ValidationException e) {
            LOG.error("Validation failed. {}.", e.getMessage());
            json = JsonUtils.getJsonValidateErrors(e);
        } catch (Throwable e) {
            LOG.error("Error.", e);
            json = JsonUtils.getJsonException(e);
        }
        return json;
    }

    private QueryCriteria buildCriteria(HttpServletRequest request) throws ValidationException {
        Map<String, String> params = new HashMap<>();

        String login = request.getParameter("sLogin");
        String lastname = request.getParameter("sLastName");
        String firstname = request.getParameter("sFirstName");
        String middlename = request.getParameter("sMiddleName");
        String role = request.getParameter("sRole");

        if (StringUtils.isNotBlank(login)) {
            params.put("username", login);
        }
        if (StringUtils.isNotBlank(lastname)) {
            params.put("lastname", lastname);
        }
        if (StringUtils.isNotBlank(firstname)) {
            params.put("firstname", firstname);
        }
        if (StringUtils.isNotBlank(middlename)) {
            params.put("middlename", middlename);
        }
        if (StringUtils.isNotBlank(role)) {
            params.put("role", role);
        }

        if (params.isEmpty()) {
            throw new ValidationException("All search params is empty. Enter at least one parameter!");
        }

        QueryCriteria criteria = QueryCriteriaFactory.getInstance();
        criteria.where();
        Iterator<Map.Entry<String, String>> iter = params.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<String, String> entry = iter.next();
            criteria.equal(entry.getKey(), entry.getValue());
            if (iter.hasNext()) {
                criteria.and();
            }
        }
        return criteria;
    }

    private User parseRequest(HttpServletRequest request) {
        User user = new User();
        user.setId(NumberUtils.toLong(request.getParameter("id")));
        user.setLogin(StringUtils.stripToNull(request.getParameter("login")));
        user.setFirstName(StringUtils.stripToNull(request.getParameter("firstName")));
        user.setLastName(StringUtils.stripToNull(request.getParameter("lastName")));
        user.setMiddleName(StringUtils.stripToNull(request.getParameter("middleName")));
        user.setRole(StringUtils.stripToNull(request.getParameter("role")));
        return user;
    }

    private void validatePassword(String newPassword, String newPassword2) throws ValidationException {
        if (StringUtils.isBlank(newPassword) && StringUtils.isBlank(newPassword2)) {
            return;
        }
        if (!newPassword.equals(newPassword2)) {
            throw new ValidationException("Пароли не совпадают!");
        }
    }
}
