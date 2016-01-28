package ru.bigcheese.jsalon.ee.dao.jdbc;

import org.apache.commons.lang3.StringUtils;
import ru.bigcheese.jsalon.ee.dao.QueryCriteria;

/**
 * Created by BigCheese on 05.11.15.
 */
public class JdbcQueryCriteria implements QueryCriteria {

    private StringBuffer sb = new StringBuffer("");

    @Override
    public QueryCriteria where() {
        sb.append(" WHERE ");
        return this;
    }

    @Override
    public QueryCriteria orderBy(int colNumber, String dir) {
        if (colNumber > 0) {
            sb.append(" ORDER BY ").append(colNumber);
            if ("desc".equalsIgnoreCase(dir)) {
                sb.append(" ").append(dir);
            }
        }
        return this;
    }

    @Override
    public QueryCriteria limit(int count, int offset) {
        sb.append(" LIMIT ").append(count).append(" OFFSET ").append(offset);
        return this;
    }

    @Override
    public QueryCriteria like(String colName, String value, String pattern) {
        if (StringUtils.isNotBlank(colName)) {
            sb.append(" ").append(colName).append(" LIKE ").append(applyPattern(value, pattern));
        }
        return this;
    }

    @Override
    public QueryCriteria equal(String colName, String value) {
        if (StringUtils.isNotBlank(colName)) {
            sb.append(" ").append(colName).append(" = ").append("'").append(value).append("'");
        }
        return this;
    }

    @Override
    public QueryCriteria and() {
        sb.append(" AND ");
        return this;
    }

    @Override
    public QueryCriteria or() {
        sb.append(" OR ");
        return this;
    }

    @Override
    public String buildSQL() {
        return sb.toString();
    }

    private String applyPattern(String str, String pattern) {
        return "'" + pattern.replace("x", str) + "'"; //TODO
    }
}
