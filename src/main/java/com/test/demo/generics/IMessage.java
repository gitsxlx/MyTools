package com.test.demo.generics;

public interface IMessage<T> {
    public String echo(T t);
}

class MessageImpl<S> implements IMessage<S> {
    @Override
    public String echo(S t) {
        return "echo" + t;
    }
}

