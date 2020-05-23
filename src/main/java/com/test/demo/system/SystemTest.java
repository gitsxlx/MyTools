package com.test.demo.system;

import java.util.Properties;

/**
 * 系统类
 * System 不能被实例化，继承（private构造，final修饰）
 *
 * @author WangChao
 * @date 2020/04/11
 */
public class SystemTest {

    public static void main(String[] args) {
        // 返回当前时间（以毫秒为单位）
        long time = System.currentTimeMillis();
        System.out.println(time);

        // 将指定源数组中的数组从指定位置复制到目标数组的指定位置。
        int[] src = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] dest = new int[10];
        System.arraycopy(src, 3, dest, 5, 4);
        System.out.println(dest);

        // 确定当前的系统属性
        Properties properties = System.getProperties();
        System.out.println(properties);

        // 获取指定键指示的系统属性
        System.out.println("用户的账户名称:" + System.getProperty("user.name"));
        System.out.println("用户的主目录:" + System.getProperty("user.home"));
        System.out.println("用户的当前工作目录:" + System.getProperty("user.dir"));
        System.out.println("操作系统的名称:" + System.getProperty("os.name"));
        System.out.println("操作系统的架构:" + System.getProperty("os.arch"));
        System.out.println("操作系统的版本:" + System.getProperty("os.version"));
        System.out.println("java安装目录:" + System.getProperty("java.home"));
        System.out.println("java运行时环境版本:" + System.getProperty("java.version"));
        System.out.println("要使用的JIT编译器的名称:" + System.getProperty("java.compiler"));
        System.out.println("java虚拟机实现名称:" + System.getProperty("java.vm.name"));
        System.out.println("java虚拟机实现版本:" + System.getProperty("java.vm.version"));
        System.out.println("java类路径:" + System.getProperty("java.class.path"));
        System.out.println("加载库时搜索的路径列表:" + System.getProperty("java.library.path"));
        System.out.println("分件分隔符:" + System.getProperty("file.separator"));
        System.out.println("路径分隔符:" + System.getProperty("path.separator"));
        System.out.println("行分隔符:" + System.getProperty("line.separator"));

        // 运行垃圾回收器。清除垃圾时，会默认调用被清空对象的finalize方法。
        System.gc();

        // 终止当前运行的Java虚拟机，非零状态码表示异常终止
        System.exit(0);
    }

}
