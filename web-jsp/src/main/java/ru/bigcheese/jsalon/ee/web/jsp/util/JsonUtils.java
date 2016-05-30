package ru.bigcheese.jsalon.ee.web.jsp.util;

import com.google.gson.*;
import ru.bigcheese.jsalon.core.Constants;
import ru.bigcheese.jsalon.core.exception.ValidationException;
import ru.bigcheese.jsalon.core.model.to.ModelTO;
import ru.bigcheese.jsalon.core.util.ExceptionUtils;

import java.lang.reflect.Type;

/**
 * Created by BigCheese on 27.08.15.
 */
public final class JsonUtils {

    public static final String EMPTY_JSON = "{}";
    public static final JsonSerializer<ModelTO> MODEL_TO_SERIALIZER;

    public static Gson getGson() {
        return getGsonBuilder().create();
    }

    public static GsonBuilder getGsonBuilder() {
        return new GsonBuilder()
                .serializeNulls()
                .setDateFormat(Constants.ISO_DATE_FORMAT);
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

    static {
        MODEL_TO_SERIALIZER = new JsonSerializer<ModelTO>() {
            @Override
            public JsonElement serialize(ModelTO src, Type typeOfSrc, JsonSerializationContext context) {
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("id", src.getId());
                jsonObject.addProperty("value", src.getName());
                return jsonObject;
            }
        };
    }
}
