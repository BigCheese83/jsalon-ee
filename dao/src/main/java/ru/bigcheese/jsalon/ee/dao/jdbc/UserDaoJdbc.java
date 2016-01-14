package ru.bigcheese.jsalon.ee.dao.jdbc;

import ru.bigcheese.jsalon.core.Constants;
import ru.bigcheese.jsalon.core.model.User;
import ru.bigcheese.jsalon.ee.dao.QueryCriteria;
import ru.bigcheese.jsalon.ee.dao.UserDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static ru.bigcheese.jsalon.ee.dao.jdbc.ModelMapper.USER_MAPPER;

/**
 * Created by BigCheese on 09.11.15.
 */
public class UserDaoJdbc extends AbstractBaseDaoJdbc<User, Long>
        implements UserDao {

    /* SQL Queries */
    private static final String GENERATE_ID =    "SELECT nextval('security.users_id_seq')";
    private static final String INSERT_SQL =
            "INSERT INTO security.users (id, username, firstname, lastname, middlename, password) " +
            "VALUES (?, ?, ?, ?, ?, md5(?))";
    private static final String INSERT_SQL_2 =
            "INSERT INTO security.user_groups (user_id, group_id) VALUES (?, ?)";
    private static final String UPDATE_SQL =
            "UPDATE security.users SET username = ?, firstname = ?, lastname = ?, middlename = ? WHERE id = ?";
    private static final String UPDATE_SQL_2 =
            "UPDATE security.user_groups SET group_id = ? WHERE user_id = ?";
    private static final String DELETE_SQL =    "DELETE FROM security.users WHERE id = ?";
    private static final String DELETE_SQL_2 =  "DELETE FROM security.user_groups WHERE user_id = ?";
    private static final String FIND_BY_LOGIN = "SELECT * FROM security.v_user_role WHERE username = ?";
    private static final String FIND_BY_ID =    "SELECT * FROM security.v_user_role WHERE id = ?";
    private static final String FIND_ALL =      "SELECT * FROM security.v_user_role";
    private static final String COUNT_ALL =     "SELECT count(*) FROM security.v_user_role";
    private static final String FIND_ROLES =    "SELECT name FROM security.groups";
    private static final String FIND_ROLE_ID_BY_NAME = "SELECT id FROM security.groups WHERE name = ?";

    @Override
    public void persist(User model) {
        if (model == null) return;
        Long id = generateID(GENERATE_ID);
        Long roleId = getRoleID(model.getRole());
        executeUpdateSQL(INSERT_SQL, new Object[]{
                getParam(id, Long.class),
                getParam(model.getLogin(), String.class),
                getParam(model.getFirstName(), String.class),
                getParam(model.getLastName(), String.class),
                getParam(model.getMiddleName(), String.class),
                Constants.USER_DEFAULT_PASSWORD });
        executeUpdateSQL(INSERT_SQL_2, new Object[]{
                getParam(id, Long.class),
                getParam(roleId, Long.class) });
        model.setId(id);
    }

    @Override
    public void update(User model) {
        if (model == null) return;
        Long roleId = getRoleID(model.getRole());
        executeUpdateSQL(UPDATE_SQL, new Object[]{
                getParam(model.getLogin(), String.class),
                getParam(model.getFirstName(), String.class),
                getParam(model.getLastName(), String.class),
                getParam(model.getMiddleName(), String.class),
                getParam(model.getId(), Long.class) });
        executeUpdateSQL(UPDATE_SQL_2, new Object[]{
                getParam(roleId, Long.class),
                getParam(model.getId(), Long.class) });
    }

    @Override
    public void delete(User model) {
        if (model == null) return;
        executeUpdateSQL(DELETE_SQL_2, new Object[]{getParam(model.getId(), Long.class)});
        executeUpdateSQL(DELETE_SQL, new Object[]{getParam(model.getId(), Long.class)});
    }

    @Override
    public User findById(Long id) {
        if (id == null) return null;
        List<User> find = executeQuerySQL(FIND_BY_ID, USER_MAPPER, new Object[]{id});
        return !find.isEmpty() ? find.get(0) : null;
    }

    @Override
    public List<User> findAll() {
        return executeQuerySQL(FIND_ALL, USER_MAPPER, null);
    }

    @Override
    public Long countAll() {
        return executeQuerySQL(COUNT_ALL, Long.class, null);
    }

    @Override
    public User getUserByLogin(String login) {
        List<User> find = executeQuerySQL(FIND_BY_LOGIN, USER_MAPPER, new Object[]
                            { getParam(login, String.class) });
        return !find.isEmpty() ? find.get(0) : null;
    }

    @Override
    public List<User> findLimitUsersByCriteria(int count, QueryCriteria criteria) {
        String criteriaPart = criteria != null ? criteria.toString() : "";
        return executeLimitQuerySQL(FIND_ALL + criteriaPart, count, USER_MAPPER, null);
    }

    @Override
    public List<User> findUsersByCriteria(QueryCriteria criteria) {
        String criteriaPart = criteria != null ? criteria.toString() : "";
        return executeQuerySQL(FIND_ALL + criteriaPart, USER_MAPPER, null);
    }

    @Override
    public List<String> getAllRoles() {
        return executeQuerySQL(FIND_ROLES, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet rs) throws SQLException {
                return rs.getString(1);
            }
        }, null);
    }

    @Override
    public Long getRoleID(String name) {
        return executeQuerySQL(FIND_ROLE_ID_BY_NAME, Long.class, new Object[]{name});
    }
}
