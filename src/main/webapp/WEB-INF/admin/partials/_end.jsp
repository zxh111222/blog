<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2024/10/23
  Time: 14:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<footer class="main-footer">
    <div class="float-right d-none d-sm-block">
        <b>Version</b> 3.2.0
    </div>
    <strong>Copyright &copy; 2014-2021 <a href="https://adminlte.io">AdminLTE.io</a>.</strong> All rights reserved.
</footer>

<!-- Control Sidebar -->
<aside class="control-sidebar control-sidebar-dark">
    <!-- Control sidebar content goes here -->
</aside>
<!-- /.control-sidebar -->
</div>
<!-- ./wrapper -->

<style>
    .cherry.fullscreen {
        z-index: 1050 !important;
    }
</style>

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


<!-- jQuery -->
<script src="vendor/adminlte-3.2.0/plugins/jquery/jquery.min.js"></script>
<!-- Bootstrap 4 -->
<script src="vendor/adminlte-3.2.0/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- AdminLTE App -->
<script src="vendor/adminlte-3.2.0/dist/js/adminlte.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="vendor/adminlte-3.2.0/dist/js/demo.js"></script>
</body>
</html>
