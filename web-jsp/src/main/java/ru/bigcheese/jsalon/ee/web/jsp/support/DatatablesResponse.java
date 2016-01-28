package ru.bigcheese.jsalon.ee.web.jsp.support;

/**
 * Created by BigCheese on 03.11.15.
 */
public class DatatablesResponse {

    private int draw;
    private int recordsTotal;
    private int recordsFiltered;
    private Object[] data;
    private String error;

    public DatatablesResponse() {}

    public DatatablesResponse(int draw, int recordsTotal, int recordsFiltered, Object[] data, String error) {
        this.draw = draw;
        this.recordsTotal = recordsTotal;
        this.recordsFiltered = recordsFiltered;
        this.data = data;
        this.error = error;
    }

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public int getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(int recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public int getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(int recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public Object[] getData() {
        return data;
    }

    public void setData(Object[] data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public static DatatablesResponse buildErrorResponse(int draw, String error) {
        return new DatatablesResponse(draw, 0, 0, new Object[]{}, error);
    }
}
