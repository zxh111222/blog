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
<%@ include file="../partials/_start.jsp" %>


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
          <h3 class="card-title">填写博客信息</h3>

          <div class="card-tools">
            <button type="button" class="btn btn-tool" data-card-widget="collapse" title="Collapse">
              <i class="fas fa-minus"></i>
            </button>
          </div>
        </div>
        <div class="card-body">
          <form id="add-blog-form" method="post" action="admin-add-blog">
            <div class="form-group">
              <label for="title">博客标题</label>
              <input type="text" id="title" class="form-control" name="title" placeholder="请输入博客标题">
            </div>
            <div class="form-group">
              <label for="content">博客内容</label>
              <textarea id="content" class="form-control" rows="20" name="content" required></textarea>
            </div>
            <div class="form-group">
              <label for="type">博客类型</label>
              <select id="type" class="form-control custom-select" name="type">
                <option value="技术" selected>技术</option>
                <option value="生活">生活</option>
                <option value="思考">思考</option>
              </select>
            </div>

          </form>
        </div>
        <div class="card-footer">
          <button type="submit" class="btn btn-primary" form="add-blog-form">保存</button>
        </div>

      </div>

    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->


<%@ include file="../partials/_end.jsp" %>