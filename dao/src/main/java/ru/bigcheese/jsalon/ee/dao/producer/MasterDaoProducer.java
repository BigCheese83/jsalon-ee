package ru.bigcheese.jsalon.ee.dao.producer;

import ru.bigcheese.jsalon.core.Constants;
import ru.bigcheese.jsalon.ee.dao.MasterDao;
import ru.bigcheese.jsalon.ee.dao.qualifier.JDBC;
import ru.bigcheese.jsalon.ee.dao.qualifier.JPA;

import javax.enterprise.inject.Produces;

/**
 * Created by BigCheese on 11.01.16.
 */
public class MasterDaoProducer {
    @Produces
    MasterDao createMasterDao(@JDBC MasterDao jdbcDao, @JPA MasterDao jpaDao) {
        if ("jpa".equalsIgnoreCase(Constants.DAO_IMPLEMENTATION)) {
            return jpaDao;
        } else {
            return jdbcDao;
        }
    }
}
