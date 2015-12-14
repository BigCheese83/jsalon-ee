package ru.bigcheese.jsalon.ee.dao;

import ru.bigcheese.jsalon.core.model.Service;

import java.util.List;

/**
 * Created by BigCheese on 02.06.15.
 */
public interface ServiceDao extends BaseDao<Service, Long> {
    List<Service> getServicesByName(String name);
}
