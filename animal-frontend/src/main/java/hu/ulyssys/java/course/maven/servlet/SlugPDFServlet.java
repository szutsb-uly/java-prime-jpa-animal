package hu.ulyssys.java.course.maven.servlet;

import hu.ulyssys.java.course.maven.service.PDFExportService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@WebServlet("/slug-pdf")
public class SlugPDFServlet extends HttpServlet {

    private static final int SIZE = 1048;

    @Inject
    private PDFExportService pdfExportService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/pdf");
        resp.setHeader("Content-disposition", "attachment; filename=slug.pdf");

        try (InputStream in = pdfExportService.processExport(); OutputStream out = resp.getOutputStream()) {
            byte[] buffer = new byte[SIZE];
            int numBytesRead;
            while ((numBytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, numBytesRead);
            }
        }
    }
}
