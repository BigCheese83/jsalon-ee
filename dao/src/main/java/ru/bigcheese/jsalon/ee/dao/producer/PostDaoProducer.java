package ru.bigcheese.jsalon.ee.dao.producer;

import ru.bigcheese.jsalon.core.Constants;
import ru.bigcheese.jsalon.ee.dao.PostDao;
import ru.bigcheese.jsalon.ee.dao.qualifier.JDBC;
import ru.bigcheese.jsalon.ee.dao.qualifier.JPA;

import javax.enterprise.inject.Produces;

/**
 * Created by BigCheese on 06.05.15.
 */
public class PostDaoProducer {
    @Produces
    PostDao createPostDao(@JDBC PostDao jdbcDao, @JPA PostDao jpaDao) {
        if ("jpa".equalsIgnoreCase(Constants.DAO_IMPLEMENTATION)) {
            return jpaDao;
        } else {
            return jdbcDao;
        }
    }
}
