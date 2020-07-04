package com.tool.demo.datastructures.d3;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * QueueTest
 *
 * @author WangChao
 * @date 2020/04/20
 */
public class QueueTest {

    public static void main(String[] args) {

        Queue<String> q = new LinkedBlockingQueue<>();

        boolean add = q.add("a");  //将指定的元素插入此队列
        System.out.println("add:" + add);

        boolean offer = q
                .offer("b");  //将指定的元素插入此队列（如果立即可行且不会违反容量限制），插入成功返回 true；否则返回 false。当使用有容量限制的队列时，offer方法通常要优于 add方法——add方法可能无法插入元素，而只是抛出一个  IllegalStateException异常
        System.out.println("offer:" + offer);

        String element = q.element();  //获取队列的头但不移除此队列的头。如果此队列为空，则将抛出NoSuchElementException异常
        System.out.println("element:" + element);

        String peek = q.peek();  //获取队列的头但不移除此队列的头。如果此队列为空，则返回 null
        System.out.println("peek:" + peek);

        String poll = q.poll();  //获取并移除此队列的头，如果此队列为空，则返回 null
        System.out.println("poll:" + poll);

        String remove = q.remove(); //获取并移除此队列的头，如果此队列为空，则抛出NoSuchElementException异常
        System.out.println("remove:" + remove);

        System.out.println("size:" + q.size());

    }

}
