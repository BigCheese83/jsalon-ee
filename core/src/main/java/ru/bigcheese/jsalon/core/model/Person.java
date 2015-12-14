package ru.bigcheese.jsalon.core.model;

import java.util.Date;

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
}
