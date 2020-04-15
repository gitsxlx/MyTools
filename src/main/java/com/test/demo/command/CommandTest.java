package com.test.demo.command;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.imageio.ImageIO;

/**
 * cmd是command的缩写.即命令行
 * Java控制电脑关机，重启，截屏
 *
 * @author WangChao
 * @date 2020/04/10
 */
public class CommandTest {

    /**
     * DOS 下操作命令
     * shutdown -s -t 1     指定1秒后关机
     * shutdown -r          重启
     * shutdown -a          停止关机
     * shutdown -r -f -t 10 指定10秒后重启，并且强制结束其他程序
     * explorer http://blog.csdn.net/Giving_bestself 打开网站
     * taskmgr  任务管理器
     * notepad--------打开记事本
     * calc-----------启动计算器
     * winmsd---------系统信息
     * winver---------检查Windows版本
     * write----------写字板
     * mspaint--------画图板
     * mstsc----------远程桌面连接
     * osk------------打开屏幕键盘
     * ntbackup-------系统备份和还原
     * 也可以放入exe文件地址，直接打开或执行
     */

    public static void main(String[] args) {
        //ShutDownPC pc = new ShutDownPC();
        //pc.shutdown();

        String path = "E:\\pc";
        System.out.println(screenShot(path));
    }

    /**
     * 3600s后强制关机
     */
    public void shutdown() {
        try {
            Runtime.getRuntime().exec("shutdown -s -f -t 3600");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 60秒后关机
     */
    public void shutdown1() {
        try {
            Runtime.getRuntime().exec("shutdown /s /t  60");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据输入的时间秒数关机
     * @param s 秒
     */
    public void shutdown1(String s) {
        try {
            // 关机
            Runtime.getRuntime().exec("shutdown /s /t " + s);  //关机时间可以自动设置
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 重启
     */
    public void restart() {
        try {
            Runtime.getRuntime().exec("shutdown -r ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 截屏
     * @param fileName 图片存放的位置
     * @return 生成图片生成的路径，图片格式是png，也可以根据自己的需要进行修改
     */
    public static String screenShot(String fileName) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle screenRectangle = new Rectangle(screenSize);
        String randomName = UUID.randomUUID().toString().replace("-", "");
        String name = randomName + ".png";
        // 截图保存的路径
        File screenFile = new File(fileName);
        Robot robot;
        String path = "";
        boolean b = false;
        try {
            robot = new Robot();
            BufferedImage image = robot.createScreenCapture(screenRectangle);
            File f = new File(screenFile, name);
            b = ImageIO.write(image, "png", f);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (b) {
            path = fileName + File.separator + name;
        }
        return path;
    }

}
