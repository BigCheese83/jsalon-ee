package ru.bigcheese.jsalon.ee.dao.test.strategy;

import ru.bigcheese.jsalon.core.model.bind.PostServiceBind;
import ru.bigcheese.jsalon.ee.dao.PostServiceDao;
import ru.bigcheese.jsalon.ee.dao.jdbc.PostServiceDaoJdbc;
import ru.bigcheese.jsalon.ee.dao.jpa.PostServiceDaoJpa;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by BigCheese on 18.02.16.
 */
public class PostServiceTestStrategy {

    private static final int NUMBER_OF_BINDS = 3;
    private PostServiceDao psDao;

    public PostServiceTestStrategy(String name) {
        if ("jdbc".equals(name)) {
            psDao = new PostServiceDaoJdbc();
        } else if ("jpa".equals(name)) {
            psDao = new PostServiceDaoJpa();
        }
    }

    public void init(EntityManager em) {
        if (psDao instanceof PostServiceDaoJpa) {
            ((PostServiceDaoJpa)psDao).setEntityManager(em);
        }
    }

    public void init(DataSource ds) {
        if (psDao instanceof PostServiceDaoJdbc) {
            ((PostServiceDaoJdbc)psDao).setDataSource(ds);
        }
    }

    public void testFindAll() {
        List<PostServiceBind> binds = psDao.findAll();
        assertEquals(NUMBER_OF_BINDS, binds.size());
    }

    public void testPersist() {
        PostServiceBind psbind = new PostServiceBind();
        psbind.setPostId(4L);
        psbind.setServiceId(4L);
        beginTransaction();
        psDao.persist(psbind);
        commitTransaction();
        assertEquals(NUMBER_OF_BINDS + 1, psDao.countAll().intValue());
    }

    public void testUpdate() {
        PostServiceBind psbind = psDao.findById(1L);
        psbind.setPostId(1L);
        beginTransaction();
        psDao.update(psbind);
        commitTransaction();
        assertEquals(1L, psDao.findById(1L).getPostId().longValue());
    }

    public void testDelete() {
        beginTransaction();
        psDao.delete(2L);
        commitTransaction();
        assertEquals(NUMBER_OF_BINDS - 1, psDao.countAll().intValue());
    }

    public void testFindById() {
        PostServiceBind psbind = psDao.findById(3L);
        assertEquals(1L, psbind.getPostId().longValue());
        assertEquals(5L, psbind.getServiceId().longValue());
    }

    public void testExistsById() {
        assertTrue(psDao.existsById(1L));
        assertFalse(psDao.existsById(11L));
    }

    private void beginTransaction() {
        if (psDao instanceof PostServiceDaoJpa) {
            ((PostServiceDaoJpa)psDao).getEntityManager().getTransaction().begin();
        }
    }

    private void commitTransaction() {
        if (psDao instanceof PostServiceDaoJpa) {
            ((PostServiceDaoJpa)psDao).getEntityManager().getTransaction().commit();
        }
    }
}
