<%--
  Created by IntelliJ IDEA.
  User: BigCheese
  Date: 07.08.15
  Time: 15:27
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Вход в АИС "Салон красоты"</title>
<link rel="stylesheet" href="<c:url value="/css/login.css"/>" type="text/css"/>
</head>
<body>
<form id="login" action="j_security_check" method="POST">
    <h1>Авторизация</h1>
    <fieldset id="inputs">
        <input id="username" type="text" name="j_username" placeholder="Логин" autofocus required>
        <input id="password" type="password" name="j_password" placeholder="Пароль" required>
    </fieldset>
    <fieldset id="actions">
        <input type="submit" id="submit" value="ВОЙТИ">
        <a href="<c:url value="/admin"/>">Консоль администратора</a>
    </fieldset>
</form>
</body>
</html>