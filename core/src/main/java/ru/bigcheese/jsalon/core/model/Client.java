package ru.bigcheese.jsalon.core.model;

import java.util.*;

/**
 * Created by BigCheese on 31.07.15.
 */
public class Client extends Person {

    private Date registrationDate;
    private Discount discount;
    private boolean inBlackList;

    public Date getRegistrationDate() {
        return new Date(registrationDate.getTime());
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = new Date(registrationDate.getTime());
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
        return "Клиент " + getFullFIO() + " [паспорт " + getPassport().getShortStr() + "]";
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
        errors.addAll(super.getValidateErrors());
        if (registrationDate == null) {
            errors.add("Укажите дату регистрации");
        }
        if (discount != null) {
            errors.addAll(discount.getValidateErrors());
        }
        return Collections.unmodifiableList(errors);
    }
}
