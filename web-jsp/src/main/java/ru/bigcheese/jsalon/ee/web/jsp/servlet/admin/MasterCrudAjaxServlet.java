package ru.bigcheese.jsalon.ee.web.jsp.servlet.admin;

import com.google.gson.GsonBuilder;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import ru.bigcheese.jsalon.core.Constants;
import ru.bigcheese.jsalon.core.exception.ValidationException;
import ru.bigcheese.jsalon.core.model.*;
import ru.bigcheese.jsalon.core.util.DateUtils;
import ru.bigcheese.jsalon.core.util.ModelUtils;
import ru.bigcheese.jsalon.core.util.NumberUtils;
import ru.bigcheese.jsalon.ee.dao.QueryCriteria;
import ru.bigcheese.jsalon.ee.dao.QueryCriteriaFactory;
import ru.bigcheese.jsalon.ee.ejb.MasterEJBLocal;
import ru.bigcheese.jsalon.ee.ejb.result.CrudEntityResult;
import ru.bigcheese.jsalon.ee.web.jsp.servlet.AbstractAjaxServlet;
import ru.bigcheese.jsalon.ee.web.jsp.to.MasterExtTO;
import ru.bigcheese.jsalon.ee.web.jsp.to.MasterTO;
import ru.bigcheese.jsalon.ee.web.jsp.util.JsonUtils;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static ru.bigcheese.jsalon.core.Constants.ISO_DATE_FORMAT;

/**
 * Created by BigCheese on 24.12.15.
 */
@WebServlet(name = "MasterCrudAjaxServlet", urlPatterns = {"/admin/master/ajax"})
public class MasterCrudAjaxServlet extends AbstractAjaxServlet {

    @EJB
    private MasterEJBLocal masterEJB;

    @Override
    protected String getJsonResponse(HttpServletRequest request) {
        String json;
        try {
            if ("true".equals(request.getParameter("searchRequest"))) {
                json = new GsonBuilder()
                        .serializeNulls()
                        .setDateFormat(Constants.ISO_DATE_FORMAT)
                        .create()
                        .toJson(MasterExtTO.toList(masterEJB.findMastersByCriteria(buildCriteria(request))));
            } else {
                String radioId = request.getParameter("radioID");
                CrudEntityResult result;
                if ("newRadio".equals(radioId)) {
                    Master master = parseRequest(request);
                    master.validate();
                    result = masterEJB.createMaster(master);
                } else if ("editRadio".equals(radioId)) {
                    Master master = parseRequest(request);
                    master.validate();
                    result = masterEJB.updateMaster(master);
                } else if ("delRadio".equals(radioId)) {
                    result = masterEJB.deleteMaster(NumberUtils.toLong(request.getParameter("masterID")));
                } else {
                    throw new Exception("Unknown operation");
                }
                json = JsonUtils.getJsonCrudEjbResult(result);
            }
        } catch (ValidationException e) {
            json = JsonUtils.getJsonValidateErrors(e);
        } catch (Throwable e) {
            json = JsonUtils.getJsonError(e);
        }
        return json;
    }

    private Master parseRequest(HttpServletRequest request) {
        Master master = new Master();
        master.setId(NumberUtils.toLong(request.getParameter("masterID")));
        master.setName(StringUtils.stripToNull(request.getParameter("Firstname")));
        master.setSurname(StringUtils.stripToNull(request.getParameter("Lastname")));
        master.setPatronymic(StringUtils.stripToNull(request.getParameter("Middlename")));
        master.setBirthDate(DateUtils.parse(request.getParameter("Birthdate"), ISO_DATE_FORMAT));
        master.setHiringDate(DateUtils.parse(request.getParameter("HiringDate"), ISO_DATE_FORMAT));
        master.setBusy(BooleanUtils.toBoolean(request.getParameter("Busy")));
        if (StringUtils.isNotBlank(request.getParameter("IssueCountry"))
                || StringUtils.isNotBlank(request.getParameter("Series"))
                || StringUtils.isNotBlank(request.getParameter("Number"))
                || StringUtils.isNotBlank(request.getParameter("IssuedBy"))
                || StringUtils.isNotBlank(request.getParameter("IssueDate"))
                || StringUtils.isNotBlank(request.getParameter("Subdivision"))) {
            master.setPassport(parsePassport(request));
        }
        if (StringUtils.isNotBlank(request.getParameter("Post"))) {
            Map<Long, Post> posts = ModelUtils.transform((List<Post>)request.getSession().getAttribute("postsList"));
            if (posts != null) {
                master.setPost(posts.get(NumberUtils.toLong(request.getParameter("Post"), 0L)));
            }
        }
        if (StringUtils.isNotBlank(request.getParameter("RegCountry"))
                || StringUtils.isNotBlank(request.getParameter("RegZip"))
                || StringUtils.isNotBlank(request.getParameter("RegDistrict"))
                || StringUtils.isNotBlank(request.getParameter("RegCity"))
                || StringUtils.isNotBlank(request.getParameter("RegStreet"))
                || StringUtils.isNotBlank(request.getParameter("RegHouse"))
                || StringUtils.isNotBlank(request.getParameter("RegSection"))
                || StringUtils.isNotBlank(request.getParameter("RegFlat"))) {
            master.setRegAddress(parseRegAddress(request));
        }
        if (StringUtils.isNotBlank(request.getParameter("LiveCountry"))
                || StringUtils.isNotBlank(request.getParameter("LiveZip"))
                || StringUtils.isNotBlank(request.getParameter("LiveDistrict"))
                || StringUtils.isNotBlank(request.getParameter("LiveCity"))
                || StringUtils.isNotBlank(request.getParameter("LiveStreet"))
                || StringUtils.isNotBlank(request.getParameter("LiveHouse"))
                || StringUtils.isNotBlank(request.getParameter("LiveSection"))
                || StringUtils.isNotBlank(request.getParameter("LiveFlat"))) {
            master.setLiveAddress(parseLiveAddress(request));
        }
        if (StringUtils.isNotBlank(request.getParameter("Phone"))
                || StringUtils.isNotBlank(request.getParameter("Email"))
                || StringUtils.isNotBlank(request.getParameter("ICQ"))
                || StringUtils.isNotBlank(request.getParameter("VK"))
                || StringUtils.isNotBlank(request.getParameter("Skype"))
                || StringUtils.isNotBlank(request.getParameter("Facebook"))
                || StringUtils.isNotBlank(request.getParameter("Twitter"))) {
            master.setContact(parseContact(request));
        }
        return master;
    }

    private Contact parseContact(HttpServletRequest request) {
        Contact contact = new Contact();
        contact.setId(NumberUtils.toLong(request.getParameter("contactID")));
        contact.setPhone(StringUtils.stripToNull(request.getParameter("Phone")));
        contact.setEmail(StringUtils.stripToNull(request.getParameter("Email")));
        contact.setIcq(StringUtils.stripToNull(request.getParameter("ICQ")));
        contact.setVk(StringUtils.stripToNull(request.getParameter("VK")));
        contact.setSkype(StringUtils.stripToNull(request.getParameter("Skype")));
        contact.setFacebook(StringUtils.stripToNull(request.getParameter("Facebook")));
        contact.setTwitter(StringUtils.stripToNull(request.getParameter("Twitter")));
        return contact;
    }

    private Address parseLiveAddress(HttpServletRequest request) {
        Address address = new Address();
        address.setId(NumberUtils.toLong(request.getParameter("regAddressID")));
        address.setCountry(StringUtils.stripToNull(request.getParameter("LiveCountry")));
        address.setZip(StringUtils.stripToNull(request.getParameter("LiveZip")));
        address.setDistrict(StringUtils.stripToNull(request.getParameter("LiveDistrict")));
        address.setCity(StringUtils.stripToNull(request.getParameter("LiveCity")));
        address.setStreet(StringUtils.stripToNull(request.getParameter("LiveStreet")));
        address.setHouse(StringUtils.stripToNull(request.getParameter("LiveHouse")));
        address.setSection(StringUtils.stripToNull(request.getParameter("LiveSection")));
        address.setFlat(StringUtils.stripToNull(request.getParameter("LiveFlat")));
        return address;
    }

    private Address parseRegAddress(HttpServletRequest request) {
        Address address = new Address();
        address.setId(NumberUtils.toLong(request.getParameter("liveAddressID")));
        address.setCountry(StringUtils.stripToNull(request.getParameter("RegCountry")));
        address.setZip(StringUtils.stripToNull(request.getParameter("RegZip")));
        address.setDistrict(StringUtils.stripToNull(request.getParameter("RegDistrict")));
        address.setCity(StringUtils.stripToNull(request.getParameter("RegCity")));
        address.setStreet(StringUtils.stripToNull(request.getParameter("RegStreet")));
        address.setHouse(StringUtils.stripToNull(request.getParameter("RegHouse")));
        address.setSection(StringUtils.stripToNull(request.getParameter("RegSection")));
        address.setFlat(StringUtils.stripToNull(request.getParameter("RegFlat")));
        return address;
    }

    private Passport parsePassport(HttpServletRequest request) {
        Passport passport = new Passport();
        passport.setId(NumberUtils.toLong(request.getParameter("passportID")));
        passport.setCountry(StringUtils.stripToNull(request.getParameter("IssueCountry")));
        passport.setSeries(StringUtils.stripToNull(request.getParameter("Series")));
        passport.setNumber(StringUtils.stripToNull(request.getParameter("Number")));
        passport.setIssuedBy(StringUtils.stripToNull(request.getParameter("IssuedBy")));
        passport.setIssueDate(DateUtils.parse(request.getParameter("IssueDate"), ISO_DATE_FORMAT));
        passport.setSubdivision(StringUtils.stripToNull(request.getParameter("Subdivision")));
        return passport;
    }

    private QueryCriteria buildCriteria(HttpServletRequest request) throws ValidationException {
        Map<String, String> params = new HashMap<>();

        String surname = request.getParameter("sSurname");
        String name = request.getParameter("sName");
        String patronymic = request.getParameter("sPatronymic");
        String birthDate = request.getParameter("sBirthDate");
        String hiringDate = request.getParameter("sHiringDate");

        if (StringUtils.isNotBlank(surname)) {
            params.put("surname", surname);
        }
        if (StringUtils.isNotBlank(name)) {
            params.put("name", name);
        }
        if (StringUtils.isNotBlank(patronymic)) {
            params.put("patronymic", patronymic);
        }
        if (StringUtils.isNotBlank(birthDate)) {
            params.put("birth_date", birthDate);
        }
        if (StringUtils.isNotBlank(hiringDate)) {
            params.put("hiring_date", hiringDate);
        }

        if (params.isEmpty()) {
            throw new ValidationException("All search params is empty. Enter at least one parameter!");
        }

        QueryCriteria criteria = QueryCriteriaFactory.getInstance();
        criteria.where();
        Iterator<Map.Entry<String, String>> iter = params.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<String, String> entry = iter.next();
            criteria.equal(entry.getKey(), entry.getValue());
            if (iter.hasNext()) {
                criteria.and();
            }
        }
        return criteria;
    }
}
