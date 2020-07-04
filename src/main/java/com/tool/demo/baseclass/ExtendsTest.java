package com.tool.demo.baseclass;

/**
 * 多态的要点：
 * 1.多态是方法的多态，不是属性的多态（多态与属性无关）
 * 2.多态存在要有3个必要条件：继承、方法重写、父类引用指向子类对象。
 * 3.父类引用指向子类对象后，用该父类引用调用子类重写的方法，此时多态就出现了。
 *
 * @author WangChao
 * @date 2020/04/19
 */
public class ExtendsTest {

    public static void main(String[] args) {
        //父类引用指向子类对象
        Person c = new Student("张三", 18, "学校");  // 在c里边只能看到Student里边Person属性
        c.eat();   // 【Student】在吃饭，子类重写覆盖了父类方法，满足多态

		//c.song();  //The method song() from the type Person is not visible，子类中的方法不是重写覆盖，是一个全新的方法

		//c.play();  //The method play() is undefined for the type Person，编译不通过

        System.out.println(c.age);  // 40，属性变量不存在重写覆盖

        Student s = new Student("张三", 18, "学校");
        System.out.println(s.age);  // 20，属性覆盖

        System.out.println(c instanceof Person);
        System.out.println(c instanceof Student);

        System.out.println(s);  // baseClass.Student@70dea4e
        System.out.println(s.toString());  // baseClass.Student@70dea4e

        Object obj = new int[]{1, 2, 3};
        if (obj instanceof int[]) {
            int[] data = (int[]) obj;
            for (int i : data) {
                System.out.println(i);
            }
        }
    }

}

class Person {

    private String name;
    protected int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = 40;
        System.out.println("【Person】实例化对象产生了");
    }

    void eat() {
        System.out.println("【Person】在吃饭");
    }

    //如果一个方法不能被继承，则不能重写它。 比较典型的就是父类的private方法。
    private void song() {
        System.out.println("【Person】在唱歌");
    }

    void sleep() {
        System.out.println("【Person】在睡觉");
    }

    //不能重写被标识为final的方法。
    final void run() {
        System.out.println("【Person】在跑步");
    }

    //重写方法不能抛出新的异常或者比被重写方法声明的检查异常更广的检查异常。但是可以抛出更少，更有限或者不抛出异常。
}

class Student extends Person {

    protected int age;
    private String name;
    private String school;

    public Student(String name, int age, String school) {
        super(name, age);  // 子类构造调用父类构造，只允许放在子类构造方法的首行。
        //默认情况下子类只会调用父类无参构造方法，写与不写此语句效果一样。如果父类没有提供无参构造，必须使用super()明确调用有参构造
        this.school = school;
        this.age = 20;
        System.out.println("【Student】实例化对象产生了");
    }

    //方法名必须相同
    //参数列表必须与被重写方法的相同。
    //返回类型必须与被重写方法的返回类型相同。
	@Override
	void eat() {
        System.out.println("【Student】在吃饭");
    }

    //不算覆写
    public void song() {
        System.out.println("【Student】在唱歌");
    }

    //Cannot reduce the visibility of the inherited method from Person
    //方法覆写不能降低从Person继承的方法的可见性，编译报错，即子类访问权限不能小于父类
    //重写方法不能比被重写方法限制有更严格的访问级别。
	/*private void sleep() {
		System.out.println("【Student】在睡觉");
	}*/

    void play() {
        System.out.println("【Student】在玩");
    }

    //Cannot override the final method from Person
    //不能重写被标识为final的方法。
	/*void run() {
		System.out.println("【Person】在跑步");
	}*/
}
