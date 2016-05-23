package ru.bigcheese.jsalon.ee.dao.jpa;

import ru.bigcheese.jsalon.core.model.Master;
import ru.bigcheese.jsalon.core.model.ModelTO;
import ru.bigcheese.jsalon.core.model.Passport;
import ru.bigcheese.jsalon.ee.dao.MasterDao;
import ru.bigcheese.jsalon.ee.dao.QueryCriteria;
import ru.bigcheese.jsalon.ee.dao.QueryCriteriaFactory;
import ru.bigcheese.jsalon.ee.dao.QueryCriteriaType;
import ru.bigcheese.jsalon.ee.dao.entity.EntityMapper;
import ru.bigcheese.jsalon.ee.dao.entity.MasterEntity;
import ru.bigcheese.jsalon.ee.dao.qualifier.JPA;

import java.util.ArrayList;
import java.util.List;

import static ru.bigcheese.jsalon.core.model.BindModel.MASTER;
import static ru.bigcheese.jsalon.ee.dao.entity.MasterEntity.EXISTS_BY_PASSPORT;

/**
 * Created by BigCheese on 25.12.15.
 */
@JPA
public class MasterDaoJpa extends AbstractBaseDaoJpa<Master, Long, MasterEntity>
        implements MasterDao {

    @Override
    public void persist(Master model) {
        MasterEntity entity = createEntity(model);
        if (entity != null) {
            model.setId(entity.getId());
            if (entity.getPassport() != null) {
                model.getPassport().setId(entity.getPassport().getId());
            }
            if (entity.getRegAddress() != null) {
                model.getRegAddress().setId(entity.getRegAddress().getId());
            }
            if (entity.getLiveAddress() != null) {
                model.getLiveAddress().setId(entity.getLiveAddress().getId());
            }
            if (entity.getContact() != null) {
                model.getContact().setId(entity.getContact().getId());
            }
        }
    }

    @Override
    public boolean existsByPassport(Passport passport) {
        return passport != null &&
                !executeNamedQuery(EXISTS_BY_PASSPORT, Long.class,
                        passport.getSeries(), passport.getNumber(), MASTER.name())
                        .isEmpty();
    }

    @Override
    public List<Master> findMastersByCriteria(QueryCriteria criteria) {
        String sql = "SELECT * FROM masters";
        if (criteria != null) {
            sql = sql + criteria.buildSQL();
        }
        return executeNativeQuery(sql);
    }

    @Override
    public List<Master> findLimitMastersByCriteria(int count, QueryCriteria criteria) {
        return findMastersByCriteria(criteria);
    }

    @Override
    public List<ModelTO> filterMastersByNames(String... names) {
        String sql = "SELECT id, surname, name, patronymic FROM masters";
        String criteriaPart = QueryCriteriaFactory.buildSQL(QueryCriteriaType.PERSON_NAMES, names);
        List<Object[]> list = (List<Object[]>) getEntityManager().createNativeQuery(sql + criteriaPart).getResultList();
        return joinNamesList(list);
    }

    @Override
    public List<ModelTO> filterMastersByNamesAndService(String service, String... names) {
        String sql = "SELECT m.id, m.surname, m.name, m.patronymic FROM masters m " +
                        "JOIN posts p ON p.id = m.id_post " +
                        "JOIN posts_services ps ON p.id = ps.post_id " +
                        "JOIN services s ON s.id = ps.service_id";
        String criteriaPart = QueryCriteriaFactory.buildSQL(QueryCriteriaType.MASTER_NAMES_SERVICE, service, names);
        List<Object[]> list = (List<Object[]>) getEntityManager().createNativeQuery(sql + criteriaPart).getResultList();
        return joinNamesList(list);
    }

    private List<ModelTO> joinNamesList(List<Object[]> list) {
        List<ModelTO> result = new ArrayList<>(list.size());
        for (Object[] row : list) {
            result.add(new ModelTO(row));
        }
        return result;
    }

    @Override
    Master toModel(MasterEntity entity) {
        return EntityMapper.toMasterModel(entity);
    }

    @Override
    MasterEntity toEntity(Master model) {
        return EntityMapper.toMasterEntity(model);
    }
}
