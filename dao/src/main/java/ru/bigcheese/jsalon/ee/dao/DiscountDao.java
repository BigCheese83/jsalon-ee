package ru.bigcheese.jsalon.ee.dao;

import ru.bigcheese.jsalon.core.model.Discount;

import java.util.List;

/**
 * Created by BigCheese on 11.03.15.
 */
public interface DiscountDao extends BaseDao<Discount, Long> {
    List<Discount> getDiscountsByName(String name);
}
