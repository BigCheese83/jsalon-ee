package ru.bigcheese.jsalon.ee.dao.producer;

import ru.bigcheese.jsalon.core.Constants;
import ru.bigcheese.jsalon.ee.dao.ServiceDao;
import ru.bigcheese.jsalon.ee.dao.qualifier.JDBC;
import ru.bigcheese.jsalon.ee.dao.qualifier.JPA;

import javax.enterprise.inject.Produces;

/**
 * Created by BigCheese on 02.06.15.
 */
public class ServiceDaoProducer {
    @Produces
    ServiceDao createServiceDao(@JDBC ServiceDao jdbcDao, @JPA ServiceDao jpaDao) {
        if ("jpa".equalsIgnoreCase(Constants.DAO_IMPLEMENTATION)) {
            return jpaDao;
        } else {
            return jdbcDao;
        }
    }
}
