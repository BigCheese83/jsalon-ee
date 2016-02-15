package ru.bigcheese.jsalon.ee.ejb;

import ru.bigcheese.jsalon.core.model.Client;
import ru.bigcheese.jsalon.core.util.ExceptionUtils;
import ru.bigcheese.jsalon.ee.dao.ClientDao;
import ru.bigcheese.jsalon.ee.dao.QueryCriteria;
import ru.bigcheese.jsalon.ee.ejb.result.CrudEntityResult;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

import static ru.bigcheese.jsalon.ee.ejb.result.ActionResult.*;

/**
 * Created by BigCheese on 11.02.16.
 */
@Stateless
public class ClientEJB implements ClientEJBLocal {

    @Resource
    private SessionContext context;

    @Inject
    private ClientDao clientDao;

    @Override
    public CrudEntityResult<Client> createClient(Client client) {
        try {
            if (client.getPassport() == null) {
                throw new IllegalArgumentException("Не заданы паспортные данные!");
            }
            if (!clientDao.existsByPassport(client.getPassport())) {
                clientDao.persist(client);
                return new CrudEntityResult<>(NORMAL, client.toString() + " успешно создан.", client);
            } else {
                return new CrudEntityResult<>(WARNING, "Паспорт \"" + client.getPassport().getShortStr() +
                        "\" уже привязан к другому клиенту.");
            }
        } catch (Throwable e) {
            context.setRollbackOnly();
            return new CrudEntityResult<>(FATAL_ERROR, "Ошибка создания клиента. " + ExceptionUtils.parse(e));
        }
    }

    @Override
    public CrudEntityResult<Client> updateClient(Client client) {
        try {
            if (clientDao.existsById(client.getId())) {
                clientDao.update(client);
                return new CrudEntityResult<>(NORMAL, client.toString() + " успешно обновлен.", client);
            } else {
                return new CrudEntityResult<>(WARNING, client.toString() + " не найден в БД.");
            }
        } catch (Throwable e) {
            context.setRollbackOnly();
            return new CrudEntityResult<>(FATAL_ERROR, "Ошибка обновления клиента. " + ExceptionUtils.parse(e));
        }
    }

    @Override
    public CrudEntityResult<Client> deleteClient(Long id) {
        try {
            if (id == null) throw new IllegalArgumentException("ID=NULL");
            if (clientDao.existsById(id)) {
                clientDao.delete(id);
                return new CrudEntityResult<>(NORMAL, "Клиент успешно удален.");
            } else {
                return new CrudEntityResult<>(WARNING, "Клиент с ID=" + id + " не найден в БД.");
            }
        } catch (Throwable e) {
            context.setRollbackOnly();
            return new CrudEntityResult<>(FATAL_ERROR, "Ошибка удаления клиента. " + ExceptionUtils.parse(e));
        }
    }

    @Override
    public List<Client> findLimitClientsByCriteria(int count, QueryCriteria criteria) {
        return clientDao.findLimitClientsByCriteria(count, criteria);
    }

    @Override
    public List<Client> findClientsByCriteria(QueryCriteria criteria) {
        return clientDao.findClientsByCriteria(criteria);
    }
}
