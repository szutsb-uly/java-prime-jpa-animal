package hu.ulyssys.java.course.maven.service.impl;

import hu.ulyssys.java.course.maven.entity.Slug;
import hu.ulyssys.java.course.maven.service.SlugService;
import hu.ulyssys.java.course.maven.service.XLSExportService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import javax.inject.Inject;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class XLSExportServiceImpl implements XLSExportService {
    @Inject
    private SlugService slugService;

    @Override
    public void processExport() {

        //https://poi.apache.org/components/spreadsheet/quick-guide.html
        Workbook wb = new HSSFWorkbook();  // or new XSSFWorkbook();
        Sheet slugSheet = wb.createSheet("Slug");

        CellStyle style = wb.createCellStyle();
        Font font = wb.createFont();
        font.setCharSet(FontCharset.ARABIC.getValue());
        font.setBold(true);
        style.setFont(font);

        Row headerRow = slugSheet.createRow(0);
        Cell id = headerRow.createCell(0);
        id.setCellValue("ID");
        id.setCellStyle(style);

        Cell name = headerRow.createCell(1);
        name.setCellValue("Név");
        name.setCellStyle(style);

        Cell legsNumber = headerRow.createCell(2);
        legsNumber.setCellValue("Lábak száma");
        legsNumber.setCellStyle(style);

        Cell type = headerRow.createCell(3);
        type.setCellValue("Állat típusa");
        type.setCellStyle(style);


        int i = 1;
        for (Slug slug : slugService.getAll()) {
            Row row = slugSheet.createRow(i);
            row.createCell(0).setCellValue(slug.getId());
            row.createCell(1).setCellValue(slug.getName());
            row.createCell(2).setCellValue(slug.getLegsNumber());
            row.createCell(3).setCellValue(slug.getType().toString());
            i++;
        }

        try (OutputStream fileOut = new FileOutputStream("workbook.xls")) {
            wb.write(fileOut);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
