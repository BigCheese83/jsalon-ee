package ru.bigcheese.jsalon.ee.dao.jpa;

import ru.bigcheese.jsalon.core.model.Appointment;
import ru.bigcheese.jsalon.core.util.DateUtils;
import ru.bigcheese.jsalon.ee.dao.AppointmentDao;
import ru.bigcheese.jsalon.ee.dao.entity.AppointmentEntity;
import ru.bigcheese.jsalon.ee.dao.entity.EntityMapper;
import ru.bigcheese.jsalon.ee.dao.qualifier.JPA;

import javax.persistence.TemporalType;
import java.util.List;

import static ru.bigcheese.jsalon.ee.dao.entity.AppointmentEntity.CHECK_TIME;

/**
 * Created by BigCheese on 17.05.16.
 */
@JPA
public class AppointmentDaoJpa extends AbstractBaseDaoJpa<Appointment, Long, AppointmentEntity>
        implements AppointmentDao {

    @Override
    public boolean checkTime(Appointment appointment) {
        List<Long> list = getEntityManager().createNamedQuery(CHECK_TIME, Long.class)
                .setParameter(1, appointment.getMasterId())
                .setParameter(2, appointment.getAppointmentDate(), TemporalType.DATE)
                .setParameter(3, DateUtils.getMinutes(appointment.getAppointmentDate()))
                .getResultList();
        return Long.valueOf(0).equals(list.get(0));
    }

    @Override
    AppointmentEntity toEntity(Appointment model) {
        return EntityMapper.toAppointmentEntity(model);
    }

    @Override
    Appointment toModel(AppointmentEntity entity) {
        return EntityMapper.toAppointmentModel(entity);
    }

}
