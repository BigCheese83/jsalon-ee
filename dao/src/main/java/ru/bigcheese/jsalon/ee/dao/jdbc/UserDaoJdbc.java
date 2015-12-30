package ru.bigcheese.jsalon.ee.dao.jdbc;

import ru.bigcheese.jsalon.core.Constants;
import ru.bigcheese.jsalon.core.exception.DatabaseRuntimeException;
import ru.bigcheese.jsalon.core.model.User;
import ru.bigcheese.jsalon.core.util.DBUtils;
import ru.bigcheese.jsalon.ee.dao.QueryCriteria;
import ru.bigcheese.jsalon.ee.dao.UserDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static ru.bigcheese.jsalon.ee.dao.jdbc.ModelMapper.*;

/**
 * Created by BigCheese on 09.11.15.
 */
public class UserDaoJdbc extends AbstractBaseDaoJdbc<User, Long>
        implements UserDao {

    private static final String SEQ_NAME = "security.users_id_seq";
    /* SQL Queries */
    private static final String INSERT_SQL =
            "INSERT INTO security.users (id, username, firstname, lastname, middlename, password) " +
            "VALUES (?, ?, ?, ?, ?, md5(?))";
    private static final String INSERT_SQL_2 =
            "INSERT INTO security.user_groups (user_id, group_id) VALUES (?, ?)";
    private static final String UPDATE_SQL =
            "UPDATE security.users SET username = ?, firstname = ?, lastname = ?, middlename = ? WHERE id = ?";
    private static final String UPDATE_SQL_2 =
            "UPDATE security.user_groups SET group_id = ? WHERE user_id = ?";
    private static final String DELETE_SQL = "DELETE FROM security.users WHERE id = ?";
    private static final String DELETE_SQL_2 = "DELETE FROM security.user_groups WHERE user_id = ?";
    private static final String FIND_BY_LOGIN = "SELECT * FROM security.v_user_role WHERE username = ?";
    private static final String FIND_BY_ID = "SELECT * FROM security.v_user_role WHERE id = ?";
    private static final String FIND_ALL = "SELECT * FROM security.v_user_role";
    private static final String FIND_ROLES = "SELECT name FROM security.groups";
    private static final String FIND_ROLE_ID_BY_NAME = "SELECT id FROM security.groups WHERE name = ?";

    @Override
    public void persist(User model) {
        if (model == null) return;
        Long id = generateSeqID(SEQ_NAME);
        super.executeUpdateSQL(INSERT_SQL, new Object[]
                { getParam(id, Long.class),
                  getParam(model.getLogin(), String.class),
                  getParam(model.getFirstName(), String.class),
                  getParam(model.getLastName(), String.class),
                  getParam(model.getMiddleName(), String.class),
                  Constants.USER_DEFAULT_PASSWORD });
        Long roleId = getRoleID(model.getRole());
        super.executeUpdateSQL(INSERT_SQL_2, new Object[]
                { getParam(id, Long.class),
                  getParam(roleId, Long.class)});
        model.setId(id);
    }

    @Override
    public void update(User model) {
        if (model == null) return;
        super.executeUpdateSQL(UPDATE_SQL, new Object[]
                { getParam(model.getLogin(), String.class),
                  getParam(model.getFirstName(), String.class),
                  getParam(model.getLastName(), String.class),
                  getParam(model.getMiddleName(), String.class),
                  getParam(model.getId(), Long.class)});
        Long roleId = getRoleID(model.getRole());
        super.executeUpdateSQL(UPDATE_SQL_2, new Object[]
                { getParam(roleId, Long.class),
                  getParam(model.getId(), Long.class)});
    }

    @Override
    public void delete(User model) {
        if (model == null) return;
        super.executeUpdateSQL(DELETE_SQL_2, new Object[]
                { getParam(model.getId(), Long.class) } );
        super.executeUpdateSQL(DELETE_SQL, new Object[]
                { getParam(model.getId(), Long.class) } );
    }

    @Override
    public User findById(Long id) {
        if (id == null) return null;
        List<User> find = super.executeQuerySQL( FIND_BY_ID, USER_MAPPER, new Object[]{id});
        return !find.isEmpty() ? find.get(0) : null;
    }

    @Override
    public List<User> findAll() {
        return super.executeQuerySQL(FIND_ALL, USER_MAPPER, null);
    }

    @Override
    public User getUserByLogin(String login) {
        List<User> find = super.executeQuerySQL(FIND_BY_LOGIN, USER_MAPPER, new Object[]
                            { getParam(login, String.class) });
        return !find.isEmpty() ? find.get(0) : null;
    }

    @Override
    public List<User> findLimitUsersByCriteria(int count, QueryCriteria criteria) {
        String criteriaPart = criteria != null ? criteria.toString() : "";
        return super.executeLimitQuerySQL(FIND_ALL + criteriaPart, count, USER_MAPPER, null);
    }

    @Override
    public List<User> findUsersByCriteria(QueryCriteria criteria) {
        String criteriaPart = criteria != null ? criteria.toString() : "";
        return super.executeQuerySQL(FIND_ALL + criteriaPart, USER_MAPPER, null);
    }

    @Override
    public List<String> getAllRoles() {
        List<String> roles = new ArrayList<>();
        try (Connection conn = getDataSource().getConnection()) {
            try (PreparedStatement stmt = conn.prepareStatement(FIND_ROLES)) {
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        roles.add(rs.getString(1));
                    }
                }
            }
        } catch (SQLException e) {
            throw new DatabaseRuntimeException(DBUtils.extractSQLMessages(e));
        }
        return roles;
    }

    @Override
    public Long getRoleID(String name) {
        try (Connection conn = getDataSource().getConnection()) {
            try (PreparedStatement pstm = conn.prepareStatement(FIND_ROLE_ID_BY_NAME)) {
                pstm.setString(1, name);
                try (ResultSet rs = pstm.executeQuery()) {
                    if (rs.next()) {
                        return rs.getLong(1);
                    }
                }
            }
        } catch (SQLException e) {
            throw new DatabaseRuntimeException(DBUtils.extractSQLMessages(e));
        }
        return null;
    }
}
