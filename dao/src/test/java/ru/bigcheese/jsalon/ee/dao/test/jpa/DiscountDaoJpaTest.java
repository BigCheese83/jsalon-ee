package ru.bigcheese.jsalon.ee.dao.test.jpa;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.bigcheese.jsalon.ee.dao.test.strategy.DiscountTestStrategy;


/**
 * Created by BigCheese on 19.03.15.
 */
public class DiscountDaoJpaTest extends AbstractDaoJpaTest {

    private DiscountTestStrategy testStrategy = new DiscountTestStrategy("jpa");

    @Before
    public void initialize() throws Exception {
        super.initialize();
        testStrategy.init(em);
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
        testStrategy.testGetDiscountsByName();
    }
}
