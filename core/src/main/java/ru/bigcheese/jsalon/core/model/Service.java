package ru.bigcheese.jsalon.core.model;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by BigCheese on 31.07.15.
 */
public class Service extends BaseModel {

    private String name;        //наименование услуги
    private BigDecimal cost;    //цена (руб)
    private Integer duration;   //длительность (мин)
    private String description; //примечание

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Услуга \"" + name + "\" [цена " + cost + "руб.]";
    }

    @Override
    protected List<String> getValidateErrors() {
        List<String> errors = new ArrayList<String>();
        if (StringUtils.isBlank(name)) {
            errors.add("Введите наименование услуги");
        }
        if (cost == null) {
            errors.add("Введите цену услуги");
        } else if (cost.compareTo(BigDecimal.ZERO) < 1) {
            errors.add("Цена должна быть положительным числом больше 0");
        }
        if (duration == null) {
            errors.add("Введите длительность услуги");
        } else if (duration <= 0) {
            errors.add("Длительность услуги должна быть положительным числом больше 0");
        }
        return Collections.unmodifiableList(errors);
    }
}
