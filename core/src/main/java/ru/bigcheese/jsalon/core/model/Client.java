package ru.bigcheese.jsalon.core.model;

import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * Created by BigCheese on 31.07.15.
 */
public class Client extends Person {

    private Date registrationDate;  //Дата регистрации
    private Discount discount;      //Скидка
    private boolean inBlackList;    //содержится ли в BlackList

    public Date getRegistrationDate() {
        return registrationDate != null ? new Date(registrationDate.getTime()) : null;
    }

    public void setRegistrationDate(Date registrationDate) {
        if (registrationDate != null) {
            this.registrationDate = new Date(registrationDate.getTime());
        }
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public boolean isInBlackList() {
        return inBlackList;
    }

    public void setInBlackList(boolean inBlackList) {
        this.inBlackList = inBlackList;
    }

    @Override
    public String toString() {
        return "Клиент " + getFullFIO() +
                (getContact() != null ? " [телефон " + getContact().getPhone() + "]" : "");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;
        if (!super.equals(o)) return false;
        Client client = (Client) o;
        return Objects.equals(inBlackList, client.inBlackList) &&
                Objects.equals(registrationDate, client.registrationDate) &&
                Objects.equals(discount, client.discount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), registrationDate, discount, inBlackList);
    }

    @Override
    protected List<String> getValidateErrors() {
        List<String> errors = new ArrayList<String>();
        if (StringUtils.isBlank(getName())) {
            errors.add("Введите имя");
        }
        if (StringUtils.isBlank(getSurname())) {
            errors.add("Введите фамилию");
        }
        if (getContact() == null) {
            errors.add("Необходимо указать контактные данные");
        } else {
            errors.addAll(getContact().getValidateErrors());
        }
        if (discount != null) {
            errors.addAll(discount.getValidateErrors());
        }
        return Collections.unmodifiableList(errors);
    }
}
