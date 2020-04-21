package com.test.demo.enumtest;

interface IMessage {

    public String getMessage();
}

enum Color implements IMessage {
    RED("红色"), GREEN, BLUE("蓝色"); //枚举对象要写在首行
    private String title;  //定义属性

    //Illegal modifier for the enum constructor; only private is permitted.
    //枚举构造函数的修饰符非法；只允许私有
    //public Color() {}
    private Color() {
    }

    Color(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return this.title;
    }

    @Override
    public String getMessage() {
        return this.title;
    }
}

/**
 * EnumTest
 *
 * @author WangChao
 * @date 2020/04/20
 */
public class EnumTest {

    public static void main(String[] args) {
        Color c = Color.RED;
        System.out.println(c);

        for (Color color : Color.values()) {
            System.out.println(color.ordinal() + " - " + color.name() + " - " + color.toString());
        }

        IMessage msg = Color.RED;
        System.out.println(msg.getMessage());
    }
	
	/*输出结果：
	红色
	0 - RED - 红色
	1 - GREEN - null
	2 - BLUE - 蓝色
	红色*/

}
