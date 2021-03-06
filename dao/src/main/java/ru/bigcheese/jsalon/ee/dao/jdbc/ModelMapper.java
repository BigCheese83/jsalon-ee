package ru.bigcheese.jsalon.ee.dao.jdbc;

import org.apache.commons.lang3.StringUtils;
import ru.bigcheese.jsalon.core.model.*;
import ru.bigcheese.jsalon.core.model.bind.PostServiceBind;
import ru.bigcheese.jsalon.core.model.enums.AppointmentStatus;
import ru.bigcheese.jsalon.core.model.to.ModelTO;
import ru.bigcheese.jsalon.core.util.DateUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

/**
 * Default Row-to-Model Mappers
 * Created by BigCheese on 30.12.15.
 */
public final class ModelMapper {

    public static final RowMapper<Post> POST_MAPPER = new RowMapper<Post>() {
        @Override
        public Post mapRow(ResultSet rs) throws SQLException {
            return new Post(rs.getLong("id"), rs.getString("name"));
        }
    };

    public static final RowMapper<Discount> DISCOUNT_MAPPER = new RowMapper<Discount>() {
        @Override
        public Discount mapRow(ResultSet rs) throws SQLException {
            return new Discount(rs.getLong("id"), rs.getString("name"), rs.getInt("value"));
        }
    };

    public static final RowMapper<Service> SERVICE_MAPPER = new RowMapper<Service>() {
        @Override
        public Service mapRow(ResultSet rs) throws SQLException {
            Service result = new Service();
            result.setId(rs.getLong("id"));
            result.setName(rs.getString("name"));
            result.setCost(rs.getBigDecimal("cost"));
            result.setDuration(rs.getInt("duration"));
            result.setDescription(rs.getString("description"));
            return result;
        }
    };

    public static final RowMapper<PostServiceBind> POST_SERVICE_MAPPER = new RowMapper<PostServiceBind>() {
        @Override
        public PostServiceBind mapRow(ResultSet rs) throws SQLException {
            PostServiceBind result = new PostServiceBind();
            result.setId(rs.getLong("id"));
            result.setPostId(rs.getLong("post_id"));
            result.setServiceId(rs.getLong("service_id"));
            result.setPostName(rs.getString("post_name"));
            result.setServiceName(rs.getString("service_name"));
            return result;
        }
    };

    public static final RowMapper<User> USER_MAPPER = new RowMapper<User>() {
        @Override
        public User mapRow(ResultSet rs) throws SQLException {
            User user = new User();
            user.setId(rs.getLong("id"));
            user.setLogin(rs.getString("username"));
            user.setFirstName(rs.getString("firstname"));
            user.setLastName(rs.getString("lastname"));
            user.setMiddleName(rs.getString("middlename"));
            user.setRole(rs.getString("role"));
            return user;
        }
    };

    public static final RowMapper<Passport> PASSPORT_MAPPER = new RowMapper<Passport>() {
        @Override
        public Passport mapRow(ResultSet rs) throws SQLException {
            Passport passport = new Passport();
            passport.setId(rs.getLong("id"));
            passport.setSeries(rs.getString("series"));
            passport.setNumber(rs.getString("num"));
            passport.setCountry(rs.getString("country"));
            passport.setIssuedBy(rs.getString("issued_by"));
            passport.setIssueDate(DateUtils.toDate(rs.getDate("issue_date")));
            passport.setSubdivision(rs.getString("subdivision"));
            return passport;
        }
    };

    public static final RowMapper<Address> ADDRESS_MAPPER = new RowMapper<Address>() {
        @Override
        public Address mapRow(ResultSet rs) throws SQLException {
            Address address = new Address();
            address.setId(rs.getLong("id"));
            address.setCountry(rs.getString("country"));
            address.setDistrict(rs.getString("district"));
            address.setCity(rs.getString("city"));
            address.setStreet(rs.getString("street"));
            address.setHouse(rs.getString("house"));
            address.setSection(rs.getString("section"));
            address.setFlat(rs.getString("flat"));
            address.setZip(rs.getString("zip"));
            return address;
        }
    };

    public static final RowMapper<Contact> CONTACT_MAPPER = new RowMapper<Contact>() {
        @Override
        public Contact mapRow(ResultSet rs) throws SQLException {
            Contact contact = new Contact();
            contact.setId(rs.getLong("id"));
            contact.setPhone(rs.getString("phone"));
            contact.setEmail(rs.getString("email"));
            contact.setVk(rs.getString("vk"));
            contact.setSkype(rs.getString("skype"));
            contact.setFacebook(rs.getString("facebook"));
            contact.setTwitter(rs.getString("twitter"));
            contact.setIcq(rs.getString("icq"));
            return contact;
        }
    };

    public static final RowMapper<Master> MASTER_MAPPER = new RowMapper<Master>() {
        @Override
        public Master mapRow(ResultSet rs) throws SQLException {
            Master result = new Master();
            result.setId(rs.getLong("id"));
            result.setSurname(rs.getString("surname"));
            result.setName(rs.getString("name"));
            result.setPatronymic(rs.getString("patronymic"));
            result.setBirthDate(DateUtils.toDate(rs.getDate("birth_date")));
            result.setHiringDate(DateUtils.toDate(rs.getDate("hiring_date")));
            result.setBusy(rs.getBoolean("busy"));
            long passportId = rs.getLong("id_passport");
            if (passportId > 0) {
                Passport passport = new Passport();
                passport.setId(passportId);
                result.setPassport(passport);
            }
            long postId = rs.getLong("id_post");
            if (postId > 0) {
                result.setPost(new Post(postId, null));
            }
            long regAddrId = rs.getLong("id_reg_address");
            if (regAddrId > 0) {
                Address address = new Address();
                address.setId(regAddrId);
                result.setRegAddress(address);
            }
            long liveAddrId = rs.getLong("id_live_address");
            if (liveAddrId > 0) {
                Address address = new Address();
                address.setId(liveAddrId);
                result.setLiveAddress(address);
            }
            long contactId = rs.getLong("id_contact");
            if (contactId > 0) {
                Contact contact = new Contact();
                contact.setId(contactId);
                result.setContact(contact);
            }
            return result;
        }
    };

    public static final RowMapper<Master> MASTER_FETCH_MAPPER = new RowMapper<Master>() {
        @Override
        public Master mapRow(ResultSet rs) throws SQLException {
            Master result = MASTER_MAPPER.mapRow(rs);
            if (result.getPassport() != null) {
                Passport passport = result.getPassport();
                passport.setSeries(rs.getString("series"));
                passport.setNumber(rs.getString("num"));
                passport.setIssuedBy(rs.getString("issued_by"));
                passport.setIssueDate(DateUtils.toDate(rs.getDate("issue_date")));
                passport.setSubdivision(rs.getString("subdivision"));
                passport.setCountry(rs.getString("passp_country"));
            }
            if (result.getPost() != null) {
                result.getPost().setName(rs.getString("post_name"));
            }
            if (result.getRegAddress() != null) {
                Address reg = result.getRegAddress();
                reg.setCountry(rs.getString("reg_country"));
                reg.setDistrict(rs.getString("reg_district"));
                reg.setCity(rs.getString("reg_city"));
                reg.setStreet(rs.getString("reg_street"));
                reg.setHouse(rs.getString("reg_house"));
                reg.setSection(rs.getString("reg_section"));
                reg.setFlat(rs.getString("reg_flat"));
                reg.setZip(rs.getString("reg_zip"));
            }
            if (result.getLiveAddress() != null) {
                Address live = result.getLiveAddress();
                live.setCountry(rs.getString("live_country"));
                live.setDistrict(rs.getString("live_district"));
                live.setCity(rs.getString("live_city"));
                live.setStreet(rs.getString("live_street"));
                live.setHouse(rs.getString("live_house"));
                live.setSection(rs.getString("live_section"));
                live.setFlat(rs.getString("live_flat"));
                live.setZip(rs.getString("live_zip"));
            }
            if (result.getContact() != null) {
                Contact contact = result.getContact();
                contact.setPhone(rs.getString("phone"));
                contact.setEmail(rs.getString("email"));
                contact.setVk(rs.getString("vk"));
                contact.setSkype(rs.getString("skype"));
                contact.setFacebook(rs.getString("facebook"));
                contact.setTwitter(rs.getString("twitter"));
                contact.setIcq(rs.getString("icq"));
            }
            return result;
        }
    };

    public static final RowMapper<Client> CLIENT_MAPPER = new RowMapper<Client>() {
        @Override
        public Client mapRow(ResultSet rs) throws SQLException {
            Client result = new Client();
            result.setId(rs.getLong("id"));
            result.setSurname(rs.getString("surname"));
            result.setName(rs.getString("name"));
            result.setPatronymic(rs.getString("patronymic"));
            result.setBirthDate(DateUtils.toDate(rs.getDate("birth_date")));
            result.setRegistrationDate(DateUtils.toDate(rs.getDate("registration_date")));
            result.setInBlackList(rs.getBoolean("in_black"));
            long passportId = rs.getLong("id_passport");
            if (passportId > 0) {
                Passport passport = new Passport();
                passport.setId(passportId);
                result.setPassport(passport);
            }
            long regAddrId = rs.getLong("id_reg_address");
            if (regAddrId > 0) {
                Address address = new Address();
                address.setId(regAddrId);
                result.setRegAddress(address);
            }
            long liveAddrId = rs.getLong("id_live_address");
            if (liveAddrId > 0) {
                Address address = new Address();
                address.setId(liveAddrId);
                result.setLiveAddress(address);
            }
            long contactId = rs.getLong("id_contact");
            if (contactId > 0) {
                Contact contact = new Contact();
                contact.setId(contactId);
                result.setContact(contact);
            }
            long discountId = rs.getLong("id_discount");
            if (discountId > 0) {
                result.setDiscount(new Discount(discountId, null, null));
            }
            return result;
        }
    };

    public static final RowMapper<Client> CLIENT_FETCH_MAPPER = new RowMapper<Client>() {
        @Override
        public Client mapRow(ResultSet rs) throws SQLException {
            Client result = CLIENT_MAPPER.mapRow(rs);
            if (result.getPassport() != null) {
                Passport passport = result.getPassport();
                passport.setSeries(rs.getString("series"));
                passport.setNumber(rs.getString("num"));
                passport.setIssuedBy(rs.getString("issued_by"));
                passport.setIssueDate(DateUtils.toDate(rs.getDate("issue_date")));
                passport.setSubdivision(rs.getString("subdivision"));
                passport.setCountry(rs.getString("passp_country"));
            }
            if (result.getDiscount() != null) {
                result.getDiscount().setName(rs.getString("disc_name"));
                result.getDiscount().setValue(rs.getInt("disc_value"));
            }
            if (result.getRegAddress() != null) {
                Address reg = result.getRegAddress();
                reg.setCountry(rs.getString("reg_country"));
                reg.setDistrict(rs.getString("reg_district"));
                reg.setCity(rs.getString("reg_city"));
                reg.setStreet(rs.getString("reg_street"));
                reg.setHouse(rs.getString("reg_house"));
                reg.setSection(rs.getString("reg_section"));
                reg.setFlat(rs.getString("reg_flat"));
                reg.setZip(rs.getString("reg_zip"));
            }
            if (result.getLiveAddress() != null) {
                Address live = result.getLiveAddress();
                live.setCountry(rs.getString("live_country"));
                live.setDistrict(rs.getString("live_district"));
                live.setCity(rs.getString("live_city"));
                live.setStreet(rs.getString("live_street"));
                live.setHouse(rs.getString("live_house"));
                live.setSection(rs.getString("live_section"));
                live.setFlat(rs.getString("live_flat"));
                live.setZip(rs.getString("live_zip"));
            }
            if (result.getContact() != null) {
                Contact contact = result.getContact();
                contact.setPhone(rs.getString("phone"));
                contact.setEmail(rs.getString("email"));
                contact.setVk(rs.getString("vk"));
                contact.setSkype(rs.getString("skype"));
                contact.setFacebook(rs.getString("facebook"));
                contact.setTwitter(rs.getString("twitter"));
                contact.setIcq(rs.getString("icq"));
            }
            return result;
        }
    };

    public static final RowMapper<ModelTO> PERSON_NAMES_MAPPER = new RowMapper<ModelTO>() {
        @Override
        public ModelTO mapRow(ResultSet rs) throws SQLException {
            String surname = StringUtils.defaultString(rs.getString("surname"));
            String name = StringUtils.defaultString(rs.getString("name"));
            String patronymic = StringUtils.defaultString(rs.getString("patronymic"));
            return new ModelTO(rs.getLong("id"), surname, name, patronymic);

        }
    };

    public static final RowMapper<ModelTO> NAME_MAPPER = new RowMapper<ModelTO>() {
        @Override
        public ModelTO mapRow(ResultSet rs) throws SQLException {
            return new ModelTO(rs.getLong("id"), rs.getString("name"));
        }
    };

    public static final RowMapper<Appointment> APPOINTMENT_MAPPER = new RowMapper<Appointment>() {
        @Override
        public Appointment mapRow(ResultSet rs) throws SQLException {
            Appointment result = new Appointment();
            Calendar date = DateUtils.toCalendar(rs.getDate("appoint_date"));
            result.setAppointmentDate(DateUtils.addMinutes(date, rs.getInt("appoint_time")));
            result.setId(rs.getLong("id"));
            result.setClientId(rs.getLong("client_id"));
            result.setMasterId(rs.getLong("master_id"));
            result.setServiceId(rs.getLong("service_id"));
            result.setNote(rs.getString("note"));
            result.setStatus(AppointmentStatus.fromName(rs.getString("status")));
            return result;
        }
    };
}
