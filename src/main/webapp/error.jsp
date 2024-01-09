<%--
  Created by IntelliJ IDEA.
  User: dmswl
  Date: 24. 1. 9.
  Time: 오전 9:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true"  isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<html>
<head>
    <title>Error Page</title>
    <link rel="stylesheet" href="style.css" />
</head>
<body>

<table>
    <tbody>
    <tr>
        <th>status_code</th>
        <td>${requestScope.status_code}</td>
    </tr>
    <tr>
        <th>exception_type</th>
        <td>${requestScope.exception_type}</td>
    </tr>
    <tr>
        <th>message</th>
        <td>${requestScope.message}</td>
    </tr>
    <tr>
        <th>exception</th>
        <td>${requestScope.exception}</td>
    </tr>
    <tr>
        <th>request_uri</th>
        <td>${requestScope.request_url}</td>
    </tr>
    </tbody>

</table>
</body>
</html>