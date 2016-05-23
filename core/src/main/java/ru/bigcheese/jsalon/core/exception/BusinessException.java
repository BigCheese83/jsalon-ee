package ru.bigcheese.jsalon.core.exception;

/**
 * Created by BigCheese on 23.05.16.
 */
public class BusinessException extends Exception {

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
