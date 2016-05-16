package ru.bigcheese.jsalon.ee.dao.jdbc;

import ru.bigcheese.jsalon.core.model.*;
import ru.bigcheese.jsalon.core.util.ModelUtils;
import ru.bigcheese.jsalon.ee.dao.ClientDao;
import ru.bigcheese.jsalon.ee.dao.QueryCriteria;
import ru.bigcheese.jsalon.ee.dao.QueryCriteriaFactory;
import ru.bigcheese.jsalon.ee.dao.QueryCriteriaType;
import ru.bigcheese.jsalon.ee.dao.qualifier.JDBC;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ru.bigcheese.jsalon.ee.dao.jdbc.ModelMapper.*;

/**
 * Created by BigCheese on 11.02.16.
 */
@JDBC
public class ClientDaoJdbc extends PersonDaoJdbc<Client>
        implements ClientDao {

    /* SQL Queries */
    private static final String GENERATE_ID = "SELECT nextval('clients_id_seq')";

    private static final String INSERT_SQL =
            "INSERT INTO clients (id, surname, name, patronymic, birth_date, registration_date, id_passport, id_reg_address, id_live_address, id_contact, id_discount, in_black) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String UPDATE_SQL =
            "UPDATE clients SET surname = ?, name = ?, patronymic = ?, birth_date = ?, registration_date = ?, id_discount = ?, in_black = ? WHERE id = ?";

    private static final String DELETE_SQL = "DELETE FROM clients WHERE id = ?";
    private static final String SELECT_ALL = "SELECT * FROM clients";
    private static final String SELECT_ALL_DISCOUNTS = "SELECT * FROM discounts";
    private static final String COUNT_ALL =  "SELECT count(*) FROM clients";

    private static final String SELECT_BY_ID = "SELECT * FROM clients WHERE id = ?";
    private static final String SELECT_BY_ID_DISCOUNT = "SELECT * FROM discounts WHERE id = ?";

    private static final String EXISTS_BY_ID =   "SELECT id FROM clients WHERE id = ?";
    private static final String EXISTS_BY_PASSPORT = "SELECT id FROM passport WHERE series = ? AND num = ? AND bind_by = ?";
    private static final String EXISTS_BY_PHONE = "SELECT id FROM contacts WHERE phone = ? AND bind_by = ?";

    private static final String SELECT_BY_ID_FETCH =
            "SELECT c.id, c.birth_date, c.in_black, c.name, c.patronymic, c.registration_date, c.surname, c.id_contact, c.id_discount, c.id_live_address, c.id_reg_address, c.id_passport, " +
                "cont.email, cont.facebook, cont.icq, cont.phone, cont.skype, cont.twitter, cont.vk, d.name as disc_name, d.value as disc_value, " +
                "live.city as live_city, live.country as live_country, live.district as live_district, live.flat as live_flat, live.house as live_house, live.section as live_section, live.street as live_street, live.zip as live_zip, " +
                "reg.city as reg_city, reg.country as reg_country, reg.district as reg_district, reg.flat as reg_flat, reg.house as reg_house, reg.section as reg_section, reg.street as reg_street, reg.zip as reg_zip, " +
                "p.country as passp_country, p.issue_date, p.issued_by, p.num, p.series, p.subdivision " +
            "FROM {oj clients c LEFT OUTER JOIN discounts d ON (d.id = c.id_discount) LEFT OUTER JOIN address live ON (live.id = c.id_live_address)}, passport p, address reg, contacts cont " +
            "WHERE ((c.id = ?) AND (((cont.id = c.id_contact) AND (reg.id = c.id_reg_address)) AND (p.id = c.id_passport)))";

    private static final String SELECT_NAMES = "SELECT surname, name, patronymic FROM clients";

    @Override
    public void persist(Client model) {
        if (model == null) return;
        Map<String,Long> ids = insertPersonData(model);
        Long id = generateID(GENERATE_ID);
        executeUpdateSQL(INSERT_SQL, new Object[]{
                getParam(id, Long.class),
                getParam(model.getSurname(), String.class),
                getParam(model.getName(), String.class),
                getParam(model.getPatronymic(), String.class),
                getParam(model.getBirthDate(), Date.class),
                getParam(model.getRegistrationDate(), Date.class),
                getParam(ids.get(PASSPORT_ID_KEY), Long.class),
                getParam(ids.get(REG_ADDRESS_ID_KEY), Long.class),
                getParam(ids.get(LIVE_ADDRESS_ID_KEY), Long.class),
                getParam(ids.get(CONTACTS_ID_KEY), Long.class),
                getParam(model.getDiscount() != null ? model.getDiscount().getId() : null, Long.class),
                getParam(model.isInBlackList(), Boolean.class) });

        model.setId(id);
        setIdsForPerson(model, ids);
    }

    @Override
    public void update(Client model) {
        if (model == null) return;
        updatePersonData(model);
        executeUpdateSQL(UPDATE_SQL, new Object[]{
                getParam(model.getSurname(), String.class),
                getParam(model.getName(), String.class),
                getParam(model.getPatronymic(), String.class),
                getParam(model.getBirthDate(), Date.class),
                getParam(model.getRegistrationDate(), Date.class),
                getParam(model.getDiscount() != null ? model.getDiscount().getId() : null, Long.class),
                getParam(model.isInBlackList(), Boolean.class),
                getParam(model.getId(), Long.class)});
    }

    @Override
    public void delete(Long id) {
        if (id == null) return;
        List<Client> find = executeQuerySQL(SELECT_BY_ID, CLIENT_MAPPER, new Object[]{id});
        if (!find.isEmpty()) {
            Client client = find.get(0);
            executeUpdateSQL(DELETE_SQL, new Object[]{client.getId()});
            deletePersonData(client);
        }
    }

    @Override
    public Client findById(Long id) {
        if (id == null) return null;
        List<Client> find = executeQuerySQL(SELECT_BY_ID_FETCH, CLIENT_FETCH_MAPPER, new Object[]{id});
        return !find.isEmpty() ? find.get(0) : null;
    }

    @Override
    public List<Client> findAll() {
        return fetchAllClients(executeQuerySQL(SELECT_ALL, CLIENT_MAPPER, null));
    }

    @Override
    public Long countAll() {
        return executeQuerySQL(COUNT_ALL, Long.class, null);
    }

    @Override
    public boolean existsById(Long id) {
        return null != executeQuerySQL(EXISTS_BY_ID, Long.class, new Object[]
                { getParam(id, Long.class) } );
    }

    @Override
    public boolean existsByPhone(String phone) {
        return phone != null &&
                null != executeQuerySQL(EXISTS_BY_PHONE, Long.class, new Object[]
                        { getParam(phone, String.class),
                          BindModel.CLIENT.name() } );
    }

    @Override
    public boolean existsByPassport(Passport passport) {
        return passport != null &&
                null != executeQuerySQL(EXISTS_BY_PASSPORT, Long.class, new Object[]
                        { getParam(passport.getSeries(), String.class),
                          getParam(passport.getNumber(), String.class),
                          BindModel.CLIENT.name() } );
    }

    @Override
    public List<Client> findClientsByCriteria(QueryCriteria criteria) {
        String criteriaPart = criteria != null ? criteria.buildSQL() : "";
        return fetchClients(executeQuerySQL(SELECT_ALL + criteriaPart, CLIENT_MAPPER, null));
    }

    @Override
    public List<Client> findLimitClientsByCriteria(int count, QueryCriteria criteria) {
        String criteriaPart = criteria != null ? criteria.buildSQL() : "";
        return fetchClients(executeLimitQuerySQL(SELECT_ALL + criteriaPart, count, CLIENT_MAPPER, null));
    }

    @Override
    public List<String> filterClientsByNames(String... names) {
        String criteriaPart = QueryCriteriaFactory.buildSQL(QueryCriteriaType.PERSON_NAMES, names);
        return executeQuerySQL(SELECT_NAMES + criteriaPart, PERSON_NAMES_MAPPER, null);
    }

    private List<Client> fetchAllClients(List<Client> clients) {
        if (clients.isEmpty()) return clients;
        fetchAllPersons(clients);
        Map<Long, Discount> discounts = ModelUtils.transform(executeQuerySQL(SELECT_ALL_DISCOUNTS, DISCOUNT_MAPPER, null));
        for (Client client : clients) {
            if (client.getDiscount() != null) {
                client.setDiscount(discounts.get(client.getDiscount().getId()));
            }
        }
        return clients;
    }

    private List<Client> fetchClients(List<Client> clients) {
        fetchPersons(clients);
        for (Client client : clients) {
            if (client.getDiscount() != null) {
                List<Discount> discounts = executeQuerySQL(SELECT_BY_ID_DISCOUNT, DISCOUNT_MAPPER, new Object[]{client.getDiscount().getId()});
                if (!discounts.isEmpty()) {
                    client.setDiscount(discounts.get(0));
                }
            }
        }
        return clients;
    }
}
