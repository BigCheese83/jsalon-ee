package ru.bigcheese.jsalon.ee.web.jsp.util;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import ru.bigcheese.jsalon.core.exception.ValidationException;
import ru.bigcheese.jsalon.core.util.ExceptionUtils;
import ru.bigcheese.jsalon.ee.ejb.result.CrudEntityResult;

import java.util.List;

/**
 * Created by BigCheese on 27.08.15.
 */
public final class JsonUtils {

    public static final String EMPTY_JSON = "{}";

    public static String getJsonArrayObject(String name, List<String> values) {
        if (StringUtils.isBlank(name)) return EMPTY_JSON;
        StringBuilder sb = new StringBuilder();
        sb.append("\"").append(name).append("\":[");
        if (values != null) {
            for (String s : values) {
                sb.append("\"").append(StringEscapeUtils.escapeJson(s)).append("\",");
            }
            if (sb.charAt(sb.length() - 1) == ',') {
                sb.deleteCharAt(sb.length() - 1);
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public static String getJsonCrudEjbResult(CrudEntityResult crudResult) {
        if (crudResult == null) return EMPTY_JSON;
        return "{\"message\":\"" + StringEscapeUtils.escapeJson(crudResult.getMessage()) +
                "\",\"code\":" + crudResult.getCode() +
                ",\"id\":" + crudResult.getId() + "}";
    }

    public static String getJsonValidateErrors(ValidationException ex) {
        if (ex == null) return EMPTY_JSON;
        return "{" + getJsonArrayObject("err", ex.getMessages()) + "}";
    }

    public static String getJsonError(Throwable ex) {
        if (ex == null) return EMPTY_JSON;
        return "{\"err\":[\"" + StringEscapeUtils.escapeJson(ExceptionUtils.parse(ex)) + "\"]}";
    }
}
