package com.test.demo.system;

import java.util.Scanner;

/**
 * Scanner
 *
 * @author WangChao
 * @date 2020/05/19
 */
public class ScannerTest {

    public static void main(String[] args) {
        // 从键盘接收数据
        Scanner scan = new Scanner(System.in);

        // next方式接收字符串
        System.out.println("next方式接收：");
        // 判断是否还有输入
        if (scan.hasNext()) {
            String str1 = scan.next();
            //scan.nextLine();
            System.out.println("输入的数据为：" + str1);
        }
        scan.close();
    }
}
