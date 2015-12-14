package ru.bigcheese.jsalon.ee.dao.producer;

import ru.bigcheese.jsalon.core.Constants;
import ru.bigcheese.jsalon.ee.dao.DiscountDao;
import ru.bigcheese.jsalon.ee.dao.qualifier.JDBC;
import ru.bigcheese.jsalon.ee.dao.qualifier.JPA;

import javax.enterprise.inject.Produces;

/**
 * Created by BigCheese on 06.05.15.
 */
public class DiscountDaoProducer {
    @Produces
    DiscountDao createDiscountDao(@JDBC DiscountDao jdbcDao, @JPA DiscountDao jpaDao) {
        if ("jpa".equalsIgnoreCase(Constants.DAO_IMPLEMENTATION)) {
            return jpaDao;
        } else {
            return jdbcDao;
        }
    }
}
