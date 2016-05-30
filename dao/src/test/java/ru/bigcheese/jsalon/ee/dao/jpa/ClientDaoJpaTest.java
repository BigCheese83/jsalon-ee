package ru.bigcheese.jsalon.ee.dao.jpa;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.bigcheese.jsalon.ee.dao.test.strategy.ClientTestStrategy;

/**
 * Created by BigCheese on 12.02.16.
 */
public class ClientDaoJpaTest extends AbstractDaoJpaTest {

    private ClientTestStrategy testStrategy = new ClientTestStrategy("jpa");

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
    public void testExistById() {
        testStrategy.testExistsById();
    }

    @Test
    public void testExistsByPassport() {
        testStrategy.testExistsByPassport();
    }

    @Test
    public void testExistsByPhone() {
        testStrategy.testExistsByPhone();
    }
}
