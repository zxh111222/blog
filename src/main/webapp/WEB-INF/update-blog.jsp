<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2024/10/21
  Time: 17:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored ="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>添加博客 - Blog</title>
</head>
<body>
<main>
  <p>正在修改博客 id=${blog.id} ：</p>
  <form method="post" action="update-blog">
    <input type="hidden" name="id" value="${blog.id}">
    <textarea rows="8" cols="25" name="title">${blog.title}</textarea>
    <textarea rows="8" cols="50" name="content">${blog.content}</textarea>
    <button type="submit">修改</button>
  </form>
</main>

</body>
</html>
