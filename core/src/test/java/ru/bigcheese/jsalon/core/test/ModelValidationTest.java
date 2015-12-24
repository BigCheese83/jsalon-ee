package ru.bigcheese.jsalon.core.test;

import org.junit.Test;
import ru.bigcheese.jsalon.core.exception.ValidationException;
import ru.bigcheese.jsalon.core.model.*;

import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.fail;

/**
 * Created by BigCheese on 24.12.15.
 */
public class ModelValidationTest {

    @Test
    public void testValidate() {
        final Object[] expected = {
                "Введите имя", "Введите фамилию", "Укажите дату рождения", "Укажите номер паспорта", "Укажите дату выдачи",
                "Укажите страну", "Укажите город (населенный пункт)", "Укажите улицу", "Укажите номер дома",
                "Укажите номер квартиры", "Укажите номер телефона", "Укажите дату приема"
        };
        Master master = new Master();
        Passport passport = new Passport();
        passport.setCountry("Россия");
        passport.setIssuedBy("ОВД г.Москвы №1323-23");
        master.setPassport(passport);
        master.setRegAddress(new Address());
        master.setPost(new Post(null, "Массажист"));
        master.setContact(new Contact());
        try {
            master.validate();
            fail("Must thrown ValidateException");
        } catch (ValidationException e) {
            List<String> errors = e.getMessages();
            assertArrayEquals(expected, errors.toArray());
        }
    }
}
