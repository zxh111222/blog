package io.github.zxh111222.blog.admin;

import io.github.zxh111222.blog.Blog;
import io.github.zxh111222.blog.util.MyDBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/admin-delete-blog")
public class BlogDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idStr = request.getParameter("id");
        if (idStr == null || idStr.trim().isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/admin-blog-list");
            return;
        }

        try {
            int id = Integer.parseInt(idStr);

            // 先获取博客信息，主要是为了获取封面图路径
            Blog blog = getBlogById(id);
            if (blog != null && blog.getCover() != null) {
                // 删除封面图文件
                String coverPath = getServletContext().getRealPath("/") + blog.getCover();
                File coverFile = new File(coverPath);
                if (coverFile.exists()) {
                    coverFile.delete();
                }
            }

            // 删除博客记录
            deleteBlog(id);

            response.sendRedirect(request.getContextPath() + "/admin-blog-list");
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/admin-blog-list");
        }
    }

    private Blog getBlogById(int id) throws ServletException {
        try (Connection conn = MyDBUtil.getConnection()) {
            String sql = "SELECT id, cover FROM blog WHERE id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    Blog blog = new Blog();
                    blog.setId(rs.getInt("id"));
                    blog.setCover(rs.getString("cover"));
                    return blog;
                }
            }
        } catch (SQLException e) {
            throw new ServletException("获取博客信息失败", e);
        }
        return null;
    }

    private void deleteBlog(int id) throws ServletException {
        try (Connection conn = MyDBUtil.getConnection()) {
            String sql = "DELETE FROM blog WHERE id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new ServletException("删除博客失败", e);
        }
    }
}