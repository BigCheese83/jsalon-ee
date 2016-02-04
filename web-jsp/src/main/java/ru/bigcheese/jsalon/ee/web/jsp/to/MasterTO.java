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

    private String surname;
    private String name;
    private String patronymic;
    private Date birthDate;
    private Date hiringDate;
    private String post;
    private boolean busy;
    private PassportTO passport;
    private AddressTO regAddress;
    private AddressTO liveAddress;
    private ContactTO contact;

    public MasterTO(Master master) {
        surname = master.getSurname();
        name = master.getName();
        patronymic = master.getPatronymic();
        birthDate = master.getBirthDate();
        hiringDate = master.getHiringDate();
        busy = master.isBusy();
        post = master.getPost() != null ? master.getPost().getName() : null;
        if (master.getPassport() != null) {
            passport = new PassportTO(master.getPassport());
        }
        if (master.getRegAddress() != null) {
            regAddress = new AddressTO(master.getRegAddress());
        }
        if (master.getLiveAddress() != null) {
            liveAddress = new AddressTO(master.getLiveAddress());
        }
        if (master.getContact() != null) {
            contact = new ContactTO(master.getContact());
        }
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

    public String getPost() {
        return post;
    }

    public boolean isBusy() {
        return busy;
    }

    public PassportTO getPassport() {
        return passport;
    }

    public AddressTO getRegAddress() {
        return regAddress;
    }

    public AddressTO getLiveAddress() {
        return liveAddress;
    }

    public ContactTO getContact() {
        return contact;
    }

    public static List<MasterTO> toList(List<Master> list) {
        if (list == null) return null;
        List<MasterTO> result = new ArrayList<>(list.size());
        for (Master m : list) {
            result.add(new MasterTO(m));
        }
        return result;
    }
}
