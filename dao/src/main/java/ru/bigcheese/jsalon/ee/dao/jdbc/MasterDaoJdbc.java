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
public class MasterDaoJdbc extends PersonDaoJdbc<Master>
        implements MasterDao {

    /* SQL Queries */
    private static final String GENERATE_ID = "SELECT nextval('masters_id_seq')";

    private static final String INSERT_SQL =
            "INSERT INTO masters (id, surname, name, patronymic, birth_date, hiring_date, id_passport, id_post, id_reg_address, id_live_address, id_contact, busy) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String UPDATE_SQL =
            "UPDATE masters SET surname = ?, name = ?, patronymic = ?, birth_date = ?, hiring_date = ?, id_post = ?, busy = ? WHERE id = ?";

    private static final String DELETE_SQL = "DELETE FROM masters WHERE id = ?";
    private static final String SELECT_ALL = "SELECT * FROM masters";
    private static final String SELECT_ALL_POSTS = "SELECT * FROM posts";
    private static final String COUNT_ALL =  "SELECT count(*) FROM masters";

    private static final String SELECT_BY_ID_FETCH =
            "SELECT m.id, m.birth_date, m.busy, m.hiring_date, m.name, m.patronymic, m.surname, m.id_contact, m.id_live_address, m.id_post, m.id_reg_address, m.id_passport, " +
                "c.email, c.facebook, c.icq, c.phone, c.skype, c.twitter, c.vk, post.name as post_name, " +
                "live.city as live_city, live.country as live_country, live.district as live_district, live.flat as live_flat, live.house as live_house, live.section as live_section, live.street as live_street, live.zip as live_zip, " +
                "reg.city as reg_city, reg.country as reg_country, reg.district as reg_district, reg.flat as reg_flat, reg.house as reg_house, reg.section as reg_section, reg.street as reg_street, reg.zip as reg_zip, " +
                "p.country as passp_country, p.issue_date, p.issued_by, p.num, p.series, p.subdivision " +
            "FROM {oj masters m LEFT OUTER JOIN address live ON (live.id = m.id_live_address)}, passport p, address reg, posts post, contacts c " +
            "WHERE ((m.id = ?) AND ((((c.id = m.id_contact) AND (post.id = m.id_post)) AND (reg.id = m.id_reg_address)) AND (p.id = m.id_passport)))";

    private static final String SELECT_BY_ID = "SELECT * FROM masters WHERE id = ?";
    private static final String SELECT_BY_ID_POST = "SELECT * FROM posts WHERE id = ?";

    private static final String EXISTS_BY_ID = "SELECT id FROM masters WHERE id = ?";
    private static final String EXISTS_BY_PASSPORT = "SELECT id FROM passport WHERE series = ? AND num = ? AND bind_by = ?";


    @Override
    public void persist(Master model) {
        if (model == null) return;
        Map<String,Long> ids = insertPersonData(model);
        Long id = generateID(GENERATE_ID);
        executeUpdateSQL(INSERT_SQL, new Object[]{
                getParam(id, Long.class),
                getParam(model.getSurname(), String.class),
                getParam(model.getName(), String.class),
                getParam(model.getPatronymic(), String.class),
                getParam(model.getBirthDate(), Date.class),
                getParam(model.getHiringDate(), Date.class),
                getParam(ids.get(PASSPORT_ID_KEY), Long.class),
                getParam(model.getPost() != null ? model.getPost().getId() : null, Long.class),
                getParam(ids.get(REG_ADDRESS_ID_KEY), Long.class),
                getParam(ids.get(LIVE_ADDRESS_ID_KEY), Long.class),
                getParam(ids.get(CONTACTS_ID_KEY), Long.class),
                getParam(model.isBusy(), Boolean.class)});

        model.setId(id);
        setIdsForPerson(model, ids);
    }

    @Override
    public void update(Master model) {
        if (model == null) return;
        updatePersonData(model);
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
        List<Master> find = executeQuerySQL(SELECT_BY_ID, MASTER_MAPPER, new Object[]{id});
        if (!find.isEmpty()) {
            Master master = find.get(0);
            executeUpdateSQL(DELETE_SQL, new Object[]{master.getId()});
            deletePersonData(master);
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
        if (masters.isEmpty()) return masters;
        fetchAllPersons(masters);
        Map<Long, Post> posts = ModelUtils.transform(executeQuerySQL(SELECT_ALL_POSTS, POST_MAPPER, null));
        for (Master master : masters) {
            if (master.getPost() != null) {
                master.setPost(posts.get(master.getPost().getId()));
            }
        }
        return masters;
    }

    private List<Master> fetchMasters(List<Master> masters) {
        fetchPersons(masters);
        for (Master master : masters) {
            if (master.getPost() != null) {
                List<Post> posts = executeQuerySQL(SELECT_BY_ID_POST, POST_MAPPER, new Object[]{master.getPost().getId()});
                if (!posts.isEmpty()) {
                    master.setPost(posts.get(0));
                }
            }
        }
        return masters;
    }
}
