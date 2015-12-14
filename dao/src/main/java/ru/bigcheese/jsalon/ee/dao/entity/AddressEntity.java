package ru.bigcheese.jsalon.ee.dao.entity;

import javax.persistence.*;

/**
 * Created by BigCheese on 03.08.15.
 */
@Entity
@Table(name = "address")
public class AddressEntity extends BaseEntity {

    private String country;
    private String district;
    private String city;
    private String street;
    private String house;
    private String section;
    private String flat;
    private String zip;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ADDRESS_SEQ")
    @SequenceGenerator(name = "ADDRESS_SEQ", sequenceName = "address_id_seq", allocationSize = 1)
    public Long getId() {
        return super.getId();
    }

    @Column(nullable = false)
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    @Column(nullable = false)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column(nullable = false)
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Column(nullable = false)
    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    @Column(nullable = false)
    public String getFlat() {
        return flat;
    }

    public void setFlat(String flat) {
        this.flat = flat;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
}
