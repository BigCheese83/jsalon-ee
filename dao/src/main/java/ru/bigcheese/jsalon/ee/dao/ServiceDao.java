package ru.bigcheese.jsalon.ee.dao;

import ru.bigcheese.jsalon.core.model.Service;

/**
 * Created by BigCheese on 02.06.15.
 */
public interface ServiceDao extends BaseDao<Service, Long> {
    Service getServiceByName(String name);
    boolean existsByName(String name);
}
