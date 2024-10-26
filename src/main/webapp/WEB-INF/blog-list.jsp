<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2024/10/26
  Time: 15:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored ="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
  <title>Title</title>
  <link rel="stylesheet" href="../vendor/bootstrap/bootstrap.min.css">
</head>
<body>

<main class="container mt-5">
  <div class="row g-4">
    <c:forEach items="${blogs}" var="blog">
      <div class="col-12">
        <div class="d-flex align-items-center border-bottom pb-3">
          <div class="flex-grow-1 me-3">
            <h3 class="h5 mb-1">${blog.title}</h3>
            <p class="text-muted mb-0">
                ${blog.content.length() > 100 ? blog.content.substring(0, 100).concat('...') : blog.content}
            </p>
          </div>
          <div class="flex-shrink-0">
            <a href="blog-show?id=${blog.id}">
              <img src="${blog.cover != null ? blog.cover : 'img/default-cover.png'}"
                   class="img-fluid rounded" alt="${blog.title}"
                   style="width: 150px; height: 100px; object-fit: cover;">
            </a>
          </div>
        </div>
      </div>
    </c:forEach>
  </div>
</main>

</body>
</html>
