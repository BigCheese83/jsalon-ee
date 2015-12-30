package ru.bigcheese.jsalon.ee.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by BigCheese on 30.12.15.
 */
public interface RowMapper<T> {
    T mapRow(ResultSet rs) throws SQLException;
}
