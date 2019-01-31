package com.helloboot.util.excel;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author lujianhao
 * @date 2019/1/31
 */
public class ExcelReader {
    private POIFSFileSystem fs;
    private HSSFWorkbook wb;
    private HSSFSheet sheet;
    private HSSFRow row;

    /**
     * 读取Excel数据内容
     *
     * @param is InputStream
     * @return Map 包含单元格数据内容的Map对象
     */
    public Map<Integer, List<String>> readExcelContent(InputStream is, Integer cellType, Integer columnNum) {
        Map<Integer, List<String>> content = new HashMap<>();
        List<String> values;
        try {
            fs = new POIFSFileSystem(is);
            wb = new HSSFWorkbook(fs);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sheet = wb.getSheetAt(0);
        // 得到总行数
        int rowNum = sheet.getLastRowNum();
        int colNum = 0;
        if(columnNum == null) {
            row = sheet.getRow(0);
            colNum = row.getPhysicalNumberOfCells();
        }else{
            colNum = columnNum;
        }
        // 正文内容应该从第二行开始,第一行为表头的标题
        for (int i = 1; i <= rowNum; i++) {
            values = new ArrayList<>();
            row = sheet.getRow(i);
            int j = 0;
            while (j < colNum) {
                // 每个单元格的数据内容用"-"分割开，以后需要时用String类的replace()方法还原数据
                // 也可以将每个单元格的数据设置到一个javabean的属性中，此时需要新建一个javabean
                HSSFCell cell = row.getCell(j);
                if(cell != null && cellType != null){
                    cell.setCellType(cellType);
                }
                values.add(getCellFormatValue(cell).trim());
                j++;
            }
            content.put(i, values);
        }
        return content;
    }

    public Map<Integer, List<String>> readExcelContentWithXssf(InputStream is, Integer cellType, Integer columnNum){
        Map<Integer, List<String>> content = new HashMap<>();
        List<String> values;
        XSSFWorkbook xswb = null;
        try {
            xswb = new XSSFWorkbook(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        XSSFSheet xssheet = xswb.getSheetAt(0);
        // 得到总行数
        int rowNum = xssheet.getLastRowNum();
        int colNum = 0;
        if(columnNum == null) {
            XSSFRow xsrow = xssheet.getRow(0);
            colNum = xsrow.getPhysicalNumberOfCells();
        }else{
            colNum = columnNum;
        }
        // 正文内容应该从第二行开始,第一行为表头的标题
        for (int i = 1; i <= rowNum; i++) {
            values = new ArrayList<>();
            XSSFRow xsrow = xssheet.getRow(i);
            int j = 0;
            while (j < colNum) {
                // 每个单元格的数据内容用"-"分割开，以后需要时用String类的replace()方法还原数据
                // 也可以将每个单元格的数据设置到一个javabean的属性中，此时需要新建一个javabean
                XSSFCell cell = xsrow.getCell(j);
                if(cell != null && cellType != null){
                    cell.setCellType(cellType);
                }
                values.add(getCellFormatValue(cell).trim());
                j++;
            }
            content.put(i, values);
        }
        return content;

    }


    public Map<Integer, List<String>> readExcelContent(InputStream is) {
        return readExcelContent(is,null,null);
    }


    /**
     * 根据HSSFCell类型设置数据
     *
     * @param cell
     * @return
     */
    private String getCellFormatValue(Cell cell) {
        String cellvalue = "";
        if (cell != null) {
            // 判断当前Cell的Type
            switch (cell.getCellTypeEnum()) {
                // 如果当前Cell的Type为NUMERIC
                case NUMERIC:
                case FORMULA: {
                    // 判断当前的cell是否为Date
                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                        // 如果是Date类型则，转化为Data格式

                        //方法1：这样子的data格式是带时分秒的：2011-10-12 0:00:00
                        //cellvalue = cell.getDateCellValue().toLocaleString();

                        //方法2：这样子的data格式是不带带时分秒的：2011-10-12
                        Date date = cell.getDateCellValue();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        cellvalue = sdf.format(date);
                    }
                    // 如果是纯数字
                    else {
                        // 取得当前Cell的数值
                        cellvalue = String.valueOf(cell.getNumericCellValue());
                    }
                    break;
                }
                // 如果当前Cell的Type为STRIN
                case STRING:
                    // 取得当前的Cell字符串
                    cellvalue = cell.getRichStringCellValue().getString();
                    break;
                // 默认的Cell值
                default:
                    cellvalue = " ";
            }
        } else {
            cellvalue = "";
        }
        return cellvalue;
    }
}
