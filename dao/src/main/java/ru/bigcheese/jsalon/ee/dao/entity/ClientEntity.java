package ru.bigcheese.jsalon.ee.dao.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by BigCheese on 04.08.15.
 */
@Entity
@Table(name = "clients")
public class ClientEntity extends BaseEntity {

    private String surname;
    private String name;
    private String patronymic;
    private Date birthDate;
    private Date registrationDate;
    private PassportEntity passport;
    private AddressEntity regAddress;
    private AddressEntity liveAddress;
    private ContactEntity contact;
    private DiscountEntity discount;
    private Boolean inBlackList;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CLIENTS_SEQ")
    @SequenceGenerator(name = "CLIENTS_SEQ", sequenceName = "clients_id_seq", allocationSize = 1)
    public Long getId() {
        return super.getId();
    }

    @Column(nullable = false)
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Column(nullable = false)
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

    @Temporal(TemporalType.DATE)
    @Column(name = "birth_date", nullable = false)
    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "registration_date", nullable = false)
    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    @OneToOne
    @JoinColumn(name = "id_passport", nullable = false)
    public PassportEntity getPassport() {
        return passport;
    }

    public void setPassport(PassportEntity passport) {
        this.passport = passport;
    }

    @ManyToOne
    @JoinColumn(name = "id_reg_address", nullable = false)
    public AddressEntity getRegAddress() {
        return regAddress;
    }

    public void setRegAddress(AddressEntity regAddress) {
        this.regAddress = regAddress;
    }

    @ManyToOne
    @JoinColumn(name = "id_live_address")
    public AddressEntity getLiveAddress() {
        return liveAddress;
    }

    public void setLiveAddress(AddressEntity liveAddress) {
        this.liveAddress = liveAddress;
    }

    @ManyToOne
    @JoinColumn(name = "id_contact", nullable = false)
    public ContactEntity getContact() {
        return contact;
    }

    public void setContact(ContactEntity contact) {
        this.contact = contact;
    }

    @ManyToOne
    @JoinColumn(name = "id_discount")
    public DiscountEntity getDiscount() {
        return discount;
    }

    public void setDiscount(DiscountEntity discount) {
        this.discount = discount;
    }

    @Column(name = "in_black")
    public Boolean getInBlackList() {
        return inBlackList;
    }

    public void setInBlackList(Boolean inBlackList) {
        this.inBlackList = inBlackList;
    }
}
