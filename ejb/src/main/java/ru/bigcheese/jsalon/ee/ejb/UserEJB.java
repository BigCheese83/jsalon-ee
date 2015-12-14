package ru.bigcheese.jsalon.ee.ejb;

import org.apache.commons.lang3.StringUtils;
import ru.bigcheese.jsalon.core.model.User;
import ru.bigcheese.jsalon.core.util.ExceptionUtils;
import ru.bigcheese.jsalon.ee.dao.QueryCriteria;
import ru.bigcheese.jsalon.ee.dao.UserDao;
import ru.bigcheese.jsalon.ee.ejb.result.CrudEntityResult;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

import static ru.bigcheese.jsalon.ee.ejb.result.ActionResult.*;

/**
 * Created by BigCheese on 06.11.15.
 */
@Stateless
@RolesAllowed({"admin", "user"})
public class UserEJB implements UserEJBLocal {

    @Resource
    private SessionContext context;

    @Inject
    private UserDao userDao;

    @EJB
    private SecurityEJB securityEJB;

    public CrudEntityResult createUser(User user, String password) {
        try {
            User find = userDao.getUserByLogin(user.getLogin());
            if (find == null) {
                userDao.persist(user);
                securityEJB.setUserPassword(user.getId(), password);
                return new CrudEntityResult(NORMAL, user.toString() + " успешно создан.", user.getId());
            } else {
                return new CrudEntityResult(WARNING, "Пользователь " + user.getLogin() + " уже содержится в БД.", user.getId());
            }
        } catch (Throwable e) {
            context.setRollbackOnly();
            return new CrudEntityResult(FATAL_ERROR, "Ошибка создания пользователя. " + ExceptionUtils.parse(e));
        }
    }

    public CrudEntityResult updateUser(User user, String password, String newPassword) {
        try {
            User find = userDao.findById(user.getId());
            if (find != null) {
                userDao.update(user);
                securityEJB.setUserPassword(user.getId(), newPassword, password);
                return new CrudEntityResult(NORMAL, user.toString() + " успешно обновлен.", user.getId());
            } else {
                return new CrudEntityResult(WARNING, "Пользователь " + user.getLogin() + " не найден в БД.", user.getId());
            }
        } catch (Throwable e) {
            context.setRollbackOnly();
            return new CrudEntityResult(FATAL_ERROR, "Ошибка обновления пользователя. " + ExceptionUtils.parse(e));
        }
    }

    public CrudEntityResult deleteUser(Long id) {
        try {
            if (id == null) throw new IllegalArgumentException("ID=NULL");
            User user = userDao.findById(id);
            if (user != null) {
                userDao.delete(user);
                return new CrudEntityResult(NORMAL, user.toString() + " успешно удален.", user.getId());
            } else {
                return new CrudEntityResult(WARNING,  "Пользователь с ID=" + id + " не найден в БД.", id);
            }
        } catch (Throwable e) {
            context.setRollbackOnly();
            return new CrudEntityResult(FATAL_ERROR, "Ошибка удаления пользователя. " + ExceptionUtils.parse(e));
        }
    }

    @Override
    public User getUserByLogin(String login) {
        if (StringUtils.isBlank(login)) {
            throw new IllegalArgumentException("Empty login!");
        }
        return userDao.getUserByLogin(login);
    }

    @Override
    public List<User> findLimitUsersByCriteria(int count, QueryCriteria criteria) {
        return userDao.findLimitUsersByCriteria(count, criteria);
    }

    @Override
    public List<User> findUsersByCriteria(QueryCriteria criteria) {
        return userDao.findUsersByCriteria(criteria);
    }

    @Override
    public List<String> getAllRoles() {
        return userDao.getAllRoles();
    }
}