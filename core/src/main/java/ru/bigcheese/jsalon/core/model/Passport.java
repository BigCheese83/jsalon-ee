package ru.bigcheese.jsalon.core.model;

import java.util.Date;

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
}
