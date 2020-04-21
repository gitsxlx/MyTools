package com.test.demo.designpattern.chain;

/**
 * FileLogger
 *
 * @author WangChao
 * @date 2020/04/21
 */
public class FileLogger extends AbstractLogger {

    public FileLogger(int level) {
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("File::Logger: " + message);
    }
}
