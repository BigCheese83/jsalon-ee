<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="jsalon" uri="http://ru.bigcheese.jsalon" %>
<div id="content">
    <fieldset>
    <legend class="content-title"><i class="fa fa-table"></i> Справочники / Скидки</legend>
    <c:forEach var="message" items="${requestScope.errMessages}">
    <p class="err">${message}</p>
    </c:forEach>
    <div class="content-table">
        <table id="datatable" class="display">
            <thead>
            <tr>
                <th>ID</th>
                <th>Наименование</th>
                <th>Значение %</th>
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
            <label for="name">Наименование</label>
            <input type="text" id="name" name="name" required>
        </div>
        <div class="form-field">
            <label for="value">Значение</label>
            <input type="text" id="value" name="value" required>
        </div>
        <input type="submit" value="Сохранить">
        <input type="hidden" name="id" class="objectID">
        <input type="hidden" name="radioID" id="radioID">
        </form>
    </div>
    </fieldset>
    <input type="hidden" id="dataset" value='<jsalon:json object="${sessionScope.discountsList}"/>'>
</div><!-- End div content -->