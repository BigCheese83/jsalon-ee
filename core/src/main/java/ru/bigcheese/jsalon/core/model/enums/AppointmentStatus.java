package ru.bigcheese.jsalon.core.model.enums;

/**
 * Created by BigCheese on 16.05.16.
 */
public enum AppointmentStatus {
    CREATED,
    CANCELLED,
    DONE;

    public static AppointmentStatus fromName(String name) {
        try {
            return Enum.valueOf(AppointmentStatus.class, name);
        } catch (RuntimeException e) {
            return null;
        }
    }
}
