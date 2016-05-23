package ru.bigcheese.jsalon.ee.dao.test.strategy;

import ru.bigcheese.jsalon.core.model.Appointment;
import ru.bigcheese.jsalon.core.model.AppointmentStatus;
import ru.bigcheese.jsalon.ee.dao.AppointmentDao;
import ru.bigcheese.jsalon.ee.dao.jdbc.AppointmentDaoJdbc;
import ru.bigcheese.jsalon.ee.dao.jpa.AppointmentDaoJpa;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by BigCheese on 18.05.16.
 */
public class AppointmentTestStrategy {

    private static final int NUMBER_OF_SCHEDULE = 5;
    private AppointmentDao appDao;

    public AppointmentTestStrategy(String name) {
        if ("jdbc".equals(name)) {
            appDao = new AppointmentDaoJdbc();
        } else if ("jpa".equals(name)) {
            appDao = new AppointmentDaoJpa();
        }
    }

    public void init(EntityManager em) {
        if (appDao instanceof AppointmentDaoJpa) {
            ((AppointmentDaoJpa) appDao).setEntityManager(em);
        }
    }

    public void init(DataSource ds) {
        if (appDao instanceof AppointmentDaoJdbc) {
            ((AppointmentDaoJdbc) appDao).setDataSource(ds);
        }
    }

    public void testFindById() {
        Appointment appointment = appDao.findById(2L);
        assertEquals(1, appointment.getClientId().intValue());
        assertEquals(1, appointment.getMasterId().intValue());
        assertEquals(5, appointment.getServiceId().intValue());
    }

    public void testFindAll() {
        List<Appointment> appointments = appDao.findAll();
        assertEquals(NUMBER_OF_SCHEDULE, appointments.size());
    }


    public void testPersist() {
        Appointment appointment = new Appointment();
        appointment.setAppointmentDate(Calendar.getInstance());
        appointment.setClientId(2L);
        appointment.setMasterId(2L);
        appointment.setServiceId(3L);
        beginTransaction();
        appDao.persist(appointment);
        commitTransaction();
        assertEquals(NUMBER_OF_SCHEDULE + 1, appDao.countAll().intValue());
    }

    public void testUpdate() {
        Appointment appointment = appDao.findById(5L);
        appointment.setNote("This is note");
        beginTransaction();
        appDao.update(appointment);
        commitTransaction();
        assertEquals("This is note", appDao.findById(5L).getNote());
    }

    public void testDelete() {
        beginTransaction();
        appDao.delete(5L);
        commitTransaction();
        assertEquals(NUMBER_OF_SCHEDULE - 1, appDao.countAll().intValue());
    }

    private void beginTransaction() {
        if (appDao instanceof AppointmentDaoJpa) {
            ((AppointmentDaoJpa) appDao).getEntityManager().getTransaction().begin();
        }
    }

    private void commitTransaction() {
        if (appDao instanceof AppointmentDaoJpa) {
            ((AppointmentDaoJpa) appDao).getEntityManager().getTransaction().commit();
        }
    }
}
