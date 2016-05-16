package ru.bigcheese.jsalon.ee.dao;

import ru.bigcheese.jsalon.ee.dao.jdbc.JdbcQueryCriteria;

/**
 * Created by BigCheese on 05.11.15.
 */
public class QueryCriteriaFactory {

    public static QueryCriteria getInstance() {
        return new JdbcQueryCriteria();
    }

    public static String buildSQL(QueryCriteriaType type, Object... params) {
        switch (type) {
            case PERSON_NAMES:
                return getPersonNamesCriteria(params).buildSQL();
            case MASTER_NAMES_SERVICE:
                return getMasterNamesServiceCriteria(params).buildSQL();
            case SERVICE_NAME_MASTER:
                return getServiceNameMasterCriteria(params).buildSQL();
            default:
                throw new IllegalArgumentException("Wrong parameter QueryCriteriaType for initialization QueryCriteria");
        }
    }

    private static QueryCriteria getPersonNamesCriteria(Object... names) {
        final String[] colNames = new String[] {"surname", "name", "patronymic"};
        final int last = names.length - 1;
        QueryCriteria criteria = getInstance().where();
        for (int i = 0; i < last; i++) {
            criteria.equal(criteria.lower(colNames[i]), (String)names[i])
                    .and();
        }
        criteria.like(criteria.lower(colNames[last]), (String)names[last], "x%")
                .orderBy("surname", "asc");
        return criteria;
    }

    private static QueryCriteria getMasterNamesServiceCriteria(Object... params) {
        QueryCriteria criteria = getInstance()
                .where()
                .equal("s.name", (String)params[0])
                .and();

        String[] names = (String[])params[1];
        final String[] colNames = new String[] {"m.surname", "m.name", "m.patronymic"};
        final int last = names.length - 1;

        for (int i = 0; i < last; i++) {
            criteria.equal(criteria.lower(colNames[i]), names[i])
                    .and();
        }
        criteria.like(criteria.lower(colNames[last]), names[last], "x%")
                .orderBy("m.surname", "asc");

        return criteria;
    }

    private static QueryCriteria getServiceNameMasterCriteria(Object... params) {
        QueryCriteria criteria = getInstance().where();
        criteria.like(criteria.lower("s.name"), (String) params[0], "x%")
                .and();

        String[] names = (String[])params[1];
        final String[] colNames = new String[] {"m.surname", "m.name", "m.patronymic"};

        for (int i = 0; i < names.length; i++) {
            criteria.equal(colNames[i], names[i]);
            if (i < names.length-1) {
                criteria.and();
            }
        }
        criteria.orderBy("s.name", "asc");
        return criteria;
    }
}
