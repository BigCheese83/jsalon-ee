package ru.bigcheese.jsalon.ee.ejb;

import ru.bigcheese.jsalon.core.model.Master;
import ru.bigcheese.jsalon.core.util.ExceptionUtils;
import ru.bigcheese.jsalon.ee.dao.MasterDao;
import ru.bigcheese.jsalon.ee.dao.QueryCriteria;
import ru.bigcheese.jsalon.ee.ejb.result.CrudEntityResult;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;

import java.util.List;

import static ru.bigcheese.jsalon.ee.ejb.result.ActionResult.*;

/**
 * Created by BigCheese on 11.01.16.
 */
@Stateless
public class MasterEJB implements MasterEJBLocal {

    @Resource
    SessionContext context;

    @Inject
    MasterDao masterDao;

    @Override
    public CrudEntityResult createMaster(Master master) {
        try {
            if (master.getPassport() == null) {
                throw new IllegalArgumentException("Не заданы паспортные данные!");
            }
            if (!masterDao.existsByPassport(master.getPassport())) {
                masterDao.persist(master);
                return new CrudEntityResult(NORMAL, master.toString() + " успешно создан.", master.getId());
            } else {
                return new CrudEntityResult(WARNING, "Паспорт \"" + master.getPassport().getShortStr() +
                        "\" уже привязан к другому мастеру.", master.getId());
            }
        } catch (Throwable e) {
            context.setRollbackOnly();
            return new CrudEntityResult(FATAL_ERROR, "Ошибка создания мастера. " + ExceptionUtils.parse(e));
        }
    }

    @Override
    public CrudEntityResult updateMaster(Master master) {
        try {
            if (masterDao.existsById(master.getId())) {
                masterDao.update(master);
                return new CrudEntityResult(NORMAL, master.toString() + " успешно обновлен.", master.getId());
            } else {
                return new CrudEntityResult(WARNING, master.toString() + " не найден в БД.", master.getId());
            }
        } catch (Throwable e) {
            context.setRollbackOnly();
            return new CrudEntityResult(FATAL_ERROR, "Ошибка обновления мастера. " + ExceptionUtils.parse(e));
        }
    }

    @Override
    public CrudEntityResult deleteMaster(Long id) {
        try {
            if (id == null) throw new IllegalArgumentException("ID=NULL");
            if (masterDao.existsById(id)) {
                masterDao.delete(id);
                return new CrudEntityResult(NORMAL, "Мастер успешно удален.", id);
            } else {
                return new CrudEntityResult(WARNING,  "Мастер с ID=" + id + " не найден в БД.", id);
            }
        } catch (Throwable e) {
            context.setRollbackOnly();
            return new CrudEntityResult(FATAL_ERROR, "Ошибка удаления мастера. " + ExceptionUtils.parse(e));
        }
    }

    @Override
    public List<Master> findLimitUsersByCriteria(int count, QueryCriteria criteria) {
        return masterDao.findLimitMastersByCriteria(count, criteria);
    }

    @Override
    public List<Master> findMastersByCriteria(QueryCriteria criteria) {
        return masterDao.findMastersByCriteria(criteria);
    }
}
