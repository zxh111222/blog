package io.github.zxh111222.blog.admin;

import io.github.zxh111222.blog.util.MyDBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

@WebServlet("/admin-add-blog")
@MultipartConfig
public class BlogAddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/admin/add-blog.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        String title = req.getParameter("title");
        String content = req.getParameter("content");
        String type = req.getParameter("type");

        if (title == null || content == null ||
                title.trim().isEmpty() || content.trim().isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/admin-blog-list");
            return;
        }

        // 要上传到哪里
        String uploadPath = getServletContext().getRealPath("/") + "uploads";

        // 处理文件上传 - start
        Part filePart = req.getPart("cover");
        String coverFileName = null;

        // 判断用户有没有上传封面图
        if (filePart != null && filePart.getSize() > 0) {
            // 获取原始文件名和扩展名
            String originalFileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));

            // 生成新的文件名：时间戳_UUID.扩展名
            String newFileName = System.currentTimeMillis() + "_" +
                    UUID.randomUUID().toString().substring(0, 8) +
                    fileExtension;

            // 检查 uploads 文件夹是否存在，如果不存在，自动创建
            Path uploadsPath = Paths.get(uploadPath);
            if (!Files.exists(uploadsPath)) {
                Files.createDirectories(uploadsPath);
            }

            // 使用新文件名保存文件
            File uploadedFile = new File(uploadPath, newFileName);
            filePart.write(uploadedFile.getAbsolutePath());
            coverFileName = "uploads/" + newFileName;
        }
        // 处理文件上传 - end

        String sql = "INSERT INTO blog (title, content, type, cover) VALUES (?, ?, ?, ?)";
        try(
                Connection connection = MyDBUtil.getConnection();
                PreparedStatement pstmt = connection.prepareStatement(sql)
        ){
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
            // 如果添加失败，删除已上传的封面图
            if (coverFileName != null) {
                File uploadedFile = new File(getServletContext().getRealPath("/") + coverFileName);
                if (uploadedFile.exists()) {
                    uploadedFile.delete();
                }
            }
        }
    }
}
