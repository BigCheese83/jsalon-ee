package ru.bigcheese.jsalon.core.model;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by BigCheese on 31.07.15.
 */
public class Person extends BaseModel {

    private String surname;
    private String name;
    private String patronymic;
    private Date birthDate;
    private Passport passport;
    private Address regAddress;
    private Address liveAddress;
    private Contact contact;

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Date getBirthDate() {
        return new Date(birthDate.getTime());
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = new Date(birthDate.getTime());
    }

    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    public Address getRegAddress() {
        return regAddress;
    }

    public void setRegAddress(Address regAddress) {
        this.regAddress = regAddress;
    }

    public Address getLiveAddress() {
        return liveAddress;
    }

    public void setLiveAddress(Address liveAddress) {
        this.liveAddress = liveAddress;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    @Override
    protected List<String> getValidateErrors() {
        List<String> errors = new ArrayList<String>();
        if (StringUtils.isBlank(name)) {
            errors.add("Введите имя");
        }
        if (StringUtils.isBlank(surname)) {
            errors.add("Введите фамилию");
        }
        if (birthDate == null) {
            errors.add("Укажите дату рождения");
        }
        if (passport == null) {
            errors.add("Необходимо указать паспортные данные");
        } else {
            errors.addAll(passport.getValidateErrors());
        }
        if (regAddress == null) {
            errors.add("Необходимо указать адрес регистрации");
        } else {
            errors.addAll(regAddress.getValidateErrors());
        }
        if (liveAddress != null) {
            errors.addAll(liveAddress.getValidateErrors());
        }
        if (contact == null) {
            errors.add("Необходимо указать контактные данные");
        } else {
            errors.addAll(contact.getValidateErrors());
        }
        return Collections.unmodifiableList(errors);
    }
}
