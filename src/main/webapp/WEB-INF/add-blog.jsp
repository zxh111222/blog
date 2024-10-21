<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2024/10/21
  Time: 17:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored ="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>添加博客</title>
</head>
<body>
<main>
    <form method="post" action="add-blog">
        <textarea rows="8" cols="25" name="title">输入博客标题</textarea>
        <textarea rows="8" cols="50" name="content">输入该博客的内容</textarea>
        <button type="submit">提交</button>
    </form>
</main>
</body>
</html>
