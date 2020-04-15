package com.test.demo.system;

import java.io.Console;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * 系统类
 * Scanner
 * Console
 * StringTokenizer
 *
 * @author WangChao
 * @date 2020/04/11
 */
public class SystemTest {

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

class ConsoleTest {

    public static void main(String[] args) {
        //用于从控制台设备读取字符信息，通常是文本和密码。尤其读取密码字符时是看不见的。
        //Java.io.Console 只能用在标准输入、输出流未被重定向的原始控制台中使用，在 Eclipse 或者其他 IDE 的控制台是用不了的。
        Console console = System.console();
        System.out.println("please input your name:");
        String name = console.readLine();
        System.out.println("please input password:");
        char[] chars = console.readPassword();
        String password = String.valueOf(chars);
        System.out.println("your name:" + name + " your password:" + password);
    }

}

class StringTokenizerTest {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("输入数据：");
        //默认情况下，StringTokenizer以空格，制表符，换行符和回车符作为分割依据。
        StringTokenizer stringTokenizer = new StringTokenizer(scanner.nextLine());
        System.out.println("分隔后：");
        while (stringTokenizer.hasMoreTokens()) {
            System.out.println(stringTokenizer.nextToken());
        }
    }
}
