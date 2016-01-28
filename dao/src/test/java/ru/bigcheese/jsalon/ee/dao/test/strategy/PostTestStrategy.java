package ru.bigcheese.jsalon.ee.dao.test.strategy;

import ru.bigcheese.jsalon.core.model.Post;
import ru.bigcheese.jsalon.ee.dao.PostDao;
import ru.bigcheese.jsalon.ee.dao.jdbc.PostDaoJdbc;
import ru.bigcheese.jsalon.ee.dao.jpa.PostDaoJpa;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by BigCheese on 28.04.15.
 */
public class PostTestStrategy {

    private static final int NUMBER_OF_POSTS = 4;
    private PostDao postDao;

    public PostTestStrategy(String name) {
        if ("jdbc".equals(name)) {
            postDao = new PostDaoJdbc();
        } else if ("jpa".equals(name)) {
            postDao = new PostDaoJpa();
        }
    }

    public void init(EntityManager em) {
        if (postDao instanceof PostDaoJpa) {
            ((PostDaoJpa)postDao).setEntityManager(em);
        }
    }

    public void init(DataSource ds) {
        if (postDao instanceof PostDaoJdbc) {
            ((PostDaoJdbc)postDao).setDataSource(ds);
        }
    }

    public void testFindById() {
        Post post = postDao.findById(2L);
        assertEquals("Парикмахер", post.getName());
    }

    public void testFindAll() {
        List<Post> posts = postDao.findAll();
        assertEquals(NUMBER_OF_POSTS, posts.size());
    }

    public void testPersist() {
        Post post = new Post(null, "Мастер по маникюру");
        beginTransaction();
        postDao.persist(post);
        commitTransaction();
        assertEquals(NUMBER_OF_POSTS + 1, postDao.countAll().intValue());
    }

    public void testUpdate() {
        Post post = postDao.findById(3L);
        post.setName("Стилист");
        beginTransaction();
        postDao.update(post);
        commitTransaction();
        assertEquals("Стилист", postDao.findById(3L).getName());
    }

    public void testDelete() {
        beginTransaction();
        postDao.delete(4L);
        commitTransaction();
        assertEquals(NUMBER_OF_POSTS - 1, postDao.countAll().intValue());
    }

    public void testGetPostByName() {
        Post post = postDao.getPostByName("Массажист");
        assertNotNull(post);
    }

    private void beginTransaction() {
        if (postDao instanceof PostDaoJpa) {
            ((PostDaoJpa)postDao).getEntityManager().getTransaction().begin();
        }
    }

    private void commitTransaction() {
        if (postDao instanceof PostDaoJpa) {
            ((PostDaoJpa)postDao).getEntityManager().getTransaction().commit();
        }
    }
}
