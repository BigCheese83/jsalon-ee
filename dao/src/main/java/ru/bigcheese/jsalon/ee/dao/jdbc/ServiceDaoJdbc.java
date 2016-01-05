package ru.bigcheese.jsalon.ee.dao.jdbc;

import ru.bigcheese.jsalon.core.model.Service;
import ru.bigcheese.jsalon.ee.dao.ServiceDao;
import ru.bigcheese.jsalon.ee.dao.qualifier.JDBC;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static ru.bigcheese.jsalon.ee.dao.jdbc.ModelMapper.*;

/**
 * Created by BigCheese on 02.06.15.
 */
@JDBC
public class ServiceDaoJdbc extends AbstractBaseDaoJdbc<Service, Long>
        implements ServiceDao {

    private static final String SEQ_NAME = "services_id_seq";
    /* SQL Queries */
    private static final String INSERT_SQL =
            "INSERT INTO services (id, name, cost, duration, description) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE_SQL =
            "UPDATE services SET name = ?, cost = ?, duration = ?, description = ? WHERE id = ?";
    private static final String DELETE_SQL =     "DELETE FROM services WHERE id = ?";
    private static final String SELECT_ALL =     "SELECT * FROM services";
    private static final String COUNT_ALL =      "SELECT count(*) FROM services";
    private static final String SELECT_BY_ID =   "SELECT * FROM services WHERE id = ?";
    private static final String SELECT_BY_NAME = "SELECT * FROM services WHERE name = ?";

    @Override
    public void persist(Service model) {
        if (model == null) return;
        Long id = generateSeqID(SEQ_NAME);
        super.executeUpdateSQL(INSERT_SQL, new Object[]
                        { getParam(id, Long.class),
                          getParam(model.getName(), String.class),
                          getParam(model.getCost(), BigDecimal.class),
                          getParam(model.getDuration(), Integer.class),
                          getParam(model.getDescription(), String.class) });
        model.setId(id);
    }

    @Override
    public void update(Service model) {
        if (model == null) return;
        super.executeUpdateSQL(UPDATE_SQL, new Object[]
                        { getParam(model.getName(), String.class),
                          getParam(model.getCost(), BigDecimal.class),
                          getParam(model.getDuration(), Integer.class),
                          getParam(model.getDescription(), String.class),
                          getParam(model.getId(), Long.class) });
    }

    @Override
    public void delete(Service model) {
        if (model == null) return;
        super.executeUpdateSQL(DELETE_SQL, new Object[]
                        { getParam(model.getId(), Long.class) });
    }

    @Override
    public Service findById(Long id) {
        if (id == null) return null;
        List<Service> find = super.executeQuerySQL(SELECT_BY_ID, SERVICE_MAPPER, new Object[]{id});
        return !find.isEmpty() ? find.get(0) : null;
    }

    @Override
    public List<Service> findAll() {
        return super.executeQuerySQL(SELECT_ALL, SERVICE_MAPPER, null);
    }

    @Override
    public Long countAll() {
        return super.executeSingleLongQuerySQL(COUNT_ALL, null);
    }

    @Override
    public List<Service> getServicesByName(String name) {
        return super.executeQuerySQL(SELECT_BY_NAME, SERVICE_MAPPER, new Object[]
                        { getParam(name, String.class) });
    }
}
