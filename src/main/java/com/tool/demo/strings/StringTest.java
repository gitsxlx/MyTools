package com.tool.demo.strings;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;

import javax.swing.plaf.synth.SynthSpinnerUI;

/**
 * String类相关
 *
 * @author WangChao
 * @date 2020/04/11
 */
public class StringTest {

    public static void main0(String[] args) {

        //String a = new String("abcd");
        //System.out.println(equals(a, "abcd"));

        long st = System.currentTimeMillis();

        //2689ms
		/*String str = "";
		for (int i = 0; i < 100000; i++) {
			str += "a";
		}*/

        //7ms
        String str = "";
        for (int i = 0; i < 100000; i++) {
            str.concat("a");
        }

        //4ms
		/*StringBuilder sbd = new StringBuilder();
		for (int i = 0; i < 100000; i++) {
			sbd.append("a");
		}*/

        //6ms
		/*StringBuffer sbf = new StringBuffer();
		for (int i = 0; i < 100000; i++) {
			sbf.append("a");
		}*/

        long et = System.currentTimeMillis();
        System.out.println(et - st);

    }

    /**
     * String类equals方法源码，之前版本
     * @param str
     * @param anObject
     * @return
     */
    public static boolean equals(String str, Object anObject) {
        if (str == anObject) {
            return true;
        }
        if (anObject instanceof String) {
            String anotherString = (String) anObject;
            int n = str.length();
            if (n == anotherString.length()) {
                char v1[] = str.toCharArray();
                char v2[] = anotherString.toCharArray();
                int i = 0;
                while (n-- != 0) {
                    if (v1[i] != v2[i]) {
                        return false;
                    }
                    i++;
                }
                return true;
            }
        }
        return false;
    }

    /**
     * 反转
     * @param originStr
     * @return
     */
    public static String reverse(String originStr) {
        if (originStr == null || originStr.length() <= 1) {
            return originStr;
        }
        return reverse(originStr.substring(1)) + originStr.charAt(0);
    }

    public static void main1(String[] args) {
        String A = "a";
        A.replace("a", "b");
        System.out.println(A);

        String B = new String("a");
        System.out.println(A == B);

        System.out.println(A.intern());
    }

    public static void main2(String[] args) {
        String str = "www.95081.com";
        char c = str.charAt(5);
        System.out.println(c);
        char[] charArray = str.toCharArray();
        for (char cha : charArray) {
            System.out.print(cha + "、");
        }
        System.out.println();

        String newStr = new String(charArray);
        System.out.println(newStr);
        System.out.println(new String(charArray, 0, 5));

        //判断每一个字符是否在数字范围之内（'0'~'9'）

        newStr.getBytes();
        //newStr.getBytes(charsetName);
        //newStr.equalsIgnoreCase(anotherString);
        newStr.compareTo(str);
        //newStr.compareToIgnoreCase(str);

        newStr.contains("abc");

        newStr.indexOf(str);
        newStr.indexOf(str, 0);
        newStr.lastIndexOf(c);

        newStr.startsWith(str);
        newStr.endsWith(str);

        newStr.replace(str, newStr);
        newStr.replaceAll(str, newStr);
        newStr.replaceFirst(str, newStr);

        newStr.split(";");
        newStr.split("\\.", 2);  //正则转义

        newStr.substring(4);
        newStr.substring(0, 2);

        String.format("姓名：%s、年龄：%d、成绩：%5.2f", "zhangsan", 18, 98.7653);

        newStr.concat(str);     //新建拼接

        newStr.intern();  //方放入字符串池

        newStr.isEmpty(); //有实例化对象时调用

        newStr.length();

        newStr.trim();

        newStr.toUpperCase();
        newStr.toLowerCase();

    }

    public static void main3(String[] args) {
        String str = "Hello ";
        change(str);
        System.out.println(str);  //Hello

        StringBuffer sb = new StringBuffer("Hello ");
        change(sb);
        System.out.println(sb);  //Hello World !

        sb.delete(6, 12).insert(6, "95081");
        System.out.println(sb);  //Hello 95081!

        sb.reverse();
        System.out.println(sb);  //!18059 olleH

        StringBuilder sbu = new StringBuilder("Hello ");  //非线程安全
    }

    private static void change(String temp) {
        temp += "World !";
    }

    private static void change(StringBuffer sb) {
        sb.append("World !");
    }


    public static void main4(String[] args) {
        Runtime rt = Runtime.getRuntime();
        System.out.println(rt.availableProcessors());  //本机cpu内核数
        System.out.println(rt.maxMemory());  //最大可用内存空间
        System.out.println(rt.totalMemory());  //获取可用内存空间
        System.out.println(rt.freeMemory());  //获取空闲内存空间
        rt.gc();  //手动进行gc处理
    }

    public static void main5(String[] args) {
        //public static native long currentTimeMillis();
        System.currentTimeMillis();
		
		/*public static void gc() {
	        Runtime.getRuntime().gc();
	    }*/
        System.gc();
    }

    public static void main6(String[] args) {
        System.out.println(Math.abs(-10.1));  //绝对值 10.1
        System.out.println(Math.max(10.2, 20.3));  //获取最大值 20.3
        System.out.println(Math.log(5));  //获取对数 1.6094379124341003
        System.out.println(Math.round(15.4));  //四舍五入，将小数点后的全都进位处理了 15
        System.out.println(Math.round(15.5));  //四舍五入 16
        System.out.println(Math.round(-15.5));  //四舍五入 -15
        System.out.println(Math.round(-15.51));  //四舍五入 -16
        System.out.println(Math.pow(10.2, 20.2));  //指数  2.364413713591828E20
        System.out.println(Math.floor(10.1));  //获取地板 10.0
        System.out.println(Math.ceil(10.1));  //获取天花板 11.0
        //自定义四舍五入
        System.out.println(round(19.86273, 2));
    }

    /**
     * 实现数据的四舍五入操作
     * @parameter num 要进行四舍五入操作的数字
     * @parameter scale 四舍五入保留的小数位数
     * @return 四舍五入处理后的结果
     * @date 2019年9月15日
     */
    public static double round(double num, int scale) {
        return Math.round(num * Math.pow(10, scale)) / Math.pow(10, scale);
    }

    public static void main7(String[] args) {
        Random rand = new Random();
        System.out.println(rand.nextInt(10));  //随机产生一个不大于边界的正整数

        BigInteger bi = new BigInteger("123123123");
        BigDecimal bd = new BigDecimal("1231231231313");

        Date date = new Date();
        System.out.println(date);
        System.out.println(date.getTime());

        LocalDateTime currentTime = LocalDateTime.now();
        System.out.println(currentTime);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String str = sdf.format(date);
        System.out.println(str);
        try {
            System.out.println(sdf.parse("2016-12-12 12:12:12.123"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Locale loc = new Locale("zh", "CN");  //中文环境
        System.out.println(loc);  //zh_CN
        System.out.println(Locale.getDefault());  //zh_CN
        System.out.println(Locale.CHINA);  //zh_CN

        //读取资源文件，ResourceBundle是一个抽象类
        //资源读取顺序：指定区域的资源文件 > 默认的本地资源 >  公共资源(没有区域设置)
        ResourceBundle resource = ResourceBundle.getBundle("Messages");  //没有文件后缀
        try {
            String val = new String(resource.getString("info").getBytes("ISO-8859-1"), "UTF-8");
            System.out.println(val);
            //格式化，占位符
            System.out.println(MessageFormat
                    .format(val, "aa", new SimpleDateFormat("yyyy-MM-dd").format(new Date())));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Locale locale = Locale.US;
        ResourceBundle rb = ResourceBundle.getBundle("Messages", locale);
        String info = rb.getString("info");
        System.out.println(info);
        System.out.println(MessageFormat
                .format(info, "aa", new SimpleDateFormat("yyyy-MM-dd").format(new Date())));
    }

    public static void main(String[] args) {
        String str = "123";
        if (str.matches("\\d+")) {
            int num = Integer.parseInt(str);
            System.out.println(num);
        }

        String s = "b";
        String regex = "a";
        System.out.println(str.matches(regex));

        //Arrays.binarySearch(a, key);  //二分查找
        //Collections.binarySearch(list, key);
    }

}
