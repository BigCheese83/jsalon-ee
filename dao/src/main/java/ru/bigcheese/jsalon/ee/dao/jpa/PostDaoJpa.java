package ru.bigcheese.jsalon.ee.dao.jpa;

import ru.bigcheese.jsalon.core.model.Post;
import ru.bigcheese.jsalon.ee.dao.PostDao;
import ru.bigcheese.jsalon.ee.dao.entity.EntityMapper;
import ru.bigcheese.jsalon.ee.dao.entity.PostEntity;
import ru.bigcheese.jsalon.ee.dao.qualifier.JPA;

import java.util.List;

import static ru.bigcheese.jsalon.ee.dao.entity.PostEntity.*;

/**
 * Created by BigCheese on 10.03.15.
 */
@JPA
public class PostDaoJpa extends AbstractBaseDaoJpa<Post, Long, PostEntity>
        implements PostDao {

    @Override
    public Post getPostByName(String name) {
        List<Post> find = executeNamedQuery(FIND_BY_NAME, name);
        return !find.isEmpty() ? find.get(0) : null;
    }

    @Override
    public boolean existsByName(String name) {
        return !executeNamedQuery(EXISTS_BY_NAME, String.class, name).isEmpty();
    }

    @Override
    Post toModel(PostEntity entity) {
        return EntityMapper.toPostModel(entity);
    }

    @Override
    PostEntity toEntity(Post model) {
        return EntityMapper.toPostEntity(model);
    }
}
