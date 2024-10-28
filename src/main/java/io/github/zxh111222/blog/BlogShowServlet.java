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

@WebServlet("/blog-show")
public class BlogShowServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id == null || id.isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/blog-list");
            return;
        }

        try {
            Blog blog = getBlogById(Integer.parseInt(id));
            if (blog == null) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "博客不存在");
                return;
            }
            req.setAttribute("blog", blog);
            req.getRequestDispatcher("/WEB-INF/blog-show.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new ServletException("获取博客详情时发生错误", e);
        }
    }

    private Blog getBlogById(int id) throws SQLException {

        String sql = "SELECT * FROM blog WHERE id = ?";
        try (
                Connection conn = MyDBUtil.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Blog blog = new Blog();
                    blog.setId(rs.getInt("id"));
                    blog.setTitle(rs.getString("title"));
                    blog.setContent(rs.getString("content"));
                    blog.setCover(rs.getString("cover"));
                    return blog;
                }
            }
        }
        return null;
    }
}