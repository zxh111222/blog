package io.github.zxh111222.blog;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/admin-add-blog")
@MultipartConfig
public class BlogAddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/add-blog.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        // todo: 1. 接收用户数据、2.验证数据、3.处理数据（保存数据库）4.响应内容 ...

        String title = req.getParameter("title");
        String content = req.getParameter("content");
        String type = req.getParameter("type");

        // 要上传到哪里
        //String uploadPath = Paths.get(System.getProperty("user.home") + "/Desktop/blog/src/main/webapp/uploads").toAbsolutePath().toString();
        String uploadPath = getServletContext().getRealPath("/") + "uploads";

        // 处理文件上传 - start
        Part filePart = req.getPart("cover");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        File uploadedFile = new File(uploadPath, fileName);
        filePart.write(uploadedFile.getAbsolutePath());
        String coverFileName = "uploads/" + fileName;
        // 处理文件上传 - end

        Connection connection = MyDBUtil.getConnection();
        String sql = "INSERT INTO blog (title, content, type, cover) VALUES (?, ?, ?, ?)";
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, title);
            pstmt.setString(2, content);
            pstmt.setString(3, type);
            pstmt.setString(4, coverFileName);

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                // 跳转
                resp.sendRedirect(req.getContextPath() + "/admin-blog-list");
            } else {
                resp.getWriter().println("添加博客失败");
            }
        } catch (SQLException e) {
            resp.getWriter().println("添加博客时发生错误: " + e.getMessage());
        } finally {
            // 关闭资源
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
