package com.test.demo.file;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.io.Serializable;
import java.io.Writer;
import java.nio.CharBuffer;
import java.util.Date;
import java.util.Scanner;

/**
 * FileTest
 *
 * @author WangChao
 * @date 2020/04/14
 */
public class FileTest {

    public static void main1(String[] args) throws Exception {
        //windows下分隔符“\”，linux下分隔符“/”
        //File file = new File("d:\\test.txt");
        File file = new File(
                "d:" + File.separator + "demo" + File.separator + "message" + File.separator
                        + "test.txt");
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        if (file.exists()) {
            System.out.println("文件删除：" + file.delete());
        } else {
            System.out.println("创建新文件：" + file.createNewFile());
        }
    }

    public static void main2(String[] args) {
        File file = new File("d:" + File.separator + "order.png");
        System.out.println("文件是否可读：" + file.canRead());
        System.out.println("文件是否可写：" + file.canWrite());
        System.out.println("文件大小：" + file.length());
        System.out.println("文件最后修改时间:" + new Date(file.lastModified()));
        System.out.println("是目录吗？" + file.isDirectory());
        System.out.println("是文件吗？" + file.isFile());
    }

    public static void main3(String[] args) {
        File file = new File("d:" + File.separator);
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                System.out.println(files[i]);
                System.out.println(files[i].getName());
            }
        }
    }

    public static void main4(String[] args) {
        File file = new File("d:" + File.separator + "demo");
        listDir(file);
        renameDir(file);
    }

    public static void listDir(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                for (int i = 0; i < files.length; i++) {
                    listDir(files[i]);
                }
            }
        }
        System.out.println(file);
    }

    private static void renameDir(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                for (int i = 0; i < files.length; i++) {
                    renameDir(files[i]);
                }
            }
        } else {
            if (file.isFile()) {
                String fileName =
                        file.getName().substring(0, file.getName().lastIndexOf(".")) + ".txt";
                File newFile = new File(file.getParentFile(), fileName);
                file.renameTo(newFile);
            }
        }
    }


    //FileOutputStream
    public static void main5(String[] args) throws Exception {
        File file = new File("d:" + File.separator + "demo" +
                File.separator + "message" + File.separator + "test.txt");
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        //可以自动创建文件，关闭流，追加
        try (OutputStream os = new FileOutputStream(file, true)) {
            String str = "www.95081.com\r\n";  //换行
            os.write(str.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //FileInputStream
    public static void main6(String[] args) throws Exception {
        File file = new File("d:" + File.separator + "demo" +
                File.separator + "message" + File.separator + "test.txt");
        InputStream is = new FileInputStream(file);
        byte[] data = new byte[1024];  //开辟一个缓冲区读取数据
        int len = is.read(data);  //读取数据，数据全部保存在字节数组中，返回读取个数
        System.out.println("【\r\n" + new String(data, 0, len) + "】");
        is.close();
    }

    //FileWriter
    public static void main7(String[] args) throws Exception {
        File file = new File("d:" + File.separator + "demo" +
                File.separator + "message" + File.separator + "test1.txt");
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        Writer fw = new FileWriter(file);
        String str = "www.95081.com\r\n";
        fw.write(str);
        fw.append("中国人民");
        //fw.flush();
        fw.close();
    }

    //FileReader
    public static void main8(String[] args) throws Exception {
        File file = new File("d:" + File.separator + "demo" +
                File.separator + "message" + File.separator + "test1.txt");
        if (file.exists()) {
            Reader fr = new FileReader(file);
            char[] data = new char[1024];
            int len = fr.read(data);
            System.out.println("读取内容：" + new String(data, 0, len));
            fr.close();
        }
    }

    //OutputStreamWriter
    public static void main9(String[] args) throws Exception {
        File file = new File("d:" + File.separator + "demo" +
                File.separator + "message" + File.separator + "test2.txt");
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        OutputStream os = new FileOutputStream(file);
        Writer osw = new OutputStreamWriter(os);
        osw.write("www.95081.com");
        osw.close();
    }

    public static void main10(String[] args) throws Exception {
        //String src = "D:\\demo\\message\\test2.txt";
        //String des = "D:\\demo\\message\\copy2.txt";
        String src = "D:\\demo";
        String des = "D:\\demo1";
        long start = System.currentTimeMillis();
        FileUtils fu = new FileUtils(src, des);
        if (new File(src).isFile()) {
            System.out.println(fu.copy() ? "文件拷贝成功" : "文件拷贝失败");
        } else {
            System.out.println(fu.copyDir() ? "文件夹拷贝成功" : "文件夹拷贝失败");
        }
        long end = System.currentTimeMillis();
        System.out.println("拷贝用时 " + (end - start) + " ms");
    }

    public static void main11(String[] args) {
        System.getProperties().list(System.out);  //本机属性
    }

    public static void main12(String[] args) throws IOException {
        String str = "www.95081.com";
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new ByteArrayInputStream(str.getBytes());  //将数据保存在内存流
            os = new ByteArrayOutputStream();  //读取内存中的数据
            int data = 0;
            while ((data = is.read()) != -1) {  //每次读取一个字节
                os.write(Character.toUpperCase(data));
            }
            System.out.println(os);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                os.close();
            }
            if (is != null) {
                is.close();
            }
        }
    }

    public static void main13(String[] args) throws IOException {
        String str = "www.95081.com";
        InputStream is = null;
        //使用子类来调用自己的扩展方法
        ByteArrayOutputStream os = null;
        try {
            is = new ByteArrayInputStream(str.getBytes());  //将数据保存在内存流
            os = new ByteArrayOutputStream();  //读取内存中的数据
            int data = 0;
            while ((data = is.read()) != -1) {  //每次读取一个字节
                os.write(Character.toUpperCase(data));
            }
            byte[] result = os.toByteArray();  //获取全部数据
            System.out.println(new String(result));  //自己处理字节数据
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                os.close();
            }
            if (is != null) {
                is.close();
            }
        }
    }

    public static void main14(String[] args) throws Exception {
        SendThread st = new SendThread();
        ReceiveThread rt = new ReceiveThread();
        st.getPos().connect(rt.getPis());
        new Thread(st, "消息发送线程").start();
        new Thread(rt, "消息接收线程").start();
    }

    public static void main15(String[] args) throws Exception {
        File file = new File("d:" + File.separator + "demo" +
                File.separator + "message" + File.separator + "test10.txt");  // 定义操作文件
        RandomAccessFile raf = new RandomAccessFile(file, "rw");  // 读写模式
        String[] names = new String[]{"zhangsan", "lisi    ", "wangwu  "};
        int[] ages = {30, 20, 16};
        for (int i = 0; i < names.length; i++) {
            raf.write(names[i].getBytes());
            raf.writeInt(ages[i]);
        }
        raf.close();
    }

    public static void main16(String[] args) throws Exception {
        File file = new File("d:" + File.separator + "demo" +
                File.separator + "message" + File.separator + "test10.txt");  // 定义操作文件
        RandomAccessFile raf = new RandomAccessFile(file, "rw");  // 读写模式
        {
            raf.skipBytes(24);
            byte[] data = new byte[8];
            int len = raf.read(data);
            System.out.println("姓名：" + new String(data, 0, len).trim() + "、年龄：" + raf.readInt());
        }
        {
            raf.seek(12);
            byte[] data = new byte[8];
            int len = raf.read(data);
            System.out.println("姓名：" + new String(data, 0, len).trim() + "、年龄：" + raf.readInt());
        }
        {
            raf.seek(0);
            byte[] data = new byte[8];
            int len = raf.read(data);
            System.out.println("姓名：" + new String(data, 0, len).trim() + "、年龄：" + raf.readInt());
        }
        raf.close();
    }

    public static void main17(String[] args) throws Exception {
        File file = new File("d:" + File.separator + "demo" +
                File.separator + "message" + File.separator + "test11.txt");
        PrintWriter pw = new PrintWriter(new FileOutputStream(file));
        String name = "小强滋滋";
        int age = 78;
        double salary = 72823.6323113;
        pw.printf("姓名：%s、年龄：%d、收入：%9.2f", name, age, salary);
        pw.close();
    }

    public static void main18(String[] args) throws Exception {
        System.setErr(new PrintStream(new FileOutputStream(
                new File("d:" + File.separator + "demo" + File.separator + "err.txt"))));
        try {
            Integer.parseInt("a");
        } catch (Exception e) {
            System.out.println(e);
            System.err.println(e);  //输出到文件里
        }
    }

    public static void main19(String[] args) throws Exception {
        InputStream is = System.in;  //此时输入流为键盘输入
        System.out.println("请输入内容：");
        byte[] data = new byte[1024];
        int len = is.read(data);
        System.out.println("输出内容为：" + new String(data, 0, len));
    }

    public static void main20(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("请输入信息：");
        String msg = br.readLine();
        System.out.println("输入内容为：" + msg);
    }

    public static void main21(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入信息");
        if (sc.hasNext()) {
            System.out.println("输入内容为：" + sc.next());
        }
        sc.close();
    }

    public static void main(String[] args) throws Exception {
        saveObject(new Person("暗室逢灯", 66));
        System.out.println(loadObject());
    }

    private static final File SAVE_FILE = new File(
            "d:" + File.separator + "demo" + File.separator + "95081.person");

    public static void saveObject(Object obj) throws Exception {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SAVE_FILE));
        oos.writeObject(obj);  //序列化
        oos.close();
    }

    public static Object loadObject() throws Exception {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SAVE_FILE));
        Object obj = ois.readObject();  //反序列化
        ois.close();
        return obj;
    }

}

class Person implements Serializable {

    private static final long serialVersionUID = -3037315968816222427L;
    private transient String name;  //不序列化
    private int age;

    public Person(String name, int age) {
        super();
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person [name=" + name + ", age=" + age + "]";
    }
}

class SendThread implements Runnable {

    private PipedOutputStream pos = null;  // 管道的输出流

    public SendThread() {
        pos = new PipedOutputStream();  // 实例化管道输出流
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                this.pos.write(("【第" + (i + 1) + "次信息发送-" + Thread.currentThread().getName()
                        + "】www.95081.com\n").getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                this.pos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public PipedOutputStream getPos() {
        return pos;
    }

    public void setPos(PipedOutputStream pos) {
        this.pos = pos;
    }
}

class ReceiveThread implements Runnable {

    private PipedInputStream pis = null;  // 管道的输出流

    public ReceiveThread() {
        pis = new PipedInputStream();  // 实例化管道输出流
    }

    @Override
    public void run() {
        try {
            byte[] data = new byte[1024];
            int len = 0;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while ((len = this.pis.read(data)) != -1) {
                baos.write(data, 0, len);
            }
            System.out.println("{" + Thread.currentThread().getName() + "接收消息}\n" + new String(
                    baos.toByteArray()));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                this.pis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public PipedInputStream getPis() {
        return pis;
    }

    public void setPis(PipedInputStream pis) {
        this.pis = pis;
    }

}

class FileUtils {

    private File srcFile;
    private File desFile;

    public FileUtils(String src, String des) {
        this(new File(src), new File(des));
    }

    public FileUtils(File srcFile, File desFile) {
        this.srcFile = srcFile;
        this.desFile = desFile;
    }

    public boolean copy() throws Exception {
        if (!this.srcFile.exists()) {
            System.out.println("拷贝的源文件不存在");
            return false;
        }
        return this.copyFileImpl(this.srcFile, this.desFile);
    }

    public boolean copyFileImpl(File srcFile, File desFile) throws Exception {
        if (!desFile.getParentFile().exists()) {
            desFile.getParentFile().mkdirs();
        }
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(srcFile);
            os = new FileOutputStream(desFile);
            byte[] data = new byte[1024];
            int len = 0;
            while ((len = is.read(data)) != -1) {
                os.write(data, 0, len);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                os.close();
            }
            if (is != null) {
                is.close();
            }
        }
        return false;
    }

    public boolean copyDir() throws Exception {
        try {
            this.copyImpl(this.srcFile);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void copyImpl(File file) throws Exception {
        if (file.isDirectory()) {
            File[] list = file.listFiles();
            if (list != null) {
                for (int i = 0; i < list.length; i++) {
                    copyImpl(list[i]);
                }
            }
        } else {
            String newFileName = file.getPath()
                    .replace(this.srcFile.getPath() + File.separator, "");
            File newFile = new File(this.desFile, newFileName);
            this.copyFileImpl(file, newFile);
        }
    }
}
