package ru.bigcheese.jsalon.core.model;

import java.io.Serializable;

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
}
