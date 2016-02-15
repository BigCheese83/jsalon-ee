package ru.bigcheese.jsalon.ee.dao.jdbc;

import ru.bigcheese.jsalon.core.model.*;
import ru.bigcheese.jsalon.core.util.ModelUtils;

import java.util.*;

import static ru.bigcheese.jsalon.ee.dao.jdbc.ModelMapper.*;

/**
 * Created by BigCheese on 11.02.16.
 */
public abstract class PersonDaoJdbc<T extends Person> extends AbstractBaseDaoJdbc<T, Long> {

    /* SQL Queries */
    private static final String GENERATE_ID_PASSPORT = "SELECT nextval('passport_id_seq')";
    private static final String GENERATE_ID_ADDRESS =  "SELECT nextval('address_id_seq')";
    private static final String GENERATE_ID_CONTACT =  "SELECT nextval('contacts_id_seq')";

    private static final String INSERT_SQL_PASSPORT =
            "INSERT INTO passport (id, series, num, issued_by, issue_date, subdivision, country, bind_by) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String INSERT_SQL_ADDRESS =
            "INSERT INTO address (id, country, district, city, street, house, section, flat, zip) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String INSERT_SQL_CONTACT =
            "INSERT INTO contacts (id, phone, email, vk, skype, facebook, twitter, icq, bind_by) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String UPDATE_SQL_PASSPORT =
            "UPDATE passport SET series = ?, num = ?, issued_by = ?, issue_date = ?, subdivision = ?, country = ? WHERE id = ?";
    private static final String UPDATE_SQL_ADDRESS =
            "UPDATE address SET country = ?, district = ?, city = ?, street = ?, house = ?, section = ?, flat = ?, zip = ? WHERE id = ?";
    private static final String UPDATE_SQL_CONTACT =
            "UPDATE contacts SET phone = ?, email = ?, vk = ?, skype = ?, facebook = ?, twitter = ?, icq = ? WHERE id = ?";

    private static final String DELETE_SQL_PASSPORT = "DELETE FROM passport WHERE id = ?";
    private static final String DELETE_SQL_ADDRESS =  "DELETE FROM address WHERE id = ?";
    private static final String DELETE_SQL_CONTACT =  "DELETE FROM contacts WHERE id = ?";

    private static final String SELECT_ALL_PASSPORTS =  "SELECT * FROM passport";
    private static final String SELECT_ALL_ADDRESSES =  "SELECT * FROM address";
    private static final String SELECT_ALL_CONTACTS =   "SELECT * FROM contacts";
    private static final String SELECT_BY_ID_PASSPORT = "SELECT * FROM passport WHERE id = ?";
    private static final String SELECT_BY_ID_ADDRESS =  "SELECT * FROM address WHERE id = ?";
    private static final String SELECT_BY_ID_CONTACT =  "SELECT * FROM contacts WHERE id = ?";

    protected static final String PASSPORT_ID_KEY = "passport";
    protected static final String REG_ADDRESS_ID_KEY = "regAddress";
    protected static final String LIVE_ADDRESS_ID_KEY = "liveAddress";
    protected static final String CONTACTS_ID_KEY = "contacts";


    public Map<String, Long> insertPersonData(Person person) {
        if (person == null) return Collections.EMPTY_MAP;
        Long passportId = null;
        Long regAddrId = null;
        Long liveAddrId = null;
        Long contactId = null;

        if (person.getPassport() != null) {
            passportId = generateID(GENERATE_ID_PASSPORT);
            executeUpdateSQL(INSERT_SQL_PASSPORT, new Object[]{
                    getParam(passportId, Long.class),
                    getParam(person.getPassport().getSeries(), String.class),
                    getParam(person.getPassport().getNumber(), String.class),
                    getParam(person.getPassport().getIssuedBy(), String.class),
                    getParam(person.getPassport().getIssueDate(), Date.class),
                    getParam(person.getPassport().getSubdivision(), String.class),
                    getParam(person.getPassport().getCountry(), String.class),
                    BindModel.getName(person) });
        }
        if (person.getRegAddress() != null) {
            regAddrId = generateID(GENERATE_ID_ADDRESS);
            executeUpdateSQL(INSERT_SQL_ADDRESS, new Object[]{
                    getParam(regAddrId, Long.class),
                    getParam(person.getRegAddress().getCountry(), String.class),
                    getParam(person.getRegAddress().getDistrict(), String.class),
                    getParam(person.getRegAddress().getCity(), String.class),
                    getParam(person.getRegAddress().getStreet(), String.class),
                    getParam(person.getRegAddress().getHouse(), String.class),
                    getParam(person.getRegAddress().getSection(), String.class),
                    getParam(person.getRegAddress().getFlat(), String.class),
                    getParam(person.getRegAddress().getZip(), String.class) });
        }
        if (person.getLiveAddress() != null) {
            liveAddrId = generateID(GENERATE_ID_ADDRESS);
            executeUpdateSQL(INSERT_SQL_ADDRESS, new Object[]{
                    getParam(liveAddrId, Long.class),
                    getParam(person.getLiveAddress().getCountry(), String.class),
                    getParam(person.getLiveAddress().getDistrict(), String.class),
                    getParam(person.getLiveAddress().getCity(), String.class),
                    getParam(person.getLiveAddress().getStreet(), String.class),
                    getParam(person.getLiveAddress().getHouse(), String.class),
                    getParam(person.getLiveAddress().getSection(), String.class),
                    getParam(person.getLiveAddress().getFlat(), String.class),
                    getParam(person.getLiveAddress().getZip(), String.class) });
        }
        if (person.getContact() != null) {
            contactId = generateID(GENERATE_ID_CONTACT);
            executeUpdateSQL(INSERT_SQL_CONTACT, new Object[]{
                    getParam(contactId, Long.class),
                    getParam(person.getContact().getPhone(), String.class),
                    getParam(person.getContact().getEmail(), String.class),
                    getParam(person.getContact().getVk(), String.class),
                    getParam(person.getContact().getSkype(), String.class),
                    getParam(person.getContact().getFacebook(), String.class),
                    getParam(person.getContact().getTwitter(), String.class),
                    getParam(person.getContact().getIcq(), String.class),
                    BindModel.getName(person) });
        }

        Map<String, Long> ids = new HashMap<>();
        if (passportId != null) ids.put(PASSPORT_ID_KEY, passportId);
        if (regAddrId != null) ids.put(REG_ADDRESS_ID_KEY, regAddrId);
        if (liveAddrId != null) ids.put(LIVE_ADDRESS_ID_KEY, liveAddrId);
        if (contactId != null) ids.put(CONTACTS_ID_KEY, contactId);
        return ids;
    }

    public void updatePersonData(Person person) {
        if (person == null) return;
        if (person.getPassport() != null) {
            executeUpdateSQL(UPDATE_SQL_PASSPORT, new Object[]{
                    getParam(person.getPassport().getSeries(), String.class),
                    getParam(person.getPassport().getNumber(), String.class),
                    getParam(person.getPassport().getIssuedBy(), String.class),
                    getParam(person.getPassport().getIssueDate(), Date.class),
                    getParam(person.getPassport().getSubdivision(), String.class),
                    getParam(person.getPassport().getCountry(), String.class),
                    getParam(person.getPassport().getId(), Long.class)});
        }
        if (person.getRegAddress() != null) {
            executeUpdateSQL(UPDATE_SQL_ADDRESS, new Object[]{
                    getParam(person.getRegAddress().getCountry(), String.class),
                    getParam(person.getRegAddress().getDistrict(), String.class),
                    getParam(person.getRegAddress().getCity(), String.class),
                    getParam(person.getRegAddress().getStreet(), String.class),
                    getParam(person.getRegAddress().getHouse(), String.class),
                    getParam(person.getRegAddress().getSection(), String.class),
                    getParam(person.getRegAddress().getFlat(), String.class),
                    getParam(person.getRegAddress().getZip(), String.class),
                    getParam(person.getRegAddress().getId(), Long.class)});
        }
        if (person.getLiveAddress() != null) {
            executeUpdateSQL(UPDATE_SQL_ADDRESS, new Object[]{
                    getParam(person.getLiveAddress().getCountry(), String.class),
                    getParam(person.getLiveAddress().getDistrict(), String.class),
                    getParam(person.getLiveAddress().getCity(), String.class),
                    getParam(person.getLiveAddress().getStreet(), String.class),
                    getParam(person.getLiveAddress().getHouse(), String.class),
                    getParam(person.getLiveAddress().getSection(), String.class),
                    getParam(person.getLiveAddress().getFlat(), String.class),
                    getParam(person.getLiveAddress().getZip(), String.class),
                    getParam(person.getLiveAddress().getId(), Long.class)});
        }
        if (person.getContact() != null) {
            executeUpdateSQL(UPDATE_SQL_CONTACT, new Object[]{
                    getParam(person.getContact().getPhone(), String.class),
                    getParam(person.getContact().getEmail(), String.class),
                    getParam(person.getContact().getVk(), String.class),
                    getParam(person.getContact().getSkype(), String.class),
                    getParam(person.getContact().getFacebook(), String.class),
                    getParam(person.getContact().getTwitter(), String.class),
                    getParam(person.getContact().getIcq(), String.class),
                    getParam(person.getContact().getId(), Long.class)});
        }
    }

    public void deletePersonData(Person person) {
        if (person == null) return;
        if (person.getPassport() != null) {
            executeUpdateSQL(DELETE_SQL_PASSPORT, new Object[]
                    { getParam(person.getPassport().getId(), Long.class) } );
        }
        if (person.getRegAddress() != null) {
            executeUpdateSQL(DELETE_SQL_ADDRESS, new Object[]
                    { getParam(person.getRegAddress().getId(), Long.class) } );
        }
        if (person.getLiveAddress() != null) {
            executeUpdateSQL(DELETE_SQL_ADDRESS, new Object[]
                    { getParam(person.getLiveAddress().getId(), Long.class) } );
        }
        if (person.getContact() != null) {
            executeUpdateSQL(DELETE_SQL_CONTACT, new Object[]
                    { getParam(person.getContact().getId(), Long.class) } );
        }
    }

    public void setIdsForPerson(Person person, Map<String, Long> ids) {
        if (person == null || ids == null) {
            throw new IllegalArgumentException("Bad params. Must be not null.");
        }
        if (person.getPassport() != null) {
            person.getPassport().setId(ids.get(PASSPORT_ID_KEY));
        }
        if (person.getRegAddress() != null) {
            person.getRegAddress().setId(ids.get(REG_ADDRESS_ID_KEY));
        }
        if (person.getLiveAddress() != null) {
            person.getLiveAddress().setId(ids.get(LIVE_ADDRESS_ID_KEY));
        }
        if (person.getContact() != null) {
            person.getContact().setId(ids.get(CONTACTS_ID_KEY));
        }
    }

    public List<Passport> findAllPassports() {
        return executeQuerySQL(SELECT_ALL_PASSPORTS, PASSPORT_MAPPER, null);
    }

    public List<Address> findAllAddresses() {
        return executeQuerySQL(SELECT_ALL_ADDRESSES, ADDRESS_MAPPER, null);
    }

    public List<Contact> findAllContacts() {
        return executeQuerySQL(SELECT_ALL_CONTACTS, CONTACT_MAPPER, null);
    }

    public Passport findPassportById(Long id) {
        if (id == null) return null;
        List<Passport> find = executeQuerySQL(SELECT_BY_ID_PASSPORT, PASSPORT_MAPPER, new Object[]{id});
        return !find.isEmpty() ? find.get(0) : null;
    }

    public Address findAddressById(Long id) {
        if (id == null) return null;
        List<Address> find = executeQuerySQL(SELECT_BY_ID_ADDRESS, ADDRESS_MAPPER, new Object[]{id});
        return !find.isEmpty() ? find.get(0) : null;
    }

    public Contact findContactById(Long id) {
        if (id == null) return null;
        List<Contact> find = executeQuerySQL(SELECT_BY_ID_CONTACT, CONTACT_MAPPER, new Object[]{id});
        return !find.isEmpty() ? find.get(0) : null;
    }

    protected void fetchAllPersons(List<? extends Person> persons) {
        if (persons == null || persons.isEmpty()) return;
        Map<Long, Passport> passports = ModelUtils.transform(findAllPassports());
        Map<Long, Address> addresses = ModelUtils.transform(findAllAddresses());
        Map<Long, Contact> contacts = ModelUtils.transform(findAllContacts());
        for (Person person : persons) {
            if (person.getPassport() != null) {
                person.setPassport(passports.get(person.getPassport().getId()));
            }
            if (person.getRegAddress() != null) {
                person.setRegAddress(addresses.get(person.getRegAddress().getId()));
            }
            if (person.getLiveAddress() != null) {
                person.setLiveAddress(addresses.get(person.getLiveAddress().getId()));
            }
            if (person.getContact() != null) {
                person.setContact(contacts.get(person.getContact().getId()));
            }
        }
    }

    protected void fetchPersons(List<? extends Person> persons) {
        if (persons == null) return;
        for (Person person : persons) {
            if (person.getPassport() != null) {
                person.setPassport(findPassportById(person.getPassport().getId()));
            }
            if (person.getRegAddress() != null) {
                person.setRegAddress(findAddressById(person.getRegAddress().getId()));

            }
            if (person.getLiveAddress() != null) {
                person.setLiveAddress(findAddressById(person.getLiveAddress().getId()));
            }
            if (person.getContact() != null) {
                person.setContact(findContactById(person.getContact().getId()));
            }
        }
    }
}
