package ru.bigcheese.jsalon.ee.web.jsp.support;

import org.apache.commons.lang3.math.NumberUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by BigCheese on 03.11.15.
 */
public class DatatablesRequest {

    private int draw;
    private int start;
    private int length;
    private String searchValue;
    private int orderColumn;
    private String orderDir;

    public DatatablesRequest(HttpServletRequest request) {
        this.draw = NumberUtils.toInt(request.getParameter("draw"));
        this.start = NumberUtils.toInt(request.getParameter("start"));
        this.length = NumberUtils.toInt(request.getParameter("length"));
        this.searchValue = request.getParameter("search[value]");
        this.orderColumn = NumberUtils.toInt(request.getParameter("order[0][column]"));
        this.orderDir = request.getParameter("order[0][dir]");
    }

    public int getDraw() {
        return draw;
    }

    public int getStart() {
        return start;
    }

    public int getLength() {
        return length;
    }

    public String getSearchValue() {
        return searchValue;
    }

    public int getOrderColumn() {
        return orderColumn;
    }

    public String getOrderDir() {
        return orderDir;
    }
}
