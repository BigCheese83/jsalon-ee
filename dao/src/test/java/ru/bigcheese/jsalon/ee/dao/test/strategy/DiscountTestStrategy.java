package ru.bigcheese.jsalon.ee.dao.test.strategy;

import org.junit.Assert;
import ru.bigcheese.jsalon.core.model.Discount;
import ru.bigcheese.jsalon.ee.dao.DiscountDao;
import ru.bigcheese.jsalon.ee.dao.jdbc.DiscountDaoJdbc;
import ru.bigcheese.jsalon.ee.dao.jpa.DiscountDaoJpa;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.List;

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

    public void testGetDiscountsByName() {
        List<Discount> discounts = discountDao.getDiscountsByName("Золотая");
        Assert.assertEquals(1, discounts.size());
    }

    public void testFindAll() {
        List<Discount> discounts = discountDao.findAll();
        Assert.assertEquals(NUMBER_OF_DISCOUNTS, discounts.size());
    }

    public void testPersist() {
        Discount discount = new Discount(null, "Платиновая", 15);
        beginTransaction();
        discountDao.persist(discount);
        commitTransaction();
        Assert.assertEquals(NUMBER_OF_DISCOUNTS + 1, discountDao.findAll().size());
    }

    public void testUpdate() {
        Discount discount = discountDao.findById(2L);
        discount.setName("GOLD");
        beginTransaction();
        discountDao.update(discount);
        commitTransaction();
        Assert.assertEquals("GOLD", discountDao.findById(2L).getName());
    }

    public void testDelete() {
        Discount discount = discountDao.findById(3L);
        beginTransaction();
        discountDao.delete(discount);
        commitTransaction();
        Assert.assertEquals(NUMBER_OF_DISCOUNTS - 1, discountDao.findAll().size());
    }

    public void testFindById() {
        Discount discount = discountDao.findById(4L);
        Assert.assertEquals("VIP", discount.getName());
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
