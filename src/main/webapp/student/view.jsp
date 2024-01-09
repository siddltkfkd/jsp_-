<%--
  Created by IntelliJ IDEA.
  User: dmswl
  Date: 24. 1. 9.
  Time: 오전 9:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>학생-조회</title>
    <link rel="stylesheet" href="/style.css" />
</head>
<body>
<table>
    <tbody>
        <tr>
            <th>아이디</th>
            <td>${student.id}</td>
        </tr>

        <tr>
            <th>이름</th>
            <td>${student.name}</td>
        </tr>

        <tr>
            <th>성별</th>
            <td>${student.gender}</td>
        </tr>

        <tr>
            <th>나이</th>
            <td>${student.age}</td>
        </tr>
        <tr>
            <th>등록일</th>
            <td>${student.createdAt}</td>
        </tr>
    </tbody>
</table>
<ul>
    <li><a href="/student/list">리스트</a></li>
    <li>
        <c:url var="update_link" value="/student/update">
            <c:param name="id" value="${student.id}"/>
        </c:url>
        <a href="${update_link}">수정</a>
    </li>
    <li>
        <form method="post" action="/student/delete?id=${student.id}">
            <input type="submit" value="삭제" />
        </form>
    </li>

 </ul>

</body>
</html>