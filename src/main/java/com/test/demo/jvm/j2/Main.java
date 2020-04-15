package com.test.demo.jvm.j2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * JFrame
 *
 * @author WangChao
 * @date 2020/04/15
 */
public class Main extends JFrame {

    private JButton jb;

    public Main() {
        this.setBounds(200, 200, 400, 200);
        this.setTitle("lambda测试");

        jb = new JButton("click");
		/*jb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("clicked");
			}
		});*/
        jb.addActionListener(event -> System.out.println("clicked"));

        this.add(jb);

        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new Main();
    }

}
