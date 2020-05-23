package com.test.demo.system;

import java.io.Console;

/**
 * Console
 *
 * @author WangChao
 * @date 2020/05/19
 */
public class ConsoleTest {

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
