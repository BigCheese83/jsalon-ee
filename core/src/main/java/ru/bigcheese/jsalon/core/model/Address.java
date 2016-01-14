package ru.bigcheese.jsalon.core.model;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Created by BigCheese on 31.07.15.
 */
public class Address extends BaseModel {

    private String country;     //страна
    private String district;    //район
    private String city;        //город (населенный пункт)
    private String street;      //улица
    private String house;       //дом
    private String section;     //корпус
    private String flat;        //квартира
    private String zip;         //индекс

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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

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

    public String getFullStr() {
        return (StringUtils.isBlank(country) ? "" : country + " ") +
                (StringUtils.isBlank(zip) ? "" : zip + " ") +
                (StringUtils.isBlank(district) ? "" : district + " ") +
                (StringUtils.isBlank(city) ? "" : "г." + city + " ") +
                (StringUtils.isBlank(street) ? "" : "ул." + street + " ") +
                (StringUtils.isBlank(house) ? "" : "д." + house) +
                (StringUtils.isBlank(section) ? "" : section + " ") + " " +
                (StringUtils.isBlank(flat) ? "" : "кв." +  flat);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        Address address = (Address) o;
        return Objects.equals(country, address.country) &&
                Objects.equals(district, address.district) &&
                Objects.equals(city, address.city) &&
                Objects.equals(street, address.street) &&
                Objects.equals(house, address.house) &&
                Objects.equals(section, address.section) &&
                Objects.equals(flat, address.flat) &&
                Objects.equals(zip, address.zip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(country, district, city, street, house, section, flat, zip);
    }

    @Override
    protected List<String> getValidateErrors() {
        List<String> errors = new ArrayList<String>();
        if (StringUtils.isBlank(country)) {
            errors.add("Укажите страну");
        }
        if (StringUtils.isBlank(city)) {
            errors.add("Укажите город (населенный пункт)");
        }
        if (StringUtils.isBlank(street)) {
            errors.add("Укажите улицу");
        }
        if (StringUtils.isBlank(house)) {
            errors.add("Укажите номер дома");
        } else if (!StringUtils.isNumeric(house)) {
            errors.add("Неправильный номер дома");
        }
        if (StringUtils.isBlank(flat)) {
            errors.add("Укажите номер квартиры");
        } else if (!StringUtils.isNumeric(flat)) {
            errors.add("Неправильный номер квартиры");
        }
        return Collections.unmodifiableList(errors);
    }
}
