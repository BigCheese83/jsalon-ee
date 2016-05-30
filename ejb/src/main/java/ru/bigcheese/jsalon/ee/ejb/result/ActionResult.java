package ru.bigcheese.jsalon.ee.ejb.result;

import java.io.Serializable;

/**
 * Created by BigCheese on 19.05.15.
 */
public class ActionResult implements Serializable {

    public static final int FATAL_ERROR = -1;
    public static final int NORMAL = 0;
    public static final int WARNING = 1;

    private int code = NORMAL;
    private String message = "";

    public ActionResult() {}

    public ActionResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
