package ru.bigcheese.jsalon.ee.dao.jpa;

import ru.bigcheese.jsalon.core.model.Service;
import ru.bigcheese.jsalon.core.util.DBUtils;
import ru.bigcheese.jsalon.ee.dao.QueryCriteriaFactory;
import ru.bigcheese.jsalon.ee.dao.QueryCriteriaType;
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
    public List<String> filterServicesByName(String name) {
        String param = DBUtils.likeSanitize(name.toLowerCase()) + "%";
        return executeNamedQuery(FILTER_BY_NAME, String.class, param);
    }

    @Override
    public List<String> filterServicesByNameAndMaster(String name, String... fio) {
        String sql = "SELECT s.name FROM services s " +
                "JOIN posts_services ps ON s.id = ps.service_id " +
                "JOIN posts p ON p.id = ps.post_id " +
                "JOIN masters m ON p.id = m.id_post ";
        String criteriaPart = QueryCriteriaFactory.buildSQL(QueryCriteriaType.SERVICE_NAME_MASTER, name, fio);
        return (List<String>) getEntityManager().createNativeQuery(sql + criteriaPart).getResultList();
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
