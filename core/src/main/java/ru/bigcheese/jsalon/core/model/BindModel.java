package ru.bigcheese.jsalon.core.model;

/**
 * Created by BigCheese on 09.02.16.
 */
public enum BindModel {
    MASTER, CLIENT;

    public static String getName(BindModel bs) {
        if (bs == null) return null;
        return bs.name();
    }

    public static <T extends Person> String getName(T person) {
        if (person instanceof Master) {
            return MASTER.name();
        } else if (person instanceof Client) {
            return CLIENT.name();
        }
        return null;
    }

    public static BindModel fromName(String name) {
        try {
            return Enum.valueOf(BindModel.class, name);
        } catch (RuntimeException e) {
            return null;
        }
    }
}
