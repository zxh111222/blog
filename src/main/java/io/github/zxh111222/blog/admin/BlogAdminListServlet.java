package io.github.zxh111222.blog.admin;

import io.github.zxh111222.blog.Blog;
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
import java.util.List;

@WebServlet("/admin-blog-list")
public class BlogAdminListServlet extends HttpServlet {
    private static final int PAGE_SIZE = 10; // 每页显示10条
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取搜索参数
        String search = req.getParameter("search");
        req.setAttribute("search", search); // 传递给JSP用于显示

        // 获取当前页码
        int currentPage = 1;
        try {
            String pageStr = req.getParameter("page");
            if (pageStr != null) {
                currentPage = Integer.parseInt(pageStr);
                if (currentPage < 1) currentPage = 1;
            }
        } catch (NumberFormatException e) {
            currentPage = 1;
        }

        try (Connection conn = MyDBUtil.getConnection()) {
            // 准备SQL语句
            String whereClause = "";
            String countSql = "SELECT COUNT(*) FROM blog";
            String selectSql = "SELECT id, title, type FROM blog";

            if (search != null && !search.trim().isEmpty()) {
                whereClause = " WHERE title LIKE ?";
                countSql += whereClause;
                selectSql += whereClause;
            }
            selectSql += " ORDER BY id DESC LIMIT ? OFFSET ?";

            // 获取总记录数
            int totalRecords = 0;
            try (PreparedStatement countStmt = conn.prepareStatement(countSql)) {
                if (!whereClause.isEmpty()) {
                    countStmt.setString(1, "%" + search + "%");
                }
                ResultSet countRs = countStmt.executeQuery();
                if (countRs.next()) {
                    totalRecords = countRs.getInt(1);
                }
            }

            // 计算总页数
            int totalPages = (int) Math.ceil((double) totalRecords / PAGE_SIZE);
            if (currentPage > totalPages) currentPage = totalPages;
            if (currentPage < 1) currentPage = 1;

            // 获取当前页的数据
            List<Blog> blogs = new ArrayList<>();
            try (PreparedStatement stmt = conn.prepareStatement(selectSql)) {
                int paramIndex = 1;
                if (!whereClause.isEmpty()) {
                    stmt.setString(paramIndex++, "%" + search + "%");
                }
                stmt.setInt(paramIndex++, PAGE_SIZE);
                stmt.setInt(paramIndex, (currentPage - 1) * PAGE_SIZE);

                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    Blog blog = new Blog();
                    blog.setId(rs.getInt("id"));
                    blog.setTitle(rs.getString("title"));
                    blog.setType(rs.getString("type"));
                    blogs.add(blog);
                }
            }
// 设置分页相关属性
            req.setAttribute("blogs", blogs);
            req.setAttribute("currentPage", currentPage);
            req.setAttribute("totalPages", totalPages);
            req.setAttribute("totalRecords", totalRecords);

        } catch (SQLException e) {
            throw new ServletException("获取博客列表失败", e);
        }
        req.getRequestDispatcher("/WEB-INF/admin/admin-blog-list.jsp").forward(req, resp);
    }
}