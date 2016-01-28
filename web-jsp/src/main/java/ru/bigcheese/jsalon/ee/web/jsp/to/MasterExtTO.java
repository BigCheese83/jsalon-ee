package ru.bigcheese.jsalon.ee.web.jsp.to;

import ru.bigcheese.jsalon.core.model.Master;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by BigCheese on 22.01.16.
 */
public class MasterExtTO extends MasterTO {

    private Long passportId;
    private String passportSeries;
    private String passportNumber;
    private String passportIssuedBy;
    private Date passportIssueDate;
    private String passportSubdivision;
    private String passportCountry;
    private Long regId;
    private String regCountry;
    private String regDistrict;
    private String regCity;
    private String regStreet;
    private String regHouse;
    private String regSection;
    private String regFlat;
    private String regZip;
    private Long liveId;
    private String liveCountry;
    private String liveDistrict;
    private String liveCity;
    private String liveStreet;
    private String liveHouse;
    private String liveSection;
    private String liveFlat;
    private String liveZip;
    private Long contactId;
    private String contactPhone;
    private String contactEmail;
    private String contactVk;
    private String contactSkype;
    private String contactFacebook;
    private String contactTwitter;
    private String contactIcq;

    public MasterExtTO(Master master) {
        super(master);
        if (master.getPassport() != null) {
            passportId = master.getPassport().getId();
            passportSeries = master.getPassport().getSeries();
            passportNumber = master.getPassport().getNumber();
            passportIssuedBy = master.getPassport().getIssuedBy();
            passportIssueDate = master.getPassport().getIssueDate();
            passportSubdivision = master.getPassport().getSubdivision();
            passportCountry = master.getPassport().getCountry();
        }
        if (master.getRegAddress() != null) {
            regId = master.getRegAddress().getId();
            regCountry = master.getRegAddress().getCountry();
            regDistrict = master.getRegAddress().getDistrict();
            regCity = master.getRegAddress().getCity();
            regStreet = master.getRegAddress().getStreet();
            regHouse = master.getRegAddress().getHouse();
            regSection = master.getRegAddress().getSection();
            regFlat = master.getRegAddress().getFlat();
            regZip = master.getRegAddress().getZip();
        }
        if (master.getLiveAddress() != null) {
            liveId = master.getLiveAddress().getId();
            liveCountry = master.getLiveAddress().getCountry();
            liveDistrict = master.getLiveAddress().getDistrict();
            liveCity = master.getLiveAddress().getCity();
            liveStreet = master.getLiveAddress().getStreet();
            liveHouse = master.getLiveAddress().getHouse();
            liveSection = master.getLiveAddress().getSection();
            liveFlat = master.getLiveAddress().getFlat();
            liveZip = master.getLiveAddress().getZip();
        }
        if (master.getContact() != null) {
            contactId = master.getContact().getId();
            contactPhone = master.getContact().getPhone();
            contactEmail = master.getContact().getEmail();
            contactVk = master.getContact().getVk();
            contactSkype = master.getContact().getSkype();
            contactFacebook = master.getContact().getFacebook();
            contactTwitter = master.getContact().getTwitter();
            contactIcq = master.getContact().getIcq();
        }
    }

    public Long getPassportId() {
        return passportId;
    }

    public String getPassportSeries() {
        return passportSeries;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public String getPassportIssuedBy() {
        return passportIssuedBy;
    }

    public Date getPassportIssueDate() {
        return passportIssueDate;
    }

    public String getPassportSubdivision() {
        return passportSubdivision;
    }

    public String getPassportCountry() {
        return passportCountry;
    }

    public Long getRegId() {
        return regId;
    }

    public String getRegCountry() {
        return regCountry;
    }

    public String getRegDistrict() {
        return regDistrict;
    }

    public String getRegCity() {
        return regCity;
    }

    public String getRegStreet() {
        return regStreet;
    }

    public String getRegHouse() {
        return regHouse;
    }

    public String getRegSection() {
        return regSection;
    }

    public String getRegFlat() {
        return regFlat;
    }

    public String getRegZip() {
        return regZip;
    }

    public Long getLiveId() {
        return liveId;
    }

    public String getLiveCountry() {
        return liveCountry;
    }

    public String getLiveDistrict() {
        return liveDistrict;
    }

    public String getLiveCity() {
        return liveCity;
    }

    public String getLiveStreet() {
        return liveStreet;
    }

    public String getLiveHouse() {
        return liveHouse;
    }

    public String getLiveSection() {
        return liveSection;
    }

    public String getLiveFlat() {
        return liveFlat;
    }

    public String getLiveZip() {
        return liveZip;
    }

    public Long getContactId() {
        return contactId;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public String getContactVk() {
        return contactVk;
    }

    public String getContactSkype() {
        return contactSkype;
    }

    public String getContactFacebook() {
        return contactFacebook;
    }

    public String getContactTwitter() {
        return contactTwitter;
    }

    public String getContactIcq() {
        return contactIcq;
    }

    public static List<MasterExtTO> toList(List<Master> list) {
        if (list == null) return null;
        List<MasterExtTO> result = new ArrayList<>(list.size());
        for (Master m : list) {
            result.add(new MasterExtTO(m));
        }
        return result;
    }
}
