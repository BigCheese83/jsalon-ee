package ru.bigcheese.jsalon.core.exception;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by BigCheese on 26.03.15.
 */
public class DatabaseRuntimeException extends RuntimeException {

    private final List<String> messages = new ArrayList<>();

    public DatabaseRuntimeException(String message) {
        this(message, null);
    }

    public DatabaseRuntimeException(String message, Throwable e) {
        super(null, e);
        messages.add(message);
    }

    public DatabaseRuntimeException(List<String> errors) {
        super();
        messages.addAll(errors);
    }

    public List<String> getMessages() {
        return Collections.unmodifiableList(messages);
    }

    @Override
    public String getMessage() {
        return StringUtils.join(messages.iterator(), ". ");
    }

}
