package ru.bigcheese.jsalon.ee.dao.entity;

import org.eclipse.persistence.annotations.BatchFetch;
import org.eclipse.persistence.annotations.BatchFetchType;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by BigCheese on 04.08.15.
 */
@Entity
@Table(name = "clients")
@NamedQueries({
    @NamedQuery(
            name = ClientEntity.EXISTS_BY_PASSPORT,
            query = "SELECT p.id FROM PassportEntity p WHERE p.series = ?1 AND p.number = ?2 AND p.bindBy = ?3"),
    @NamedQuery(
            name = ClientEntity.EXISTS_BY_PHONE,
            query = "SELECT c.id FROM ContactEntity c WHERE c.phone = ?1 AND c.bindBy = ?2")
})
public class ClientEntity extends BaseEntity {

    public static final String EXISTS_BY_PASSPORT = "Client.existsByPassport";
    public static final String EXISTS_BY_PHONE = "Client.existsByPhone";

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
    @Column(name = "birth_date")
    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "registration_date")
    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_passport")
    @BatchFetch(BatchFetchType.JOIN)
    public PassportEntity getPassport() {
        return passport;
    }

    public void setPassport(PassportEntity passport) {
        this.passport = passport;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_reg_address")
    @BatchFetch(BatchFetchType.JOIN)
    public AddressEntity getRegAddress() {
        return regAddress;
    }

    public void setRegAddress(AddressEntity regAddress) {
        this.regAddress = regAddress;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_live_address")
    @BatchFetch(BatchFetchType.JOIN)
    public AddressEntity getLiveAddress() {
        return liveAddress;
    }

    public void setLiveAddress(AddressEntity liveAddress) {
        this.liveAddress = liveAddress;
    }

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_contact", nullable = false)
    @BatchFetch(BatchFetchType.JOIN)
    public ContactEntity getContact() {
        return contact;
    }

    public void setContact(ContactEntity contact) {
        this.contact = contact;
    }

    @ManyToOne
    @JoinColumn(name = "id_discount")
    @BatchFetch(BatchFetchType.JOIN)
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
