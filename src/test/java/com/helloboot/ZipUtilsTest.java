package com.helloboot;

import com.helloboot.util.file.FileUtils;
import com.helloboot.util.file.ZipUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


import static com.helloboot.TestConfig.PATH_TEMP;
import static com.helloboot.TestConfig.PATH_ZIP;
import static junit.framework.TestCase.assertTrue;

/**
 * @author lujianhao
 * @date 2019/1/25
 */
public class ZipUtilsTest {
    private String zipFile  = PATH_TEMP + "zipFile.zip";
    private String zipFiles = PATH_TEMP + "zipFiles.zip";

    @Before
    public void setUp() throws Exception {
        FileUtils.createOrExistsDir(PATH_TEMP);
        assertTrue(ZipUtils.zipFile(PATH_ZIP, zipFile, "测试zip"));
    }

    @Test
    public void zipFiles() throws Exception {
        List<String> files = new ArrayList<>();
        files.add(PATH_ZIP + "test.txt");
        files.add(PATH_ZIP);
        files.add(PATH_ZIP + "testDir");
        assertTrue(ZipUtils.zipFiles(files, zipFiles));
    }

    @Test
    public void unzipFile() throws Exception {
        System.out.println(ZipUtils.unzipFile(zipFile, PATH_TEMP));
    }

    @Test
    public void unzipFileByKeyword() throws Exception {
        System.out.println((ZipUtils.unzipFileByKeyword(zipFile, PATH_TEMP, null)).toString());
    }

    @Test
    public void getFilesPath() throws Exception {
        System.out.println(ZipUtils.getFilesPath(zipFile));
    }

    @Test
    public void getComments() throws Exception {
        System.out.println(ZipUtils.getComments(zipFile));
    }

    @After
    public void tearDown() {
        FileUtils.deleteAllInDir(PATH_TEMP);
    }
}
