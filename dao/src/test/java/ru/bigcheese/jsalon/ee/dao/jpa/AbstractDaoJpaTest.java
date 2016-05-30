package ru.bigcheese.jsalon.ee.dao.jpa;

import ru.bigcheese.jsalon.core.Constants;
import ru.bigcheese.jsalon.core.util.DBUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

/**
 * Created by BigCheese on 12.03.15.
 */
public abstract class AbstractDaoJpaTest {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory(Constants.TEST_JPA_PU);
    EntityManager em;

    void initialize() throws Exception {
        em = emf.createEntityManager();
        runScript("create.sql");
        runScript("insert.sql");
    }

    void close() throws Exception {
        em.clear();
        runScript("drop.sql");
        em.close();
    }

    private void runScript(String filename) throws Exception {
        List<String> queries = DBUtils.parseSQLQueries(filename);
        if (queries.size() > 0) {
            em.getTransaction().begin();
            for (String query : queries) {
                em.createNativeQuery(query).executeUpdate();
            }
            em.getTransaction().commit();
        }
    }
}
