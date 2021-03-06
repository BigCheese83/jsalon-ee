package ru.bigcheese.jsalon.ee.dao.jdbc;

import ru.bigcheese.jsalon.core.Constants;
import ru.bigcheese.jsalon.core.exception.DatabaseRuntimeException;
import ru.bigcheese.jsalon.core.model.BaseModel;
import ru.bigcheese.jsalon.core.util.DBUtils;
import ru.bigcheese.jsalon.ee.dao.BaseDao;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by BigCheese on 12.03.15.
 */
public abstract class AbstractBaseDaoJdbc<T extends BaseModel, K extends Serializable>
        implements BaseDao<T, K> {

    @Resource(lookup = Constants.SALON_JNDI_NAME)
    private DataSource dataSource;

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    void executeUpdateSQL(String sql, Object[] params) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)
        ) {
            fillStatement(pstm, params);
            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseRuntimeException(DBUtils.extractSQLMessages(e));
        }
    }

    void batchUpdateSQL(String sql, Object[][] params) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)
        ) {
            for (Object[] p : params) {
                fillStatement(pstm, p);
                pstm.addBatch();
            }
            pstm.executeBatch();
        } catch (SQLException e) {
            throw new DatabaseRuntimeException(DBUtils.extractSQLMessages(e));
        }
    }

    <X> List<X> executeQuerySQL(String sql, RowMapper<X> mapper, Object[] params) {
        List<X> result = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)
        ) {
            fillStatement(pstm, params);
            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    result.add(mapper.mapRow(rs));
                }
            }
        } catch (SQLException e) {
            throw new DatabaseRuntimeException(DBUtils.extractSQLMessages(e));
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    <X> X executeQuerySQL(String sql, Class<X> targetClass, Object[] params) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)
        ) {
            fillStatement(pstm, params);
            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    if (targetClass == Long.class) {
                        return (X)Long.valueOf(rs.getLong(1));
                    } else if (targetClass == String.class) {
                        return (X)rs.getString(1);
                    }
                }
            }
        } catch (SQLException e) {
            throw new DatabaseRuntimeException(DBUtils.extractSQLMessages(e));
        }
        return null;
    }

    <X> List<X> executeLimitQuerySQL(String sql, int count, RowMapper<X> mapper, Object[] params) {
        List<X> result = new ArrayList<>();
        try (Connection conn = dataSource.getConnection()) {
            try (PreparedStatement pstm = conn.prepareStatement(sql)) {
                pstm.setFetchSize(count);
                pstm.setMaxRows(count);
                fillStatement(pstm, params);
                try (ResultSet rs = pstm.executeQuery()) {
                    while (rs.next()) {
                        result.add(mapper.mapRow(rs));
                    }
                }
            }
        } catch (SQLException e) {
            throw new DatabaseRuntimeException(DBUtils.extractSQLMessages(e));
        }
        return result;
    }

    Long generateID(String sql) {
        return executeQuerySQL(sql, Long.class, null);
    }

    Object getParam(Object param, Class<?> paramClass) {
        if (param != null) return param;
        if (paramClass == null) return new NullParam(Types.NULL);

        if (paramClass == String.class) {
            return new NullParam(Types.VARCHAR);
        } else if (paramClass == Long.class) {
            return new NullParam(Types.BIGINT);
        } else if (paramClass == Integer.class) {
            return new NullParam(Types.INTEGER);
        } else if (paramClass == java.util.Date.class) {
            return new NullParam(Types.DATE);
        } else if (paramClass == BigDecimal.class) {
            return new NullParam(Types.NUMERIC);
        } else if (paramClass == Boolean.class) {
            return new NullParam(Types.BOOLEAN);
        } else if (paramClass == byte[].class) {
            return new NullParam(Types.BLOB);
        }

        return new NullParam(Types.NULL);
    }
    
    private void fillStatement(PreparedStatement stmt, Object[] params) throws SQLException {
        if (params == null || params.length == 0) return;
        int pos = 1;
        for (Object param : params) {
            if (param instanceof String) {
                stmt.setString(pos++, (String)param);
            } else if (param instanceof Integer) {
                 stmt.setInt(pos++, (Integer)param);
            } else if (param instanceof Long) {
                stmt.setLong(pos++, (Long)param);
            } else if (param instanceof java.util.Date) {
                stmt.setDate(pos++,  new java.sql.Date(((java.util.Date)param).getTime()));
            } else if (param instanceof BigDecimal) {
                stmt.setBigDecimal(pos++, (BigDecimal)param);
            } else if (param instanceof Boolean) {
                stmt.setBoolean(pos++, (Boolean)param);
            } else if (param instanceof byte[]) {
                stmt.setBytes(pos++, (byte[])param);
            } else if (param instanceof NullParam) {
                stmt.setNull(pos++, ((NullParam)param).getType());
            } else {
                stmt.setObject(pos++, param);
            }
        }
    }
}
