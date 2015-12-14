package ru.bigcheese.jsalon.ee.dao;

import ru.bigcheese.jsalon.ee.dao.jdbc.JdbcQueryCriteria;

/**
 * Created by BigCheese on 05.11.15.
 */
public class QueryCriteriaFactory {

    public static QueryCriteria getInstance() {
        //if ("jpa".equalsIgnoreCase(Constants.DAO_IMPLEMENTATION)) {
        //    return jpaCriteria;
        return new JdbcQueryCriteria();
    }
}
