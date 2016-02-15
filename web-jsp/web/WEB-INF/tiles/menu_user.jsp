<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="menu">
    <ul id="main-menu">
        <li>
            <a href="#"><i class="fa fa-users"></i> Клиенты</a>
            <ul>
                <li><a href="<c:url value="/user/clients"/>">Все клиенты</a></li>
                <li><a href="<c:url value="/user/client"/>">Поиск/Редактирование</a></li>
            </ul>
        </li>
    </ul>
</div><!-- End div menu -->