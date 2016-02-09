package ru.bigcheese.jsalon.ee.dao.entity;

import org.eclipse.persistence.annotations.BatchFetch;
import org.eclipse.persistence.annotations.BatchFetchType;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by BigCheese on 04.08.15.
 */
@Entity
@Table(name = "masters")
@NamedQueries({
    @NamedQuery(
            name = MasterEntity.EXISTS_BY_PASSPORT,
            query = "SELECT p.id FROM PassportEntity p WHERE p.series = ?1 AND p.number = ?2 AND p.bindBy = ?3")
})
public class MasterEntity extends BaseEntity {

    public static final String EXISTS_BY_PASSPORT = "Master.existsByPassport";

    private String surname;
    private String name;
    private String patronymic;
    private Date birthDate;
    private Date hiringDate;
    private PassportEntity passport;
    private PostEntity post;
    private AddressEntity regAddress;
    private AddressEntity liveAddress;
    private ContactEntity contact;
    private Boolean busy;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MASTERS_SEQ")
    @SequenceGenerator(name = "MASTERS_SEQ", sequenceName = "masters_id_seq", allocationSize = 1)
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
    @Column(name = "hiring_date", nullable = false)
    public Date getHiringDate() {
        return hiringDate;
    }

    public void setHiringDate(Date hiringDate) {
        this.hiringDate = hiringDate;
    }

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_passport", nullable = false)
    @BatchFetch(BatchFetchType.JOIN)
    public PassportEntity getPassport() {
        return passport;
    }

    public void setPassport(PassportEntity passport) {
        this.passport = passport;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_post", nullable = false)
    @BatchFetch(BatchFetchType.JOIN)
    public PostEntity getPost() {
        return post;
    }

    public void setPost(PostEntity post) {
        this.post = post;
    }

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_reg_address", nullable = false)
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

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_contact", nullable = false)
    @BatchFetch(BatchFetchType.JOIN)
    public ContactEntity getContact() {
        return contact;
    }

    public void setContact(ContactEntity contact) {
        this.contact = contact;
    }

    public Boolean getBusy() {
        return busy;
    }

    public void setBusy(Boolean busy) {
        this.busy = busy;
    }
}
