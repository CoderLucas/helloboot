package com.helloboot.util.excel;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author lujianhao
 * @date 2018/12/17
 */
public class ExcelUtils {

    /**
     * 下载报告逻辑
     *
     * @param request
     * @param response
     * @param titleList        excl 列标题
     * @param dataList         excl 行值
     * @param downloadFileName excl 下载名
     * @return void
     */
    public static void download(HttpServletRequest request,
                                HttpServletResponse response,
                                List<String> titleList,
                                List<List<String>> dataList,
                                String downloadFileName) {
        ExcelWrite xls = new ExcelWrite();
        xls.createNewSheet();
        xls.setLineValue(titleList, 0, true);
        for (int row = 0; row < dataList.size(); row++) {
            List<String> recordValue = dataList.get(row);
            // 将表单中的记录，按照相应的属性写入xls
            xls.setLineValue(recordValue, row + 1, false);
        }
        // 根据内容长度自适应 cell 宽度
        XSSFSheet xSSFSheet = xls.getSheet();
        for (int i = 0; i < titleList.size() + 1; i++) {
            xSSFSheet.autoSizeColumn(i);
        }
        xls.setSheet(xSSFSheet);

        download(request, response, xls.getWorkbook(), downloadFileName + ".xlsx");
    }

    /**
     * 输出下载
     *
     * @param request
     * @param response
     * @param xSSFWorkbook
     * @param downloadFileName
     */
    private static void download(HttpServletRequest request,
                                 HttpServletResponse response,
                                 XSSFWorkbook xSSFWorkbook,
                                 String downloadFileName) {
        OutputStream out = null;
        try {
            String agent = request.getHeader("User-Agent").toString();
            if (agent.indexOf("IE") != -1) {
                response.reset(); // 设置缓存为空，这样ie6下才可以下载
                response.addHeader("Content-Disposition",
                        "attachment;filename=" +
                                URLEncoder.encode(downloadFileName, "UTF-8"));// 使ie文件名下不会乱码
            } else {
                response.addHeader("Content-Disposition",
                        "attachment; filename=\"" +
                                new String((downloadFileName).getBytes("UTF-8"), "iso-8859-1") + "\"");
            }
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            out = response.getOutputStream();
            xSSFWorkbook.write(out);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeQuietly(out);
        }
    }


    /**
     * 读取Excel数据内容
     *
     * @param is InputStream
     * @return Map 包含单元格数据内容的Map对象
     */
    public static Map<Integer, List<String>> readExcelContent(InputStream is, CellType cellType, Integer columnNum) {
        HSSFWorkbook wb = null;
        HSSFRow row;
        Map<Integer, List<String>> content = new HashMap<>();
        List<String> values;
        try {
            POIFSFileSystem fs = new POIFSFileSystem(is);
            wb = new HSSFWorkbook(fs);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HSSFSheet sheet = wb.getSheetAt(0);
        // 得到总行数
        int rowNum = sheet.getLastRowNum();
        int colNum = 0;
        if (columnNum == null) {
            row = sheet.getRow(0);
            colNum = row.getPhysicalNumberOfCells();
        } else {
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
                if (cell != null && cellType != null) {
                    cell.setCellType(cellType);
                }
                values.add(getCellFormatValue(cell).trim());
                j++;
            }
            content.put(i, values);
        }
        return content;
    }

    public static Map<Integer, List<String>> readExcelContentWithXssf(InputStream is, CellType cellType, Integer columnNum) {
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
        if (columnNum == null) {
            XSSFRow xsrow = xssheet.getRow(0);
            colNum = xsrow.getPhysicalNumberOfCells();
        } else {
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
                if (cell != null && cellType != null) {
                    cell.setCellType(cellType);
                }
                values.add(getCellFormatValue(cell).trim());
                j++;
            }
            content.put(i, values);
        }
        return content;

    }


    public static Map<Integer, List<String>> readExcelContent(InputStream is) {
        return readExcelContent(is, null, null);
    }

    /**
     * 根据HSSFCell类型设置数据
     *
     * @param cell
     * @return
     */
    private static String getCellFormatValue(Cell cell) {
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

    private static void closeQuietly(final Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException ignored) {
                // No output exception
            }
        }
    }
}
