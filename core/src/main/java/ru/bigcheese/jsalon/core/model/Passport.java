package ru.bigcheese.jsalon.core.model;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by BigCheese on 31.07.15.
 */
public class Passport extends BaseModel {

    private String series;      //серия
    private String number;      //номер
    private String issuedBy;    //кем выдан
    private Date issueDate;     //дата выдачи
    private String subdivision; //код подразделения, выдавшего документ
    private String country;     //страна, в которой выдан документ

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getIssuedBy() {
        return issuedBy;
    }

    public void setIssuedBy(String issuedBy) {
        this.issuedBy = issuedBy;
    }

    public Date getIssueDate() {
        return new Date(issueDate.getTime());
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = new Date(issueDate.getTime());
    }

    public String getSubdivision() {
        return subdivision;
    }

    public void setSubdivision(String subdivision) {
        this.subdivision = subdivision;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    protected List<String> getValidateErrors() {
        List<String> errors = new ArrayList<String>();
        if (StringUtils.isBlank(number)) {
            errors.add("Укажите номер паспорта");
        }
        if (StringUtils.isBlank(issuedBy)) {
            errors.add("Укажите, кем выдан паспорт");
        }
        if (issueDate == null) {
            errors.add("Укажите дату выдачи");
        }
        if (StringUtils.isBlank(country)) {
            errors.add("Укажите страну, в которой выдан паспорт");
        }
        return Collections.unmodifiableList(errors);
    }
}
