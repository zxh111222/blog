package io.github.zxh111222.blog;

import io.github.zxh111222.blog.util.MyDBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/blog-list")
public class BlogListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<Blog> blogs = new ArrayList<>();

        String sql = "select id, title, content, cover from blog order by id desc";

        try (
                // 连接数据库
                Connection connection = MyDBUtil.getConnection();
                // 发送查询语句，
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            // 获取结果
            ResultSet rs = preparedStatement.executeQuery();
            // 构造出Blog实例
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String content = rs.getString("content");
                String cover = rs.getString("cover");
                // 构造出Blog实例
                Blog blog = new Blog(id, title, content, cover);
                // 添加到 blogs
                blogs.add(blog);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        req.setAttribute("blogs", blogs);

        req.setAttribute("title", "博客列表");
        req.getRequestDispatcher("/WEB-INF/blog-list.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
