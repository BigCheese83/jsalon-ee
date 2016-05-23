package ru.bigcheese.jsalon.ee.dao.entity;

import ru.bigcheese.jsalon.core.model.*;
import ru.bigcheese.jsalon.core.util.DateUtils;

import java.util.Calendar;

/**
 * Created by BigCheese on 06.08.15.
 */
public final class EntityMapper {

    public static Service toServiceModel(ServiceEntity entity) {
        if (entity == null) return null;
        Service result = new Service();
        result.setId(entity.getId());
        result.setName(entity.getName());
        result.setCost(entity.getCost());
        result.setDuration(entity.getDuration());
        result.setDescription(entity.getDescription());
        return result;
    }

    public static ServiceEntity toServiceEntity(Service model) {
        if (model == null) return null;
        ServiceEntity result = new ServiceEntity();
        result.setId(model.getId());
        result.setName(model.getName());
        result.setCost(model.getCost());
        result.setDuration(model.getDuration());
        result.setDescription(model.getDescription());
        return result;
    }

    public static Discount toDiscountModel(DiscountEntity entity) {
        if (entity == null) return null;
        return new Discount(entity.getId(), entity.getName(), entity.getValue());
    }

    public static DiscountEntity toDiscountEntity(Discount model) {
        if (model == null) return null;
        return new DiscountEntity(model.getId(), model.getName(), model.getValue());
    }

    public static Post toPostModel(PostEntity entity) {
        if (entity == null) return null;
        return new Post(entity.getId(), entity.getName());
    }

    public static PostEntity toPostEntity(Post model) {
        if (model == null) return null;
        return new PostEntity(model.getId(), model.getName());
    }

    public static Passport toPassportModel(PassportEntity entity) {
        if (entity == null) return null;
        Passport passport = new Passport();
        passport.setId(entity.getId());
        passport.setSeries(entity.getSeries());
        passport.setNumber(entity.getNumber());
        passport.setIssuedBy(entity.getIssuedBy());
        passport.setIssueDate(entity.getIssueDate());
        passport.setSubdivision(entity.getSubdivision());
        passport.setCountry(entity.getCountry());
        return passport;
    }

    public static PassportEntity toPassportEntity(Passport model) {
        if (model == null) return null;
        PassportEntity result = new PassportEntity();
        result.setId(model.getId());
        result.setSeries(model.getSeries());
        result.setNumber(model.getNumber());
        result.setIssuedBy(model.getIssuedBy());
        result.setIssueDate(model.getIssueDate());
        result.setSubdivision(model.getSubdivision());
        result.setCountry(model.getCountry());
        return result;
    }

    public static Address toAddressModel(AddressEntity entity) {
        if (entity == null) return null;
        Address address = new Address();
        address.setId(entity.getId());
        address.setCountry(entity.getCountry());
        address.setDistrict(entity.getDistrict());
        address.setCity(entity.getCity());
        address.setStreet(entity.getStreet());
        address.setHouse(entity.getHouse());
        address.setSection(entity.getSection());
        address.setFlat(entity.getFlat());
        address.setZip(entity.getZip());
        return address;
    }

    public static AddressEntity toAddressEntity(Address model) {
        if (model == null) return null;
        AddressEntity result = new AddressEntity();
        result.setId(model.getId());
        result.setCountry(model.getCountry());
        result.setDistrict(model.getDistrict());
        result.setCity(model.getCity());
        result.setStreet(model.getStreet());
        result.setHouse(model.getHouse());
        result.setSection(model.getSection());
        result.setFlat(model.getFlat());
        result.setZip(model.getZip());
        return result;
    }

    public static Contact toContactModel(ContactEntity entity) {
        if (entity == null) return null;
        Contact contact = new Contact();
        contact.setId(entity.getId());
        contact.setPhone(entity.getPhone());
        contact.setEmail(entity.getEmail());
        contact.setVk(entity.getVk());
        contact.setSkype(entity.getSkype());
        contact.setFacebook(entity.getFacebook());
        contact.setTwitter(entity.getTwitter());
        contact.setIcq(entity.getIcq());
        return contact;
    }

    public static ContactEntity toContactEntity(Contact model) {
        if (model == null) return null;
        ContactEntity result = new ContactEntity();
        result.setId(model.getId());
        result.setPhone(model.getPhone());
        result.setEmail(model.getEmail());
        result.setVk(model.getVk());
        result.setSkype(model.getSkype());
        result.setFacebook(model.getFacebook());
        result.setTwitter(model.getTwitter());
        result.setIcq(model.getIcq());
        return result;
    }

    public static Master toMasterModel(MasterEntity entity) {
        if (entity == null) return null;
        Master master = new Master();
        master.setId(entity.getId());
        master.setSurname(entity.getSurname());
        master.setName(entity.getName());
        master.setPatronymic(entity.getPatronymic());
        master.setBirthDate(entity.getBirthDate());
        master.setHiringDate(entity.getHiringDate());
        master.setPassport(toPassportModel(entity.getPassport()));
        master.setPost(toPostModel(entity.getPost()));
        master.setRegAddress(toAddressModel(entity.getRegAddress()));
        master.setLiveAddress(toAddressModel(entity.getLiveAddress()));
        master.setContact(toContactModel(entity.getContact()));
        master.setBusy(entity.getBusy());
        return master;
    }

    public static MasterEntity toMasterEntity(Master model) {
        if (model == null) return null;
        MasterEntity result = new MasterEntity();
        result.setId(model.getId());
        result.setSurname(model.getSurname());
        result.setName(model.getName());
        result.setPatronymic(model.getPatronymic());
        result.setBirthDate(model.getBirthDate());
        result.setHiringDate(model.getHiringDate());
        result.setPassport(toPassportEntity(model.getPassport()));
        result.setPost(toPostEntity(model.getPost()));
        result.setRegAddress(toAddressEntity(model.getRegAddress()));
        result.setLiveAddress(toAddressEntity(model.getLiveAddress()));
        result.setContact(toContactEntity(model.getContact()));
        result.setBusy(model.isBusy());
        String bindBy = BindModel.MASTER.name();
        if (result.getPassport() != null) {
            result.getPassport().setBindBy(bindBy);
        }
        if (result.getContact() != null) {
            result.getContact().setBindBy(bindBy);
        }
        return result;
    }

    public static Client toClientModel(ClientEntity entity) {
        if (entity == null) return null;
        Client client = new Client();
        client.setId(entity.getId());
        client.setSurname(entity.getSurname());
        client.setName(entity.getName());
        client.setPatronymic(entity.getPatronymic());
        client.setBirthDate(entity.getBirthDate());
        client.setRegistrationDate(entity.getRegistrationDate());
        client.setPassport(toPassportModel(entity.getPassport()));
        client.setDiscount(toDiscountModel(entity.getDiscount()));
        client.setRegAddress(toAddressModel(entity.getRegAddress()));
        client.setLiveAddress(toAddressModel(entity.getLiveAddress()));
        client.setContact(toContactModel(entity.getContact()));
        client.setInBlackList(entity.getInBlackList());
        return client;
    }

    public static ClientEntity toClientEntity(Client model) {
        if (model == null) return null;
        ClientEntity result = new ClientEntity();
        result.setId(model.getId());
        result.setSurname(model.getSurname());
        result.setName(model.getName());
        result.setPatronymic(model.getPatronymic());
        result.setBirthDate(model.getBirthDate());
        result.setRegistrationDate(model.getRegistrationDate());
        result.setPassport(toPassportEntity(model.getPassport()));
        result.setRegAddress(toAddressEntity(model.getRegAddress()));
        result.setLiveAddress(toAddressEntity(model.getLiveAddress()));
        result.setContact(toContactEntity(model.getContact()));
        result.setDiscount(toDiscountEntity(model.getDiscount()));
        result.setInBlackList(model.isInBlackList());
        String bindBy = BindModel.CLIENT.name();
        if (result.getPassport() != null) {
            result.getPassport().setBindBy(bindBy);
        }
        if (result.getContact() != null) {
            result.getContact().setBindBy(bindBy);
        }
        return result;
    }

    public static PostServiceBind toPostServiceBind(PostServiceEntity entity) {
        if (entity == null) return null;
        PostServiceBind result = new PostServiceBind();
        result.setId(entity.getId());
        if (entity.getPost() != null) {
            result.setPostId(entity.getPost().getId());
            result.setPostName(entity.getPost().getName());
        }
        if (entity.getService() != null) {
            result.setServiceId(entity.getService().getId());
            result.setServiceName(entity.getService().getName());
        }
        return result;
    }

    public static Appointment toAppointmentModel(AppointmentEntity entity) {
        if (entity == null) return null;
        Appointment result = new Appointment();
        result.setId(entity.getId());
        result.setAppointmentDate(DateUtils.addMinutes(entity.getAppointmentDate(), entity.getAppointmentTime()));
        result.setClientId(entity.getClientId());
        result.setMasterId(entity.getMasterId());
        result.setServiceId(entity.getServiceId());
        result.setNote(entity.getNote());
        result.setStatus(entity.getStatus());
        return result;
    }

    public static AppointmentEntity toAppointmentEntity(Appointment model) {
        if (model == null) return null;
        AppointmentEntity result = new AppointmentEntity();
        result.setId(model.getId());
        if (model.getAppointmentDate() != null) {
            result.setAppointmentDate(model.getAppointmentDate());
            int hours = model.getAppointmentDate().get(Calendar.HOUR_OF_DAY);
            int minutes = model.getAppointmentDate().get(Calendar.MINUTE);
            result.setAppointmentTime(hours*60 + minutes);
        }
        result.setClientId(model.getClientId());
        result.setMasterId(model.getMasterId());
        result.setServiceId(model.getServiceId());
        result.setNote(model.getNote());
        result.setStatus(model.getStatus());
        return result;
    }
}
