package ru.bigcheese.jsalon.ee.dao;

import ru.bigcheese.jsalon.core.model.Discount;

/**
 * Created by BigCheese on 11.03.15.
 */
public interface DiscountDao extends BaseDao<Discount, Long> {
    Discount getDiscountByName(String name);
    boolean existsByName(String name);
}
