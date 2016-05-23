package ru.bigcheese.jsalon.ee.ejb;

import ru.bigcheese.jsalon.core.model.Client;
import ru.bigcheese.jsalon.core.model.ModelTO;
import ru.bigcheese.jsalon.ee.dao.QueryCriteria;
import ru.bigcheese.jsalon.ee.ejb.result.CrudEntityResult;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by BigCheese on 11.02.16.
 */
@Local
public interface ClientEJBLocal {
    CrudEntityResult<Client> createClient(Client client);
    CrudEntityResult<Client> updateClient(Client client);
    CrudEntityResult<Client> deleteClient(Long id);
    List<Client> findLimitClientsByCriteria(int count, QueryCriteria criteria);
    List<Client> findClientsByCriteria(QueryCriteria criteria);
    List<ModelTO> filterClientsByNames(String fio);
}
