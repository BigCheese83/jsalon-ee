package ru.bigcheese.jsalon.ee.ejb.result;

import ru.bigcheese.jsalon.core.model.BaseModel;

/**
 * Created by BigCheese on 26.10.15.
 */
public class CrudEntityResult<T extends BaseModel> extends ActionResult {

    private T result;

    public CrudEntityResult() { super(); }

    public CrudEntityResult(int code, String message) {
        super(code, message);
    }

    public CrudEntityResult(int code, String message, T result) {
        super(code, message);
        this.result = result;
    }

    public T getResult() {
        return result;
    }
}
