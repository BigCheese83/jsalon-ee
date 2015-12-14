<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<div id="content">
    <fieldset>
        <legend class="content-title">Системная информация</legend>
        <fieldset>
            <legend>Server</legend>
            <p>OS: ${sessionScope.systeminfo.os}</p>
            <p>Java Vendor: ${sessionScope.systeminfo.javaVendor} <a href="${sessionScope.systeminfo.javaVendorUrl}">${sessionScope.systeminfo.javaVendorUrl}</a></p>
            <p>JRE: ${sessionScope.systeminfo.jre}</p>
            <p>JVM: ${sessionScope.systeminfo.jvm}</p>
            <p>JVM Memory: ${sessionScope.systeminfo.jvmMemory}</p>
            <p>Server: <%=response.getHeader("Server")%></p>
            <p>Server date: <%=new java.util.Date()%></p>
            <p>X-Powered-By: <%=response.getHeader("X-Powered-By")%></p>
        </fieldset>
        <fieldset>
            <legend>DataBase</legend>
            <p>Database: ${sessionScope.dbmetadata.databaseProductName} ${sessionScope.dbmetadata.databaseProductVersion}</p>
            <p>Driver name: ${sessionScope.dbmetadata.driverName}</p>
            <p>Driver version: ${sessionScope.dbmetadata.driverVersion}</p>
            <p>JDBC version: ${sessionScope.dbmetadata.jdbcMajorVersion}.${sessionScope.dbmetadata.jdbcMinorVersion}</p>
            <p>URL: ${sessionScope.dbmetadata.url}</p>
            <p>Username: ${sessionScope.dbmetadata.username}</p>
            <p>Max connections: ${sessionScope.dbmetadata.maxConnections}</p>
        </fieldset>
    </fieldset>
</div><!-- End div content -->