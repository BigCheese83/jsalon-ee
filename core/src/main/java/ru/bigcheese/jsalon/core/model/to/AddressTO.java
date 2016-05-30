package ru.bigcheese.jsalon.core.model.to;

import ru.bigcheese.jsalon.core.model.Address;

import java.io.Serializable;

/**
 * Created by BigCheese on 03.02.16.
 */
public class AddressTO implements Serializable {

    private String country;
    private String district;
    private String city;
    private String street;
    private String house;
    private String section;
    private String flat;
    private String zip;

    public AddressTO(Address address) {
        country = address.getCountry();
        district = address.getDistrict();
        city = address.getCity();
        street = address.getStreet();
        house = address.getHouse();
        section = address.getSection();
        flat = address.getFlat();
        zip = address.getZip();
    }

    public String getCountry() {
        return country;
    }

    public String getDistrict() {
        return district;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getHouse() {
        return house;
    }

    public String getSection() {
        return section;
    }

    public String getFlat() {
        return flat;
    }

    public String getZip() {
        return zip;
    }
}
