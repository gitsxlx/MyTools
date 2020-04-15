package com.test.demo.javagui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

/**
 * JDialog
 *
 * @author WangChao
 * @date 2020/04/10
 */
public class JDialogTest extends JDialog {        // 继承JDialog类

    public JDialogTest() {
        super(new MyJFrame(), "这是一个JDialog窗体", true); // 实例化一个JDialog类对象，指定其父窗体、窗口标题和类型
        Container container = this.getContentPane();
        container.add(new JLabel("http://www.cnblogs.com/adamjwh/"));
        this.setSize(500, 350);
    }

    public static void main(String[] args) {
        new JDialogTest();
    }

}

// 下面这部分内容包含监听器，可自行查阅资料
class MyJFrame extends JFrame {

    public MyJFrame() {
        this.setVisible(true);
        this.setSize(700, 500);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Container container = this.getContentPane();
        container.setLayout(null);

        JButton jb = new JButton("点击弹出对话框");        // 创建按钮
        jb.setBounds(30, 30, 200, 50);        // 按钮位置及大小
        jb.addActionListener(new ActionListener() {        // 监听器，用于监听点击事件
            @Override
            public void actionPerformed(ActionEvent e) {
                new JDialogTest().setVisible(true);
            }
        });
        container.add(jb);
    }
}
