package ru.bigcheese.jsalon.ee.web.jsp.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by BigCheese on 21.08.15.
 */
public final class HttpUtils {

    public static Cookie getCookie(HttpServletRequest request, String name) {
        if (request != null && request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals(name)) {
                    return cookie;
                }
            }
        }
        return null;
    }
}
