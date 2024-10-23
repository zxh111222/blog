package io.github.zxh111222.blog;

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
import java.util.List;

@WebServlet("/blog-list")
public class BlogListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Blog> blogs = new ArrayList<>();

        int page = 1;
        String pageFromRequest = req.getParameter("page");
        if (pageFromRequest != null) {
            try {
                page = Integer.parseInt(pageFromRequest);
            } catch (NumberFormatException e) {
                System.out.println("非法的 page 值（非整数）：" + pageFromRequest);
            }
            if (page < 1) {
                System.out.println("非法的 page 值（小于 1）：" + pageFromRequest);
                page = 1;
            }
        }

        int count = 10;
        String countFromRequest = req.getParameter("count");
        if (countFromRequest != null) {
            try {
                count = Integer.parseInt(countFromRequest);
            } catch (NumberFormatException e) {
                System.out.println("非法的 count 值（非整数）：" + countFromRequest);
            }
            if (count > 50) {
                System.out.println("非法的 count 值（大于 50）：" + countFromRequest);
                count = 50;
            }
        }

        int offset = (page - 1) * count;

        String sql = "select id, title, content, type from blog order by id desc limit ?, ?";
        String searchString = req.getParameter("title");
        if (searchString != null && !searchString.trim().isEmpty()) {
            sql = "select id, title, content, type from blog where title like ? order by id desc limit ?, ?";
        }

        Connection connection = MyDBUtil.getConnection();

        try (
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {

            if (searchString != null && !searchString.trim().isEmpty()) {
                preparedStatement.setString(1, "%" + searchString + "%");
                preparedStatement.setInt(2, offset);
                preparedStatement.setInt(3, count);
                req.setAttribute("searchString", searchString);
            } else {
                preparedStatement.setInt(1, offset);
                preparedStatement.setInt(2, count);
            }

            // 获取结果
            ResultSet rs = preparedStatement.executeQuery();
            // 构造出Blog实例
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String content = rs.getString("content");
                String type = rs.getString("type");
                // 构造出Blog实例
                Blog blog = new Blog(id, title, content, type);
                // 添加到 blogs
                blogs.add(blog);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        req.setAttribute("blogs", blogs);

        req.setAttribute("page", page);

        req.getRequestDispatcher("/blog-list.jsp").forward(req, resp);
    }
}