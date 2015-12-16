<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="content">
    <fieldset>
    <legend class="content-title"><i class="fa fa-pencil-square-o"></i> Поиск / Редактирование</legend>
    <table id="search" cellpadding="5">
        <tr>
            <td>
                <label for="sUsername">Логин</label>
                <input type="text" id="sUsername" name="sUsername">
            </td>
            <td>
                <label for="sLastname">Фамилия</label>
                <input type="text" id="sLastname" name="sLastname">
            </td>
            <td>
                <label for="sFirstname">Имя</label>
                <input type="text" id="sFirstname" name="sFirstname">
            </td>
            <td>
                <label for="sMiddlename">Отчество</label>
                <input type="text" id="sMiddlename" name="sMiddlename">
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
                <th>ID</th>
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
    <div id="actionMessage" style="margin-top: 5px;"></div>
    <div id="hiddenForm">
        <form>
            <div class="form-field">
                <label for="userLogin">Логин</label>
                <input type="text" id="userLogin" name="userLogin" required>
            </div>
            <div class="form-field">
                <label for="userLastname">Фамилия</label>
                <input type="text" id="userLastname" name="userLastname" required>
            </div>
            <div class="form-field">
                <label for="userFirstname">Имя</label>
                <input type="text" id="userFirstname" name="userFirstname" required>
            </div>
            <div class="form-field">
                <label for="userMiddlename">Отчество</label>
                <input type="text" id="userMiddlename" name="userMiddlename">
            </div>
            <div class="form-field">
                <label for="userRole">Роль</label>
                <select id="userRole" name="userRole" required>
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
            <input type="hidden" id="userID" name="userID">
            <input type="hidden" name="radioID">
        </form>
    </div>
    </fieldset>
</div><!-- End div content -->
