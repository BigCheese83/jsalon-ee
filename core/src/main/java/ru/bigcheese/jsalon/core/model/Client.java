package ru.bigcheese.jsalon.core.model;

import java.util.Date;

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
}
