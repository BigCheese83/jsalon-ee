package ru.bigcheese.jsalon.core.util;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by BigCheese on 19.05.15.
 */
public final class ExceptionUtils {

    public static String parse(Throwable e) {
        if (e == null) return "";
        StringBuilder sb = new StringBuilder(StringUtils.defaultString(e.getMessage()));
        Throwable cause;
        Throwable root = e;
        while ((cause = root.getCause())!= null && (cause != root)) {
            String message = cause.getMessage();
            if (StringUtils.isNotBlank(message) && sb.indexOf(message) < 0) {
                if (sb.length() > 0) {
                    sb.append( sb.toString().endsWith(".") ? " " : ". " );
                }
                sb.append(message);
            }
            root = cause;
        }
        if (sb.length() == 0) {
            sb.append(e.getClass().getName());
        }
        return sb.toString();
    }
}
