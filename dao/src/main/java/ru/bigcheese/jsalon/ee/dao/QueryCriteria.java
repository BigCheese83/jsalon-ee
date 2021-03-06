package ru.bigcheese.jsalon.ee.dao;

/**
 * Created by BigCheese on 05.11.15.
 */
public interface QueryCriteria {
    QueryCriteria where();
    QueryCriteria orderBy(String col, String dir);
    QueryCriteria limit(int count, int offset);
    QueryCriteria like(String colName, String value, String pattern);
    QueryCriteria equal(String colName, String value);
    QueryCriteria and();
    QueryCriteria or();
    String lower(String colName);
    String upper(String colName);
    String buildSQL();
}
