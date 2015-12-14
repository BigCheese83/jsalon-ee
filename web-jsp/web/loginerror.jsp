<%--
  Created by IntelliJ IDEA.
  User: BigCheese
  Date: 07.08.15
  Time: 15:28
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Error Authentication</title>
<link rel="stylesheet" href="<c:url value="/css/login.css"/>" type="text/css"/>
</head>
<body>
<div id="login">
    <h1>Ошибка!</h1>
    <div style="margin: 50px 30px 50px 30px">
        <div class="error">
            <p>Не удалось войти в систему.</p>
            <p>Неправильный логин или пароль.</p>
            <p>Возможно у вас выбрана другая раскладка клавиатуры или нажата клавиша "Caps Lock".</p>
        </div>
    </div>
    <div align="center"><a href="javascript:history.back();void 0">Вернуться назад</a></div>
</div>
</body>
</html>