package com.test.demo.file;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * FileUtil
 *
 * @author WangChao
 * @date 2020/04/14
 */
public class FileUtil {

    public static void main(String[] args) {
        String path = "-vipOrder-page.txt";
        readFile(path);
    }

    public static void readFile(String filepath) {
        int count = 0;
        File file = new File("D:\\temp\\123");
        if (file.isDirectory()) {
            System.out.println("文件夹");
            String[] filelist = file.list();
            for (int i = 0; i < filelist.length; i++) {
                File readfile = new File(filepath + "\\" + filelist[i]);
                if (!readfile.isDirectory()) {
					/*System.out.println("path=" + readfile.getPath());
					System.out.println("absolutepath="
							+ readfile.getAbsolutePath());*/
                    System.out.println("name=" + readfile.getName());
                    if (readfile.getName().endsWith("-vipOrder-page.txt")) {
                        count = count + 1;
                    }
                }/* else if (readfile.isDirectory()) {
					readFile(filepath + "\\" + filelist[i]);
				}*/
            }
        }
        System.out.println("共：" + count);
    }

    public static void writeFile(String filepath, List<String> list) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(filepath, true); //创建文本文件，追加写入
            int i = 0;
            while (list != null && i < list.size()) { //循环写入
                System.out.println(list.get(i));
                fileWriter.write(list.get(i) + "\r\n"); //写入 \r\n换行
                i++;
            }
            //fileWriter.write("共"+i+"条");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileWriter != null) {
                    fileWriter.flush();
                    fileWriter.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
