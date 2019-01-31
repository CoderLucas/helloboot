package com.helloboot.util.excel;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

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
