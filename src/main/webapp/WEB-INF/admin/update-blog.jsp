<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2024/10/23
  Time: 19:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored ="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="partials/_start.jsp" %>


<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
  <!-- Content Header (Page header) -->
  <section class="content-header">
    <div class="container-fluid">
      <div class="row mb-2">
        <div class="col-sm-6">
          <h1>博客管理</h1>
        </div>
        <div class="col-sm-6">
          <ol class="breadcrumb float-sm-right">
            <li class="breadcrumb-item"><a href="#">Home</a></li>
            <li class="breadcrumb-item active">博客管理</li>
          </ol>
        </div>
      </div>
    </div><!-- /.container-fluid -->
  </section>

  <!-- Main content -->
  <section class="content">

    <!-- Default box -->
    <div class="card card-primary">
      <div class="card-header">
        <h3 class="card-title">修改博客信息 - 正在修改博客 id=${blog.id}</h3>

        <div class="card-tools">
          <button type="button" class="btn btn-tool" data-card-widget="collapse" title="Collapse">
            <i class="fas fa-minus"></i>
          </button>
        </div>
      </div>
      <div class="card-body">
        <form id="update-blog-form" method="post" action="admin-update-blog?id=" enctype="multipart/form-data">
          <input type="hidden" name="id" value="${blog.id}">
          <div class="form-group">
            <label for="title">博客标题</label>
            <input type="text" id="title" class="form-control" name="title" placeholder="${blog.title}">
          </div>
          <div class="form-group">
            <label for="content">博客内容</label>
            <textarea id="content" class="form-control" rows="20" name="content" required>${blog.content}</textarea>
          </div>
          <div class="form-group">
            <label for="type">博客类型</label>
            <select id="type" class="form-control custom-select" name="type">
              <option value="${blog.type}" selected>${blog.type}</option>
              <c:choose>
                <c:when test="${blog.type== '技术'}">
                  <option value="生活">生活</option>
                  <option value="思考">思考</option>
                </c:when>
                <c:when test="${blog.type== '生活'}">
                  <option value="技术">技术</option>
                  <option value="思考">思考</option>
                </c:when>
                <c:when test="${blog.type== '思考'}">
                  <option value="技术">技术</option>
                  <option value="生活">生活</option>
                </c:when>
              </c:choose>
            </select>
          </div>
          <div class="form-group">
            <label for="cover">博客封面图</label>
            <input type="file" class="d-block" id="cover" name="cover" accept="image/*">
          </div>
        </form>
      </div>
      <div class="card-footer">
        <button type="submit" class="btn btn-primary" form="update-blog-form">保存</button>
      </div>

    </div>

  </section>
  <!-- /.content -->
</div>
<!-- /.content-wrapper -->


<%@ include file="partials/_end.jsp" %>