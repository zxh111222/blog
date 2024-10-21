<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2024/10/21
  Time: 16:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored ="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>博客列表 - Blog</title>
</head>
<body>
<table>
    <tr>
        <th>ID</th>
        <th>Title</th>
        <th>Content</th>
    </tr>
    <c:forEach items="${blogs}" var="blog">
        <tr>
            <td>${blog.id}</td>
            <td>${blog.title}</td>
            <td>${blog.content}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
