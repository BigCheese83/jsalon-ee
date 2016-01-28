package ru.bigcheese.jsalon.ee.dao;

import ru.bigcheese.jsalon.core.model.User;

import java.util.List;

/**
 * Created by BigCheese on 09.11.15.
 */
public interface UserDao extends BaseDao<User, Long> {
    User getUserByLogin(String login);
    boolean existsByLogin(String login);
    List<User> findLimitUsersByCriteria(int count, QueryCriteria criteria);
    List<User> findUsersByCriteria(QueryCriteria criteria);
    List<String> getAllRoles();
    Long getRoleID(String name);
}
