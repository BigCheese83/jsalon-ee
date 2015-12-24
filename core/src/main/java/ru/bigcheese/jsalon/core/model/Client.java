package ru.bigcheese.jsalon.core.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

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
