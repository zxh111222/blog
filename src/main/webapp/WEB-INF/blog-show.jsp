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

<style>
  /* 覆盖1: border-left */
  .cherry-previewer {
    border-left: none;
  }

  /* 覆盖2: box-shadow */
  /*.cherry {
      box-shadow: none;
  }*/

  /* 覆盖3: background-color */
  /*.cherry {
      background: none;
  }
  .cherry-previewer {
      background-color: unset;
  }
  .cherry-markdown.theme__light {
      background-color: unset;
  }
  .cherry.theme__light .cherry-previewer {
      background-color: unset;
  }*/

  /* 覆盖4: a.anchor:before */
  .cherry-markdown a.anchor:before {
    content: "";
  }
</style>

<main class="container mt-5">
  <div class="row">
    <div class="col mx-auto">
      <article>
        <h1 class="mb-4">${blog.title}</h1>
        <div id="blog-content" class="blog-content">
          <!-- 这里将由 JavaScript 填充解析后的 HTML 内容 -->
        </div>
      </article>
      <div class="my-5">
        <a href="blog-list" class="btn btn-primary">返回博客列表</a>
      </div>
    </div>
  </div>
</main>

<script>
  document.addEventListener('DOMContentLoaded', function() {
    // 创建Cherry实例，使用纯预览模式
    const cherry = new Cherry({
      id: 'blog-content',
      value: `${blog.content}`,
      editor: {
        defaultModel: 'previewOnly',
      },
      toolbars: {
        // 打开侧边目录
        toc: {
          updateLocationHash: false, // 不更新URL的hash
          defaultModel: 'full', // 完整模式，展示所有标题
        },
      }
    });
  });
</script>

<%@ include file="partials/_end.jsp" %>