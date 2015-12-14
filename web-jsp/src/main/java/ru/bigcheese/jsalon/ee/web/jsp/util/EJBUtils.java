package ru.bigcheese.jsalon.ee.web.jsp.util;

import ru.bigcheese.jsalon.ee.ejb.result.ActionResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BigCheese on 21.08.15.
 */
public final class EJBUtils {

    public static List<String> getMessages(int code, ActionResult... actions) {
        List<String> result = new ArrayList<>();
        if (actions != null) {
            for (ActionResult action : actions) {
                if (action.getCode() == code) {
                    result.add(action.getMessage());
                }
            }
        }
        return result;
    }
}
