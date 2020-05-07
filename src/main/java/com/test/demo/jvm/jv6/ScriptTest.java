package com.test.demo.jvm.jv6;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * Demo
 *
 * @author WangChao
 * @date 2020/04/15
 */
public class ScriptTest {

    public static void main(String[] args) {

        ScriptEngineManager sem = new ScriptEngineManager();
        ScriptEngine se = sem.getEngineByName("JavaScript");

        try {
            Object obj = se.eval("function add(a,b) {return a + b;} add(2,3);");
            System.out.println(obj);
        } catch (ScriptException e) {
            e.printStackTrace();
        }

    }

}
