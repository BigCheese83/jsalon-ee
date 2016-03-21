package ru.bigcheese.jsalon.ee.web.jsp.servlet;

import org.slf4j.MDC;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.Principal;

/**
 * Created by BigCheese on 14.03.2016.
 */
@WebFilter(filterName = "MdcFilter", urlPatterns = {"/user/*", "/admin/*"})
public class MdcFilter implements Filter {

    private static final String USER_KEY = "user";

    public void destroy() {}

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        boolean success = false;
        HttpServletRequest request = (HttpServletRequest) req;
        Principal principal = request.getUserPrincipal();
        if (principal != null) {
            success = registerMDC(principal.getName());
        }
        try {
            chain.doFilter(request, resp);
        } finally {
            if (success) {
                MDC.remove(USER_KEY);
            }
        }
    }

    public void init(FilterConfig config) throws ServletException {}

    private boolean registerMDC(String user) {
        if (user != null && user.trim().length() > 0) {
            MDC.put(USER_KEY, user);
            return true;
        }
        return false;
    }
}
