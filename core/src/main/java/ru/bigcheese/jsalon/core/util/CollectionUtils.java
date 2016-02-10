package ru.bigcheese.jsalon.core.util;

import java.util.*;

/**
 * Created by BigCheese on 10.02.2016.
 */
public final class CollectionUtils {

    public static List<String> listProperties(Properties properties) {
        if (properties == null) return null;
        List<String> result = new ArrayList<>(properties.size());
        Set<String> keys = properties.stringPropertyNames();
        for (String key : keys) {
            result.add(key + "=" + properties.getProperty(key));
        }
        return result;
    }
}
