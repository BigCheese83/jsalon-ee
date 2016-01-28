package ru.bigcheese.jsalon.ee.dao.jdbc;

import ru.bigcheese.jsalon.core.model.Discount;
import ru.bigcheese.jsalon.ee.dao.DiscountDao;
import ru.bigcheese.jsalon.ee.dao.qualifier.JDBC;

import java.util.List;

import static ru.bigcheese.jsalon.ee.dao.jdbc.ModelMapper.DISCOUNT_MAPPER;

/**
 * Created by BigCheese on 19.03.15.
 */
@JDBC
public class DiscountDaoJdbc extends AbstractBaseDaoJdbc<Discount, Long>
        implements DiscountDao {

    /* SQL Queries */
    private static final String GENERATE_ID =    "SELECT nextval('discounts_id_seq')";
    private static final String INSERT_SQL =     "INSERT INTO discounts (id, name, value) VALUES (?, ?, ?)";
    private static final String UPDATE_SQL =     "UPDATE discounts SET name = ?, value = ? WHERE id = ?";
    private static final String DELETE_SQL =     "DELETE FROM discounts WHERE id = ?";
    private static final String SELECT_ALL =     "SELECT * FROM discounts";
    private static final String COUNT_ALL =      "SELECT count(*) FROM discounts";
    private static final String SELECT_BY_ID =   "SELECT * FROM discounts WHERE id = ?";
    private static final String SELECT_BY_NAME = "SELECT * FROM discounts WHERE name = ?";
    private static final String EXISTS_BY_ID =   "SELECT id FROM discounts WHERE id = ?";
    private static final String EXISTS_BY_NAME = "SELECT name FROM discounts WHERE name = ?";

    @Override
    public void persist(Discount model) {
        if (model == null) return;
        Long id = generateID(GENERATE_ID);
        executeUpdateSQL(INSERT_SQL, new Object[]
                    { getParam(id, Long.class),
                      getParam(model.getName(), String.class),
                      getParam(model.getValue(), Integer.class) });
        model.setId(id);
    }

    @Override
    public void update(Discount model) {
        if (model == null) return;
        executeUpdateSQL(UPDATE_SQL, new Object[]
                {getParam(model.getName(), String.class),
                        getParam(model.getValue(), Integer.class),
                        getParam(model.getId(), Long.class)});
    }

    @Override
    public void delete(Long id) {
        if (id == null) return;
        executeUpdateSQL(DELETE_SQL, new Object[]{id});
    }

    @Override
    public Discount findById(Long id) {
        if (id == null) return null;
        List<Discount> find = executeQuerySQL(SELECT_BY_ID, DISCOUNT_MAPPER, new Object[]{id});
        return !find.isEmpty() ? find.get(0) : null;
    }

    @Override
    public List<Discount> findAll() {
        return executeQuerySQL(SELECT_ALL, DISCOUNT_MAPPER, null);
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
    public Discount getDiscountByName(String name) {
        List<Discount> find = executeQuerySQL(SELECT_BY_NAME, DISCOUNT_MAPPER, new Object[]
                    { getParam(name, String.class) });
        return !find.isEmpty() ? find.get(0) : null;
    }

    @Override
    public boolean existsByName(String name) {
        return null != executeQuerySQL(EXISTS_BY_NAME, String.class, new Object[]{
                getParam(name, String.class)});
    }
}
