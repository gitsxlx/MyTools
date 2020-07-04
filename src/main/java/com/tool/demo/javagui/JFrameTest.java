package com.tool.demo.javagui;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

/*Java世界中，目前最知名的三大GUI库分别是:
	1、AWT(Abstract Window Toolkit)：抽象窗口工具包，早期编写图形界面应用程序的包。
	2、Swing ：为解决 AWT 存在的问题而新开发的图形界面包。Swing是对AWT的改良和扩展。
	3、来自IBM Eclipse开源项目的SWT（Standard Widget Toolkit）标准窗口部件库，不包含于JDK中，需要从Eclipse单独下载。*/
public class JFrameTest {

    public void CreateJFrame(String title) {
        JFrame jf = new JFrame(title); // 实例化一个JFrame对象
        jf.setVisible(true); // 设置窗体可视
        jf.setSize(500, 350); // 设置窗体大小
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // 设置窗体关闭方式
    }

    public static void main(String[] args) {
        String title = "项目群全体";
        new JFrameTest().CreateJFrame(title); // 调用CreateJFrame()方法
    }

}
