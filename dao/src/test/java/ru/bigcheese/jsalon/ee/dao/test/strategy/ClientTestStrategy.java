package ru.bigcheese.jsalon.ee.dao.test.strategy;

import ru.bigcheese.jsalon.core.model.*;
import ru.bigcheese.jsalon.ee.dao.ClientDao;
import ru.bigcheese.jsalon.ee.dao.jdbc.ClientDaoJdbc;
import ru.bigcheese.jsalon.ee.dao.jpa.ClientDaoJpa;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by BigCheese on 12.02.16.
 */
public class ClientTestStrategy {

    private static final int NUMBER_OF_CLIENTS = 2;
    private ClientDao clientDao;

    public ClientTestStrategy(String name) {
        if ("jdbc".equals(name)) {
            clientDao = new ClientDaoJdbc();
        } else if ("jpa".equals(name)) {
            clientDao = new ClientDaoJpa();
        }
    }

    public void init(EntityManager em) {
        if (clientDao instanceof ClientDaoJpa) {
            ((ClientDaoJpa)clientDao).setEntityManager(em);
        }
    }

    public void init(DataSource ds) {
        if (clientDao instanceof ClientDaoJdbc) {
            ((ClientDaoJdbc)clientDao).setDataSource(ds);
        }
    }

    public void testFindAll() {
        List<Client> clients = clientDao.findAll();
        assertEquals(NUMBER_OF_CLIENTS, clients.size());
    }

    public void testPersist() {
        Client client = getClient();
        beginTransaction();
        clientDao.persist(client);
        commitTransaction();
        assertEquals(NUMBER_OF_CLIENTS + 1, clientDao.countAll().intValue());
    }

    public void testUpdate() {
        Client client = clientDao.findById(1L);
        client.setSurname("Бакомчев");
        client.setInBlackList(true);
        client.getContact().setIcq("500-000-555");
        beginTransaction();
        clientDao.update(client);
        commitTransaction();
        assertEquals("Бакомчев", clientDao.findById(1L).getSurname());
    }

    public void testDelete() {
        beginTransaction();
        clientDao.delete(2L);
        commitTransaction();
        assertEquals(NUMBER_OF_CLIENTS - 1, clientDao.countAll().intValue());
    }

    public void testFindById() {
        Client client = clientDao.findById(1L);
        assertEquals("Сергей", client.getName());
    }

    public void testExistsById() {
        assertTrue(clientDao.existsById(1L));
        assertFalse(clientDao.existsById(11L));
    }

    public void testExistsByPassport() {
        assertTrue(clientDao.existsByPassport(clientDao.findById(2L).getPassport()));
        assertFalse(clientDao.existsByPassport(getClient().getPassport()));
    }

    public void testExistsByPhone() {
        assertTrue(clientDao.existsByPhone(clientDao.findById(2L).getContact().getPhone()));
        assertFalse(clientDao.existsByPhone(getClient().getContact().getPhone()));
    }

    private void beginTransaction() {
        if (clientDao instanceof ClientDaoJpa) {
            ((ClientDaoJpa)clientDao).getEntityManager().getTransaction().begin();
        }
    }

    private void commitTransaction() {
        if (clientDao instanceof ClientDaoJpa) {
            ((ClientDaoJpa)clientDao).getEntityManager().getTransaction().commit();
        }
    }

    private Client getClient() {
        Client client = new Client();
        client.setSurname("Попов");
        client.setName("Игорь");
        client.setBirthDate(new GregorianCalendar(1989, 8, 10).getTime());
        client.setRegistrationDate(new GregorianCalendar(2015, 4, 1).getTime());
        Passport passport = new Passport();
        passport.setCountry("Россия");
        passport.setSeries("5890");
        passport.setNumber("980456");
        passport.setIssuedBy("ОВД г.Тула");
        passport.setIssueDate(new GregorianCalendar(2007, 4, 21).getTime());
        client.setPassport(passport);
        client.setDiscount(new Discount(4L, "VIP", 20));
        Address address = new Address();
        address.setCountry("Россия");
        address.setCity("Москва");
        address.setStreet("9-я Индустриальная");
        address.setHouse("54");
        address.setFlat("256");
        client.setRegAddress(address);
        client.setContact(new Contact("89264590867"));
        return client;
    }
}
