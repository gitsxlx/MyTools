package com.test.demo.interview;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * java笔试题
 *
 * @author WangChao
 * @date 2020/04/10
 */
public class InterViewTest {

    //1、String,StringBuilder,StringBuffer
    /*String：内容不可变的字符串，底层使用了一个不可变的字符数组，private final char value[]，jdk1.9开始变为byte类型数据;
    StringBuilder：底层使用可变数组 char[] value; 可进行改变。线程不安全，效率较高。
    StringBuffer：底层使用可变数组 char[] value; 可进行改变。线程安全，效率较低。如append方法加了synchronized修饰

    最典型的就是拼接字符串
	①、循环体内，字符串的连接方式，使用 StringBuilder 的 append 方法进行扩展。而不要使用+。
    ②、如果不是在循环体中进行字符串拼接的话，直接使用+就好了。
    ③、如果在并发场景中进行字符串拼接的话，要使用StringBuffer来代替StringBuilder。*/
    public static void main1(String[] args) {
        String test = "";
        List<Employee> list = new ArrayList<Employee>(2);
        Employee employee = new Employee("张三", 1200.00);
        list.add(employee);
        employee = new Employee("李四", 1300.00);
        list.add(employee);
        for (Employee e : list) {
            test += "员工名称为：" + e.getName();
            test += "\n";
        }
        System.out.println(test);
        //员工名称为：张三
        //员工名称为：李四
    }

    public static void main2(String[] args) {
        List<Employee> list = new ArrayList<Employee>(2);
        Employee employee = new Employee("张三", 1200.00);
        list.add(employee);
        employee = new Employee("李四", 1300.00);
        list.add(employee);
        StringBuilder stringBuilder = new StringBuilder("");
        for (Employee e : list) {
            stringBuilder.append("员工名称为：");
            stringBuilder.append(e.getName());
            stringBuilder.append("\n");
        }
        System.out.println(stringBuilder.toString());
        //员工名称为：张三
        //员工名称为：李四
    }

    //2、字符串未做空验证，就做判断值相等
    private static final String REAL_NAME = "张三";

    public static boolean checkParam(String name) {
        return name.equals(REAL_NAME);  //错 java.lang.NullPointerException
        //return REAL_NAME.equals(name);  //对
    }

    public static void main3(String[] args) {
        System.out.println(checkParam(null));
    }

    //3、Integer
    //在值域为 [-128,127]之间，用==符号来比较Integer的值，是相等的。
    //为啥会有这样的结果呢？因为Integer内部特别处理了这之间的数，详情查看private static class IntegerCache。
    public static void main4(String[] args) {
        Integer one = 128;
        Integer two = 128;
        System.out.println(one == two);  //false

        Integer three = 100;
        Integer four = 100;
        System.out.println(three == four);  //true
    }

    //注意final
    static final Integer[] A = {1, 2};

    //4、值传递，不是引用传递
    public static void main5(String[] args) {
        Integer total = 0;
        paramCheck(total);
        System.out.println(total);  //0

        //对比
        final Integer[] b = A;
        b[1] = 3;
        System.out.println("A[0] = " + A[0]);  //A[0] = 1
        System.out.println("A[1] = " + A[1]);  //A[1] = 3
        System.out.println("b[0] = " + b[0]);  //b[0] = 1
        System.out.println("b[0] = " + b[1]);  //b[0] = 3
        System.out.println(A);  //[Ljava.lang.Integer;@5d5eef3d
        System.out.println(b);  //[Ljava.lang.Integer;@5d5eef3d
        System.out.println(A == b);  //true
    }

    private static void paramCheck(Integer total) {
        if (total < 1) {
            total += 1;
        }
    }

    //5、由数组转换的list，不能增删，只能查改，否则抛异常。
    // 此时是java.util.Arrays.ArrayList 这里面有java.util.Arrays里面的内部类，里面没有重写增删方法，就会调用父类的AbstractList，可以看到父类的增删方法，就会报错
    public static void main6(String[] args) {
        String[] array = new String[]{"张三", "李四", "王五"};
        List<String> list = Arrays.asList(array);
        for (String string : list) {
            System.out.println(string);
            //张三
            //李四
            //王五
        }
        try {
            list.add("孙六");  //java.lang.UnsupportedOperationException
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            list.remove(0);  //java.lang.UnsupportedOperationException
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(list.size());  //3
    }

    //6、移除
    //Iterator 是工作在一个独立的线程中，并且拥有一个 mutex 锁。 Iterator 被创建之后会建立一个指向原来对象的单链索引表，当原来的对象数量发生变化时，这个索引表的内容不会同步改变，所以当索引指针往后移动的时候就找不到要迭代的对象
    public static void main7(String[] args) {
        List<String> list = new ArrayList<String>();
        String removeName = "李明";
        list.add("张三");
        list.add("李四");
        list.add("王五");
        list.add("李明");
        for (String name : list) {
            if (removeName.equals(name)) {
                list.remove(name);  //java.util.ConcurrentModificationException
            }
        }

        //正确的
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String next = iterator.next();
            iterator.remove();
        }
    }

    //7、File
    public static void main8(String[] args) {
        //String path = "D:/home/nginx";
        String path1 = "D:/home/nginx/file";
        //new File(path1).mkdir();  //只会建立一级的文件夹
        new File(path1).mkdirs();  //可以建立多级文件夹
    }

    //8、double和float精度丢失问题及解决方法， BigDecimal
    public static void main9(String[] args) {
        Float totalMoney = 200000.8f;
        Float ownMoney = 170000.5f;
        Float leftMoney = totalMoney - ownMoney;
        System.out.println("还剩余：" + leftMoney);  //非精准输出，还剩余：30000.297
        BigDecimal bigDecimal1 = new BigDecimal(Float.toString(totalMoney));
        BigDecimal bigDecimal2 = new BigDecimal(Float.toString(ownMoney));
        System.out.println(bigDecimal1.add(bigDecimal2));  //精确的输出，370001.3
    }

    //9、i本身就是int类型的，它怎么可能比int的最大值还大，一直循环
    public static final int ENDNUMBER = Integer.MAX_VALUE;
    public static final int STARTNUMBER = ENDNUMBER - 2;

    public static void main10(String[] args) {
        int count = 0;
        System.out.println(count);  //0
        for (int i = STARTNUMBER; i <= ENDNUMBER; i++) {
            System.out.println(i);
            //2147483645
            //2147483646
            //2147483647
            //-2147483648
            //-2147483647
            //-2147483646
            count++;
        }
        System.out.println(count);  // 前边一直循环，走不到这
    }

    //10、返回false
    public static void main11(String[] args) {
        System.out.println(findResult());  //false
    }

    @SuppressWarnings("finally")
    public static boolean findResult() {
        try {
            return true;
        } catch (Exception e) {
            return true;
        } finally {
            return false;
        }
    }

    //11、字符串比较
    /*总结一下：
    字面量创建字符串会先在字符串池中找，看是否有相等的对象，没有的话就在堆中创建，把地址驻留在字符串池；有的话则直接用池中的引用，避免重复创建对象。
    new关键字创建时，前面的操作和字面量创建一样，只不过最后在运行时会创建一个新对象，变量所引用的都是这个新对象的地址。
    由于不同版本的JDK内存会有些变化，JDK1.6字符串常量池在永久代，1.7移到了堆中，1.8用元空间代替了永久代。但是基本对上面的结论没有影响，思想是一样的。*/
    public static void main12(String[] args) {
		/*当一个类被加载之后，类信息就存储在非堆的方法区中。在方法区中，有一块叫做运行时常量池（Runtime Constant Pool）,它是每个类私有的，每个class文件中的“常量池”被加载器加载之后就映射存放在这。
		和String最相关的是字符串池（String Pool），其位置在方法区上面的驻留字符串（Interned Strings）的位置，之前一直把它和运行时常量池搞混，其实是两个完全不同的存储区域，字符串常量池是全局共享的。字符串调用String.intern()方法后，其引用就存放在String Pool中。*/
        String a = "a";  //储存在 常量存储区中的字符串常量池中
        String b = "a";
        String c = new String("a");  //存储在堆中
        String d = new String("a");
        System.out.println(a == b);  //true
        System.out.println(a == c);  //false
        System.out.println(c == d);  //false
        System.out.println(a.equals(b));  //true
        System.out.println(a.equals(c));  //true
        System.out.println(a.equals(d));  //true
        System.out.println("--------------------------------");
        String a1 = a + b;
        String a2 = "aa";
        String a3 = "a" + "a";
        String a4 = a + "a";
        System.out.println(a1 == a2);  //false
        System.out.println(a2 == a3);  //true
        System.out.println(a2 == a4);  //false
    }

    //12、数据类型转换
    public static void main13(String[] args) {
        int x = 4;
        System.out.println("value is " + ((x > 4) ? 99.9 : 9));  //value is 9.0
        System.out.println((x > 4) ? 99.9 : 9);  //9.0
        if (x > 4) {
            System.out.println(99.9);
        } else {
            System.out.println(9);  //9
        }
    }

    //13、构造函数中try、catch、finally
    public static void main14(String[] args) {
        InterViewTest t = new InterViewTest();
        System.out.println("main");  //main
    }

    public InterViewTest() {
        try {
            if (true) {
                throw new ArithmeticException();
            }
        } catch (ArithmeticException e) {
            System.out.println("begin");  //begin
            return;
        } finally {
            System.out.println("in");  //in
        }
        System.out.println("end");
    }

    //14、语法
	/*NullTest nullTest = null;
	System.out.println(nullTest.getInt());
	class NullTest{
		public static int getInt() {
			return 1;
		}
	}*/

    //15、数组
    public static void main15(String[] args) {
        //new Demo();
        int[] a = {};
        System.out.println(a);  //[I@26f67b76
        System.out.println(a.length);  //0
        //a[0] = 1;  //java.lang.ArrayIndexOutOfBoundsException
        System.out.println(a);  //[I@26f67b76
        System.out.println(a.length);  //0

        int data[] = new int[]{10, 20, 30};  // 静态初始化
        int temp[] = data;  // 引用传递
        temp[0] = 90;
        for (int i : data) {
            System.out.println(i);
            //90
            //20
            //30
        }

        // 数据类型 数据名称 [][] = new 数据类型[行个数][列个数];
        int s[][] = new int[][]{
                {1, 2, 3, 4, 5}, {1, 2, 3}, {5, 6, 7, 8}
        };
        System.out.println(s[2][3]);  // 8

        //System.arraycopy(源数组, 源数组开始点, 目标数组, 目标数组开始点, 拷贝长度);
        int[] dataA = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] dataB = new int[]{11, 22, 33, 44, 55, 66, 77, 88, 99};
        System.arraycopy(dataA, 5, dataB, 3, 3);
        //arraycopy(dataA, 5, dataB, 3, 3);
        printArray(dataB);  // 11、22、33、6、7、8、77、88、99、

        int k[] = {3, 4, 5, 1, 0, 2};
        Arrays.sort(k);
        printArray(k);  // 0、1、2、3、4、5、

        //data = sort(k);
        //reverse1(data);
        //reverse2(data);

        System.out.println(sum());  // 0
        System.out.println(sum(null));  // 0
        System.out.println(sum(1));  // 1
        System.out.println(sum(1, 2, 3));  // 6
        System.out.println(sum(new int[]{1, 2, 3, 4, 5, 6}));  //21
    }

    //15.1、数组排序（升序）
    public static int[] sort(int[] data) {
        printArray(data);
        for (int x = 0; x < data.length; x++) {
            for (int y = 0; y < data.length - x - 1; y++) {
                if (data[y] > data[y + 1]) {
                    int temp = data[y];
                    data[y] = data[y + 1];
                    data[y + 1] = temp;
                }
            }
        }
        printArray(data);
        return data;
    }

    //15.2、数组打印
    public static void printArray(int[] data) {
        for (int i = 0; i < data.length; i++) {
            System.out.print(data[i] + "、");
        }
        System.out.println();
    }

    //15.3、数组转置1(创建新数组，循环次数多，会产生垃圾)
    public static void reverse1(int[] data) {
        printArray(data);
        int[] temp = new int[data.length];
        int foot = temp.length - 1;
        for (int x = 0; x < data.length; x++) {
            temp[foot--] = data[x];
        }
        data = temp;
        printArray(data);
    }

    //15.4、数组转置2(直接交换)
    public static void reverse2(int[] data) {
        printArray(data);
        int center = data.length / 2;  // 确定转换的次数
        int head = 0; // 操作脚标
        int tail = data.length - 1;
        for (int x = 0; x < center; x++) {
            int temp = data[head];
            data[head] = data[tail];
            data[tail] = temp;
            head++;
            tail--;
        }
        printArray(data);
    }

    //15.5、数组拷贝
    public static void arraycopy(int[] src, int sindex, int[] dsc, int dindex, int len) {
        for (int i = 0; i < len; i++) {
            dsc[dindex++] = src[sindex++];
        }
    }

    //15.6、求和
    public static int sum(int... data) {
        int sum = 0;
        if (data != null) {
            for (int i : data) {
                sum += i;
            }
        }
        return sum;
    }

    //16、深复制和浅复制
    //由于复制可能引起问题，因此在Object内，clone( )方法被说明为protected。这就意味着它必须或者被由实现Cloneable的类所定义的方法调用，或者必须被那些类显式重载以便它是公共的。
    //深复制：将一个对象复制后，不论是基本数据类型还有引用类型，都是重新创建的。简单来说，就是深复制进行了完全彻底的复制，而浅复制不彻底。
    //一个类只有实现了Serializable接口，它的对象才是可序列化的。
    //同时使用transient修饰，用来表示不可序列化的。

    //17、弹跳高度
    public static void main16(String[] args) {
        float height = 100F;
        float heightSum = 0F;
        for (int i = 0; i < 11; i++) {
            if (i == 0) {
                System.out.println("初始高度：" + height);  //初始高度：100.0
            } else {
                height = height / 2;
                System.out.println("第" + i + "次弹跳高度：" + height);
                /*第1次弹跳高度：50.0
                第2次弹跳高度：25.0
                第3次弹跳高度：12.5
                第4次弹跳高度：6.25
                第5次弹跳高度：3.125
                第6次弹跳高度：1.5625
                第7次弹跳高度：0.78125
                第8次弹跳高度：0.390625
                第9次弹跳高度：0.1953125
                第10次弹跳高度：0.09765625*/
                heightSum += height;
            }
        }
        System.out.println("第10次弹跳高度：" + height);  //第10次弹跳高度：0.09765625
        System.out.println("10次弹跳高度总计：" + heightSum);  //10次弹跳高度总计：99.90234
    }

    //18、右移
    public static void main17(String[] args) {
        int oldCapacity = 12;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        System.out.println(newCapacity);  //18
    }

    //19、时间格式转换
    public static void main18(String[] args) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = simpleDateFormat.format(new Date());
        System.out.println(date);  //2020-04-10 16:55:56
    }

    //20、map遍历
    public static void main19(String[] args) {
        Map<String, String> map = new HashMap<String, String>();
        for (int i = 0; i < 10; i++) {
            map.put("a" + i, "b" + i);
        }
        Iterator<Entry<String, String>> iterable = map.entrySet().iterator();
        while (iterable.hasNext()) {
            Entry<String, String> entrty = iterable.next();
            System.out.println(entrty.getKey() + ":" + entrty.getValue());
        }
    }

    //21、在JAVA中如何跳出当前的多重嵌套循环
    //21.1 go
    public static void main20(String[] args) {
        ok:
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.println("i=" + i + ",j=" + j);
                if (j == 5) {
                    break ok;
                }
            }
        }
    }

    //21.2 值判断
    public static void main21(String[] args) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.println("i=" + i + "; j=" + j);
                if (j == 3) {
                    i = 4;
                    break;
                }
            }
        }
    }

    //21.3 循环变量
    public static void main22(String[] args) {
        int[][] arr = {{1, 2, 3}, {4, 5, 6, 7}, {9}};
        boolean found = false;
        for (int i = 0; i < arr.length && !found; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.println("i=" + i + ",j=" + j);
                if (arr[i][j] == 5) {
                    found = true;
                    break;
                }
            }
        }
    }

    //22、整形与短整型
    public static void main23(String[] args) {
        short s1 = 1;
        //s1 = s1 + 1;  //语法错误，编辑不能通过，eclipse提示cannot convert from int to short

        short s2 = 1;
        s2 += 1;
    }


    //23、枚举
    public enum Color {
        RED, GREEN, BLANK, YELLOW
    }

    //24、switch语句能否作用在byte上，能否作用在long上，能否作用在String上
    //int,byte,short,char基本类型或Integer包装类型，String都可以，long不行
    public static void main24(String[] args) {
        byte b = 1;
        switch (b) {
            case 1:
                System.out.println(b);
                break;
            default:
                break;
        }

        String str = "a";
        switch (str) {
            case "a":
                System.out.println(str);
                break;
            default:
                break;
        }
        //Cannot switch on a value of type long. Only convertible int values, strings or enum variables are permitted
		/*long l = 78541258611L;
		switch (l) {
			case 78541258611L:
				System.out.println(l);
				break;
			default:
				break;
		}*/
        Color c = Color.RED;
        switch (c) {
            case RED:
                System.out.println(c);
                break;
            default:
                break;
        }

        System.out.println(2 << 3);
    }

    //25、map
    public static void main25(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("1", "a");
        System.out.println(map);  //{1=a}
        Map<String, String> map1 = new HashMap<>();
        map.putAll(map1);
        System.out.println(map);  //{1=a}
        System.out.println(map1);  //{}
    }

    //26、BigDecimal
    public static void main26(String[] args) {
        System.out.println(new BigDecimal(0.04 * 6000).setScale(3, BigDecimal.ROUND_HALF_UP)
                .doubleValue());  //240.0
        //MapUtils.getDoubleValue(order, shareSnapshot.getShareBase());
    }

    //27、RpcException
    public static void main27(String[] args) {
        Exception e = new Exception(
                "com.alibaba.dubbo.rpc.RpcException: Forbid consumer 192.168.10.11 access service com.emotte.dubbo.order.OrderInterfaceService from registry 192.168.10.11:2182 use dubbo version 2.5.3, Please check registry access list (whitelist/blacklist).");
        System.out.println(
                e.getMessage());  //com.alibaba.dubbo.rpc.RpcException: Forbid consumer 192.168.10.11 access service com.emotte.dubbo.order.OrderInterfaceService from registry 192.168.10.11:2182 use dubbo version 2.5.3, Please check registry access list (whitelist/blacklist).
    }

    //28、乘法口诀
//    1*1=1
//    2*1=2	2*2=4
//    3*1=3	3*2=6	3*3=9
//    4*1=4	4*2=8	4*3=12	4*4=16
//    5*1=5	5*2=10	5*3=15	5*4=20	5*5=25
//    6*1=6	6*2=12	6*3=18	6*4=24	6*5=30	6*6=36
//    7*1=7	7*2=14	7*3=21	7*4=28	7*5=35	7*6=42	7*7=49
//    8*1=8	8*2=16	8*3=24	8*4=32	8*5=40	8*6=48	8*7=56	8*8=64
//    9*1=9	9*2=18	9*3=27	9*4=36	9*5=45	9*6=54	9*7=63	9*8=72	9*9=81
    public static void main28(String[] args) {
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(i + "*" + j + "=" + (i * j) + "\t");
            }
            System.out.println();
        }
    }

    //29、三角形
//        *
//       * *
//      * * *
//     * * * *
//    * * * * *
    public static void main29(String[] args) {
        int line = 5; // 总行数
        for (int i = 0; i < line; i++) {
            for (int j = 0; j < line - i; j++) {
                System.out.print(" ");
            }
            for (int j = 0; j <= i; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }

    //30、阶乘
    public static void main30(String[] args) {
        System.out.println(sum(10));  //4037913.0
    }

    public static double sum(int num) {
        if (num == 1) {
            return 1;
        }
        return fan(num) + sum(num - 1);
    }

    public static double fan(int num) {
        if (num == 1) {
            return 1;
        }
        return num * fan(num - 1);
    }

    //31、大小写转换
    public static void main(String[] args) {
        System.out.println("520131419990909012x".toLowerCase());  //520131419990909012x
        System.out.println("520131419990909012x".toUpperCase());  //520131419990909012X
        System.out.println("520131419990909012X".toLowerCase());  //520131419990909012x
        System.out.println("520131419990909012X".toUpperCase());  //520131419990909012X
        System.out.println("5201314199909090123".toUpperCase());  //5201314199909090123
    }

}
