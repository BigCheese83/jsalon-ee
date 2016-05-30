package ru.bigcheese.jsalon.core;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

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
    public static final Properties JSALON_SYSTEM_PROPERTIES;
    public static final Properties JSALON_ENVIRONMENT_PROPERTIES;

    public static final String DAO_IMPLEMENTATION_PROPERTY = "jsalon.dao.implementation";
    public static final String LOG_PATH_PROPERTY = "jsalon.log.path";

    public static final String ENVIRONMENT_PROPERTY = "jsalon.environment";

    /* JNDI */
    public static final String SALON_JNDI_NAME = "jdbc/salon";

    /* DAO Implementation */
    public static final String DAO_IMPLEMENTATION;

    public static final String LOG_PATH;

    /* JPA Persistence Unit */
    public static final String SALON_JPA_PU = "Salon_PU";
    public static final String TEST_JPA_PU = "TEST_PU";

    /* Security */
    public static final String USER_DEFAULT_PASSWORD = "123321";

    public static final Map<String, String> ALL_COUNTRIES;

    /* Date/Time Formats */
    public static final String ISO_DATE_FORMAT = "yyyy-MM-dd";
    public static final String CASUAL_DATE_FORMAT = "dd.MM.yyyy";
    public static final String CASUAL_DATETIME_FORMAT = "dd.MM.yyyy HH:mm";

    static {
        DAO_IMPLEMENTATION = StringUtils.defaultIfBlank(System.getProperty(DAO_IMPLEMENTATION_PROPERTY), "jdbc");
        LOG_PATH = StringUtils.defaultIfBlank(System.getProperty(LOG_PATH_PROPERTY), "");
        COPYRIGHT_STRING = "\u00a9" + " " + SOFTWARE_NAME + " " + Calendar.getInstance().get(Calendar.YEAR) + " г.";
        ALL_COUNTRIES = initCountries();
        JSALON_SYSTEM_PROPERTIES = initProperties();
        JSALON_ENVIRONMENT_PROPERTIES = initEnvironment();
    }

    private static Properties initProperties() {
        Properties props = new Properties();
        props.setProperty(DAO_IMPLEMENTATION_PROPERTY, DAO_IMPLEMENTATION);
        props.setProperty(LOG_PATH_PROPERTY, LOG_PATH);
        return props;
    }

    private static Properties initEnvironment() {
        Properties props = new Properties();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try (InputStream is = classLoader.getResourceAsStream("env.properties")) {
            props.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return props;
    }

    private static Map<String, String> initCountries() {
        Map<String, String> map = new LinkedHashMap<>(200);
        map.put("AUS", "Австралия");
        map.put("AUT", "Австрия");
        map.put("ALB", "Албания");
        map.put("AND", "Андорра");
        map.put("ARG", "Аргентина");
        map.put("BLR", "Белоруссия");
        map.put("BEL", "Бельгия");
        map.put("BGR", "Болгария");
        map.put("BIH", "Босния и Герцеговина");
        map.put("BRA", "Бразилия");
        map.put("GBR", "Великобритания ");
        map.put("HUN", "Венгрия");
        map.put("VEN", "Венесуэла");
        map.put("VNM", "Вьетнам");
        map.put("DEU", "Германия");
        map.put("GRC", "Греция");
        map.put("DNK", "Дания");
        map.put("ISR", "Израиль");
        map.put("IND", "Индия");
        map.put("IDN", "Индонезия");
        map.put("IRQ", "Ирак");
        map.put("IRN", "Иран");
        map.put("IRL", "Ирландия");
        map.put("ISL", "Исландия");
        map.put("ESP", "Испания");
        map.put("ITA", "Италия");
        map.put("KAZ", "Казахстан");
        map.put("CAN", "Канада");
        map.put("CHN", "Китай");
        map.put("PRK", "КНДР");
        map.put("COL", "Колумбия");
        map.put("CUB", "Куба");
        map.put("LIE", "Лихтенштейн");
        map.put("LUX", "Люксембург");
        map.put("MKD", "Македония");
        map.put("MEX", "Мексика");
        map.put("MNG", "Монголия");
        map.put("NLD", "Нидерланды");
        map.put("NZL", "Новая Зеландия");
        map.put("NOR", "Норвегия");
        map.put("ARE", "ОАЭ");
        map.put("PAK", "Пакистан");
        map.put("PRY", "Парагвай");
        map.put("PER", "Перу");
        map.put("POL", "Польша");
        map.put("PRT", "Португалия");
        map.put("KOR", "Республика Корея");
        map.put("RUS", "Россия");
        map.put("ROU", "Румыния");
        map.put("SMR", "Сан-Марино");
        map.put("SAU", "Саудовская Аравия");
        map.put("SRB", "Сербия");
        map.put("SVK", "Словакия");
        map.put("SVK", "Словакия");
        map.put("USA", "США");
        map.put("THA", "Таиланд");
        map.put("TUR", "Турция");
        map.put("UKR", "Украина");
        map.put("URY", "Уругвай");
        map.put("PHL", "Филиппины");
        map.put("FIN", "Финляндия");
        map.put("FRA", "Франция");
        map.put("HRV", "Хорватия");
        map.put("MNE", "Черногория");
        map.put("CZE", "Чехия");
        map.put("CHL", "Чили");
        map.put("CHE", "Швейцария");
        map.put("SWE", "Швеция");
        map.put("ECU", "Эквадор");
        map.put("ZAF", "ЮАР");
        map.put("JPN", "Япония");
        return Collections.unmodifiableMap(map);
    }
}
