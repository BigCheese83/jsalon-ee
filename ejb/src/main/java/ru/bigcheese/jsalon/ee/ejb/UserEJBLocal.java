package ru.bigcheese.jsalon.ee.ejb;

import ru.bigcheese.jsalon.core.model.User;
import ru.bigcheese.jsalon.ee.dao.QueryCriteria;
import ru.bigcheese.jsalon.ee.ejb.result.CrudEntityResult;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by BigCheese on 09.11.15.
 */
@Local
public interface UserEJBLocal {
    CrudEntityResult createUser(User user, String password);
    CrudEntityResult updateUser(User user, String password, String newPassword);
    CrudEntityResult deleteUser(Long id);
    User getUserByLogin(String login);
    List<User> findLimitUsersByCriteria(int count, QueryCriteria criteria);
    List<User> findUsersByCriteria(QueryCriteria criteria);
    List<String> getAllRoles();
}
