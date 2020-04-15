package com.test.demo.thread.example.thre5;

/**
 * Shop
 *
 * @author WangChao
 * @date 2020/04/15
 */
public interface Shop {

    //Illegal modifier for the interface method push;
    //only public, abstract, default, static and strictfp are permitted
    void push();

    public void take();

    public void size();

}
