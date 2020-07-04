package com.tool.demo.designpattern.chain;

/**
 * ErrorLogger
 *
 * @author WangChao
 * @date 2020/04/21
 */
public class ErrorLogger extends AbstractLogger {

    public ErrorLogger(int level) {
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("Error Console::Logger: " + message);
    }
}
