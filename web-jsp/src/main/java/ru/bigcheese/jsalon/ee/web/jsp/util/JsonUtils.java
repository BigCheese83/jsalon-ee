package ru.bigcheese.jsalon.ee.web.jsp.util;

import com.google.gson.*;
import ru.bigcheese.jsalon.core.Constants;
import ru.bigcheese.jsalon.core.exception.ValidationException;
import ru.bigcheese.jsalon.core.util.ExceptionUtils;

/**
 * Created by BigCheese on 27.08.15.
 */
public final class JsonUtils {

    public static final String EMPTY_JSON = "{}";

    public static Gson getGson() {
        return new GsonBuilder()
                .serializeNulls()
                .setDateFormat(Constants.ISO_DATE_FORMAT)
                .create();
    }

    public static String getJsonValidateErrors(ValidationException ex) {
        if (ex == null) return EMPTY_JSON;
        JsonObject root = new JsonObject();
        JsonArray jsonArray = new JsonArray();
        for (String message : ex.getMessages()) {
            jsonArray.add(new JsonPrimitive(message));
        }
        root.add("err", jsonArray);
        return getGson().toJson(root);
    }

    public static String getJsonException(Throwable ex) {
        if (ex == null) return EMPTY_JSON;
        JsonObject root = new JsonObject();
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(new JsonPrimitive(ExceptionUtils.parse(ex)));
        root.add("err", jsonArray);
        return getGson().toJson(root);
    }
}
