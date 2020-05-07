package com.test.demo.jvm.jv3;

import java.io.InputStream;

/**
 * ClassLoaderDemo
 *
 * @author WangChao
 * @date 2020/04/15
 */
public class ClassLoaderTest {

    public static void main(String[] args) throws Exception {

        ClassLoader mycl = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {

                String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";

                InputStream is = getClass().getResourceAsStream(fileName);

                if (is == null) {
                    return super.loadClass(name);
                }

                try {
                    byte[] buff = new byte[is.available()];
                    is.read(buff);

                    return defineClass(name, buff, 0, buff.length);
                } catch (Exception e) {
                    throw new ClassNotFoundException();
                }

            }
        };

        Object c = mycl.loadClass("jvm.jv3.ClassLoaderDemo").newInstance();

        System.out.println(c.getClass());

        System.out.println(c instanceof ClassLoaderTest);

    }

}
