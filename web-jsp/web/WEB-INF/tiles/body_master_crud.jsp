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
    <div id="actionMessage" style="margin-top:5px;"></div>
    <div id="hiddenForm">
        <form>
        <table>
            <tr>
                <td>
                    <div class="form-field inline-field">
                        <label for="surname">Фамилия</label>
                        <input type="text" id="surname" name="surname" required>
                    </div>
                    <div class="form-field inline-field">
                        <label for="name">Имя</label>
                        <input type="text" id="name" name="name" required>
                    </div>
                    <div class="form-field inline-field">
                        <label for="patronymic">Отчество</label>
                        <input type="text" id="patronymic" name="patronymic">
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <div class="form-field inline-field">
                        <label for="birthDate">Дата рождения</label>
                        <input type="text" id="birthDate" name="birthDate" class="datepicker" required>
                    </div>
                    <div class="form-field inline-field">
                        <label for="hiringDate">Дата приема</label>
                        <input type="text" id="hiringDate" name="hiringDate" class="datepicker" required>
                    </div>
                    <div class="inline-field">
                        <label for="busy">Уволен</label>
                        <input type="checkbox" id="busy" name="busy">
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <div class="form-field inline-field">
                        <label for="postId">Должность</label>
                        <select id="postId" name="post.id" required>
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
                            <label for="passportSeries">Серия</label>
                            <input type="text" id="passportSeries" name="passport.series" style="width:50px">
                        </div>
                        <div class="form-field inline-field">
                            <label for="passportNumber">Номер</label>
                            <input type="text" id="passportNumber" name="passport.number" style="width:100px" required>
                        </div>
                        <div class="form-field inline-field">
                            <label for="passportCountry">Страна выдачи</label>
                            <select id="passportCountry" name="passport.country" required>
                                <option></option>
                            <c:forEach var="country" items="${applicationScope.countriesList}">
                                <option>${country.value}</option>
                            </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div>
                        <div class="form-field inline-field">
                            <label for="passportIssuedBy">Выдан</label>
                            <input type="text" id="passportIssuedBy" name="passport.issuedBy" style="width:570px" required>
                        </div>
                    </div>
                    <div>
                        <div class="form-field inline-field">
                            <label for="passportIssueDate">Дата выдачи</label>
                            <input type="text" id="passportIssueDate" name="passport.issueDate" class="datepicker" required>
                        </div>
                        <div class="form-field inline-field">
                            <label for="passportSubdivision">Код подразделения</label>
                            <input type="text" id="passportSubdivision" name="passport.subdivision" style="width:100px">
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
                            <label for="regAddressCountry">Страна</label>
                            <select id="regAddressCountry" name="regAddress.country" required>
                                <option></option>
                            <c:forEach var="country" items="${applicationScope.countriesList}">
                                <option>${country.value}</option>
                            </c:forEach>
                            </select>
                        </div>
                        <div class="form-field inline-field">
                            <label for="regAddressZip">Индекс</label>
                            <input type="text" id="regAddressZip" name="regAddress.zip" style="width:100px">
                        </div>
                    </div>
                    <div>
                        <div class="form-field inline-field">
                            <label for="regAddressDistrict">Область</label>
                            <input type="text" id="regAddressDistrict" name="regAddress.district">
                        </div>
                        <div class="form-field inline-field">
                            <label for="regAddressCity">Город</label>
                            <input type="text" id="regAddressCity" name="regAddress.city" required>
                        </div>
                    </div>
                    <div>
                        <div class="form-field inline-field">
                            <label for="regAddressStreet">Улица</label>
                            <input type="text" id="regAddressStreet" name="regAddress.street" required>
                        </div>
                        <div class="form-field inline-field">
                            <label for="regAddressHouse">Дом</label>
                            <input type="text" id="regAddressHouse" name="regAddress.house" style="width:50px" required>
                        </div>
                        <div class="form-field inline-field">
                            <label for="regAddressSection">Корпус</label>
                            <input type="text" id="regAddressSection" name="regAddress.section" style="width:50px">
                        </div>
                        <div class="form-field inline-field">
                            <label for="regAddressFlat">Квартира</label>
                            <input type="text" id="regAddressFlat" name="regAddress.flat" style="width:50px" required>
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
                            <label for="liveAddressCountry">Страна</label>
                            <select id="liveAddressCountry" name="liveAddress.country" required>
                                <option></option>
                            <c:forEach var="country" items="${applicationScope.countriesList}">
                                <option>${country.value}</option>
                            </c:forEach>
                            </select>
                        </div>
                        <div class="form-field inline-field">
                            <label for="liveAddressZip">Индекс</label>
                            <input type="text" id="liveAddressZip" name="liveAddress.zip" style="width:100px">
                        </div>
                    </div>
                    <div>
                        <div class="form-field inline-field">
                            <label for="liveAddressDistrict">Область</label>
                            <input type="text" id="liveAddressDistrict" name="liveAddress.district">
                        </div>
                        <div class="form-field inline-field">
                            <label for="liveAddressCity">Город</label>
                            <input type="text" id="liveAddressCity" name="liveAddress.city" required>
                        </div>
                    </div>
                    <div>
                        <div class="form-field inline-field">
                            <label for="liveAddressStreet">Улица</label>
                            <input type="text" id="liveAddressStreet" name="liveAddress.street" required>
                        </div>
                        <div class="form-field inline-field">
                            <label for="liveAddressHouse">Дом</label>
                            <input type="text" id="liveAddressHouse" name="liveAddress.house" style="width:50px" required>
                        </div>
                        <div class="form-field inline-field">
                            <label for="liveAddressSection">Корпус</label>
                            <input type="text" id="liveAddressSection" name="liveAddress.section" style="width:50px">
                        </div>
                        <div class="form-field inline-field">
                            <label for="liveAddressFlat">Квартира</label>
                            <input type="text" id="liveAddressFlat" name="liveAddress.flat" style="width:50px" required>
                        </div>
                    </div>
                    <div>
                        <label for="Identity">Совпадает с адресом регистрации</label>
                        <input type="checkbox" id="Identity" onclick="copyRegToLiveAddress(this.checked)">
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
                            <label for="contactPhone"><i class="fa fa-phone"></i> Телефон</label>
                            <input type="tel" id="contactPhone" name="contact.phone" required>
                        </div>
                        <div class="form-field inline-field">
                            <label for="contactEmail"><i class="fa fa-envelope-o"></i> E-mail</label>
                            <input type="email" id="contactEmail" name="contact.email">
                        </div>
                        <div class="form-field inline-field">
                            <label for="contactIcq">ICQ</label>
                            <input type="text" id="contactIcq" name="contact.icq" style="width:100px">
                        </div>
                    </div>
                    <div>
                        <div class="form-field inline-field">
                            <label for="contactVk"><i class="fa fa-vk"></i> VK</label>
                            <input type="text" id="contactVk" name="contact.vk" style="width:100px">
                        </div>
                        <div class="form-field inline-field">
                            <label for="contactSkype"><i class="fa fa-skype"></i> Skype</label>
                            <input type="text" id="contactSkype" name="contact.skype" style="width:100px">
                        </div>
                        <div class="form-field inline-field">
                            <label for="contactFacebook"><i class="fa fa-facebook"></i> Facebook</label>
                            <input type="text" id="contactFacebook" name="contact.facebook" style="width:100px">
                        </div>
                        <div class="form-field inline-field">
                            <label for="contactTwitter"><i class="fa fa-twitter"></i> Twitter</label>
                            <input type="text" id="contactTwitter" name="contact.twitter" style="width:100px">
                        </div>
                    </div>
                    </fieldset>
                </td>
            </tr>
        </table>
        <input type="submit" value="Сохранить">
        <input type="hidden" name="id" class="objectID">
        <input type="hidden" name="passport.id" class="objectID">
        <input type="hidden" name="regAddress.id" class="objectID">
        <input type="hidden" name="liveAddress.id" class="objectID">
        <input type="hidden" name="contact.id" class="objectID">
        <input type="hidden" id="radioID" name="radioID">
        </form>
    </div>
    </fieldset>
</div><!-- End div content -->