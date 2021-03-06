package ru.bigcheese.jsalon.ee.web.jsp.servlet.admin;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.bigcheese.jsalon.core.exception.ValidationException;
import ru.bigcheese.jsalon.core.model.*;
import ru.bigcheese.jsalon.core.util.DateUtils;
import ru.bigcheese.jsalon.core.util.ModelUtils;
import ru.bigcheese.jsalon.core.util.NumberUtils;
import ru.bigcheese.jsalon.ee.dao.QueryCriteria;
import ru.bigcheese.jsalon.ee.dao.QueryCriteriaFactory;
import ru.bigcheese.jsalon.ee.ejb.MasterFacade;
import ru.bigcheese.jsalon.ee.ejb.result.CrudEntityResult;
import ru.bigcheese.jsalon.ee.web.jsp.servlet.AbstractAjaxServlet;
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
    private static final Logger LOG = LoggerFactory.getLogger(MasterCrudAjaxServlet.class);

    @EJB
    private MasterFacade masterFacade;

    @Override
    protected String getJsonResponse(HttpServletRequest request) {
        String json;
        try {
            if ("true".equals(request.getParameter("searchRequest"))) {
                json = JsonUtils.getGson().toJson(masterFacade.findMastersByCriteria(buildCriteria(request)));
            } else {
                String radioId = request.getParameter("radioID");
                CrudEntityResult<Master> result;
                if ("newRadio".equals(radioId)) {
                    Master master = parseRequest(request);
                    master.validate();
                    result = masterFacade.createMaster(master);
                } else if ("editRadio".equals(radioId)) {
                    Master master = parseRequest(request);
                    master.validate();
                    result = masterFacade.updateMaster(master);
                } else if ("delRadio".equals(radioId)) {
                    result = masterFacade.deleteMaster(NumberUtils.toLong(request.getParameter("id")));
                } else {
                    throw new Exception("Unknown operation");
                }
                json = JsonUtils.getGson().toJson(result);
            }
        } catch (ValidationException e) {
            LOG.error("Validation failed. {}.", e.getMessage());
            json = JsonUtils.getJsonValidateErrors(e);
        } catch (Throwable e) {
            LOG.error("Error.", e);
            json = JsonUtils.getJsonException(e);
        }
        return json;
    }

    private Master parseRequest(HttpServletRequest request) {
        Master master = new Master();
        master.setId(NumberUtils.toLong(request.getParameter("id")));
        master.setName(StringUtils.stripToNull(request.getParameter("name")));
        master.setSurname(StringUtils.stripToNull(request.getParameter("surname")));
        master.setPatronymic(StringUtils.stripToNull(request.getParameter("patronymic")));
        master.setBirthDate(DateUtils.parse(request.getParameter("birthDate"), ISO_DATE_FORMAT));
        master.setHiringDate(DateUtils.parse(request.getParameter("hiringDate"), ISO_DATE_FORMAT));
        master.setBusy(BooleanUtils.toBoolean(request.getParameter("busy")));
        if (StringUtils.isNotBlank(request.getParameter("passport.country"))
                || StringUtils.isNotBlank(request.getParameter("passport.series"))
                || StringUtils.isNotBlank(request.getParameter("passport.number"))
                || StringUtils.isNotBlank(request.getParameter("passport.issuedBy"))
                || StringUtils.isNotBlank(request.getParameter("passport.issueDate"))
                || StringUtils.isNotBlank(request.getParameter("passport.subdivision"))) {
            master.setPassport(parsePassport(request));
        }
        if (StringUtils.isNotBlank(request.getParameter("post.id"))) {
            Map<Long, Post> posts = ModelUtils.transform((List<Post>)request.getSession().getAttribute("postsList"));
            if (posts != null) {
                master.setPost(posts.get(NumberUtils.toLong(request.getParameter("post.id"), 0L)));
            }
        }
        if (StringUtils.isNotBlank(request.getParameter("regAddress.country"))
                || StringUtils.isNotBlank(request.getParameter("regAddress.zip"))
                || StringUtils.isNotBlank(request.getParameter("regAddress.district"))
                || StringUtils.isNotBlank(request.getParameter("regAddress.city"))
                || StringUtils.isNotBlank(request.getParameter("regAddress.street"))
                || StringUtils.isNotBlank(request.getParameter("regAddress.house"))
                || StringUtils.isNotBlank(request.getParameter("regAddress.section"))
                || StringUtils.isNotBlank(request.getParameter("regAddress.flat"))) {
            master.setRegAddress(parseRegAddress(request));
        }
        if (StringUtils.isNotBlank(request.getParameter("liveAddress.country"))
                || StringUtils.isNotBlank(request.getParameter("liveAddress.zip"))
                || StringUtils.isNotBlank(request.getParameter("liveAddress.district"))
                || StringUtils.isNotBlank(request.getParameter("liveAddress.city"))
                || StringUtils.isNotBlank(request.getParameter("liveAddress.street"))
                || StringUtils.isNotBlank(request.getParameter("liveAddress.house"))
                || StringUtils.isNotBlank(request.getParameter("liveAddress.section"))
                || StringUtils.isNotBlank(request.getParameter("liveAddress.flat"))) {
            master.setLiveAddress(parseLiveAddress(request));
        }
        if (StringUtils.isNotBlank(request.getParameter("contact.phone"))
                || StringUtils.isNotBlank(request.getParameter("contact.email"))
                || StringUtils.isNotBlank(request.getParameter("contact.icq"))
                || StringUtils.isNotBlank(request.getParameter("contact.vk"))
                || StringUtils.isNotBlank(request.getParameter("contact.skype"))
                || StringUtils.isNotBlank(request.getParameter("contact.facebook"))
                || StringUtils.isNotBlank(request.getParameter("contact.twitter"))) {
            master.setContact(parseContact(request));
        }
        return master;
    }

    private Contact parseContact(HttpServletRequest request) {
        Contact contact = new Contact();
        contact.setId(NumberUtils.toLong(request.getParameter("contact.id")));
        contact.setPhone(StringUtils.stripToNull(request.getParameter("contact.phone")));
        contact.setEmail(StringUtils.stripToNull(request.getParameter("contact.email")));
        contact.setIcq(StringUtils.stripToNull(request.getParameter("contact.icq")));
        contact.setVk(StringUtils.stripToNull(request.getParameter("contact.vk")));
        contact.setSkype(StringUtils.stripToNull(request.getParameter("contact.skype")));
        contact.setFacebook(StringUtils.stripToNull(request.getParameter("contact.facebook")));
        contact.setTwitter(StringUtils.stripToNull(request.getParameter("contact.twitter")));
        return contact;
    }

    private Address parseLiveAddress(HttpServletRequest request) {
        Address address = new Address();
        address.setId(NumberUtils.toLong(request.getParameter("liveAddress.id")));
        address.setCountry(StringUtils.stripToNull(request.getParameter("liveAddress.country")));
        address.setZip(StringUtils.stripToNull(request.getParameter("liveAddress.zip")));
        address.setDistrict(StringUtils.stripToNull(request.getParameter("liveAddress.district")));
        address.setCity(StringUtils.stripToNull(request.getParameter("liveAddress.city")));
        address.setStreet(StringUtils.stripToNull(request.getParameter("liveAddress.street")));
        address.setHouse(StringUtils.stripToNull(request.getParameter("liveAddress.house")));
        address.setSection(StringUtils.stripToNull(request.getParameter("liveAddress.section")));
        address.setFlat(StringUtils.stripToNull(request.getParameter("liveAddress.flat")));
        return address;
    }

    private Address parseRegAddress(HttpServletRequest request) {
        Address address = new Address();
        address.setId(NumberUtils.toLong(request.getParameter("regAddress.id")));
        address.setCountry(StringUtils.stripToNull(request.getParameter("regAddress.country")));
        address.setZip(StringUtils.stripToNull(request.getParameter("regAddress.zip")));
        address.setDistrict(StringUtils.stripToNull(request.getParameter("regAddress.district")));
        address.setCity(StringUtils.stripToNull(request.getParameter("regAddress.city")));
        address.setStreet(StringUtils.stripToNull(request.getParameter("regAddress.street")));
        address.setHouse(StringUtils.stripToNull(request.getParameter("regAddress.house")));
        address.setSection(StringUtils.stripToNull(request.getParameter("regAddress.section")));
        address.setFlat(StringUtils.stripToNull(request.getParameter("regAddress.flat")));
        return address;
    }

    private Passport parsePassport(HttpServletRequest request) {
        Passport passport = new Passport();
        passport.setId(NumberUtils.toLong(request.getParameter("passport.id")));
        passport.setCountry(StringUtils.stripToNull(request.getParameter("passport.country")));
        passport.setSeries(StringUtils.stripToNull(request.getParameter("passport.series")));
        passport.setNumber(StringUtils.stripToNull(request.getParameter("passport.number")));
        passport.setIssuedBy(StringUtils.stripToNull(request.getParameter("passport.issuedBy")));
        passport.setIssueDate(DateUtils.parse(request.getParameter("passport.issueDate"), ISO_DATE_FORMAT));
        passport.setSubdivision(StringUtils.stripToNull(request.getParameter("passport.subdivision")));
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
