package ru.bigcheese.jsalon.ee.ejb;

import org.apache.commons.lang3.StringUtils;
import ru.bigcheese.jsalon.core.model.Master;
import ru.bigcheese.jsalon.core.model.ModelTO;
import ru.bigcheese.jsalon.core.util.ExceptionUtils;
import ru.bigcheese.jsalon.core.util.ModelUtils;
import ru.bigcheese.jsalon.ee.dao.MasterDao;
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
 * Created by BigCheese on 11.01.16.
 */
@Stateless
public class MasterEJB implements MasterEJBLocal {

    @Resource
    private SessionContext context;

    @Inject
    private MasterDao masterDao;

    @Override
    public CrudEntityResult<Master> createMaster(Master master) {
        try {
            if (master.getPassport() == null) {
                throw new IllegalArgumentException("Не заданы паспортные данные!");
            }
            if (!masterDao.existsByPassport(master.getPassport())) {
                masterDao.persist(master);
                return new CrudEntityResult<>(NORMAL, master.toString() + " успешно создан.", master);
            } else {
                return new CrudEntityResult<>(WARNING, "Паспорт \"" + master.getPassport().getShortStr() +
                        "\" уже привязан к другому мастеру.");
            }
        } catch (Throwable e) {
            context.setRollbackOnly();
            return new CrudEntityResult<>(FATAL_ERROR, "Ошибка создания мастера. " + ExceptionUtils.parse(e));
        }
    }

    @Override
    public CrudEntityResult<Master> updateMaster(Master master) {
        try {
            if (masterDao.existsById(master.getId())) {
                masterDao.update(master);
                return new CrudEntityResult<>(NORMAL, master.toString() + " успешно обновлен.", master);
            } else {
                return new CrudEntityResult<>(WARNING, master.toString() + " не найден в БД.");
            }
        } catch (Throwable e) {
            context.setRollbackOnly();
            return new CrudEntityResult<>(FATAL_ERROR, "Ошибка обновления мастера. " + ExceptionUtils.parse(e));
        }
    }

    @Override
    public CrudEntityResult<Master> deleteMaster(Long id) {
        try {
            if (id == null) throw new IllegalArgumentException("ID=NULL");
            if (masterDao.existsById(id)) {
                masterDao.delete(id);
                return new CrudEntityResult<>(NORMAL, "Мастер успешно удален.");
            } else {
                return new CrudEntityResult<>(WARNING,  "Мастер с ID=" + id + " не найден в БД.");
            }
        } catch (Throwable e) {
            context.setRollbackOnly();
            return new CrudEntityResult<>(FATAL_ERROR, "Ошибка удаления мастера. " + ExceptionUtils.parse(e));
        }
    }

    @Override
    public List<Master> findLimitMastersByCriteria(int count, QueryCriteria criteria) {
        return masterDao.findLimitMastersByCriteria(count, criteria);
    }

    @Override
    public List<Master> findMastersByCriteria(QueryCriteria criteria) {
        return masterDao.findMastersByCriteria(criteria);
    }

    @Override
    public List<ModelTO> filterMastersByNames(String fio) {
        if (StringUtils.isBlank(fio)) {
            return Collections.emptyList();
        }
        return masterDao.filterMastersByNames(ModelUtils.parseFIO(fio.toLowerCase()));
    }

    @Override
    public List<ModelTO> filterMastersByNamesAndService(String fio, String service) {
        if (StringUtils.isBlank(fio)) {
            return Collections.emptyList();
        }
        if (StringUtils.isBlank(service)) {
            return filterMastersByNames(fio);
        }
        return masterDao.filterMastersByNamesAndService(service, ModelUtils.parseFIO(fio.toLowerCase()));
    }
}
