package ru.bigcheese.jsalon.ee.dao.jdbc;

import ru.bigcheese.jsalon.core.model.Post;
import ru.bigcheese.jsalon.ee.dao.PostDao;
import ru.bigcheese.jsalon.ee.dao.qualifier.JDBC;

import java.util.List;

import static ru.bigcheese.jsalon.ee.dao.jdbc.ModelMapper.POST_MAPPER;

/**
 * Created by BigCheese on 19.03.15.
 */
@JDBC
public class PostDaoJdbc extends AbstractBaseDaoJdbc<Post, Long>
        implements PostDao {

    private static final String SEQ_NAME = "posts_id_seq";
    /* SQL Queries */
    private static final String INSERT_SQL =     "INSERT INTO posts (id, name) VALUES (?, ?)";
    private static final String UPDATE_SQL =     "UPDATE posts SET name = ? WHERE id = ?";
    private static final String DELETE_SQL =     "DELETE FROM posts WHERE id = ?";
    private static final String SELECT_ALL =     "SELECT * FROM posts";
    private static final String COUNT_ALL =      "SELECT count(*) FROM posts";
    private static final String SELECT_BY_ID =   "SELECT * FROM posts WHERE id = ?";
    private static final String SELECT_BY_NAME = "SELECT * FROM posts WHERE name = ?";

    @Override
    public void persist(Post model) {
        if (model == null) return;
        Long id = generateSeqID(SEQ_NAME);
        super.executeUpdateSQL(INSERT_SQL, new Object[]
                        { getParam(id, Long.class),
                          getParam(model.getName(), String.class) });
        model.setId(id);
    }

    @Override
    public void update(Post model) {
        if (model == null) return;
        super.executeUpdateSQL(UPDATE_SQL, new Object[]
                        { getParam(model.getName(), String.class),
                          getParam(model.getId(), Long.class) });
    }

    @Override
    public void delete(Post model) {
        if (model == null) return;
        super.executeUpdateSQL(DELETE_SQL, new Object[]
                        { getParam(model.getId(), Long.class) });
    }

    @Override
    public Post findById(Long id) {
        if (id == null) return null;
        List<Post> find = super.executeQuerySQL(SELECT_BY_ID, POST_MAPPER, new Object[]{id});
        return !find.isEmpty() ? find.get(0) : null;
    }

    @Override
    public List<Post> findAll() {
        return super.executeQuerySQL(SELECT_ALL, POST_MAPPER, null);
    }

    @Override
    public Long countAll() {
        return super.executeSingleLongQuerySQL(COUNT_ALL, null);
    }

    @Override
    public List<Post> getPostsByName(String name) {
        return super.executeQuerySQL(SELECT_BY_NAME, POST_MAPPER, new Object[]
                        { getParam(name, String.class) });
    }
}
