package ru.bigcheese.jsalon.ee.dao;

import ru.bigcheese.jsalon.core.model.Appointment;

/**
 * Created by BigCheese on 17.05.16.
 */
public interface AppointmentDao extends BaseDao<Appointment, Long> {
    boolean checkTime(Appointment appointment);
}
