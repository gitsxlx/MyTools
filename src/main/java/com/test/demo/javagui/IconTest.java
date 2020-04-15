package com.test.demo.javagui;

import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics;

import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

/**
 * Icon
 *
 * @author WangChao
 * @date 2020/04/10
 */
public class IconTest extends JFrame implements Icon {

    private int width;        // 声明图标的宽
    private int height;        // 声明图标的长

    public IconTest() {
    }        // 定义无参构造方法

    public IconTest(int width, int height) {        // 定义有参构造方法
        this.width = width;
        this.height = height;
    }

    @Override
    public int getIconHeight() {        // 实现getIconHeight()方法
        return this.height;
    }

    @Override
    public int getIconWidth() {            // 实现getIconWidth()方法
        return this.width;
    }

    @Override
    public void paintIcon(Component arg0, Graphics arg1, int arg2,
            int arg3) {        // 实现paintIcon()方法
        arg1.fillOval(arg2, arg3, width, height);        // 绘制一个圆形
    }

    public void init() {    // 定义一个方法用于实现界面
        IconTest iconTest = new IconTest(15, 15);        // 定义图标的长和宽
        JLabel jb = new JLabel("icon测试", iconTest, SwingConstants.CENTER);    // 设置标签上的文字在标签正中间

        Container container = getContentPane();
        container.add(jb);

        this.setVisible(true);
        this.setSize(500, 350);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new IconTest().init();
    }

}
