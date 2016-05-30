package ru.bigcheese.jsalon.core.util;

import java.util.*;

/**
 * Created by BigCheese on 10.02.2016.
 */
public final class CollectionUtils {

    /**
     * Transform Properties to string List.
     * String has format "key=value"
     *
     * @param properties
     * @return list of strings
     */
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
