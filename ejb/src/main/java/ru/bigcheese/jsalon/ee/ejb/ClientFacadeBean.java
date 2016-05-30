package ru.bigcheese.jsalon.ee.ejb;

import org.apache.commons.lang3.StringUtils;
import ru.bigcheese.jsalon.core.model.Client;
import ru.bigcheese.jsalon.core.model.to.ModelTO;
import ru.bigcheese.jsalon.core.util.ExceptionUtils;
import ru.bigcheese.jsalon.ee.dao.ClientDao;
import ru.bigcheese.jsalon.ee.dao.QueryCriteria;
import ru.bigcheese.jsalon.ee.ejb.result.CrudEntityResult;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

import static ru.bigcheese.jsalon.ee.ejb.result.ActionResult.*;

/**
 * Created by BigCheese on 11.02.16.
 */
@Stateless
public class ClientFacadeBean implements ClientFacade {

    @Resource
    private SessionContext context;

    @Inject
    private ClientDao clientDao;

    @Override
    public CrudEntityResult<Client> createClient(Client client) {
        try {
            if (client.getContact() == null) {
                throw new IllegalArgumentException("Не заданы контактные данные!");
            }
            if (clientDao.existsByPhone(client.getContact().getPhone())) {
                return new CrudEntityResult<>(WARNING, "Телефон \"" + client.getContact().getPhone() +
                        "\" уже привязан к другому клиенту.");
            }
            if (clientDao.existsByPassport(client.getPassport())) {
                return new CrudEntityResult<>(WARNING, "Паспорт \"" + client.getPassport().getShortStr() +
                        "\" уже привязан к другому клиенту.");
            }
            clientDao.persist(client);
            return new CrudEntityResult<>(NORMAL, client.toString() + " успешно создан.", client);
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

    @Override
    public List<ModelTO> filterClientsByNames(String fio) {
        if (StringUtils.isBlank(fio)) {
            return Collections.emptyList();
        }
        List<String> names = new ArrayList<>(3);
        StringTokenizer tokenizer = new StringTokenizer(fio.toLowerCase(), " ");
        while (tokenizer.hasMoreTokens() && names.size() < 3) {
            names.add(tokenizer.nextToken());
        }
        return clientDao.filterClientsByNames(names.toArray(new String[names.size()]));
    }
}
