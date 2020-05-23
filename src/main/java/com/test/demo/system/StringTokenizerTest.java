package com.test.demo.system;

import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * StringTokenizer
 *
 * @author WangChao
 * @date 2020/05/19
 */
public class StringTokenizerTest {

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
