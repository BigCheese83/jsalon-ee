package ru.bigcheese.jsalon.ee.dao.jdbc;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.bigcheese.jsalon.ee.dao.test.strategy.PostTestStrategy;

/**
 * Created by BigCheese on 29.04.15.
 */
public class PostDaoJdbcTest extends AbstractDaoJdbcTest {

    private PostTestStrategy testStrategy = new PostTestStrategy("jdbc");

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
    public void testGetPostsByName() {
        testStrategy.testGetPostByName();
    }
}
