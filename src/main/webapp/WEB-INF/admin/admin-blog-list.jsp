<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2024/10/23
  Time: 14:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored ="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="partials/_start.jsp" %>


<div class="content-wrapper" id="main">
  <!-- Content Header (Page header) -->
  <section class="content-header">
    <div class="container-fluid">
      <div class="row mb-2">
        <div class="col-12 d-flex justify-content-between">
          <h1>博客列表</h1>
          <a href="${pageContext.request.contextPath}/admin-add-blog">创建新博客</a>
        </div>
      </div>
    </div><!-- /.container-fluid -->
  </section>

  <!-- Main content -->
  <section class="content">
    <div class="card">
      <!-- 添加搜索框 -->
      <div class="card-header">
        <form method="get" class="form-inline">
          <div class="input-group" style="width: 300px;">
            <input type="text" name="search" class="form-control"
                   placeholder="搜索博客标题..." value="${search}">
            <div class="input-group-append">
              <button type="submit" class="btn btn-default">
                <i class="fas fa-search"></i>
              </button>
              <c:if test="${not empty search}">
                <a href="${pageContext.request.contextPath}${pageContext.request.getAttribute('jakarta.servlet.forward.servlet_path')}"
                   class="btn btn-default"
                   title="清除搜索条件">
                  <i class="fas fa-times"></i>
                </a>
              </c:if>
            </div>
          </div>
        </form>
      </div>
    </div>
    <div class="card-body table-responsive p-0">
      <table class="table table-bordered table-hover dataTable dtr-inline">
        <thead>
        <tr>
          <th>ID</th>
          <th>博客标题</th>
          <th>博客类型</th>
          <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${blogs}" var="blog">
          <tr>
            <td>${blog.id}</td>
            <td><a href="${pageContext.request.contextPath}/blog-show?id=${blog.id}" target="_blank">${blog.title}</a>
            </td>
            <td><span class="tag tag-success">${blog.type}</span></td>
            <td style="display: flex;">
              <a href="${pageContext.request.contextPath}/admin-update-blog?id=${blog.id}" class="text-sm">
                <i class="fas fa-edit"></i>
                Edit
              </a>
              <a href="${pageContext.request.contextPath}/admin-delete-blog?id=${blog.id}" class="text-sm pl-3"
                 onclick="return confirm('确定要删除这篇博客吗？此操作不可恢复！')">
                <i class="fas fa-trash"></i>
                Remove
              </a>
            </td>
          </tr>
        </c:forEach>
        </tbody>
      </table>

      <div class="card-footer clearfix">
        <div class="row">
          <div class="col-sm-12 col-md-5">
            <div class="dataTables_info" role="status" aria-live="polite">
              共 ${totalRecords} 条记录，当前第 ${currentPage}/${totalPages} 页
            </div>
          </div>
          <div class="col-sm-12 col-md-7">
            <div class="dataTables_paginate paging_simple_numbers">
              <ul class="pagination">
                <li class="paginate_button page-item previous ${currentPage == 1 ? 'disabled' : ''}">
                  <a href="${pageContext.request.contextPath}${pageContext.request.getAttribute('jakarta.servlet.forward.servlet_path')}?page=${currentPage - 1}${search != null ? '&search='.concat(search) : ''}"
                     class="page-link">上一页</a>
                </li>
                <c:forEach begin="1" end="${totalPages}" var="i">
                  <li class="paginate_button page-item ${currentPage == i ? 'active' : ''}">
                    <a href="${pageContext.request.contextPath}${pageContext.request.getAttribute('jakarta.servlet.forward.servlet_path')}?page=${i}${search != null ? '&search='.concat(search) : ''}"
                       class="page-link">${i}</a>
                  </li>
                </c:forEach>
                <li class="paginate_button page-item next">
                  <a href="${pageContext.request.contextPath}${pageContext.request.getAttribute('jakarta.servlet.forward.servlet_path')}?page=${currentPage + 1}${search != null ? '&search='.concat(search) : ''}"
                     class="page-link">下一页</a></li>
              </ul>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- /.card-body -->
  </section>
  <!-- /.content -->
</div>
<!-- /.content-wrapper -->


<%@ include file="partials/_end.jsp" %>