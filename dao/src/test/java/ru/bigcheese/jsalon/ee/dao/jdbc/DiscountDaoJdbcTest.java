package ru.bigcheese.jsalon.ee.dao.jdbc;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.bigcheese.jsalon.ee.dao.test.strategy.DiscountTestStrategy;


/**
 * Created by BigCheese on 26.03.15.
 */
public class DiscountDaoJdbcTest extends AbstractDaoJdbcTest {

    private DiscountTestStrategy testStrategy = new DiscountTestStrategy("jdbc");

    @Before
    public void initialize() throws Exception {
        super.initialize();
        testStrategy.init(dataSource);
    }

    @After
    public void close() throws Exception {
        super.close();
    }

    @Test
    public void testFindById() {
        testStrategy.testFindById();
    }

    @Test
    public void testFindAll() {
        testStrategy.testFindAll();
    }

    @Test
    public void testPersist() {
        testStrategy.testPersist();
    }

    @Test
    public void testUpdate() {
        testStrategy.testUpdate();
    }

    @Test
    public void testDelete() {
        testStrategy.testDelete();
    }

    @Test
    public void testGetDiscountsByName() {
        testStrategy.testGetDiscountByName();
    }

    @Test
    public void testExistsById() {
        testStrategy.testExistsById();
    }

    @Test
    public void testExistsByName() {
        testStrategy.testExistsByName();
    }
}
