package ru.bigcheese.jsalon.core.model;

import org.apache.commons.lang3.StringUtils;
import ru.bigcheese.jsalon.core.exception.ValidationException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BigCheese on 31.07.15.
 */
public class Discount extends BaseModel {

    private String name;
    private Integer value;

    public Discount() {}

    public Discount(Long id, String name, Integer value) {
        super(id);
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Скидка \"" + name + " " + value + "%\"";
    }

    public void validate() throws ValidationException {
        List<String> errors = new ArrayList<String>();
        if (StringUtils.isBlank(name)) {
            errors.add("Введите наименование скидки");
        }
        if (value == null || value < 0 || value > 100) {
            errors.add("Значение должно быть числом в диапазоне от 0 до 100");
        }
        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }
}
