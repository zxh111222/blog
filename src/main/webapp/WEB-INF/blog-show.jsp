<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2024/10/26
  Time: 15:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored ="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ include file="partials/_start.jsp" %>

<main class="container mt-5">
  <div class="row">
    <div class="col mx-auto">
      <article>
        <h1 class="mb-4">${blog.title}</h1>
        <div class="blog-content">
          ${blog.content}
        </div>
      </article>
      <div class="my-5">
        <a href="blog-list" class="btn btn-primary">返回博客列表</a>
      </div>
    </div>
  </div>
</main>

<%@ include file="partials/_end.jsp" %>