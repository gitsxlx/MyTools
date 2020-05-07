package com.test.demo.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * Java中有四种引用类型：
 * 强引用：永远不会被回收，即使内存不足
 * 软引用：当内存不足，会触发JVM的GC，如果GC后，内存还是不足，就会把软引用的包裹的对象给干掉，也就是只有在内存不足，JVM才会回收该对象
 * 弱引用：不管内存是否足够，只要发生GC，都会被回收
 * 虚引用：无法通过虚引用来获取对一个对象的真实引用；
 *        虚引用必须与ReferenceQueue一起使用，当GC准备回收一个对象，如果发现它还有虚引用，就会在回收之前，把这个虚引用加入到与之关联的ReferenceQueue中
 *
 * 一个对象是否可以被回收，主要看是否有引用指向此对象，说的专业点，叫可达性分析
 *
 * @author WangChao
 * @date 2020/04/26
 */
public class ReferenceTest {

    public static void main(String[] args) {
        // 强引用
        Object o = new Object();
        System.out.println(o);

        // 软引用
        SoftReference<Student> studentSoftReference = new SoftReference<Student>(new Student());
        Student student = studentSoftReference.get();
        System.out.println(student);

        // 弱引用
        WeakReference<byte[]> weakReference = new WeakReference<byte[]>(new byte[1024 * 1024 * 10]);
        System.out.println(weakReference.get());

        // 虚引用
        ReferenceQueue queue = new ReferenceQueue();
        PhantomReference<byte[]> reference = new PhantomReference<byte[]>(new byte[1], queue);
        System.out.println(reference.get());
    }

}

class Student {

    private String name;
}
