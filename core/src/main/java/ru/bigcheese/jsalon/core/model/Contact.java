package ru.bigcheese.jsalon.core.model;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Created by BigCheese on 31.07.15.
 */
public class Contact extends BaseModel {

    private String phone;
    private String email;
    private String vk;
    private String skype;
    private String facebook;
    private String twitter;
    private String icq;

    public Contact() {}

    public Contact(String phone) {
        this.phone = phone;
    }

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

    public String getFullStr() {
        return (StringUtils.isBlank(phone) ? "" : "phone " + phone + " ") +
                (StringUtils.isBlank(email) ? "" : "email " + email + " ") +
                (StringUtils.isBlank(vk) ? "" : "vk " + vk + " ") +
                (StringUtils.isBlank(skype) ? "" : "skype " + skype + " ") +
                (StringUtils.isBlank(facebook) ? "" : "facebook " + facebook + " ") +
                (StringUtils.isBlank(twitter) ? "" : "twitter " + twitter + " ") +
                (StringUtils.isBlank(icq) ? "" : "icq " + icq);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contact)) return false;
        Contact contact = (Contact) o;
        return Objects.equals(phone, contact.phone) &&
                Objects.equals(email, contact.email) &&
                Objects.equals(vk, contact.vk) &&
                Objects.equals(skype, contact.skype) &&
                Objects.equals(facebook, contact.facebook) &&
                Objects.equals(twitter, contact.twitter) &&
                Objects.equals(icq, contact.icq);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phone, email, vk, skype, facebook, twitter, icq);
    }

    @Override
    protected List<String> getValidateErrors() {
        List<String> errors = new ArrayList<String>();
        if (StringUtils.isBlank(phone)) {
            errors.add("Укажите номер телефона");
        } else if (!Pattern.matches("[+]*[0-9-()]+", phone)){
            errors.add("Неправильный номер телефона");
        }
        return Collections.unmodifiableList(errors);
    }
}
