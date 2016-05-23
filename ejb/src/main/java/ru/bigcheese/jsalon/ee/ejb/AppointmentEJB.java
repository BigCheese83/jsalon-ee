package ru.bigcheese.jsalon.ee.ejb;

import ru.bigcheese.jsalon.core.exception.BusinessException;
import ru.bigcheese.jsalon.core.model.Appointment;
import ru.bigcheese.jsalon.core.util.ExceptionUtils;
import ru.bigcheese.jsalon.ee.dao.AppointmentDao;
import ru.bigcheese.jsalon.ee.ejb.AppointmentEJBLocal;
import ru.bigcheese.jsalon.ee.ejb.result.CrudEntityResult;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;

import static ru.bigcheese.jsalon.ee.ejb.result.ActionResult.FATAL_ERROR;
import static ru.bigcheese.jsalon.ee.ejb.result.ActionResult.NORMAL;

/**
 * Created by BigCheese on 20.05.16.
 */
@Stateless
public class AppointmentEJB implements AppointmentEJBLocal {

    @Resource
    private SessionContext context;

    @Inject
    private AppointmentDao appDao;

    @Override
    public CrudEntityResult<Appointment> createAppointment(Appointment appointment) {
        try {
            if (!appDao.checkTime(appointment)) {
                throw new BusinessException(
                        "<br/>К сожалению, данный мастер уже занят в указанное время.:(<br/>" +
                        "Попробуйте записаться на другое время или к другому мастеру.");
            }
            appDao.persist(appointment);
            return new CrudEntityResult<>(NORMAL, "Поздравляем! Запись успешно создана!", appointment);
        } catch (Throwable e) {
            context.setRollbackOnly();
            return new CrudEntityResult<>(FATAL_ERROR, "Ошибка создания записи. " + ExceptionUtils.parse(e));
        }
    }
}
