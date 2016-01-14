package ru.bigcheese.jsalon.core.model;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Created by BigCheese on 13.08.15.
 */
public class User extends BaseModel {

    private String login;
    private String firstName;
    private String lastName;
    private String middleName;
    private String role;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFirstName() {
        return firstName != null ? firstName : "";
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName != null ? lastName : "";
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName != null ? middleName : "";
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getFullFIO() {
        return (getLastName() + " " + getFirstName() + " " + getMiddleName()).trim();
    }

    public String getShortFIO() {
        return StringUtils.isBlank(lastName) ? "" : (lastName + " " + getInitial(firstName) + getInitial(middleName)).trim();
    }

    private String getInitial(String name) {
        return StringUtils.isBlank(name) ? "" : String.valueOf(name.charAt(0)).toUpperCase() + ".";
    }

    @Override
    public String toString() {
        return "Пользователь " + login + " {" + getFullFIO() + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(login, user.login) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(middleName, user.middleName) &&
                Objects.equals(role, user.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, firstName, lastName, middleName, role);
    }

    @Override
    protected List<String> getValidateErrors() {
        List<String> errors = new ArrayList<String>();
        if (StringUtils.isBlank(login)) {
            errors.add("Введите логин пользователя");
        }
        if (StringUtils.isBlank(firstName)) {
            errors.add("Введите имя пользователя");
        }
        if (StringUtils.isBlank(lastName)) {
            errors.add("Введите фамилию пользователя");
        }
        if (StringUtils.isBlank(role)) {
            errors.add("Укажите роль пользователя");
        }
        return Collections.unmodifiableList(errors);
    }
}
