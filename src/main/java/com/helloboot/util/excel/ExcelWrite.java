package com.helloboot.util.excel;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * @author lujianhao
 * @date 2019/1/31
 */
public class ExcelWrite {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    public ExcelWrite() {
        this.workbook = new XSSFWorkbook();
    }

    public void createNewSheet(String name) {
        this.sheet = workbook.createSheet(name);
    }

    public void createNewSheet() {
        this.sheet = workbook.createSheet();
    }

    public XSSFSheet getSheet() {
        return sheet;
    }

    public void setSheet(XSSFSheet sheet) {
        this.sheet = sheet;
    }

    public XSSFWorkbook getWorkbook() {
        return workbook;
    }

    public void setLineValue(List<String> values, int rowNumber, boolean isTitle) {
        XSSFRow row = this.sheet.createRow(rowNumber);
        int i = 0;
        XSSFCell cell;
        for (String value : values) {
            cell = row.createCell(i);
            if (isTitle) {
                XSSFCellStyle cellStyle = this.workbook.createCellStyle();
                XSSFFont font = this.workbook.createFont();
                font.setBold(true);
                cellStyle.setFont(font);
                cellStyle.setAlignment(HorizontalAlignment.CENTER);
                cell.setCellStyle(cellStyle);
            }
            cell.setCellType(CellType.STRING);
            cell.setCellValue(value);
            i++;
        }
    }

    public void writeXlsx(String filePath) throws IOException {
        FileOutputStream fOut = new FileOutputStream(filePath);
        this.workbook.write(fOut);
        fOut.flush();
        fOut.close();
    }
}
