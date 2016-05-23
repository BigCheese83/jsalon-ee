package ru.bigcheese.jsalon.core.model;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * Created by BigCheese on 18.05.16.
 */
public class ModelTO extends BaseModel {

    private String name;

    public ModelTO(Long id, String name) {
        super(id);
        this.name = name;
    }

    public ModelTO(Long id, String... names) {
        super(id);
        this.name = StringUtils.join(names, " ").trim();
    }

    public ModelTO(Object[] params) {
        if (params == null) {
            throw new IllegalArgumentException("Params is null");
        }
        if (params.length < 1 || !(params[0] instanceof Long)) {
            throw new IllegalArgumentException("First param must be ID with class Long");
        }
        setId((Long)params[0]);
        this.name = StringUtils.join(params, " ", 1, params.length).trim();
    }

    public String getName() {
        return name;
    }

    @Override
    protected List<String> getValidateErrors() {
        return null; //No validation
    }
}
