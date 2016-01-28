package ru.bigcheese.jsalon.ee.web.jsp.to;

import ru.bigcheese.jsalon.core.model.Master;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by BigCheese on 21.01.16.
 */
public class MasterTO implements Serializable {

    private Long id;
    private String surname;
    private String name;
    private String patronymic;
    private Date birthDate;
    private Date hiringDate;
    private String passportInfo;
    private String regAddressInfo;
    private String liveAddressInfo;
    private String contactInfo;
    private String postInfo;
    private boolean busy;

    public MasterTO(Master master) {
        id = master.getId();
        surname = master.getSurname();
        name = master.getName();
        patronymic = master.getPatronymic();
        birthDate = master.getBirthDate();
        hiringDate = master.getHiringDate();
        busy = master.isBusy();
        passportInfo = master.getPassport() != null ? master.getPassport().getFullStr() : "";
        regAddressInfo = master.getRegAddress() != null ? master.getRegAddress().getFullStr() : "";
        liveAddressInfo = master.getLiveAddress() != null ? master.getLiveAddress().getFullStr() : "";
        contactInfo = master.getContact() != null ? master.getContact().getFullStr() : "";
        postInfo = master.getPost() != null ? master.getPost().getName() : "";
    }

    public Long getId() {
        return id;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public Date getHiringDate() {
        return hiringDate;
    }

    public String getPassportInfo() {
        return passportInfo;
    }

    public String getRegAddressInfo() {
        return regAddressInfo;
    }

    public String getLiveAddressInfo() {
        return liveAddressInfo;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public String getPostInfo() {
        return postInfo;
    }

    public boolean isBusy() {
        return busy;
    }
}
