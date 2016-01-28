package ru.bigcheese.jsalon.ee.dao.test.strategy;

import ru.bigcheese.jsalon.core.model.Discount;
import ru.bigcheese.jsalon.ee.dao.DiscountDao;
import ru.bigcheese.jsalon.ee.dao.jdbc.DiscountDaoJdbc;
import ru.bigcheese.jsalon.ee.dao.jpa.DiscountDaoJpa;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by BigCheese on 28.04.15.
 */
public class DiscountTestStrategy {

    private static final int NUMBER_OF_DISCOUNTS = 4;
    private DiscountDao discountDao;

    public DiscountTestStrategy(String name) {
        if ("jdbc".equals(name)) {
            discountDao = new DiscountDaoJdbc();
        } else if ("jpa".equals(name)) {
            discountDao = new DiscountDaoJpa();
        }
    }

    public void init(EntityManager em) {
        if (discountDao instanceof DiscountDaoJpa) {
            ((DiscountDaoJpa)discountDao).setEntityManager(em);
        }
    }
    public void init(DataSource ds) {
        if (discountDao instanceof DiscountDaoJdbc) {
            ((DiscountDaoJdbc)discountDao).setDataSource(ds);
        }
    }

    public void testGetDiscountByName() {
        Discount discount = discountDao.getDiscountByName("Золотая");
        assertNotNull(discount);
    }

    public void testFindAll() {
        List<Discount> discounts = discountDao.findAll();
        assertEquals(NUMBER_OF_DISCOUNTS, discounts.size());
    }

    public void testPersist() {
        Discount discount = new Discount(null, "Платиновая", 15);
        beginTransaction();
        discountDao.persist(discount);
        commitTransaction();
        assertEquals(NUMBER_OF_DISCOUNTS + 1, discountDao.countAll().intValue());
    }

    public void testUpdate() {
        Discount discount = discountDao.findById(2L);
        discount.setName("GOLD");
        beginTransaction();
        discountDao.update(discount);
        commitTransaction();
        assertEquals("GOLD", discountDao.findById(2L).getName());
    }

    public void testDelete() {
        beginTransaction();
        discountDao.delete(3L);
        commitTransaction();
        assertEquals(NUMBER_OF_DISCOUNTS - 1, discountDao.countAll().intValue());
    }

    public void testFindById() {
        Discount discount = discountDao.findById(4L);
        assertEquals("VIP", discount.getName());
    }

    public void testExistsById() {
        assertTrue(discountDao.existsById(2L));
        assertFalse(discountDao.existsById(20L));
    }

    public void testExistsByName() {
        assertTrue(discountDao.existsByName("VIP"));
        assertFalse(discountDao.existsByName("Unknown"));
    }

    private void beginTransaction() {
        if (discountDao instanceof DiscountDaoJpa) {
            ((DiscountDaoJpa)discountDao).getEntityManager().getTransaction().begin();
        }
    }

    private void commitTransaction() {
        if (discountDao instanceof DiscountDaoJpa) {
            ((DiscountDaoJpa)discountDao).getEntityManager().getTransaction().commit();
        }
    }
}
