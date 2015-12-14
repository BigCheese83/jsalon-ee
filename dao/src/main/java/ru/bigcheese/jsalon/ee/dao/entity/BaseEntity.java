package ru.bigcheese.jsalon.ee.dao.entity;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Created by BigCheese on 26.10.15.
 */
@MappedSuperclass
public class BaseEntity implements Serializable {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
