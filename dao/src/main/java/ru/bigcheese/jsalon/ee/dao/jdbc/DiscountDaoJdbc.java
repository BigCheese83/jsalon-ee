package ru.bigcheese.jsalon.ee.dao.jdbc;

import ru.bigcheese.jsalon.core.model.Discount;
import ru.bigcheese.jsalon.ee.dao.DiscountDao;
import ru.bigcheese.jsalon.ee.dao.qualifier.JDBC;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static ru.bigcheese.jsalon.ee.dao.jdbc.ModelMapper.*;

/**
 * Created by BigCheese on 19.03.15.
 */
@JDBC
public class DiscountDaoJdbc extends AbstractBaseDaoJdbc<Discount, Long>
        implements DiscountDao {

    private static final String SEQ_NAME = "discounts_id_seq";
    /* SQL Queries */
    private static final String INSERT_SQL =     "INSERT INTO discounts (id, name, value) VALUES (?, ?, ?)";
    private static final String UPDATE_SQL =     "UPDATE discounts SET name = ?, value = ? WHERE id = ?";
    private static final String DELETE_SQL =     "DELETE FROM discounts WHERE id = ?";
    private static final String SELECT_ALL =     "SELECT * FROM discounts";
    private static final String COUNT_ALL =      "SELECT count(*) FROM discounts";
    private static final String SELECT_BY_ID =   "SELECT * FROM discounts WHERE id = ?";
    private static final String SELECT_BY_NAME = "SELECT * FROM discounts WHERE name = ?";

    @Override
    public void persist(Discount model) {
        if (model == null) return;
        Long id = generateSeqID(SEQ_NAME);
        super.executeUpdateSQL(INSERT_SQL, new Object[]
                        { getParam(id, Long.class),
                          getParam(model.getName(), String.class),
                          getParam(model.getValue(), Integer.class) });
        model.setId(id);
    }

    @Override
    public void update(Discount model) {
        if (model == null) return;
        super.executeUpdateSQL(UPDATE_SQL, new Object[]
                        { getParam(model.getName(), String.class),
                          getParam(model.getValue(), Integer.class),
                          getParam(model.getId(), Long.class) });
    }

    @Override
    public void delete(Discount model) {
        if (model == null) return;
        super.executeUpdateSQL(DELETE_SQL, new Object[]
                        { getParam(model.getId(), Long.class) });
    }

    @Override
    public Discount findById(Long id) {
        if (id == null) return null;
        List<Discount> find = super.executeQuerySQL( SELECT_BY_ID, DISCOUNT_MAPPER, new Object[]{id} );
        return !find.isEmpty() ? find.get(0) : null;
    }

    @Override
    public List<Discount> findAll() {
        return super.executeQuerySQL(SELECT_ALL, DISCOUNT_MAPPER, null);
    }

    @Override
    public Long countAll() {
        return super.executeSingleLongQuerySQL(COUNT_ALL, null);
    }

    @Override
    public List<Discount> getDiscountsByName(String name) {
        return super.executeQuerySQL(SELECT_BY_NAME, DISCOUNT_MAPPER, new Object[]
                        { getParam(name, String.class) });
    }
}
