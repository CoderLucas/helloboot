package com.helloboot.util.excel;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

/**
 * @author lujianhao
 * @date 2019/1/31
 */
public class ExcelReader {
    private POIFSFileSystem fs;
    private HSSFWorkbook wb;
    private HSSFSheet sheet;
    private HSSFRow row;

    public POIFSFileSystem getFs() {
        return fs;
    }

    public void setFs(POIFSFileSystem fs) {
        this.fs = fs;
    }

    public HSSFWorkbook getWb() {
        return wb;
    }

    public void setWb(HSSFWorkbook wb) {
        this.wb = wb;
    }

    public HSSFSheet getSheet() {
        return sheet;
    }

    public void setSheet(HSSFSheet sheet) {
        this.sheet = sheet;
    }

    public HSSFRow getRow() {
        return row;
    }

    public void setRow(HSSFRow row) {
        this.row = row;
    }
}
