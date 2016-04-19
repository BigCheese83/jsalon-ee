package ru.bigcheese.jsalon.ee.dao.test.jdbc;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.bigcheese.jsalon.ee.dao.test.strategy.PostServiceTestStrategy;

/**
 * Created by BigCheese on 18.02.16.
 */
public class PostServiceDaoJdbcTest extends AbstractDaoJdbcTest {

    private PostServiceTestStrategy testStrategy = new PostServiceTestStrategy("jdbc");

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
    public void testExistById() {
        testStrategy.testExistsById();
    }
}
