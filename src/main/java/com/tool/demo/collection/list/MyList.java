package com.tool.demo.collection.list;

/**
 * MyList
 *
 * @author WangChao
 * @date 2020/04/20
 */
public interface MyList<E> {

    void add(E item);

    void remove(E item);

    Object get(int index);

    int size();

}
