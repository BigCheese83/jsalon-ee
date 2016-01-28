package ru.bigcheese.jsalon.core.util;

import ru.bigcheese.jsalon.core.model.BaseModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by BigCheese on 30.12.15.
 */
public final class ModelUtils {

    public static <T extends BaseModel> Map<Long, T> transform(List<T> list) {
        if (list == null) return null;
        Map<Long, T> map = new HashMap<>(list.size());
        for (T elem : list) {
            map.put(elem.getId(), elem);
        }
        return map;
    }

    public static <T extends BaseModel> Long getID(T model) {
        return (model != null) ? model.getId() : null;
    }
}
