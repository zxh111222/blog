package io.github.zxh111222.blog.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import java.util.logging.Logger;

@WebServlet("/upload-image")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,    // 1 MB
        maxFileSize = 1024 * 1024 * 5,      // 5 MB
        maxRequestSize = 1024 * 1024 * 10   // 10 MB
)
public class ImageUploadServlet extends HttpServlet {

    private static final String UPLOAD_DIRECTORY = "uploads";
    private static final Logger logger = Logger.getLogger(ImageUploadServlet.class.getName());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        try {
            Part filePart = request.getPart("image");
            if (filePart.getSize() > 5 * 1024 * 1024) {
                throw new IllegalArgumentException("文件大小不能超过5MB");
            }
            String fileName = getUniqueFileName(filePart.getSubmittedFileName());
            String filePath = uploadPath + File.separator + fileName;

            filePart.write(filePath);

            // 构建完整的URL
            String fileUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                    + request.getContextPath() + "/" + UPLOAD_DIRECTORY + "/" + fileName;

            logger.info("File uploaded successfully. URL: " + fileUrl);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            String jsonResponse = "{\"success\": 1, \"file\": {\"url\": \"" + fileUrl + "\"}}";
            response.getWriter().write(jsonResponse);

            logger.info("Response sent: " + jsonResponse);
        } catch (IllegalArgumentException e) {
            logger.warning("File size exceeded: " + e.getMessage());
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"success\": 0, \"message\": \"" + e.getMessage() + "\"}");
        } catch (Exception e) {
            logger.severe("Error uploading file: " + e.getMessage());
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"success\": 0, \"message\": \"上传失败: " + e.getMessage() + "\"}");
        }
    }

    private String getUniqueFileName(String originalFileName) {
        String extension = "";
        int i = originalFileName.lastIndexOf('.');
        if (i > 0) {
            extension = originalFileName.substring(i);
        }
        return UUID.randomUUID().toString() + extension;
    }
}
