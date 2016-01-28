package ru.bigcheese.jsalon.ee.dao.jpa;

import ru.bigcheese.jsalon.core.model.Service;
import ru.bigcheese.jsalon.ee.dao.ServiceDao;
import ru.bigcheese.jsalon.ee.dao.entity.EntityMapper;
import ru.bigcheese.jsalon.ee.dao.entity.ServiceEntity;
import ru.bigcheese.jsalon.ee.dao.qualifier.JPA;

import java.util.List;

import static ru.bigcheese.jsalon.ee.dao.entity.ServiceEntity.*;

/**
 * Created by BigCheese on 02.06.15.
 */
@JPA
public class ServiceDaoJpa  extends AbstractBaseDaoJpa<Service, Long, ServiceEntity>
        implements ServiceDao {

    @Override
    public Service getServiceByName(String name) {
        List<Service> find = executeNamedQuery(FIND_BY_NAME, name);
        return !find.isEmpty() ? find.get(0) : null;
    }

    @Override
    public boolean existsByName(String name) {
        return !executeNamedQuery(EXISTS_BY_NAME, String.class, name).isEmpty();
    }

    @Override
    Service toModel(ServiceEntity entity) {
        return EntityMapper.toServiceModel(entity);
    }

    @Override
    ServiceEntity toEntity(Service model) {
        return EntityMapper.toServiceEntity(model);
    }
}
