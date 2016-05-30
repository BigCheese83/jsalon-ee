package ru.bigcheese.jsalon.ee.dao.entity;

import ru.bigcheese.jsalon.core.model.enums.AppointmentStatus;

import javax.persistence.*;
import javax.validation.constraints.Max;
import java.util.Calendar;

/**
 * Created by BigCheese on 17.05.16.
 */
@Entity
@Table(name = "schedule")
@NamedQueries({
    @NamedQuery(
        name = AppointmentEntity.CHECK_TIME,
        query = "SELECT count(app) FROM AppointmentEntity app " +
                    "JOIN ServiceEntity s ON s.id = app.serviceId " +
                "WHERE app.masterId = ?1 AND app.appointmentDate = ?2 " +
                    "AND (?3 BETWEEN app.appointmentTime AND (app.appointmentTime + s.duration))")
})
public class AppointmentEntity extends BaseEntity {

    public static final String CHECK_TIME = "Appointment.checkTime";

    private Calendar appointmentDate;
    private Integer appointmentTime;
    private Long masterId;
    private Long clientId;
    private Long serviceId;
    private String note;
    private AppointmentStatus status;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SCHEDULE_SEQ")
    @SequenceGenerator(name = "SCHEDULE_SEQ", sequenceName = "schedule_id_seq", allocationSize = 1)
    public Long getId() {
        return super.getId();
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "appoint_date", nullable = false)
    public Calendar getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Calendar appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    @Column(name = "appoint_time", nullable = false)
    @Max(1440)
    public Integer getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(Integer appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    @Column(name = "client_id", nullable = false)
    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    @Column(name = "master_id", nullable = false)
    public Long getMasterId() {
        return masterId;
    }

    public void setMasterId(Long masterId) {
        this.masterId = masterId;
    }

    @Column(name = "service_id", nullable = false)
    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }

    @PrePersist
    public void initStatus() {
        status = AppointmentStatus.CREATED;
    }
}
