package com.test.demo.designpattern.singletion;

/**
 * 单例设计模式
 *
 * 经验之谈：一般情况下，不建议使用第 1 种和第 2 种懒汉方式，建议使用第 3 种饿汉方式。
 * 只有在要明确实现 lazy loading 效果时，才会使用第 5 种登记方式。
 * 如果涉及到反序列化创建对象时，可以尝试使用第 6 种枚举方式。
 * 如果有其他特殊的需求，可以考虑使用第 4 种双检锁方式。
 *
 * @author WangChao
 * @date 2018/07/30
 */
public class Singleton {

    //1、懒汉式，线程不安全
    /*private static Singleton instance;
    private Singleton (){}
    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }*/

    //2、懒汉式，线程安全
	/*private static Singleton singleton;
	private Singleton() {
	}
	public static synchronized Singleton getSingleton() {
		if(singleton == null) {
			singleton = new Singleton();
		}
		return singleton;
	}*/

    //3、饿汉式（线程安全）
	/*private static Singleton singleton = new Singleton();
	private Singleton() {
	}
	public static Singleton getSingleton() {
		return singleton;
	}*/

    //4、双检锁/双重校验锁（DCL，即 double-checked locking）
    private volatile static Singleton singleton;

    private Singleton() {
    }

    public static Singleton getSingleton() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }

    //5、登记式/静态内部类（线程安全）
	/*private static class SingletonHolder{
		private static final Singleton SINGLETON = new Singleton();
	}
	private Singleton() {
	}
	public static final Singleton getSingleton() {
		return SingletonHolder.SINGLETON;
	}*/

    //6、枚举
    /*public enum Singleton {
        INSTANCE;
        public void whateverMethod() {
        }
    }*/

    public static void main(String[] args) {
        System.out.println(getSingleton());
    }

}
