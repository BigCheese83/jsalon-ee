package ru.bigcheese.jsalon.core.model.enums;

import ru.bigcheese.jsalon.core.model.Client;
import ru.bigcheese.jsalon.core.model.Master;
import ru.bigcheese.jsalon.core.model.Person;

/**
 * Created by BigCheese on 09.02.16.
 */
public enum BindModel {
    MASTER, CLIENT;

    public static String getName(BindModel bs) {
        return (bs != null) ? bs.name() : null;
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
