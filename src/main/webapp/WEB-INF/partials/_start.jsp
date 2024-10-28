<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2024/10/23
  Time: 14:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored ="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%
    String protocol = request.getScheme();
    String serverName = request.getServerName();
    int serverPort = request.getServerPort();
    String contextPath = request.getContextPath();

    String baseUrl = protocol + "://" + serverName;

    if ((serverPort != 80 && protocol.equals("http")) || (serverPort != 443 && protocol.equals("https"))) {
        baseUrl += ":" + serverPort;
    }

    baseUrl += contextPath;
%>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>${title == null ? "ZXinhao" : title.concat(" - ZXinhao")}</title>
    <link rel="stylesheet" href="vendor/bootstrap-5.3.3/bootstrap.min.css">
    <link rel="stylesheet" href="css/base.css">
    <!-- 在 head 部分添加 cherry-markdown 的 CSS 和 JS 文件 -->
    <link rel="stylesheet" href="vendor/cherry-markdown-0.8.49/dist/cherry-markdown.min.css">
    <script src="vendor/cherry-markdown-0.8.49/dist/cherry-markdown.min.js"></script>
    <!-- 提供 description 和 keywords 这两个基础的元信息，有利于 SEO -->
    <meta name="description" content="${seoDescription != null ? seoDescription : "记录零散的所思所想"}">
    <meta name="keywords" content="${seoKeywords != null ? seoKeywords : "Java,博客,Blog"}">
    <!-- 提供 og 开头的几个最基本元信息，方便在社交软件中更友好的展示 -->
    <meta property="og:title" content="${ogTitle != null ? ogTitle : "Yeah"}" />
    <meta property="og:description" content="${ogDescription != null ? ogDescription : "记录零散的所思所想"}" />
    <meta property="og:image" content="<%=request.getAttribute("ogTitle") != null ? request.getAttribute("ogTitle") : baseUrl + "/og-image.png" %>" />
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light navbar-glass py-2">
    <div class="container">
        <a class="navbar-brand fw-medium" href="<%= request.getContextPath() + "/" %>">ZXinhao</a>
        <button class="navbar-toggler border-0" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse justify-content-end" id="navbarSupportedContent">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link px-3 <%= "/blog-list".equals(request.getAttribute("jakarta.servlet.forward.servlet_path")) ? "active" : "" %>" href="blog-list">博客</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link px-3" href="admin">后台管理</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link px-3 <%= "/about".equals(request.getAttribute("jakarta.servlet.forward.servlet_path")) ? "active" : "" %>" href="about">关于我</a>
                </li>
            </ul>
        </div>
    </div>
</nav>