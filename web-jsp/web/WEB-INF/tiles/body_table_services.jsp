<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="content">
    <fieldset>
    <legend class="content-title"><i class="fa fa-table"></i> Справочники / Услуги</legend>
    <c:forEach var="message" items="${requestScope.errMessages}">
    <p class="err">${message}</p>
    </c:forEach>
    <div class="content-table">
        <table id="datatable" class="display">
            <thead>
            <tr>
                <th>ID</th>
                <th>Наименование</th>
                <th>Цена, руб.</th>
                <th>Длительность, мин.</th>
                <th>Описание</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="service" items="${sessionScope.servicesList}">
                <tr><td>${service.id}</td><td>${service.name}</td><td>${service.cost}</td><td>${service.duration}</td><td>${service.description}</td></tr>
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
                <label for="serviceName">Наименование</label>
                <input type="text" id="serviceName" name="serviceName">
            </div>
            <div class="form-field">
                <label for="serviceCost">Цена</label>
                <input type="text" id="serviceCost" name="serviceCost">
            </div>
            <div class="form-field">
                <label for="serviceDuration">Длительность</label>
                <input type="text" id="serviceDuration" name="serviceDuration">
            </div>
            <div class="form-field">
                <label for="serviceDescription">Описание</label>
                <input type="text" id="serviceDescription" name="serviceDescription">
            </div>
            <input type="submit" value="Сохранить">
            <input type="hidden" id="serviceID" name="serviceID">
            <input type="hidden" name="radioID">
        </form>
    </div>
    </fieldset>
</div><!-- End div content -->