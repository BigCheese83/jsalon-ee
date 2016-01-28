<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="content">
    <fieldset>
    <legend class="content-title"><i class="fa fa-pencil-square-o"></i> Поиск / Редактирование</legend>
    <table id="search" cellpadding="5">
        <tr>
            <td>
                <label for="sSurname">Фамилия</label>
                <input type="text" id="sSurname" name="sSurname">
            </td>
            <td>
                <label for="sName">Имя</label>
                <input type="text" id="sName" name="sName">
            </td>
            <td>
                <label for="sPatronymic">Отчество</label>
                <input type="text" id="sPatronymic" name="sPatronymic">
            </td>
            <td>
                <label for="sBirthDate">Дата рождения</label>
                <input type="text" id="sBirthDate" name="sBirthDate" class="datepicker">
            </td>
            <td>
                <label for="sHiringDate">Дата приема</label>
                <input type="text" id="sHiringDate" name="sHiringDate" class="datepicker">
            </td>
        </tr>
        <tr><td colspan="0"><button id="searchButton"><i class="fa fa-search"></i> Поиск</button></td></tr>
    </table>
    <div id="searchMessage" class="err" style="margin:20px 0"></div>
    <div class="content-table">
        <table id="datatable" class="display">
            <thead>
            <tr>
                <th></th>
                <th>ID</th>
                <th>Фамилия</th>
                <th>Имя</th>
                <th>Отчество</th>
                <th>Дата рождения</th>
                <th>Дата приема</th>
                <th>Должность</th>
                <th>Уволен</th>
            </tr>
            </thead>
        </table>
    </div>
    <div id="buttonset">
        <input type="radio" id="newRadio" name="radio"><label for="newRadio">Создать</label>
        <input type="radio" id="editRadio" name="radio"><label for="editRadio">Редактировать</label>
        <input type="radio" id="delRadio" name="radio"><label for="delRadio">Удалить</label>
    </div>
    <div id="deleteConfirm" title="Удаление">
        <p>Удалить выбранную строку?</p>
    </div>
    <div id="actionMessage" style="margin-top: 5px;"></div>
    <div id="hiddenForm">
        <form>
        <table>
            <tr>
                <td>
                    <div class="form-field inline-field">
                        <label for="Lastname">Фамилия</label>
                        <input type="text" id="Lastname" name="Lastname" required>
                    </div>
                    <div class="form-field inline-field">
                        <label for="Firstname">Имя</label>
                        <input type="text" id="Firstname" name="Firstname" required>
                    </div>
                    <div class="form-field inline-field">
                        <label for="Middlename">Отчество</label>
                        <input type="text" id="Middlename" name="Middlename">
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <div class="form-field inline-field">
                        <label for="Birthdate">Дата рождения</label>
                        <input type="text" id="Birthdate" name="Birthdate" class="datepicker" required>
                    </div>
                    <div class="form-field inline-field">
                        <label for="HiringDate">Дата приема</label>
                        <input type="text" id="HiringDate" name="HiringDate" class="datepicker" required>
                    </div>
                    <div class="inline-field">
                        <label for="Busy">Уволен</label>
                        <input type="checkbox" id="Busy" name="Busy">
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <div class="form-field inline-field">
                        <label for="Post">Должность</label>
                        <select id="Post" name="Post" required>
                            <option selected></option>
                        <c:forEach var="p" items="${sessionScope.postsList}">
                            <option value="${p.id}">${p.name}</option>
                        </c:forEach>
                        </select>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <fieldset>
                    <legend>Паспорт</legend>
                    <div>
                        <div class="form-field inline-field">
                            <label for="Series">Серия</label>
                            <input type="text" id="Series" name="Series" style="width:50px">
                        </div>
                        <div class="form-field inline-field">
                            <label for="Number">Номер</label>
                            <input type="text" id="Number" name="Number" style="width:100px" required>
                        </div>
                        <div class="form-field inline-field">
                            <label for="IssueCountry">Страна выдачи</label>
                            <select id="IssueCountry" name="IssueCountry" required>
                                <option></option>
                            <c:forEach var="country" items="${applicationScope.countriesList}">
                                <option>${country.value}</option>
                            </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div>
                        <div class="form-field inline-field">
                            <label for="IssuedBy">Выдан</label>
                            <input type="text" id="IssuedBy" name="IssuedBy" style="width:570px" required>
                        </div>
                    </div>
                    <div>
                        <div class="form-field inline-field">
                            <label for="IssueDate">Дата выдачи</label>
                            <input type="text" id="IssueDate" name="IssueDate" class="datepicker" required>
                        </div>
                        <div class="form-field inline-field">
                            <label for="Subdivision">Код подразделения</label>
                            <input type="text" id="Subdivision" name="Subdivision" style="width:100px">
                        </div>
                    </div>
                    </fieldset>
                </td>
            </tr>
            <tr>
                <td>
                    <fieldset>
                    <legend>Адрес регистрации</legend>
                    <div>
                        <div class="form-field inline-field">
                            <label for="RegCountry">Страна</label>
                            <select id="RegCountry" name="RegCountry" required>
                                <option></option>
                            <c:forEach var="country" items="${applicationScope.countriesList}">
                                <option>${country.value}</option>
                            </c:forEach>
                            </select>
                        </div>
                        <div class="form-field inline-field">
                            <label for="RegZip">Индекс</label>
                            <input type="text" id="RegZip" name="RegZip" style="width:100px">
                        </div>
                    </div>
                    <div>
                        <div class="form-field inline-field">
                            <label for="RegDistrict">Область</label>
                            <input type="text" id="RegDistrict" name="RegDistrict">
                        </div>
                        <div class="form-field inline-field">
                            <label for="RegCity">Город</label>
                            <input type="text" id="RegCity" name="RegCity" required>
                        </div>
                    </div>
                    <div>
                        <div class="form-field inline-field">
                            <label for="RegStreet">Улица</label>
                            <input type="text" id="RegStreet" name="RegStreet" required>
                        </div>
                        <div class="form-field inline-field">
                            <label for="RegHouse">Дом</label>
                            <input type="text" id="RegHouse" name="RegHouse" style="width:50px" required>
                        </div>
                        <div class="form-field inline-field">
                            <label for="RegSection">Корпус</label>
                            <input type="text" id="RegSection" name="RegSection" style="width:50px">
                        </div>
                        <div class="form-field inline-field">
                            <label for="RegFlat">Квартира</label>
                            <input type="text" id="RegFlat" name="RegFlat" style="width:50px" required>
                        </div>
                    </div>
                    </fieldset>
                </td>
            </tr>
            <tr>
                <td>
                    <fieldset>
                    <legend>Адрес проживания</legend>
                    <div>
                        <div class="form-field inline-field">
                            <label for="LiveCountry">Страна</label>
                            <select id="LiveCountry" name="LiveCountry" required>
                                <option></option>
                            <c:forEach var="country" items="${applicationScope.countriesList}">
                                <option>${country.value}</option>
                            </c:forEach>
                            </select>
                        </div>
                        <div class="form-field inline-field">
                            <label for="LiveZip">Индекс</label>
                            <input type="text" id="LiveZip" name="LiveZip" style="width:100px">
                        </div>
                    </div>
                    <div>
                        <div class="form-field inline-field">
                            <label for="LiveDistrict">Область</label>
                            <input type="text" id="LiveDistrict" name="LiveDistrict">
                        </div>
                        <div class="form-field inline-field">
                            <label for="LiveCity">Город</label>
                            <input type="text" id="LiveCity" name="LiveCity" required>
                        </div>
                    </div>
                    <div>
                        <div class="form-field inline-field">
                            <label for="LiveStreet">Улица</label>
                            <input type="text" id="LiveStreet" name="LiveStreet" required>
                        </div>
                        <div class="form-field inline-field">
                            <label for="LiveHouse">Дом</label>
                            <input type="text" id="LiveHouse" name="LiveHouse" style="width:50px" required>
                        </div>
                        <div class="form-field inline-field">
                            <label for="LiveSection">Корпус</label>
                            <input type="text" id="LiveSection" name="LiveSection" style="width:50px">
                        </div>
                        <div class="form-field inline-field">
                            <label for="LiveFlat">Квартира</label>
                            <input type="text" id="LiveFlat" name="LiveFlat" style="width:50px" required>
                        </div>
                    </div>
                    <div>
                        <label for="LiveIdentity">Совпадает с адресом регистрации</label>
                        <input type="checkbox" id="LiveIdentity" name="LiveIdentity" onclick="copyRegToLiveAddress(this.checked)">
                    </div>
                    </fieldset>
                </td>
            </tr>
            <tr>
                <td>
                    <fieldset>
                    <legend>Контакты</legend>
                    <div>
                        <div class="form-field inline-field">
                            <label for="Phone"><i class="fa fa-phone"></i> Телефон</label>
                            <input type="tel" id="Phone" name="Phone" required>
                        </div>
                        <div class="form-field inline-field">
                            <label for="Email"><i class="fa fa-envelope-o"></i> E-mail</label>
                            <input type="email" id="Email" name="Email">
                        </div>
                        <div class="form-field inline-field">
                            <label for="ICQ">ICQ</label>
                            <input type="text" id="ICQ" name="ICQ" style="width:100px">
                        </div>
                    </div>
                    <div>
                        <div class="form-field inline-field">
                            <label for="VK"><i class="fa fa-vk"></i> VK</label>
                            <input type="text" id="VK" name="VK" style="width:100px">
                        </div>
                        <div class="form-field inline-field">
                            <label for="Skype"><i class="fa fa-skype"></i> Skype</label>
                            <input type="text" id="Skype" name="Skype" style="width:100px">
                        </div>
                        <div class="form-field inline-field">
                            <label for="Facebook"><i class="fa fa-facebook"></i> Facebook</label>
                            <input type="text" id="Facebook" name="Facebook" style="width:100px">
                        </div>
                        <div class="form-field inline-field">
                            <label for="Twitter"><i class="fa fa-twitter"></i> Twitter</label>
                            <input type="text" id="Twitter" name="Twitter" style="width:100px">
                        </div>
                    </div>
                    </fieldset>
                </td>
            </tr>
        </table>
        <input type="submit" value="Сохранить">
        <input type="hidden" id="masterID" name="masterID">
        <input type="hidden" id="passportID" name="passportID">
        <input type="hidden" id="regAddressID" name="regAddressID">
        <input type="hidden" id="liveAddressID" name="liveAddressID">
        <input type="hidden" id="contactID" name="contactID">
        <input type="hidden" name="radioID">
        </form>
    </div>
    </fieldset>
</div><!-- End div content -->