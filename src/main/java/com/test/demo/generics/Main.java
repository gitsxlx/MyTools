package com.test.demo.generics;

/*1.泛型的定义：在程序中我们将一个对象放入集合中，但是集合不会记住对象的类型，当我们在次使用对象的时候，对象变为Object类型，而程序中还是原来的类型，我们必须要自己转换其类型，为了解决这个问题，则提出泛型。
2.泛型要求包容的是对象类型，而基本数据类型在Java中不属于对象。但是基本数据类型有其封装类，且为对象类型。
3.想放int类型，要放Integer类型不能直接放int（基本数据类型）。*/
public class Main {

    public static void main(String[] args) {
		
		/*泛型字母
		形式类型参数（formal type parameters）即泛型字母
		命名泛型字母可以随意指定，尽量使用单个的大写字母（有时候多个泛型类型时会加上数字，比如T1，T2）
		常见字母（见名知意）

		T Type
		K V Key Value
		E Element
		当类被使用时，会使用具体的实际类型参数（actual type argument）代替
		
		泛型类
		只能用在成员变量上，只能使用引用类型
		
		泛型接口
		只能用在抽象方法上
		
		泛型方法
		返回值前面加上<T>*/

    }

}
