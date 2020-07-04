package com.tool.demo.zip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;
import org.apache.tools.tar.TarEntry;
import org.apache.tools.tar.TarInputStream;
import org.apache.tools.tar.TarOutputStream;

/**
 * 压缩、解压
 *
 * @author WangChao
 * @date 2020/04/11
 */
public class ZipTest {

    public static void main(String[] args) {
        String path = "E:\\MyTest";
        String name = "E:\\MyTest\\myTest.zip";
        //ZipOutputStreamWrite(path, name);

        String gzFileStr = "E:\\MyTest\\myTest.zip";
        ZipFileWrite(gzFileStr);
    }

    //使用GZIPOutputStream压缩文件
    public static void GZIPOutputStreamWrite() {
        String str = "F:\\MyTest\\myTestFile.txt";
        //开始压缩文件到一个.gz包中  而这里的.gz之前的文件名可与之前不一致，如下面的文件名，
        //使用解压工具看到里面的文件是去掉.gz之前的部分myTestFile001.txt
        try (FileOutputStream fileOutputStream = new FileOutputStream(
                "F:\\MyTest\\myTestFile001.txt.gz");
                GZIPOutputStream gzipOutputStream = new GZIPOutputStream(fileOutputStream)) {
            FileInputStream fileInputStream = new FileInputStream(str);
            byte[] b = new byte[1024 * 1024 * 5];
            int length = 0;
            while ((length = fileInputStream.read(b)) != -1) {
                gzipOutputStream.write(b, 0, length);
            }
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("压缩完成...");
    }

    //使用GZIPOutputStream解压指定文件
    public static void GZIPOutputStreamRead() {
        String ouputfile = "";
        String sourcedir = "F:\\MyTest\\myTestFile001.txt.gz";

        System.out.println(sourcedir.substring(0, sourcedir.lastIndexOf('.')));
        try (  //建立gzip压缩文件输入流
                FileInputStream fin = new FileInputStream(sourcedir);
                //建立gzip解压工作流
                GZIPInputStream gzin = new GZIPInputStream(fin)) {

            //建立解压文件输出流文件路劲 
            ouputfile = sourcedir.substring(0, sourcedir.lastIndexOf('.'));

            FileOutputStream fout = new FileOutputStream(ouputfile);

            int num;
            byte[] buf = new byte[1024 * 1024 * 5];
            while ((num = gzin.read(buf, 0, buf.length)) != -1) {
                fout.write(buf, 0, num);
            }
            fout.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("解压完成...");
    }

    //使用ZipOutputStream压缩多个指定文件
    public static void ZipOutputStreamWrite(String path, String name) {
        //String [] strArrays = {"F:\\MyTest\\myTestFile001.txt","F:\\MyTest\\myTestFile001.txt.gz"};
        List<String> list = Collections.synchronizedList(new ArrayList<String>());
        File f = new File(path);
        if (f.exists()) {
            if (f.isDirectory()) {
                Arrays.asList(f.listFiles()).forEach(item -> list.add(item.getAbsolutePath()));
            } else if (f.isFile()) {
                list.add(f.getAbsolutePath());
            }
        }
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(name));) {
            for (String string : list) {
                File file = new File(string);
                //和TarOutputStream的TarEntry放的是File类型，这里放的是文件名
                ZipEntry zipEntry = new ZipEntry(file.getName());
                zipOutputStream.putNextEntry(zipEntry);
                FileInputStream fileInputStream = new FileInputStream(string);
                byte[] b = new byte[1024 * 1024 * 5];
                int length = 0;
                while ((length = fileInputStream.read(b)) != -1) {
                    zipOutputStream.write(b, 0, length);
                }

                fileInputStream.close();
                zipOutputStream.closeEntry();
                //调用了这个方法之后，后面的文件是不能被添加的，压缩包里面只有第一个文件，
//				gzipOutputStream.finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("压缩完成...");
    }

    //解压zip包
    public static void ZipFileWrite(String gzFileStr) {
        //String gzFileStr = "F:\\MyTest\\myTest.zip";
        File file = new File(gzFileStr);
        if (!file.exists()) {
            System.out.println("文件不存在");
            return;
        }
        try (ZipFile zf = new ZipFile(gzFileStr);) {
            Enumeration<? extends ZipEntry> entries = zf.entries();

            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                String zipEntryName = entry.getName();

                System.out.println(zipEntryName);
                FileOutputStream fileOutputStream = new FileOutputStream(
                        file.getParent() + "\\" + "unzip" + zipEntryName);
                InputStream inputStream = zf.getInputStream(entry);
                byte[] b = new byte[1024 * 1024 * 5];
                int length = 0;
                while ((length = inputStream.read(b)) != -1) {
                    fileOutputStream.write(b, 0, length);
                }
                fileOutputStream.close();
                inputStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("解压完成...");
    }

    //使用TarOutputStream打包多个文件：（只是打包，没有压缩文件）
    public void TarOutputStream() {
        String[] strArrays = {"F:\\MyTest\\myTestFile001.txt", "F:\\MyTest\\myTestFile001.txt.gz"};
        try (
                FileOutputStream fileOutputStream = new FileOutputStream("F:\\MyTest\\myTest.tar");
                TarOutputStream tarOutputStream = new TarOutputStream(fileOutputStream);
                //先放到内存中
//				ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//				TarOutputStream tarOutputStream = new TarOutputStream(byteArrayOutputStream);
        ) {

            for (String string : strArrays) {
                File file = new File(string);
                TarEntry tarEntry = new TarEntry(file);
                tarEntry.setName(file.getName());
                tarOutputStream.putNextEntry(tarEntry);
                FileInputStream fileInputStream = new FileInputStream(string);
                byte[] b = new byte[1024 * 1024 * 5];
                int length = 0;
                while ((length = fileInputStream.read(b)) != -1) {
                    tarOutputStream.write(b, 0, length);
                }

                fileInputStream.close();
                tarOutputStream.flush();
                tarOutputStream.closeEntry();
            }
            tarOutputStream.flush();
            tarOutputStream.finish();

            //如果是在内存中的数据，则使用工具类写入到文件
//			byte[] byteArray = byteArrayOutputStream.toByteArray();
//			IOUtils.write(byteArray, fileOutputStream);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //解压TarOutputStream打包的文件
    public void TarInputStream() {
        String gzFileStr = "F:\\MyTest\\myTest.tar";//使用tar打包的，使用zip解压错误
        File file = new File(gzFileStr);
        try {
            TarInputStream tarTestFile = new TarInputStream(new FileInputStream(file));
            TarEntry nextEntry = null;
            while ((nextEntry = tarTestFile.getNextEntry()) != null) {
                String tarEntryName = nextEntry.getName();//获取压缩包中 的文件名
                System.out.println(tarEntryName);
                FileOutputStream fileOutputStream = new FileOutputStream(
                        file.getParent() + "\\" + "untar" + tarEntryName);
                byte[] b = new byte[1024 * 1024 * 5];
                int length = 0;
                while ((length = tarTestFile.read(b)) != -1) {
                    fileOutputStream.write(b, 0, length);
                }
                fileOutputStream.close();
            }
            tarTestFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 压缩一个目录 不改变目录的结构
     * @Description: ZipOutputStream压缩一个目录 不改变目录的结构
     * @return void
     */
    public void getZipOutPutStreamEntryFile() {
        String strFolder = "D:\\tmp";
        File fileFolder = new File(strFolder);
        File[] listFiles = fileFolder.listFiles();
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(
                new FileOutputStream("D:\\tmp.zip"))) {
            for (File file : listFiles) {
                getZipOutPutStreamEntryFile(zipOutputStream, file);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getZipOutPutStreamEntryFile(ZipOutputStream zipOutputStream, File file)
            throws Exception {

        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            for (File file2 : listFiles) {
                getZipOutPutStreamEntryFile(zipOutputStream, file2);
            }

        } else {
            try {
                String path = file.getPath();
                //和TarOutputStream的TarEntry放的是File类型，这里放的是文件名
                ZipEntry zipEntry = new ZipEntry(
                        path.substring(path.indexOf("\\") + 1, path.length()));
                zipOutputStream.putNextEntry(zipEntry);
                FileInputStream fileInputStream = new FileInputStream(file.getPath());
                byte[] b = new byte[1024 * 1024 * 5];
                int length = 0;
                while ((length = fileInputStream.read(b)) != -1) {
                    zipOutputStream.write(b, 0, length);
                }

                fileInputStream.close();
                zipOutputStream.closeEntry();
                //调用了这个方法之后，后面的文件是不能被添加的，压缩包里面只有第一个文件，
//			gzipOutputStream.finish();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
