package com.helloboot;

import com.helloboot.util.excel.ExcelReader;
import com.helloboot.util.excel.ExcelUtils;
import org.apache.poi.ss.usermodel.CellType;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;

/**
 * @author lujianhao
 * @date 2019/1/31
 */
public class ExcelUtilsTest {

    @Test
    public void excel2Map() throws FileNotFoundException {
        File file = new File("/Users/lujianhao/Desktop/12月20号-12月23号义诊数据.xls");
        ExcelReader excelReader = new ExcelReader();
        Map map = ExcelUtils.readExcelContent(new FileInputStream(file), CellType.STRING,null );
        System.out.println(map);
    }
}
