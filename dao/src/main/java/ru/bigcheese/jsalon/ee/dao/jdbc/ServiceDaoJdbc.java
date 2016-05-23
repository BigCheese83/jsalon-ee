package ru.bigcheese.jsalon.ee.dao.jdbc;

import ru.bigcheese.jsalon.core.model.ModelTO;
import ru.bigcheese.jsalon.core.model.Service;
import ru.bigcheese.jsalon.core.util.DBUtils;
import ru.bigcheese.jsalon.ee.dao.QueryCriteriaFactory;
import ru.bigcheese.jsalon.ee.dao.QueryCriteriaType;
import ru.bigcheese.jsalon.ee.dao.ServiceDao;
import ru.bigcheese.jsalon.ee.dao.qualifier.JDBC;

import java.math.BigDecimal;
import java.util.List;

import static ru.bigcheese.jsalon.ee.dao.jdbc.ModelMapper.NAME_MAPPER;
import static ru.bigcheese.jsalon.ee.dao.jdbc.ModelMapper.SERVICE_MAPPER;

/**
 * Created by BigCheese on 02.06.15.
 */
@JDBC
public class ServiceDaoJdbc extends AbstractBaseDaoJdbc<Service, Long>
        implements ServiceDao {

    /* SQL Queries */
    private static final String GENERATE_ID =    "SELECT nextval('services_id_seq')";
    private static final String INSERT_SQL =
            "INSERT INTO services (id, name, cost, duration, description) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE_SQL =
            "UPDATE services SET name = ?, cost = ?, duration = ?, description = ? WHERE id = ?";
    private static final String DELETE_SQL =     "DELETE FROM services WHERE id = ?";
    private static final String SELECT_ALL =     "SELECT * FROM services";
    private static final String COUNT_ALL =      "SELECT count(*) FROM services";
    private static final String SELECT_BY_ID =   "SELECT * FROM services WHERE id = ?";
    private static final String SELECT_BY_NAME = "SELECT * FROM services WHERE name = ?";
    private static final String EXISTS_BY_ID =   "SELECT id FROM services WHERE id = ?";
    private static final String EXISTS_BY_NAME = "SELECT name FROM services WHERE name = ?";
    private static final String FILTER_BY_NAME = "SELECT id, name FROM services WHERE lower(name) LIKE ? ESCAPE '!' ORDER BY name";
    private static final String FILTER_BY_NAME_MASTER =
            "SELECT s.id, s.name FROM services s " +
                "JOIN posts_services ps ON s.id = ps.service_id " +
                "JOIN posts p ON p.id = ps.post_id " +
                "JOIN masters m ON p.id = m.id_post ";

    @Override
    public void persist(Service model) {
        if (model == null) return;
        Long id = generateID(GENERATE_ID);
        executeUpdateSQL(INSERT_SQL, new Object[]
                { getParam(id, Long.class),
                  getParam(model.getName(), String.class),
                  getParam(model.getCost(), BigDecimal.class),
                  getParam(model.getDuration(), Integer.class),
                  getParam(model.getDescription(), String.class)});
        model.setId(id);
    }

    @Override
    public void update(Service model) {
        if (model == null) return;
        executeUpdateSQL(UPDATE_SQL, new Object[]
                { getParam(model.getName(), String.class),
                  getParam(model.getCost(), BigDecimal.class),
                  getParam(model.getDuration(), Integer.class),
                  getParam(model.getDescription(), String.class),
                  getParam(model.getId(), Long.class)});
    }

    @Override
    public void delete(Long id) {
        if (id == null) return;
        executeUpdateSQL(DELETE_SQL, new Object[]{id});
    }

    @Override
    public Service findById(Long id) {
        if (id == null) return null;
        List<Service> find = executeQuerySQL(SELECT_BY_ID, SERVICE_MAPPER, new Object[]{id});
        return !find.isEmpty() ? find.get(0) : null;
    }

    @Override
    public List<Service> findAll() {
        return executeQuerySQL(SELECT_ALL, SERVICE_MAPPER, null);
    }

    @Override
    public Long countAll() {
        return executeQuerySQL(COUNT_ALL, Long.class, null);
    }

    @Override
    public boolean existsById(Long id) {
        return null != executeQuerySQL(EXISTS_BY_ID, Long.class, new Object[]{
                getParam(id, Long.class)});
    }

    @Override
    public Service getServiceByName(String name) {
        List<Service> find = executeQuerySQL(SELECT_BY_NAME, SERVICE_MAPPER, new Object[]
                { getParam(name, String.class) });
        return !find.isEmpty() ? find.get(0) : null;
    }

    @Override
    public boolean existsByName(String name) {
        return null != executeQuerySQL(EXISTS_BY_NAME, String.class, new Object[]{
                getParam(name, String.class)});
    }

    @Override
    public List<ModelTO> filterServicesByName(String name) {
        String param = DBUtils.likeSanitize(name) + "%";
        return executeQuerySQL(FILTER_BY_NAME, NAME_MAPPER, new Object[]{param});
    }

    @Override
    public List<ModelTO> filterServicesByNameAndMaster(String name, String... fio) {
        String criteriaPart = QueryCriteriaFactory.buildSQL(QueryCriteriaType.SERVICE_NAME_MASTER, name, fio);
        return executeQuerySQL(FILTER_BY_NAME_MASTER + criteriaPart, NAME_MAPPER, null);
    }
}
