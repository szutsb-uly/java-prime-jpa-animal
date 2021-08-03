package hu.ulyssys.java.course.maven.service.impl;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import hu.ulyssys.java.course.maven.entity.Slug;
import hu.ulyssys.java.course.maven.service.PDFExportService;
import hu.ulyssys.java.course.maven.service.SlugService;

import javax.inject.Inject;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class PDFExportServiceImpl implements PDFExportService {
    @Inject
    private SlugService slugService;

    @Override
    public InputStream processExport() {
        Document document = new Document();
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, outputStream);
            document.open();
            for (Slug slug : slugService.getAll()) {
                document.add(new Paragraph("Id: " + slug.getId() + ", name:" + slug.getName()));
            }
            //Innen loptam https://stackoverflow.com/questions/35288194/how-to-add-url-to-pdf-document-using-itext
            Chunk chunk = new Chunk("Ulyssys.hu");
            chunk.setAnchor("https://ulyssys.hu/");
            document.add(chunk);
            document.close();
            return new ByteArrayInputStream(outputStream.toByteArray());
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return null;
    }
}
