<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="menu">
    <ul id="main-menu">
        <li>
            <a href="#"><i class="fa fa-users"></i> Пользователи</a>
            <ul>
                <li><a href="<c:url value="/admin/users"/>">Все пользователи</a></li>
                <li><a href="<c:url value="/admin/user"/>">Поиск/Редактирование</a></li>
            </ul>
        </li>
        <li>
            <a href="#"><i class="fa fa-table"></i> Справочники</a>
            <ul>
                <li><a href="<c:url value="/admin/discounts"/>">Скидки</a></li>
                <li><a href="<c:url value="/admin/posts"/>">Должности</a></li>
                <li><a href="<c:url value="/admin/services"/>">Услуги</a></li>
                <li><a href="<c:url value="/admin/postservice"/>">Связь Должности-Услуги</a></li>
            </ul>
        </li>
        <li>
            <a href="#"><i class="fa fa-user"></i> Мастера</a>
            <ul>
                <li><a href="<c:url value="/admin/masters"/>">Все мастера</a></li>
                <li><a href="<c:url value="/admin/master"/>">Поиск/Редактирование</a></li>
            </ul>
        </li>
        <li><a href="<c:url value="/admin/settings"/>"><i class="fa fa-wrench"></i> Настройки</a></li>
        <li><a href="<c:url value="/admin/sysinfo"/>"><i class="fa fa-cogs"></i> Систем. информация</a></li>
    </ul>
</div><!-- End div menu -->