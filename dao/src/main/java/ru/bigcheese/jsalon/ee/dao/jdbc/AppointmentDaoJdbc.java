package ru.bigcheese.jsalon.ee.dao.jdbc;

import ru.bigcheese.jsalon.core.model.Appointment;
import ru.bigcheese.jsalon.core.model.enums.AppointmentStatus;
import ru.bigcheese.jsalon.core.util.DateUtils;
import ru.bigcheese.jsalon.ee.dao.AppointmentDao;
import ru.bigcheese.jsalon.ee.dao.qualifier.JDBC;

import java.util.Date;
import java.util.List;

import static ru.bigcheese.jsalon.ee.dao.jdbc.ModelMapper.APPOINTMENT_MAPPER;

/**
 * Created by BigCheese on 18.05.16.
 */
@JDBC
public class AppointmentDaoJdbc extends AbstractBaseDaoJdbc<Appointment, Long>
        implements AppointmentDao {

    /* SQL Queries */
    private static final String GENERATE_ID = "SELECT nextval('schedule_id_seq')";
    private static final String INSERT_SQL =
            "INSERT INTO schedule (id, appoint_date, appoint_time, master_id, client_id, service_id, note, status) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_SQL =
            "UPDATE schedule " +
            "SET appoint_date = ?, appoint_time = ?, master_id = ?, client_id = ?, service_id = ?, note = ? " +
            "WHERE id = ?";
    private static final String DELETE_SQL =   "DELETE FROM schedule WHERE id = ?";
    private static final String SELECT_ALL =   "SELECT * FROM schedule";
    private static final String COUNT_ALL =    "SELECT count(*) FROM schedule";
    private static final String SELECT_BY_ID = "SELECT * FROM schedule WHERE id = ?";
    private static final String EXISTS_BY_ID = "SELECT id FROM schedule WHERE id = ?";
    private static final String CHECK_TIME =
            "SELECT count(*) FROM schedule " +
                "JOIN services s ON s.id = schedule.service_id " +
            "WHERE master_id = ? AND appoint_date = ? AND (? BETWEEN appoint_time AND (appoint_time + s.duration))";

    @Override
    public void persist(Appointment model) {
        if (model == null) return;
        Long id = generateID(GENERATE_ID);
        executeUpdateSQL(INSERT_SQL, new Object[]
                { getParam(id, Long.class),
                  getParam(DateUtils.toDate(model.getAppointmentDate()), Date.class),
                  getParam(DateUtils.getMinutes(model.getAppointmentDate()), Integer.class),
                  getParam(model.getMasterId(), Long.class),
                  getParam(model.getClientId(), Long.class),
                  getParam(model.getServiceId(), Long.class),
                  getParam(model.getNote(), String.class),
                  getParam(AppointmentStatus.CREATED.name(), String.class) });
        model.setId(id);
    }

    @Override
    public void update(Appointment model) {
        if (model == null) return;
        executeUpdateSQL(UPDATE_SQL, new Object[]
                { getParam(DateUtils.toDate(model.getAppointmentDate()), Date.class),
                  getParam(DateUtils.getMinutes(model.getAppointmentDate()), Integer.class),
                  getParam(model.getMasterId(), Long.class),
                  getParam(model.getClientId(), Long.class),
                  getParam(model.getServiceId(), Long.class),
                  getParam(model.getNote(), String.class),
                  getParam(model.getId(), Long.class) });
    }

    @Override
    public void delete(Long id) {
        if (id == null) return;
        executeUpdateSQL(DELETE_SQL, new Object[]{id});
    }

    @Override
    public Appointment findById(Long id) {
        if (id == null) return null;
        List<Appointment> find = executeQuerySQL(SELECT_BY_ID, APPOINTMENT_MAPPER, new Object[]{id});
        return !find.isEmpty() ? find.get(0) : null;
    }

    @Override
    public List<Appointment> findAll() {
        return executeQuerySQL(SELECT_ALL, APPOINTMENT_MAPPER, null);
    }

    @Override
    public Long countAll() {
        return executeQuerySQL(COUNT_ALL, Long.class, null);
    }

    @Override
    public boolean existsById(Long id) {
        return null != executeQuerySQL(EXISTS_BY_ID, Long.class, new Object[]
                { getParam(id, Long.class) } );
    }

    @Override
    public boolean checkTime(Appointment appointment) {
        Long result = executeQuerySQL(CHECK_TIME, Long.class, new Object[]{
                appointment.getMasterId(),
                DateUtils.toDate(appointment.getAppointmentDate()),
                DateUtils.getMinutes(appointment.getAppointmentDate())
        });
        return Long.valueOf(0).equals(result);
    }
}
