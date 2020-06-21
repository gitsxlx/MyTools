package com.test.demo.document.pdf;

import java.io.File;

/**
 * FileDeleteTest
 *
 * @author WangChao
 * @date 2020/06/20
 */
public class FileDeleteTest {

	/**
	 * 清除文件
	 * @param workspaceRootPath
	 */
    public void clearFiles(String workspaceRootPath) {
        File file = new File(workspaceRootPath);
        if (file.exists()) {
            deleteFile(file);
        }
    }

	/**
	 * 删除文件
	 * @param file
	 */
	public void deleteFile(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                deleteFile(files[i]);
            }
        }
        file.delete();
    }
}
