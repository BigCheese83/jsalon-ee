<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="content">
    <fieldset>
    <legend class="content-title"><i class="fa fa-table"></i> Справочники / Должности</legend>
    <c:forEach var="message" items="${requestScope.errMessages}">
    <p class="err">${message}</p>
    </c:forEach>
    <div class="content-table">
        <table id="datatable" class="display">
            <thead>
            <tr>
                <th>ID</th>
                <th>Наименование</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="post" items="${sessionScope.postsList}">
                <tr><td>${post.id}</td><td>${post.name}</td></tr>
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
                <label for="postName">Наименование</label>
                <input type="text" id="postName" name="postName" required>
            </div>
            <input type="submit" value="Сохранить">
            <input type="hidden" id="postID" name="postID">
            <input type="hidden" name="radioID">
        </form>
    </div>
    </fieldset>
</div><!-- End div content -->