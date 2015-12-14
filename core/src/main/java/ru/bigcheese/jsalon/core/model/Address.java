package ru.bigcheese.jsalon.core.model;

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
}