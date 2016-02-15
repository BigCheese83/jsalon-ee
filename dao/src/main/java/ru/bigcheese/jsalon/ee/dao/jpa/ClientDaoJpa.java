package ru.bigcheese.jsalon.ee.dao.jpa;

import ru.bigcheese.jsalon.core.model.Client;
import ru.bigcheese.jsalon.core.model.Passport;
import ru.bigcheese.jsalon.ee.dao.ClientDao;
import ru.bigcheese.jsalon.ee.dao.QueryCriteria;
import ru.bigcheese.jsalon.ee.dao.entity.ClientEntity;
import ru.bigcheese.jsalon.ee.dao.entity.EntityMapper;
import ru.bigcheese.jsalon.ee.dao.qualifier.JPA;

import java.util.List;

import static ru.bigcheese.jsalon.core.model.BindModel.CLIENT;

/**
 * Created by BigCheese on 11.02.16.
 */
@JPA
public class ClientDaoJpa extends AbstractBaseDaoJpa<Client, Long, ClientEntity>
        implements ClientDao {

    @Override
    public void persist(Client model) {
        ClientEntity entity = createEntity(model);
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
                !executeNamedQuery(ClientEntity.EXISTS_BY_PASSPORT, Long.class,
                        passport.getSeries(), passport.getNumber(), CLIENT.name())
                        .isEmpty();
    }

    @Override
    public List<Client> findClientsByCriteria(QueryCriteria criteria) {
        String sql = "SELECT * FROM clients";
        if (criteria != null) {
            sql = sql + criteria.buildSQL();
        }
        return executeNativeQuery(sql);
    }

    @Override
    public List<Client> findLimitClientsByCriteria(int count, QueryCriteria criteria) {
        return findClientsByCriteria(criteria);
    }

    @Override
    Client toModel(ClientEntity entity) {
        return EntityMapper.toClientModel(entity);
    }

    @Override
    ClientEntity toEntity(Client model) {
        return EntityMapper.toClientEntity(model);
    }
}
