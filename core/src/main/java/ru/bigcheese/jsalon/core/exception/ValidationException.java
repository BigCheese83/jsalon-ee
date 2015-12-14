package ru.bigcheese.jsalon.core.exception;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BigCheese on 26.08.15.
 */
public class ValidationException extends Exception {

    private final List<String> messages = new ArrayList<>();

    public ValidationException(List<String> errors) {
        super();
        messages.addAll(errors);
    }

    public ValidationException(String error) {
        super(error);
        messages.add(error);
    }

    public List<String> getMessages() {
        return messages;
    }

    @Override
    public String getMessage() {
        return StringUtils.join(messages.toArray(), ". ");
    }
}
