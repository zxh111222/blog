package io.github.zxh111222.blog;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/add-blog1")
public class BlogAddServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();

        String title = req.getParameter("title");
        System.out.println("博客标题：" + title);
        String content = req.getParameter("content");
        System.out.println("博客内容：" + content);
        String type = req.getParameter("blog-type");
        System.out.println("博客类型：" + type);

        writer.write("博客保存成功！");
    }
}
