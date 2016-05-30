package ru.bigcheese.jsalon.ee.ejb;

import ru.bigcheese.jsalon.core.model.Appointment;
import ru.bigcheese.jsalon.ee.ejb.result.CrudEntityResult;

import javax.ejb.Local;

/**
 * Created by BigCheese on 20.05.16.
 */
@Local
public interface AppointmentFacade {
    CrudEntityResult<Appointment> createAppointment(Appointment appointment);

}
