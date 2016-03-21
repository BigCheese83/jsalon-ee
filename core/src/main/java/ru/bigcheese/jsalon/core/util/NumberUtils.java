package ru.bigcheese.jsalon.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

/**
 * Created by BigCheese on 26.08.15.
 */
public final class NumberUtils {
    private static final Logger LOG = LoggerFactory.getLogger(NumberUtils.class);

    public static Integer toInteger(String str) {
        return toInteger(str, null);
    }

    public static Long toLong(String str) {
        return toLong(str, null);
    }

    public static BigDecimal toBigDecimal(String str) {
        return toBigDecimal(str, null);
    }

    public static Integer toInteger(String str, Integer defaultValue) {
        if (str == null) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            LOG.error("Error convert \"{}\" to Integer, return {}.", str, defaultValue);
            return defaultValue;
        }
    }

    public static Long toLong(String str, Long defaultValue) {
        if (str == null) {
            return defaultValue;
        }
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public static BigDecimal toBigDecimal(String str, BigDecimal defaultValue) {
        if (str == null) {
            return defaultValue;
        }
        try {
            return new BigDecimal(str);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}
