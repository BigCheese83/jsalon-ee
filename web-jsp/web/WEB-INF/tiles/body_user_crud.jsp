<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="content">
    <fieldset>
    <legend class="content-title"><i class="fa fa-pencil-square-o"></i> Поиск / Редактирование</legend>
    <table id="search" cellpadding="5">
        <tr>
            <td>
                <label for="sLogin">Логин</label>
                <input type="text" id="sLogin" name="sLogin">
            </td>
            <td>
                <label for="sLastName">Фамилия</label>
                <input type="text" id="sLastName" name="sLastName">
            </td>
            <td>
                <label for="sFirstName">Имя</label>
                <input type="text" id="sFirstName" name="sFirstName">
            </td>
            <td>
                <label for="sMiddleName">Отчество</label>
                <input type="text" id="sMiddleName" name="sMiddleName">
            </td>
            <td>
                <label for="sRole">Роль</label>
                <input type="text" id="sRole" name="sRole">
            </td>
        </tr>
        <tr><td colspan="0"><button id="searchButton"><i class="fa fa-search"></i> Поиск</button></td></tr>
    </table>
    <div id="searchMessage" class="err" style="margin: 20px 0"></div>
    <div class="content-table">
        <table id="datatable" class="display">
            <thead>
            <tr>
                <th>Логин</th>
                <th>Фамилия</th>
                <th>Имя</th>
                <th>Отчество</th>
                <th>Роль</th>
            </tr>
            </thead>
        </table>
    </div>
    <div id="buttonset">
        <input type="radio" id="newRadio" name="radio"><label for="newRadio">Создать</label>
        <input type="radio" id="editRadio" name="radio"><label for="editRadio">Редактировать</label>
        <input type="radio" id="delRadio" name="radio"><label for="delRadio">Удалить</label>
    </div>
    <div id="deleteConfirm" title="Удаление" >
        <p>Удалить выбранную строку?</p>
    </div>
    <div id="actionMessage" style="margin-top:5px;"></div>
    <div id="hiddenForm">
        <form>
            <div class="form-field">
                <label for="login">Логин</label>
                <input type="text" id="login" name="login" required>
            </div>
            <div class="form-field">
                <label for="lastName">Фамилия</label>
                <input type="text" id="lastName" name="lastName" required>
            </div>
            <div class="form-field">
                <label for="firstName">Имя</label>
                <input type="text" id="firstName" name="firstName" required>
            </div>
            <div class="form-field">
                <label for="middleName">Отчество</label>
                <input type="text" id="middleName" name="middleName">
            </div>
            <div class="form-field">
                <label for="role">Роль</label>
                <select id="role" name="role" required>
                <c:forEach var="r" items="${sessionScope.allRoles}">
                    <option>${r}</option>
                </c:forEach>
                </select>
            </div>
            <div class="form-field edit">
                <label for="oldPassword">Пароль</label>
                <input type="password" id="oldPassword" name="oldPassword">
            </div>
            <div class="form-field">
                <label for="newPassword">Новый пароль</label>
                <input type="password" id="newPassword" name="newPassword">
            </div>
            <div class="form-field">
                <label for="newPassword2">Повторить пароль</label>
                <input type="password" id="newPassword2" name="newPassword2">
            </div>
            <input type="submit" value="Сохранить">
            <input type="hidden" name="id" class="objectID">
            <input type="hidden" id="radioID" name="radioID">
        </form>
    </div>
    </fieldset>
</div><!-- End div content -->
