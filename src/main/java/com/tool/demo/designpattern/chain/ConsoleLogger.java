package com.tool.demo.designpattern.chain;

/**
 * ConsoleLogger
 *
 * @author WangChao
 * @date 2020/04/21
 */
public class ConsoleLogger extends AbstractLogger {

    public ConsoleLogger(int level) {
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("Standard Console::Logger: " + message);
    }
}
