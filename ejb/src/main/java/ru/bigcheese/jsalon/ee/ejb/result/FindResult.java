package ru.bigcheese.jsalon.ee.ejb.result;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BigCheese on 06.08.15.
 */
public class FindResult<T> extends ActionResult {

    List<T> result = new ArrayList<>();

    public FindResult(int code, String message) {
        super(code, message);
    }

    public FindResult(List<T> result) {
        this.result = result;
    }

    public List<T> getResult() {
        return result;
    }
}
