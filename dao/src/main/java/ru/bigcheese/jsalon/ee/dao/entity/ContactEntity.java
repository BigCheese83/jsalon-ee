package ru.bigcheese.jsalon.ee.dao.entity;

import javax.persistence.*;

/**
 * Created by BigCheese on 04.08.15.
 */
@Entity
@Table(name = "contacts")
public class ContactEntity extends BaseEntity {

    private String phone;
    private String email;
    private String vk;
    private String skype;
    private String facebook;
    private String twitter;
    private String icq;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CONTACTS_SEQ")
    @SequenceGenerator(name = "CONTACTS_SEQ", sequenceName = "contacts_id_seq", allocationSize = 1)
    public Long getId() {
        return super.getId();
    }

    @Column(nullable = false, unique = true)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVk() {
        return vk;
    }

    public void setVk(String vk) {
        this.vk = vk;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getIcq() {
        return icq;
    }

    public void setIcq(String icq) {
        this.icq = icq;
    }
}
