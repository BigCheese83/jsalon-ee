package ru.bigcheese.jsalon.ee.dao;

import ru.bigcheese.jsalon.core.model.Client;
import ru.bigcheese.jsalon.core.model.to.ModelTO;
import ru.bigcheese.jsalon.core.model.Passport;

import java.util.List;

/**
 * Created by BigCheese on 11.02.16.
 */
public interface ClientDao extends BaseDao<Client, Long> {
    boolean existsByPassport(Passport passport);
    boolean existsByPhone(String phone);
    List<Client> findClientsByCriteria(QueryCriteria criteria);
    List<Client> findLimitClientsByCriteria(int count, QueryCriteria criteria);
    List<ModelTO> filterClientsByNames(String... names);
}
