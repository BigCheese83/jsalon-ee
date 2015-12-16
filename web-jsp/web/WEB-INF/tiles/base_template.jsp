<%--
  Created by IntelliJ IDEA.
  User: BigCheese
  Date: 11.08.15
  Time: 17:25
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><tiles:getAsString name="title"/></title>
<link rel="stylesheet" href="<c:url value="/css/jsalon.css"/>" type="text/css"/>
<link rel="stylesheet" href="<c:url value="/css/jquery-ui.css"/>" type="text/css"/>
<link rel="stylesheet" href="<c:url value="/css/jquery.dataTables.css"/>" type="text/css"/>
<link rel="stylesheet" href="<c:url value="/css/font-awesome.css"/>" type="text/css"/>
</head>
<body>
<tiles:insertAttribute name="header"/>
<tiles:insertAttribute name="menu"/>
<tiles:insertAttribute name="body"/>
<tiles:insertAttribute name="footer"/>

<script type="text/javascript" src="<c:url value="/js/jquery-1.11.3.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery-ui.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jsalon.js"/>"></script>
<tiles:insertAttribute name="scripts"/>
</body>
</html>