package ru.bigcheese.jsalon.ee.dao.jdbc;

import ru.bigcheese.jsalon.core.model.*;
import ru.bigcheese.jsalon.core.util.ModelUtils;
import ru.bigcheese.jsalon.ee.dao.MasterDao;
import ru.bigcheese.jsalon.ee.dao.QueryCriteria;
import ru.bigcheese.jsalon.ee.dao.qualifier.JDBC;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static ru.bigcheese.jsalon.ee.dao.jdbc.ModelMapper.*;

/**
 * Created by BigCheese on 28.12.15.
 */
@JDBC
public class MasterDaoJdbc extends AbstractBaseDaoJdbc<Master, Long>
        implements MasterDao {

    /* SQL Queries */
    private static final String GENERATE_ID =          "SELECT nextval('masters_id_seq')";
    private static final String GENERATE_ID_PASSPORT = "SELECT nextval('passport_id_seq')";
    private static final String GENERATE_ID_ADDRESS =  "SELECT nextval('address_id_seq')";
    private static final String GENERATE_ID_CONTACT =  "SELECT nextval('contacts_id_seq')";

    private static final String INSERT_SQL =
            "INSERT INTO masters (id, surname, name, patronymic, birth_date, hiring_date, id_passport, id_post, id_reg_address, id_live_address, id_contact, busy) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String INSERT_SQL_PASSPORT =
            "INSERT INTO passport (id, series, num, issued_by, issue_date, subdivision, country, bind_by) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String INSERT_SQL_ADDRESS =
            "INSERT INTO address (id, country, district, city, street, house, section, flat, zip) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String INSERT_SQL_CONTACT =
            "INSERT INTO contacts (id, phone, email, vk, skype, facebook, twitter, icq, bind_by) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String UPDATE_SQL =
            "UPDATE masters SET surname = ?, name = ?, patronymic = ?, birth_date = ?, hiring_date = ?, id_post = ?, busy = ? WHERE id = ?";
    private static final String UPDATE_SQL_PASSPORT =
            "UPDATE passport SET series = ?, num = ?, issued_by = ?, issue_date = ?, subdivision = ?, country = ? WHERE id = ?";
    private static final String UPDATE_SQL_ADDRESS =
            "UPDATE address SET country = ?, district = ?, city = ?, street = ?, house = ?, section = ?, flat = ?, zip = ? WHERE id = ?";
    private static final String UPDATE_SQL_CONTACT =
            "UPDATE contacts SET phone = ?, email = ?, vk = ?, skype = ?, facebook = ?, twitter = ?, icq = ? WHERE id = ?";

    private static final String DELETE_SQL =          "DELETE FROM masters WHERE id = ?";
    private static final String DELETE_SQL_PASSPORT = "DELETE FROM passport WHERE id = ?";
    private static final String DELETE_SQL_ADDRESS =  "DELETE FROM address WHERE id = ?";
    private static final String DELETE_SQL_CONTACT =  "DELETE FROM contacts WHERE id = ?";

    private static final String SELECT_ALL =           "SELECT * FROM masters";
    private static final String SELECT_ALL_PASSPORTS = "SELECT * FROM passport";
    private static final String SELECT_ALL_POSTS =     "SELECT * FROM posts";
    private static final String SELECT_ALL_ADDRESSES = "SELECT * FROM address";
    private static final String SELECT_ALL_CONTACTS =  "SELECT * FROM contacts";
    private static final String COUNT_ALL = "SELECT count(*) FROM masters";

    private static final String SELECT_BY_ID_FETCH =
            "SELECT m.id, m.birth_date, m.busy, m.hiring_date, m.name, m.patronymic, m.surname, m.id_contact, m.id_live_address, m.id_post, m.id_reg_address, m.id_passport, " +
                "c.email, c.facebook, c.icq, c.phone, c.skype, c.twitter, c.vk, post.name as post_name, " +
                "live.city as live_city, live.country as live_country, live.district as live_district, live.flat as live_flat, live.house as live_house, live.section as live_section, live.street as live_street, live.zip as live_zip, " +
                "reg.city as reg_city, reg.country as reg_country, reg.district as reg_district, reg.flat as reg_flat, reg.house as reg_house, reg.section as reg_section, reg.street as reg_street, reg.zip as reg_zip, " +
                "p.country as passp_country, p.issue_date, p.issued_by, p.num, p.series, p.subdivision " +
            "FROM {oj masters m LEFT OUTER JOIN address live ON (live.id = m.id_live_address)}, passport p, address reg, posts post, contacts c " +
            "WHERE ((m.id = ?) AND ((((c.id = m.id_contact) AND (post.id = m.id_post)) AND (reg.id = m.id_reg_address)) AND (p.id = m.id_passport)))";

    private static final String SELECT_BY_ID = "SELECT * FROM masters WHERE id = ?";
    private static final String SELECT_BY_ID_PASSPORT = "SELECT * FROM passport WHERE id = ?";
    private static final String SELECT_BY_ID_POST =     "SELECT * FROM posts WHERE id = ?";
    private static final String SELECT_BY_ID_ADDRESS =  "SELECT * FROM address WHERE id = ?";
    private static final String SELECT_BY_ID_CONTACT =  "SELECT * FROM contacts WHERE id = ?";

    private static final String EXISTS_BY_ID =   "SELECT id FROM masters WHERE id = ?";
    private static final String EXISTS_BY_PASSPORT = "SELECT id FROM passport WHERE series = ? AND num = ? AND bind_by = ?";

    @Override
    public void persist(Master model) {
        if (model == null) return;
        Long passportId = null;
        Long regAddrId = null;
        Long liveAddrId = null;
        Long contactId = null;

        if (model.getPassport() != null) {
            passportId = generateID(GENERATE_ID_PASSPORT);
            executeUpdateSQL(INSERT_SQL_PASSPORT, new Object[]{
                    getParam(passportId, Long.class),
                    getParam(model.getPassport().getSeries(), String.class),
                    getParam(model.getPassport().getNumber(), String.class),
                    getParam(model.getPassport().getIssuedBy(), String.class),
                    getParam(model.getPassport().getIssueDate(), Date.class),
                    getParam(model.getPassport().getSubdivision(), String.class),
                    getParam(model.getPassport().getCountry(), String.class),
                    BindModel.MASTER.name() });
        }
        if (model.getRegAddress() != null) {
            regAddrId = generateID(GENERATE_ID_ADDRESS);
            executeUpdateSQL(INSERT_SQL_ADDRESS, new Object[]{
                    getParam(regAddrId, Long.class),
                    getParam(model.getRegAddress().getCountry(), String.class),
                    getParam(model.getRegAddress().getDistrict(), String.class),
                    getParam(model.getRegAddress().getCity(), String.class),
                    getParam(model.getRegAddress().getStreet(), String.class),
                    getParam(model.getRegAddress().getHouse(), String.class),
                    getParam(model.getRegAddress().getSection(), String.class),
                    getParam(model.getRegAddress().getFlat(), String.class),
                    getParam(model.getRegAddress().getZip(), String.class) });
        }
        if (model.getLiveAddress() != null) {
            liveAddrId = generateID(GENERATE_ID_ADDRESS);
            executeUpdateSQL(INSERT_SQL_ADDRESS, new Object[]{
                    getParam(liveAddrId, Long.class),
                    getParam(model.getLiveAddress().getCountry(), String.class),
                    getParam(model.getLiveAddress().getDistrict(), String.class),
                    getParam(model.getLiveAddress().getCity(), String.class),
                    getParam(model.getLiveAddress().getStreet(), String.class),
                    getParam(model.getLiveAddress().getHouse(), String.class),
                    getParam(model.getLiveAddress().getSection(), String.class),
                    getParam(model.getLiveAddress().getFlat(), String.class),
                    getParam(model.getLiveAddress().getZip(), String.class) });
        }
        if (model.getContact() != null) {
            contactId = generateID(GENERATE_ID_CONTACT);
            executeUpdateSQL(INSERT_SQL_CONTACT, new Object[]{
                    getParam(contactId, Long.class),
                    getParam(model.getContact().getPhone(), String.class),
                    getParam(model.getContact().getEmail(), String.class),
                    getParam(model.getContact().getVk(), String.class),
                    getParam(model.getContact().getSkype(), String.class),
                    getParam(model.getContact().getFacebook(), String.class),
                    getParam(model.getContact().getTwitter(), String.class),
                    getParam(model.getContact().getIcq(), String.class),
                    BindModel.MASTER.name() });
        }
        Long id = generateID(GENERATE_ID);
        executeUpdateSQL(INSERT_SQL, new Object[]{
                getParam(id, Long.class),
                getParam(model.getSurname(), String.class),
                getParam(model.getName(), String.class),
                getParam(model.getPatronymic(), String.class),
                getParam(model.getBirthDate(), Date.class),
                getParam(model.getHiringDate(), Date.class),
                getParam(passportId, Long.class),
                getParam(model.getPost() != null ? model.getPost().getId() : null, Long.class),
                getParam(regAddrId, Long.class),
                getParam(liveAddrId, Long.class),
                getParam(contactId, Long.class),
                getParam(model.isBusy(), Boolean.class)});

        model.setId(id);
        if (model.getPassport() != null) {
            model.getPassport().setId(passportId);
        }
        if (model.getRegAddress() != null) {
            model.getRegAddress().setId(regAddrId);
        }
        if (model.getLiveAddress() != null) {
            model.getLiveAddress().setId(liveAddrId);
        }
        if (model.getContact() != null) {
            model.getContact().setId(contactId);
        }
    }

    @Override
    public void update(Master model) {
        if (model == null) return;
        if (model.getPassport() != null) {
            executeUpdateSQL(UPDATE_SQL_PASSPORT, new Object[]{
                    getParam(model.getPassport().getSeries(), String.class),
                    getParam(model.getPassport().getNumber(), String.class),
                    getParam(model.getPassport().getIssuedBy(), String.class),
                    getParam(model.getPassport().getIssueDate(), Date.class),
                    getParam(model.getPassport().getSubdivision(), String.class),
                    getParam(model.getPassport().getCountry(), String.class),
                    getParam(model.getPassport().getId(), Long.class)});
        }
        if (model.getRegAddress() != null) {
            executeUpdateSQL(UPDATE_SQL_ADDRESS, new Object[]{
                    getParam(model.getRegAddress().getCountry(), String.class),
                    getParam(model.getRegAddress().getDistrict(), String.class),
                    getParam(model.getRegAddress().getCity(), String.class),
                    getParam(model.getRegAddress().getStreet(), String.class),
                    getParam(model.getRegAddress().getHouse(), String.class),
                    getParam(model.getRegAddress().getSection(), String.class),
                    getParam(model.getRegAddress().getFlat(), String.class),
                    getParam(model.getRegAddress().getZip(), String.class),
                    getParam(model.getRegAddress().getId(), Long.class)});
        }
        if (model.getLiveAddress() != null) {
            executeUpdateSQL(UPDATE_SQL_ADDRESS, new Object[]{
                    getParam(model.getLiveAddress().getCountry(), String.class),
                    getParam(model.getLiveAddress().getDistrict(), String.class),
                    getParam(model.getLiveAddress().getCity(), String.class),
                    getParam(model.getLiveAddress().getStreet(), String.class),
                    getParam(model.getLiveAddress().getHouse(), String.class),
                    getParam(model.getLiveAddress().getSection(), String.class),
                    getParam(model.getLiveAddress().getFlat(), String.class),
                    getParam(model.getLiveAddress().getZip(), String.class),
                    getParam(model.getLiveAddress().getId(), Long.class)});
        }
        if (model.getContact() != null) {
            executeUpdateSQL(UPDATE_SQL_CONTACT, new Object[]{
                    getParam(model.getContact().getPhone(), String.class),
                    getParam(model.getContact().getEmail(), String.class),
                    getParam(model.getContact().getVk(), String.class),
                    getParam(model.getContact().getSkype(), String.class),
                    getParam(model.getContact().getFacebook(), String.class),
                    getParam(model.getContact().getTwitter(), String.class),
                    getParam(model.getContact().getIcq(), String.class),
                    getParam(model.getContact().getId(), Long.class)});
        }
        executeUpdateSQL(UPDATE_SQL, new Object[]{
                getParam(model.getSurname(), String.class),
                getParam(model.getName(), String.class),
                getParam(model.getPatronymic(), String.class),
                getParam(model.getBirthDate(), Date.class),
                getParam(model.getHiringDate(), Date.class),
                getParam(model.getPost() != null ? model.getPost().getId() : null, Long.class),
                getParam(model.isBusy(), Boolean.class),
                getParam(model.getId(), Long.class) });
    }

    @Override
    public void delete(Long id) {
        if (id == null) return;
        List<Master> list = executeQuerySQL(SELECT_BY_ID, MASTER_MAPPER, new Object[]{id});
        if (list.isEmpty()) return;
        Master master = list.get(0);
        executeUpdateSQL(DELETE_SQL, new Object[]
                    { getParam(master.getId(), Long.class) });
        if (master.getPassport() != null) {
            executeUpdateSQL(DELETE_SQL_PASSPORT, new Object[]
                    { getParam(master.getPassport().getId(), Long.class)} );
        }
        if (master.getRegAddress() != null) {
            executeUpdateSQL(DELETE_SQL_ADDRESS, new Object[]
                    { getParam(master.getRegAddress().getId(), Long.class)} );
        }
        if (master.getLiveAddress() != null) {
            executeUpdateSQL(DELETE_SQL_ADDRESS, new Object[]
                    { getParam(master.getLiveAddress().getId(), Long.class)} );
        }
        if (master.getContact() != null) {
            executeUpdateSQL(DELETE_SQL_CONTACT, new Object[]
                    { getParam(master.getContact().getId(), Long.class)});
        }
    }

    @Override
    public Master findById(Long id) {
        if (id == null) return null;
        List<Master> find = executeQuerySQL(SELECT_BY_ID_FETCH, MASTER_FETCH_MAPPER, new Object[]{id});
        return !find.isEmpty() ? find.get(0) : null;
    }

    @Override
    public List<Master> findAll() {
        return fetchAllMasters(executeQuerySQL(SELECT_ALL, MASTER_MAPPER, null));
    }

    @Override
    public Long countAll() {
        return executeQuerySQL(COUNT_ALL, Long.class, null);
    }

    @Override
    public boolean existsById(Long id) {
        return null != executeQuerySQL(EXISTS_BY_ID, Long.class, new Object[]
                { getParam(id, Long.class)} );
    }

    @Override
    public boolean existsByPassport(Passport passport) {
        return passport != null &&
                null != executeQuerySQL(EXISTS_BY_PASSPORT, Long.class, new Object[]
                    { getParam(passport.getSeries(), String.class),
                      getParam(passport.getNumber(), String.class),
                      BindModel.MASTER.name() } );
    }

    @Override
    public List<Master> findMastersByCriteria(QueryCriteria criteria) {
        String criteriaPart = criteria != null ? criteria.buildSQL() : "";
        return fetchMasters(executeQuerySQL(SELECT_ALL + criteriaPart, MASTER_MAPPER, null));
    }

    @Override
    public List<Master> findLimitMastersByCriteria(int count, QueryCriteria criteria) {
        String criteriaPart = criteria != null ? criteria.buildSQL() : "";
        return fetchMasters(executeLimitQuerySQL(SELECT_ALL + criteriaPart, count, MASTER_MAPPER, null));
    }

    private List<Master> fetchAllMasters(List<Master> masters) {
        if (!masters.isEmpty()) {
            Map<Long, Passport> passports = ModelUtils.transform(
                    executeQuerySQL(SELECT_ALL_PASSPORTS, PASSPORT_MAPPER, null));
            Map<Long, Post> posts = ModelUtils.transform(
                    executeQuerySQL(SELECT_ALL_POSTS, POST_MAPPER, null));
            Map<Long, Address> addresses = ModelUtils.transform(
                    executeQuerySQL(SELECT_ALL_ADDRESSES, ADDRESS_MAPPER, null));
            Map<Long, Contact> contacts = ModelUtils.transform(
                    executeQuerySQL(SELECT_ALL_CONTACTS, CONTACT_MAPPER, null));
            for (Master master : masters) {
                if (master.getPassport() != null) {
                    master.setPassport(passports.get(master.getPassport().getId()));
                }
                if (master.getPost() != null) {
                    master.setPost(posts.get(master.getPost().getId()));
                }
                if (master.getRegAddress() != null) {
                    master.setRegAddress(addresses.get(master.getRegAddress().getId()));
                }
                if (master.getLiveAddress() != null) {
                    master.setLiveAddress(addresses.get(master.getLiveAddress().getId()));
                }
                if (master.getContact() != null) {
                    master.setContact(contacts.get(master.getContact().getId()));
                }
            }
        }
        return masters;
    }

    private List<Master> fetchMasters(List<Master> masters) {
        for (Master master : masters) {
            if (master.getPassport() != null) {
                List<Passport> passports = executeQuerySQL(SELECT_BY_ID_PASSPORT, PASSPORT_MAPPER, new Object[]{master.getPassport().getId()});
                if (!passports.isEmpty()) {
                    master.setPassport(passports.get(0));
                }
            }
            if (master.getPost() != null) {
                List<Post> posts = executeQuerySQL(SELECT_BY_ID_POST, POST_MAPPER, new Object[]{master.getPost().getId()});
                if (!posts.isEmpty()) {
                    master.setPost(posts.get(0));
                }
            }
            if (master.getRegAddress() != null) {
                List<Address> addresses = executeQuerySQL(SELECT_BY_ID_ADDRESS, ADDRESS_MAPPER, new Object[]{master.getRegAddress().getId()});
                if (!addresses.isEmpty()) {
                    master.setRegAddress(addresses.get(0));
                }
            }
            if (master.getLiveAddress() != null) {
                List<Address> addresses = executeQuerySQL(SELECT_BY_ID_ADDRESS, ADDRESS_MAPPER, new Object[]{master.getLiveAddress().getId()});
                if (!addresses.isEmpty()) {
                    master.setLiveAddress(addresses.get(0));
                }
            }
            if (master.getContact() != null) {
                List<Contact> contacts = executeQuerySQL(SELECT_BY_ID_CONTACT, CONTACT_MAPPER, new Object[]{master.getContact().getId()});
                if (!contacts.isEmpty()) {
                    master.setContact(contacts.get(0));
                }
            }
        }
        return masters;
    }
}
