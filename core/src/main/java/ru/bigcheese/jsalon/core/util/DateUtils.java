package ru.bigcheese.jsalon.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        if (sqlDate == null) return null;
        return new Date(sqlDate.getTime());
    }
}
