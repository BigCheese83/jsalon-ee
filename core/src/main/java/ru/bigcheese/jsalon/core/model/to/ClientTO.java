package ru.bigcheese.jsalon.core.model.to;

import ru.bigcheese.jsalon.core.model.Client;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by BigCheese on 11.02.16.
 */
public class ClientTO implements Serializable {

    private String surname;
    private String name;
    private String patronymic;
    private Date birthDate;
    private Date registrationDate;
    private String discount;
    private boolean inBlack;
    private PassportTO passport;
    private AddressTO regAddress;
    private AddressTO liveAddress;
    private ContactTO contact;

    public ClientTO(Client client) {
        surname = client.getSurname();
        name = client.getName();
        patronymic = client.getPatronymic();
        birthDate = client.getBirthDate();
        registrationDate = client.getRegistrationDate();
        inBlack = client.isInBlackList();
        discount = client.getDiscount() != null ? client.getDiscount().getName() : null;
        if (client.getPassport() != null) {
            passport = new PassportTO(client.getPassport());
        }
        if (client.getRegAddress() != null) {
            regAddress = new AddressTO(client.getRegAddress());
        }
        if (client.getLiveAddress() != null) {
            liveAddress = new AddressTO(client.getLiveAddress());
        }
        if (client.getContact() != null) {
            contact = new ContactTO(client.getContact());
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

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public String getDiscount() {
        return discount;
    }

    public boolean isInBlack() {
        return inBlack;
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

    public static List<ClientTO> toList(List<Client> list) {
        if (list == null) return null;
        List<ClientTO> result = new ArrayList<>(list.size());
        for (Client c : list) {
            result.add(new ClientTO(c));
        }
        return result;
    }
}
