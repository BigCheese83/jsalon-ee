package ru.bigcheese.jsalon.ee.ejb;

import org.apache.commons.lang3.StringUtils;
import ru.bigcheese.jsalon.core.model.to.ModelTO;
import ru.bigcheese.jsalon.core.model.Service;
import ru.bigcheese.jsalon.core.util.ExceptionUtils;
import ru.bigcheese.jsalon.core.util.ModelUtils;
import ru.bigcheese.jsalon.ee.dao.ServiceDao;
import ru.bigcheese.jsalon.ee.ejb.result.CrudEntityResult;
import ru.bigcheese.jsalon.ee.ejb.result.FindResult;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

import static ru.bigcheese.jsalon.ee.ejb.result.ActionResult.*;

/**
 * Created by BigCheese on 02.06.15.
 */
@Stateless
public class ServiceFacadeBean implements ServiceFacade {

    @Resource
    private SessionContext context;

    @Inject
    private ServiceDao serviceDao;

    @Override
    public CrudEntityResult<Service> createService(Service service) {
        try {
            if (!serviceDao.existsByName(service.getName())) {
                serviceDao.persist(service);
                return new CrudEntityResult<>(NORMAL, service.toString() + " успешно создана.", service);
            } else {
                return new CrudEntityResult<>(WARNING, "Услуга \"" + service.getName() + "\" уже содержится в БД.");
            }
        } catch (Throwable e) {
            context.setRollbackOnly();
            return new CrudEntityResult<>(FATAL_ERROR, "Ошибка создания услуги. " + ExceptionUtils.parse(e));
        }
    }

    @Override
    public CrudEntityResult<Service> updateService(Service service) {
        try {
            if (serviceDao.existsById(service.getId())) {
                serviceDao.update(service);
                return new CrudEntityResult<>(NORMAL, service.toString() + " успешно обновлена.", service);
            } else {
                return new CrudEntityResult<>(WARNING, service.toString() + " не найдена в БД.");
            }
        } catch (Throwable e) {
            context.setRollbackOnly();
            return new CrudEntityResult<>(FATAL_ERROR, "Ошибка обновления услуги. " + ExceptionUtils.parse(e));
        }
    }

    @Override
    public CrudEntityResult<Service> deleteService(Long id) {
        try {
            if (id == null) throw new IllegalArgumentException("ID=NULL");
            if (serviceDao.existsById(id)) {
                serviceDao.delete(id);
                return new CrudEntityResult<>(NORMAL, "Услуга успешно удалена.");
            } else {
                return new CrudEntityResult<>(WARNING,  "Услуга с ID=" + id + " не найдена в БД.");
            }
        } catch (Throwable e) {
            context.setRollbackOnly();
            return new CrudEntityResult<>(FATAL_ERROR, "Ошибка удаления услуги. " + ExceptionUtils.parse(e));
        }
    }

    @Override
    public FindResult<Service> getAllServices() {
        try {
            return new FindResult<>(serviceDao.findAll());
        } catch (Throwable e) {
            return new FindResult<>(FATAL_ERROR, ExceptionUtils.parse(e));
        }
    }

    @Override
    public List<ModelTO> filterServicesByName(String name) {
        if (StringUtils.isBlank(name)) {
            return Collections.emptyList();
        }
        return serviceDao.filterServicesByName(name.toLowerCase());
    }

    @Override
    public List<ModelTO> filterServicesByNameAndMaster(String name, String fio) {
        if (StringUtils.isBlank(name)) {
            return Collections.emptyList();
        }
        if (StringUtils.isBlank(fio)) {
            return filterServicesByName(name);
        }
        return serviceDao.filterServicesByNameAndMaster(name.toLowerCase(), ModelUtils.parseFIO(fio));
    }
}
