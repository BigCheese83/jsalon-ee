package ru.bigcheese.jsalon.core.util;

import org.apache.commons.lang3.StringUtils;
import ru.bigcheese.jsalon.core.model.BaseModel;
import ru.bigcheese.jsalon.core.model.Person;

import java.util.*;

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

    public static String[] parseFIO(String fio) {
        if (StringUtils.isBlank(fio)) {
            return null;
        }
        List<String> names = new ArrayList<>(3);
        StringTokenizer tokenizer = new StringTokenizer(fio, " ");
        while (tokenizer.hasMoreTokens() && names.size() < 3) {
            names.add(tokenizer.nextToken());
        }
        return names.toArray(new String[names.size()]);
    }

    public static <T extends Person> String getFullFIO(T model) {
        return (model == null) ? "null" : model.getFullFIO();
    }
}
