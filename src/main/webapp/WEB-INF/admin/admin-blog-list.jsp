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

    <div class="card">
      <div class="card-header">
        <h3 class="card-title">博客列表</h3>

        <div class="card-tools">
          <form action="admin-blog-list" method="get" class="input-group input-group-sm" style="width: 510px;">
            <div class="input-group-append">
              <a href="<%= request.getContextPath() %>/blog-list" class="btn btn-default" style="width: 100px; margin: 0 15px;">
                详细页面
              </a>
            </div>
            <input type="text" name="title"  value="${searchString}" class="form-control float-right" placeholder="Search">

            <div class="input-group-append">
              <button type="submit" class="btn btn-default">
                <i class="fas fa-search"></i>
              </button>
              <a href="<%= request.getContextPath() %>/admin-add-blog" class="btn btn-default" style="width: 100px; margin: 0 15px;">
                添加博客
              </a>
            </div>
          </form>
        </div>
      </div>
    </div>
    <!-- /.card-header -->
    <div class="card-body">
      <div id="example2_wrapper" class="dataTables_wrapper dt-bootstrap4">
        <div class="row">
          <div class="col-sm-12 col-md-6"></div>
          <div class="col-sm-12 col-md-6"></div>
        </div>
        <div class="row">
          <div class="col-sm-12">
            <table id="example2" class="table table-bordered table-hover dataTable dtr-inline"
                   aria-describedby="example2_info">
              <thead>
              <tr>
                <th>ID</th>
                <th>博客标题</th>
                <th>博客内容</th>
                <th>博客类型</th>
                <th>操作</th>
              </tr>
              </thead>
              <tbody>
              <c:forEach items="${blogs}" var="blog">
                <tr>
                  <td>${blog.id}</td>
                  <td>${blog.title}</td>
                  <td>${blog.content}</td>
                  <td><span class="tag tag-success">${blog.type}</span></td>
                  <td style="display: flex;">
                    <a href="admin-update-blog?id=${blog.id}" type="submit" class="btn btn-block btn-secondary btn-sm" style="width: 60px; height: 35px; margin: 0 5px">修改</a>
                    <a href="admin-delete-blog?id=${blog.id}" type="submit" class="btn btn-block btn-secondary btn-sm" style="width: 60px; height: 35px; margin: 0 5px">删除</a>
                  </td>
                </tr>
              </c:forEach>
              </tbody>
            </table>
          </div>
        </div>

        <div class="row">
          <div class="col-sm-12 col-md-5">
            <div class="dataTables_info" id="example2_info" role="status" aria-live="polite">Showing 1 to 10 of 57
              entries
            </div>
          </div>
          <div class="col-sm-12 col-md-7">
            <div class="dataTables_paginate paging_simple_numbers" id="example2_paginate">
              <ul class="pagination">
                <li class="paginate_button page-item previous" id="example2_previous"><a href="admin-blog-list?page=${page - 1}${searchString != null ? "&title=".concat(searchString) : ""}"
                                                                                                  aria-controls="example2"
                                                                                                  class="page-link">Previous</a>
                </li>
                <li class="paginate_button page-item active"><a href="#" aria-controls="example2" data-dt-idx="1"
                                                                tabindex="0" class="page-link">1</a></li>
                <li class="paginate_button page-item "><a href="#" aria-controls="example2" data-dt-idx="2"
                                                          tabindex="0" class="page-link">2</a></li>
                <li class="paginate_button page-item "><a href="#" aria-controls="example2" data-dt-idx="3"
                                                          tabindex="0" class="page-link">3</a></li>
                <li class="paginate_button page-item "><a href="#" aria-controls="example2" data-dt-idx="4"
                                                          tabindex="0" class="page-link">4</a></li>
                <li class="paginate_button page-item "><a href="#" aria-controls="example2" data-dt-idx="5"
                                                          tabindex="0" class="page-link">5</a></li>
                <li class="paginate_button page-item "><a href="#" aria-controls="example2" data-dt-idx="6"
                                                          tabindex="0" class="page-link">6</a></li>
                <li class="paginate_button page-item next" id="example2_next"><a href="admin-blog-list?page=${page + 1}${searchString != null ? "&title=".concat(searchString) : ""}"
                                                                                 aria-controls="example2"
                                                                                 class="page-link">Next</a></li>
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