package ru.bigcheese.jsalon.ee.dao.test.jdbc;

import org.h2.jdbcx.JdbcDataSource;
import ru.bigcheese.jsalon.core.util.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

/**
 * Created by BigCheese on 26.03.15.
 */
public abstract class AbstractDaoJdbcTest {
    JdbcDataSource dataSource = new JdbcDataSource();
    private Connection connection;


    void initialize() throws Exception {
        dataSource.setURL("jdbc:h2:mem:testdb");
        dataSource.setUser("sa");
        dataSource.setPassword("");
        connection = dataSource.getConnection();
        runScript("create.sql");
        runScript("insert.sql");
    }

    void close() throws Exception {
        runScript("drop.sql");
        if (connection != null) connection.close();
    }

    private void runScript(String filename) throws Exception {
        List<String> queries = DBUtils.parseSQLQueries(filename);
        if (queries.size() > 0) {
            for (String query : queries) {
                PreparedStatement stmt = connection.prepareStatement(query);
                stmt.executeUpdate();
                stmt.close();
            }
        }
    }
}
