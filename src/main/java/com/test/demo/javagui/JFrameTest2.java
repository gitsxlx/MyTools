package com.test.demo.javagui;

import java.awt.Color;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

/**
 * JFrame
 *
 * @author WangChao
 * @date 2020/04/10
 */
public class JFrameTest2 extends JFrame {

    public void init() {
        this.setVisible(true);        // 可视化
        this.setSize(500, 350);        // 大小
        this.setTitle("博客园");        // 标题
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);        // 关闭方式

        JLabel jl = new JLabel("http://www.cnblogs.com/adamjwh/");        // 创建一个JLabel标签
        jl.setHorizontalAlignment(SwingConstants.CENTER);        // 使标签文字居中

        Container container = this.getContentPane();        // 获取一个容器
        container.add(jl);        // 将标签添加至容器
        container.setBackground(Color.YELLOW);        // 设置容器背景颜色
    }

    public static void main(String[] args) {
        new JFrameTest2().init();
    }

}
