package ru.bigcheese.jsalon.ee.ejb.result;

/**
 * Created by BigCheese on 26.10.15.
 */
public class CrudEntityResult extends ActionResult {

    private Long id;

    public CrudEntityResult() { super(); }

    public CrudEntityResult(int code, String message) {
        super(code, message);
    }

    public CrudEntityResult(int code, String message, Long id) {
        super(code, message);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
