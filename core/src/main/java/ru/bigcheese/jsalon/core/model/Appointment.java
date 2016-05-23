package ru.bigcheese.jsalon.core.model;

import java.util.*;

/**
 * Created by BigCheese on 16.05.16.
 */
public class Appointment extends BaseModel {

    private Calendar appointmentDate;
    private Long clientId;
    private Long masterId;
    private Long serviceId;
    private String note;
    private AppointmentStatus status;

    public Calendar getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Calendar appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getMasterId() {
        return masterId;
    }

    public void setMasterId(Long masterId) {
        this.masterId = masterId;
    }

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

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Запись " + (appointmentDate != null ? appointmentDate.getTime() : "null") +
                " клиент id="  + clientId + ", мастер id=" + masterId + ", услуга id=" + serviceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Appointment)) return false;
        Appointment that = (Appointment) o;
        return Objects.equals(appointmentDate, that.appointmentDate) &&
                Objects.equals(clientId, that.clientId) &&
                Objects.equals(masterId, that.masterId) &&
                Objects.equals(serviceId, that.serviceId) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(appointmentDate, clientId, masterId, serviceId, status);
    }

    @Override
    protected List<String> getValidateErrors() {
        List<String> errors = new ArrayList<>();
        if (clientId == null) {
            errors.add("Укажите клиента");
        }
        if (masterId == null) {
            errors.add("Укажите мастера");
        }
        if (serviceId == null) {
            errors.add("Укажите услугу");
        }
        if (appointmentDate == null) {
            errors.add("Укажите дату и время записи");
        }
        return Collections.unmodifiableList(errors);
    }
}
