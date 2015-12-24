package ru.bigcheese.jsalon.core.model;

import ru.bigcheese.jsalon.core.exception.ValidationException;

import java.io.Serializable;
import java.util.List;

/**
 * Created by BigCheese on 31.07.15.
 */
public abstract class BaseModel implements Serializable {

    private Long id;

    public BaseModel() {}

    BaseModel(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void validate() throws ValidationException {
        List<String> errors = getValidateErrors();
        if (errors != null && !errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }

    protected abstract List<String> getValidateErrors();
}
