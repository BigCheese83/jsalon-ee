package ru.bigcheese.jsalon.ee.web.jsp.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by BigCheese on 17.08.15.
 */
@WebFilter(filterName = "TimeoutSessionFilter", urlPatterns = {"/user/*", "/admin/*"})
public class TimeoutSessionFilter implements Filter {

    public void destroy() {}

    public void init(FilterConfig config) throws ServletException {}

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        if (!"/admin".equals(request.getServletPath())) {
            if (request.getSession(false) == null || request.getSession(false).getAttribute("user") == null) {
                if (request.getServletPath().startsWith("/admin")) {
                    response.sendRedirect(request.getContextPath() + "/admin");
                } else {
                    response.sendRedirect(request.getContextPath());
                }
                return;
            }
        }
        chain.doFilter(req, resp);
    }
}
