package ru.bigcheese.jsalon.core.model.to;

import ru.bigcheese.jsalon.core.model.Passport;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by BigCheese on 03.02.16.
 */
public class PassportTO implements Serializable {

    private String series;
    private String number;
    private String issuedBy;
    private Date issueDate;

    public PassportTO(Passport passport) {
        series = passport.getSeries();
        number = passport.getNumber();
        issuedBy = passport.getIssuedBy();
        issueDate = passport.getIssueDate();
    }

    public String getSeries() {
        return series;
    }

    public String getNumber() {
        return number;
    }

    public String getIssuedBy() {
        return issuedBy;
    }

    public Date getIssueDate() {
        return issueDate;
    }
}
