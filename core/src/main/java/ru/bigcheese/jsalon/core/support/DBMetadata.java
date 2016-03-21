package ru.bigcheese.jsalon.core.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.DatabaseMetaData;
import java.sql.SQLException;

/**
 * Created by BigCheese on 14.12.15.
 */
public class DBMetadata {
    private static final Logger LOG = LoggerFactory.getLogger(DBMetadata.class);

    private String databaseProductName;
    private String databaseProductVersion;
    private String driverName;
    private String driverVersion;
    private int jdbcMajorVersion;
    private int jdbcMinorVersion;
    private int maxConnections;
    private String url;
    private String username;

    public DBMetadata() {}

    public DBMetadata(DatabaseMetaData metaData) {
        if (metaData == null) return;
        try {
            this.databaseProductName = metaData.getDatabaseProductName();
            this.databaseProductVersion = metaData.getDatabaseProductVersion();
            this.driverName = metaData.getDriverName();
            this.driverVersion = metaData.getDriverVersion();
            this.jdbcMajorVersion = metaData.getJDBCMajorVersion();
            this.jdbcMinorVersion = metaData.getJDBCMinorVersion();
            this.maxConnections = metaData.getMaxConnections();
            this.url = metaData.getURL();
            this.username = metaData.getUserName();
        } catch (SQLException e) {
            LOG.error("Error in constructor DBMetadata class.", e);
        }
        int idx = this.url.indexOf('?');
        if (idx != -1) {
            this.url = this.url.substring(0, idx);
        }
    }

    public String getDatabaseProductName() {
        return databaseProductName;
    }

    public String getDatabaseProductVersion() {
        return databaseProductVersion;
    }

    public String getDriverName() {
        return driverName;
    }

    public String getDriverVersion() {
        return driverVersion;
    }

    public int getJdbcMajorVersion() {
        return jdbcMajorVersion;
    }

    public int getJdbcMinorVersion() {
        return jdbcMinorVersion;
    }

    public int getMaxConnections() {
        return maxConnections;
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }
}
