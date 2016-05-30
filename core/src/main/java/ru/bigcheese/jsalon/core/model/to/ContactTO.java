package ru.bigcheese.jsalon.core.model.to;

import ru.bigcheese.jsalon.core.model.Contact;

import java.io.Serializable;

/**
 * Created by BigCheese on 03.02.16.
 */
public class ContactTO implements Serializable {

    private String phone;
    private String email;
    private String vk;
    private String skype;
    private String facebook;
    private String twitter;
    private String icq;

    public ContactTO(Contact contact) {
        phone = contact.getPhone();
        email = contact.getEmail();
        vk = contact.getVk();
        skype = contact.getSkype();
        facebook = contact.getFacebook();
        twitter = contact.getTwitter();
        icq = contact.getIcq();
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getVk() {
        return vk;
    }

    public String getSkype() {
        return skype;
    }

    public String getFacebook() {
        return facebook;
    }

    public String getTwitter() {
        return twitter;
    }

    public String getIcq() {
        return icq;
    }
}
