package ru.bigcheese.jsalon.ee.dao.jdbc;

import ru.bigcheese.jsalon.core.model.PostServiceBind;
import ru.bigcheese.jsalon.ee.dao.PostServiceDao;
import ru.bigcheese.jsalon.ee.dao.qualifier.JDBC;

import java.util.List;

import static ru.bigcheese.jsalon.ee.dao.jdbc.ModelMapper.*;

/**
 * Created by BigCheese on 18.02.16.
 */
@JDBC
public class PostServiceDaoJdbc extends AbstractBaseDaoJdbc<PostServiceBind, Long>
        implements PostServiceDao {

    /* SQL Queries */
    private static final String GENERATE_ID =    "SELECT nextval('posts_services_id_seq')";
    private static final String INSERT_SQL =     "INSERT INTO posts_services (id, post_id, service_id) VALUES (?, ?, ?)";
    private static final String UPDATE_SQL =     "UPDATE posts_services SET post_id = ?, service_id = ? WHERE id = ?";
    private static final String DELETE_SQL =     "DELETE FROM posts_services WHERE id = ?";
    private static final String SELECT_ALL =
            "SELECT ps.id, post_id, service_id, p.name as post_name, s.name as service_name " +
            "FROM posts_services ps " +
                "JOIN posts p ON ps.post_id = p.id " +
                "JOIN services s ON ps.service_id = s.id";
    private static final String SELECT_BY_ID =
            "SELECT ps.id, post_id, service_id, p.name as post_name, s.name as service_name " +
            "FROM posts_services ps " +
                "JOIN posts p ON ps.post_id = p.id " +
                "JOIN services s ON ps.service_id = s.id " +
             "WHERE ps.id = ?";
    private static final String COUNT_ALL =      "SELECT count(*) FROM posts_services";
    private static final String EXISTS_BY_ID =   "SELECT id FROM posts_services WHERE id = ?";

    @Override
    public void persist(PostServiceBind model) {
        if (model == null) return;
        Long id = generateID(GENERATE_ID);
        executeUpdateSQL(INSERT_SQL, new Object[]
                { getParam(id, Long.class),
                  getParam(model.getPostId(), Long.class),
                  getParam(model.getServiceId(), Long.class) });
        model.setId(id);
    }

    @Override
    public void update(PostServiceBind model) {
        if (model == null) return;
        executeUpdateSQL(UPDATE_SQL, new Object[]
                { getParam(model.getPostId(), Long.class),
                  getParam(model.getServiceId(), Long.class),
                  getParam(model.getId(), Long.class) });
    }

    @Override
    public void delete(Long id) {
        if (id == null) return;
        executeUpdateSQL(DELETE_SQL, new Object[]{id});
    }

    @Override
    public PostServiceBind findById(Long id) {
        if (id == null) return null;
        List<PostServiceBind> find = executeQuerySQL(SELECT_BY_ID, POST_SERVICE_MAPPER, new Object[]{id});
        return !find.isEmpty() ? find.get(0) : null;
    }

    @Override
    public List<PostServiceBind> findAll() {
        return executeQuerySQL(SELECT_ALL, POST_SERVICE_MAPPER, null);
    }

    @Override
    public Long countAll() {
        return executeQuerySQL(COUNT_ALL, Long.class, null);
    }

    @Override
    public boolean existsById(Long id) {
        return null != executeQuerySQL(EXISTS_BY_ID, Long.class, new Object[]{
                getParam(id, Long.class)});
    }
}
