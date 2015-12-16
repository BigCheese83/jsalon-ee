<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <tbody>
            <c:forEach var="discount" items="${sessionScope.discountsList}">
                <tr><td>${discount.id}</td><td>${discount.name}</td><td>${discount.value}</td></tr>
            </c:forEach>
            </tbody>
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
            <label for="discountName">Наименование</label>
            <input type="text" id="discountName" name="discountName" required>
        </div>
        <div class="form-field">
            <label for="discountValue">Значение</label>
            <input type="text" id="discountValue" name="discountValue" required>
        </div>
        <input type="submit" value="Сохранить">
        <input type="hidden" id="discountID" name="discountID">
        <input type="hidden" name="radioID">
        </form>
    </div>
    </fieldset>
</div><!-- End div content -->