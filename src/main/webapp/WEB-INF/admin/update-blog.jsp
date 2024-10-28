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

<!-- 在 head 部分添加 cherry-markdown 的 CSS 和 JS 文件 -->
<link rel="stylesheet" href="vendor/cherry-markdown-0.8.49/dist/cherry-markdown.min.css">
<script src="vendor/cherry-markdown-0.8.49/dist/cherry-markdown.min.js"></script>

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
        <form id="update-blog-form" method="post" action="admin-update-blog" enctype="multipart/form-data">
          <input type="hidden" name="id" value="${blog.id}">
          <div class="form-group">
            <label for="title">博客标题</label>
            <input type="text" id="title" class="form-control" name="title" placeholder="${blog.title}">
          </div>
          <div class="form-group">
            <label for="content">博客内容</label>
            <textarea id="content" class="form-control" rows="20" name="content" style="display: none" required></textarea>
            <div id="markdown-container" style="height:600px"></div>
          </div>
          <div class="form-group">
            <label for="type">博客类型</label>
            <select id="type" class="form-control custom-select" name="type">
              <c:choose>
                <c:when test="${blog.type== '技术'}">
                  <option value="${blog.type}" selected>${blog.type}</option>
                  <option value="生活">生活</option>
                  <option value="思考">思考</option>
                </c:when>
                <c:when test="${blog.type== '生活'}">
                  <option value="技术">技术</option>
                  <option value="${blog.type}" selected>${blog.type}</option>
                  <option value="思考">思考</option>
                </c:when>
                <c:when test="${blog.type== '思考'}">
                  <option value="技术">技术</option>
                  <option value="生活">生活</option>
                  <option value="${blog.type}" selected>${blog.type}</option>
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

<script>

  new Cherry({
    id: 'markdown-container',
    value: '',
    toolbars: {
      // 定义顶部工具栏
      toolbar: ['bold','italic','strikethrough','|','color','header','ruby','|','list','panel','detail'],
      // 定义侧边栏，默认为空
      sidebar: ['theme'],
      // 定义顶部右侧工具栏，默认为空
      toolbarRight: ['fullScreen', 'export'],
      // 定义选中文字时弹出的“悬浮工具栏”，默认为 ['bold', 'italic', 'underline', 'strikethrough', 'sub', 'sup', 'quote', '|', 'size', 'color']
      bubble: false,
      // 定义光标出现在行首位置时出现的“提示工具栏”，默认为 ['h1', 'h2', 'h3', '|', 'checklist', 'quote', 'table', 'code']
      float: false,
    },
    callback: {
      afterChange: (text, html) => {
        document.getElementById('content').value = text;
      }
    },
    fileUpload: (file, callback) => {
      const formData = new FormData();
      formData.append('image', file);
      fetch('<%=request.getContextPath()%>/upload-image', {
        method: 'POST',
        body: formData
      })
              .then(response => response.json())
              .then(data => {
                if (data.success) {
                  callback(data.file.url);
                } else {
                  console.error('图片上传失败:', data.message);
                  alert('图片上传失败: ' + data.message);
                }
              })
              .catch(error => {
                console.error('图片上传出错:', error);
                alert('图片上传出错，请稍后重试');
              });
    },
  });

</script>


<%@ include file="partials/_end.jsp" %>