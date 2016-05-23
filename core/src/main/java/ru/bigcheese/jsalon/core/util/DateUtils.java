package ru.bigcheese.jsalon.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by BigCheese on 24.12.15.
 */
public final class DateUtils {
    private static final Logger LOG = LoggerFactory.getLogger(DateUtils.class);

    public static Date parse(String str, String pattern) {
        Date date = null;
        try {
            date = org.apache.commons.lang3.time.DateUtils.parseDate(str, pattern);
        } catch (Exception e) {
            LOG.error("Error parse date \"{}\", return null.", str);
        }
        return date;
    }

    public static java.util.Date toDate(java.sql.Date sqlDate) {
        return (sqlDate == null) ? null : new Date(sqlDate.getTime());
    }

    public static java.util.Date toDate(Calendar calendar) {
        return (calendar == null) ? null : calendar.getTime();
    }

    public static Calendar toCalendar(java.sql.Date sqlDate) {
        if (sqlDate == null) return null;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(sqlDate.getTime()));
        return calendar;
    }

    public static Calendar addMinutes(Calendar date, Integer time) {
        if (date == null) return null;
        if (time == null) return date;
        date.add(Calendar.MINUTE, time);
        return date;
    }

    public static Integer getMinutes(Calendar calendar) {
        if (calendar == null) return null;
        return calendar.get(Calendar.HOUR_OF_DAY)*60 + calendar.get(Calendar.MINUTE);
    }
}
