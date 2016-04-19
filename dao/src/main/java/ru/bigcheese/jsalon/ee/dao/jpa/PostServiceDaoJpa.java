package ru.bigcheese.jsalon.ee.dao.jpa;

import ru.bigcheese.jsalon.core.model.PostServiceBind;
import ru.bigcheese.jsalon.ee.dao.PostServiceDao;
import ru.bigcheese.jsalon.ee.dao.entity.EntityMapper;
import ru.bigcheese.jsalon.ee.dao.entity.PostEntity;
import ru.bigcheese.jsalon.ee.dao.entity.PostServiceEntity;
import ru.bigcheese.jsalon.ee.dao.entity.ServiceEntity;
import ru.bigcheese.jsalon.ee.dao.qualifier.JPA;

/**
 * Created by BigCheese on 18.02.16.
 */
@JPA
public class PostServiceDaoJpa extends AbstractBaseDaoJpa<PostServiceBind, Long, PostServiceEntity>
        implements PostServiceDao {

    @Override
    PostServiceBind toModel(PostServiceEntity entity) {
        return EntityMapper.toPostServiceBind(entity);
    }

    @Override
    PostServiceEntity toEntity(PostServiceBind model) {
        if (model == null) return null;
        PostServiceEntity entity = new PostServiceEntity();
        PostEntity post = getEntityManager().find(PostEntity.class, model.getPostId());
        ServiceEntity service = getEntityManager().find(ServiceEntity.class, model.getServiceId());
        entity.setId(model.getId());
        entity.setPost(post);
        entity.setService(service);
        return entity;
    }
}
