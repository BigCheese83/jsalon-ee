<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="ru.bigcheese.jsalon.core.Constants" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="header">
    <div class="header-title"><%=Constants.SOFTWARE_NAME%> (Консоль Администратора)</div>
    <table class="header-table">
        <tr><td>Версия ПО:</td><td><%=Constants.VERSION_NUMBER%> от <%=Constants.VERSION_DATE%></td></tr>
        <tr><td>Пользователь:</td><td>${sessionScope.user.login} (${sessionScope.user.shortFIO})</td></tr>
        <tr><td colspan="2"><a href="<c:url value="/admin/logout"/>">Выход</a></td></tr>
    </table>
</div><!-- End div header -->
