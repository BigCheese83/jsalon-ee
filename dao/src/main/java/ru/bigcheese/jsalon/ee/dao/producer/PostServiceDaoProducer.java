package ru.bigcheese.jsalon.ee.dao.producer;

import ru.bigcheese.jsalon.core.Constants;
import ru.bigcheese.jsalon.ee.dao.PostServiceDao;
import ru.bigcheese.jsalon.ee.dao.qualifier.JDBC;
import ru.bigcheese.jsalon.ee.dao.qualifier.JPA;

import javax.enterprise.inject.Produces;

/**
 * Created by BigCheese on 18.04.16.
 */
public class PostServiceDaoProducer {
    @Produces
    PostServiceDao createPostServiceDao(@JDBC PostServiceDao jdbcDao, @JPA PostServiceDao jpaDao) {
        if ("jpa".equalsIgnoreCase(Constants.DAO_IMPLEMENTATION)) {
            return jpaDao;
        } else {
            return jdbcDao;
        }
    }
}
