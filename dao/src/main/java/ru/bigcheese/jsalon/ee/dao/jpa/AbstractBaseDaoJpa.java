package ru.bigcheese.jsalon.ee.dao.jpa;

import ru.bigcheese.jsalon.core.model.BaseModel;
import ru.bigcheese.jsalon.ee.dao.BaseDao;
import ru.bigcheese.jsalon.ee.dao.entity.BaseEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by BigCheese on 10.03.15.
 */
public abstract class AbstractBaseDaoJpa<T extends BaseModel, K extends Serializable, E extends BaseEntity>
        implements BaseDao<T , K> {

    private Class<E> entityClass;

    @PersistenceContext
    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void persist(T model) {
        E entity = createEntity(model);
        if (entity != null) {
            model.setId(entity.getId());
        }
    }

    @Override
    public void update(T model) {
        if (model == null) return;
        entityManager.merge(toEntity(model));
    }

    @Override
    public void delete(T model) {
        if (model == null) return;
        entityManager.remove(entityManager.merge(toEntity(model)));
    }

    @Override
    public T findById(K id) {
        if (id == null) return null;
        return toModel(entityManager.find(getEntityClass(), id));
    }

    @Override
    public List<T> findAll() {
        return toModelList(
                entityManager.createQuery("SELECT e FROM " + getEntityClass().getName() + " e", getEntityClass())
                        .getResultList());
    }

    @Override
    public Long countAll() {
        return entityManager.createQuery("SELECT COUNT(e) FROM " + getEntityClass().getName() + " e", Long.class)
                        .getResultList().get(0);
    }

    abstract T toModel(E entity);
    abstract E toEntity(T model);

    Class<E> getEntityClass() {
        if (entityClass == null) {
            entityClass = (Class<E>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[2];
        }
        return entityClass;
    }

    List<T> toModelList(List<E> list) {
        if (list == null) return null;
        List<T> result = new ArrayList<>(list.size());
        for (E entity : list) {
            result.add(toModel(entity));
        }
        return result;
    }

    E createEntity(T model) {
        if (model == null) return null;
        E entity = toEntity(model);
        entityManager.persist(entity);
        entityManager.flush();
        return entity;
    }

    List<T> executeNamedQuery(String jpql, Object... params) {
        TypedQuery<E> query = entityManager.createNamedQuery(jpql, getEntityClass());
        int pos = 1;
        for (Object param : params) {
            query.setParameter(pos++, param);
        }
        return toModelList(query.getResultList());
    }
}
