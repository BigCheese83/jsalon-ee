<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="menu">
    <ul id="main-menu">
        <li>
            <a href="#">Пользователи</a>
            <ul>
                <li><a href="<c:url value="/admin/users"/>">Все пользователи</a></li>
                <li><a href="<c:url value="/admin/user"/>">Поиск/Редактирование</a></li>
            </ul>
        </li>
        <li>
            <a href="#">Справочники</a>
            <ul>
                <li><a href="<c:url value="/admin/discounts"/>">Скидки</a></li>
                <li><a href="<c:url value="/admin/posts"/>">Должности</a></li>
                <li><a href="<c:url value="/admin/services"/>">Услуги</a></li>
            </ul>
        </li>
        <li><a href="<c:url value="/admin/settings"/>">Настройки</a></li>
        <li><a href="<c:url value="/admin/sysinfo"/>">Систем. информация</a></li>
    </ul>
</div><!-- End div menu -->