package io.github.zxh111222.blog.admin;

import io.github.zxh111222.blog.Blog;
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
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/admin-update-blog")
@MultipartConfig
public class BlogUpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 接收参数
        String id = req.getParameter("id");

        // 根据 id，从数据库获取匹配的用户信息
        Connection connection = MyDBUtil.getConnection();
        String sql = "SELECT id, title, content, type, cover FROM blog WHERE id=?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, id);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Blog blog = new Blog(Integer.parseInt(id), rs.getString("title"), rs.getString("content"), rs.getString("type"), rs.getString("cover"));
                // 响应
                req.setAttribute("blog", blog);
                req.getRequestDispatcher("/WEB-INF/admin/update-blog.jsp").forward(req, resp);
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
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        // 接收用户输入的内容
        String id = req.getParameter("id");
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        String type = req.getParameter("type");


        // 要上传到哪里
        //String uploadPath = Paths.get(System.getProperty("user.home") + "/Desktop/blog/src/main/webapp/uploads").toAbsolutePath().toString();
        String uploadPath = getServletContext().getRealPath("/") + "uploads";

        // 处理文件上传 - start
        Part filePart = req.getPart("cover");
        String coverFileName = null;

        // 判断用户有没有上传封面图
        if (filePart != null && filePart.getSize() > 0) {
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

            // 检查 uploads 文件夹是否存在，如果不存在，自动创建
            Path uploadsPath = Paths.get(uploadPath);
            if (!Files.exists(uploadsPath)) {
                Files.createDirectories(uploadsPath);
            }

            File uploadedFile = new File(uploadPath, fileName);
            filePart.write(uploadedFile.getAbsolutePath());
            coverFileName = "uploads/" + fileName;
        }
        // 处理文件上传 - end


        // 验证用户的输入

        // 保存到数据库
        Connection connection = MyDBUtil.getConnection();
        String sql;
        if (coverFileName != null) {
            sql = "UPDATE blog SET title=?, content=?, type=?, cover=? WHERE id=?";
        } else {
            sql = "UPDATE blog SET title=?, content=?, type=? WHERE id=?";
        }

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, title);
            pstmt.setString(2, content);
            pstmt.setString(3, type);

            if (coverFileName != null) {
                pstmt.setString(4, coverFileName);
                pstmt.setString(5, id);
            } else {
                pstmt.setString(4, id);
            }

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                resp.sendRedirect(req.getContextPath() + "/admin-blog-list");
            } else {
                resp.getWriter().println("修改博客失败");
            }
        } catch (SQLException e) {
            resp.getWriter().println("修改博客时发生错误: " + e.getMessage());
        }

    }
}