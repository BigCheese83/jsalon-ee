<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="jsalon" uri="http://ru.bigcheese.jsalon" %>
<div id="content">
    <fieldset>
    <legend class="content-title"><i class="fa fa-table"></i> Справочники / Связь Должности-Услуги</legend>
    <c:forEach var="message" items="${requestScope.errMessages}">
    <p class="err">${message}</p>
    </c:forEach>
    <div class="content-table">
        <table id="datatable" class="display">
            <thead>
            <tr>
                <th>ID</th>
                <th>Должность</th>
                <th>Услуга</th>
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
            <div class="form-field">
                <label for="postId">Должность</label>
                <select id="postId" name="postId" required>
                    <option selected></option>
                    <c:forEach var="p" items="${sessionScope.postsList}">
                        <option value="${p.id}">${p.name}</option>
                    </c:forEach>
                </select>
                <input type="hidden" id="postName" name="postName">
            </div>
            <div class="form-field">
                <label for="serviceId">Услуга</label>
                <select id="serviceId" name="serviceId" required>
                    <option selected></option>
                    <c:forEach var="s" items="${sessionScope.servicesList}">
                        <option value="${s.id}">${s.name}</option>
                    </c:forEach>
                </select>
                <input type="hidden" id="serviceName" name="serviceName">
            </div>
            <input type="submit" value="Сохранить" onclick="fillNames()">
            <input type="hidden" name="id" class="objectID" >
            <input type="hidden" id="radioID" name="radioID">
        </form>
    </div>
    </fieldset>
    <input type="hidden" id="dataset" value='<jsalon:json object="${sessionScope.postserviceList}"/>'>
</div><!-- End div content -->
<script>
    function fillNames() {
        $('#postName').val($('#postId').find('option:selected').text());
        $('#serviceName').val($('#serviceId').find('option:selected').text());
    }
</script>
