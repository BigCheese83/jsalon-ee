package ru.bigcheese.jsalon.core.model;

import org.apache.commons.lang3.StringUtils;
import ru.bigcheese.jsalon.core.exception.ValidationException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BigCheese on 31.07.15.
 */
public class Post extends BaseModel {

    private String name;

    public Post() {}

    public Post(Long id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Должность \"" + name + "\"";
    }

    public void validate() throws ValidationException {
        List<String> errors = new ArrayList<String>();
        if (StringUtils.isBlank(name)) {
            errors.add("Введите наименование должности");
        }
        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }
}
