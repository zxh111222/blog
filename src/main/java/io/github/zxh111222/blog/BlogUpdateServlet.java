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

@WebServlet("/update-blog")
public class BlogUpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 接收参数
        String id = req.getParameter("id");
        // 根据 id，从数据库获取匹配的用户信息
        Connection connection = MyDBUtil.getConnection();
        String sql = "SELECT id, title, content, type FROM blog WHERE id=?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, id);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Blog blog = new Blog(Integer.parseInt(id), rs.getString("title"), rs.getString("content"), rs.getString("type"));
                // 响应
                req.setAttribute("blog", blog);
                req.getRequestDispatcher("/update-blog.jsp").forward(req, resp);
            } else {
                resp.setContentType("text/html");
                resp.getWriter().println("id="+ id +" 的博客不存在");
            }
        } catch (SQLException e) {
            resp.getWriter().println("查看博客（id=" + id + "）时发生错误: " + e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        // 接收用户输入的内容
        String id = req.getParameter("id");
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        String type = req.getParameter("type");
        // 验证用户的输入

        // 保存到数据库
        Connection connection = MyDBUtil.getConnection();
        String sql = "UPDATE blog set title=?, content=?, type=? where id=?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, title);
            pstmt.setString(2, content);
            pstmt.setString(3, type);
            pstmt.setString(4, id);

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                resp.sendRedirect(req.getContextPath() + "/blog-list");
            } else {
                resp.getWriter().println("修改博客失败");
            }
        } catch (SQLException e) {
            resp.getWriter().println("修改博客时发生错误: " + e.getMessage());
        }
    }
}
