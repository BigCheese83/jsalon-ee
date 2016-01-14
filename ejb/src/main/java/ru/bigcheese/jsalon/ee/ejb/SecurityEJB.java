package ru.bigcheese.jsalon.ee.ejb;

import org.apache.commons.lang3.StringUtils;
import ru.bigcheese.jsalon.core.Constants;
import ru.bigcheese.jsalon.core.exception.DatabaseRuntimeException;
import ru.bigcheese.jsalon.core.support.DBMetadata;
import ru.bigcheese.jsalon.core.util.DBUtils;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.*;

/**
 * Created by BigCheese on 13.08.15.
 */
@Stateless
@RolesAllowed({"admin"})
public class SecurityEJB {

    @Resource(lookup = Constants.SALON_JNDI_NAME)
    private DataSource dataSource;

    private static final String COMPARE_PASSWORD = "SELECT password, md5(?) FROM security.users WHERE id = ?";
    private static final String UPDATE_PASSWORD =  "UPDATE security.users SET password = md5(?) WHERE id = ?";

    public void setUserPassword(Long userId, String newPassword) {
        setUserPassword(userId, newPassword, null);
    }

    public void setUserPassword(Long userId, String newPassword, String password) {
        if (StringUtils.isBlank(newPassword)) return;
        checkUserPassword(userId, password);
        try (Connection conn = dataSource.getConnection()) {
            try (PreparedStatement pstm = conn.prepareStatement(UPDATE_PASSWORD)) {
                pstm.setString(1, newPassword);
                pstm.setLong(2, userId);
                pstm.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DatabaseRuntimeException(DBUtils.extractSQLMessages(e));
        }
    }

    public void checkUserPassword(Long userId, String password) {
        if (StringUtils.isBlank(password)) {
            password = Constants.USER_DEFAULT_PASSWORD;
        }
        try (Connection conn = dataSource.getConnection()) {
            try (PreparedStatement pstm = conn.prepareStatement(COMPARE_PASSWORD)) {
                pstm.setString(1, password);
                pstm.setLong(2, userId);
                try (ResultSet rs = pstm.executeQuery()) {
                    if (rs.next()) {
                        if (rs.getString(1).equals(rs.getString(2))) {
                            return;
                        }
                    }
                }
            }
        } catch (SQLException e) {
            throw new DatabaseRuntimeException(DBUtils.extractSQLMessages(e));
        }
        throw new SecurityException("Wrong password. Enter correct password.");
    }

    public DBMetadata getDatabaseMetaData() {
        try {
            DatabaseMetaData data = dataSource.getConnection().getMetaData();
            return new DBMetadata(data);
        } catch (Exception e) {
            return null;
        }
    }
}
