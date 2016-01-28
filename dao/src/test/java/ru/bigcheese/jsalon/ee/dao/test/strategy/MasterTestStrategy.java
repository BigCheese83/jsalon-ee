package ru.bigcheese.jsalon.ee.dao.test.strategy;

import ru.bigcheese.jsalon.core.model.*;
import ru.bigcheese.jsalon.ee.dao.MasterDao;
import ru.bigcheese.jsalon.ee.dao.jdbc.MasterDaoJdbc;
import ru.bigcheese.jsalon.ee.dao.jpa.MasterDaoJpa;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by BigCheese on 25.12.15.
 */
public class MasterTestStrategy {

    private static final int NUMBER_OF_MASTERS = 2;
    private MasterDao masterDao;

    public MasterTestStrategy(String name) {
        if ("jdbc".equals(name)) {
            masterDao = new MasterDaoJdbc();
        } else if ("jpa".equals(name)) {
            masterDao = new MasterDaoJpa();
        }
    }

    public void init(EntityManager em) {
        if (masterDao instanceof MasterDaoJpa) {
            ((MasterDaoJpa)masterDao).setEntityManager(em);
        }
    }

    public void init(DataSource ds) {
        if (masterDao instanceof MasterDaoJdbc) {
            ((MasterDaoJdbc)masterDao).setDataSource(ds);
        }
    }

    public void testFindAll() {
        List<Master> masters = masterDao.findAll();
        assertEquals(NUMBER_OF_MASTERS, masters.size());
    }

    public void testPersist() {
        Master master = getMaster();
        beginTransaction();
        masterDao.persist(master);
        commitTransaction();
        assertEquals(NUMBER_OF_MASTERS + 1, masterDao.countAll().intValue());
    }

    public void testUpdate() {
        Master master = masterDao.findById(1L);
        master.setSurname("Разгильдяев");
        master.setBusy(true);
        master.getContact().setIcq("666-000-555");
        beginTransaction();
        masterDao.update(master);
        commitTransaction();
        assertEquals("Разгильдяев", masterDao.findById(1L).getSurname());
    }

    public void testDelete() {
        beginTransaction();
        masterDao.delete(2L);
        commitTransaction();
        assertEquals(NUMBER_OF_MASTERS - 1, masterDao.countAll().intValue());
    }

    public void testFindById() {
        Master master = masterDao.findById(1L);
        assertEquals("Иван", master.getName());
    }

    public void testExistsById() {
        assertTrue(masterDao.existsById(1L));
        assertFalse(masterDao.existsById(11L));
    }

    public void testExistsByPassport() {
        assertTrue(masterDao.existsByPassport(masterDao.findById(2L).getPassport()));
        assertFalse(masterDao.existsByPassport(getMaster().getPassport()));
    }

    private void beginTransaction() {
        if (masterDao instanceof MasterDaoJpa) {
            ((MasterDaoJpa)masterDao).getEntityManager().getTransaction().begin();
        }
    }

    private void commitTransaction() {
        if (masterDao instanceof MasterDaoJpa) {
            ((MasterDaoJpa)masterDao).getEntityManager().getTransaction().commit();
        }
    }

    private Master getMaster() {
        Master master = new Master();
        master.setSurname("Ботов");
        master.setName("Дмитрий");
        master.setBirthDate(new GregorianCalendar(1988, 4, 8).getTime());
        master.setHiringDate(new GregorianCalendar(2015, 8, 2).getTime());
        Passport passport = new Passport();
        passport.setCountry("Россия");
        passport.setSeries("6079");
        passport.setNumber("012345");
        passport.setIssuedBy("ОВД г. Тольятти");
        passport.setIssueDate(new GregorianCalendar(2006, 4, 21).getTime());
        master.setPassport(passport);
        master.setPost(new Post(2L, "Парикмахер"));
        Address address = new Address();
        address.setCountry("Россия");
        address.setCity("Москва");
        address.setStreet("9-я Индустриальная");
        address.setHouse("54");
        address.setFlat("256");
        master.setRegAddress(address);
        master.setContact(new Contact("89226873920"));
        return master;
    }
}
