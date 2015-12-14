package ru.bigcheese.jsalon.ee.dao.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by BigCheese on 04.08.15.
 */
@Entity
@Table(name = "passport")
public class PassportEntity extends BaseEntity {

    private String series = "";
    private String number;
    private String issuedBy;
    private Date issueDate;
    private String subdivision;
    private String country;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PASSPORT_SEQ")
    @SequenceGenerator(name = "PASSPORT_SEQ", sequenceName = "passport_id_seq", allocationSize = 1)
    public Long getId() {
        return super.getId();
    }

    @Column(nullable = false)
    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    @Column(name = "num", nullable = false)
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Column(name = "issued_by", nullable = false)
    public String getIssuedBy() {
        return issuedBy;
    }

    public void setIssuedBy(String issuedBy) {
        this.issuedBy = issuedBy;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "issue_date", nullable = false)
    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public String getSubdivision() {
        return subdivision;
    }

    public void setSubdivision(String subdivision) {
        this.subdivision = subdivision;
    }

    @Column(nullable = false)
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
