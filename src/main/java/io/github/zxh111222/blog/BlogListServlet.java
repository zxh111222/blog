package io.github.zxh111222.blog;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/blog-list")
public class BlogListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Blog> blogs = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            Blog blog = new Blog(i, "title-" + i, "content-" + i);
            blogs.add(blog);
        }

        req.setAttribute("blogs", blogs);

        req.getRequestDispatcher("/WEB-INF/blog-list.jsp").forward(req, resp);
    }
}