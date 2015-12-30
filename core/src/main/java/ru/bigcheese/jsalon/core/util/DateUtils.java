package ru.bigcheese.jsalon.core.util;

import java.util.Date;

/**
 * Created by BigCheese on 24.12.15.
 */
public final class DateUtils {

    public static Date parse(String str, String pattern) {
        Date date = null;
        try {
            date = org.apache.commons.lang3.time.DateUtils.parseDate(str, pattern);
        } catch (Exception ignored) {
        }
        return date;
    }

    public static java.util.Date toDate(java.sql.Date sqlDate) {
        if (sqlDate == null) return null;
        return new Date(sqlDate.getTime());
    }
}
