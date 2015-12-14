package ru.bigcheese.jsalon.core;

import org.apache.commons.lang3.StringUtils;

import java.util.Calendar;

/**
 * Created by BigCheese on 05.08.15.
 */
public final class Constants {

    /* Software */
    public static final String SOFTWARE_NAME = "АИС \"Салон Красоты\"";
    public static final String VERSION_NUMBER = "v.1.0";
    public static final String VERSION_DATE = "14.12.2015";
    public static final String COPYRIGHT_STRING;

    /* JVM System Properties */
    private static final String DAO_IMPLEMENTATION_PROPERTY = "JSALON_DAO_IMPLEMENTATION";

    /* JNDI */
    public static final String SALON_JNDI_NAME = "jdbc/salon";

    /* DAO Implementation */
    public static final String DAO_IMPLEMENTATION;

    /* JPA Persistence Unit */
    public static final String SALON_JPA_PU = "Salon_PU";
    public static final String TEST_JPA_PU = "TEST_PU";

    /* Security */
    public static final String USER_DEFAULT_PASSWORD = "123321";

    static {
        DAO_IMPLEMENTATION = StringUtils.defaultIfBlank(System.getProperty(DAO_IMPLEMENTATION_PROPERTY), "jdbc");
        COPYRIGHT_STRING = "\u00a9" + " " + SOFTWARE_NAME + " " + Calendar.getInstance().get(Calendar.YEAR) + " г.";
    }
}
