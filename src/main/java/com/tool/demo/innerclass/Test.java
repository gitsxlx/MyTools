package com.tool.demo.innerclass;

interface IMessage {

    public void send(String str);
}

class Outer {

    private String msg = "www.95081.com";

    public void fun() {
        Inner in = new Inner();
        in.print();
    }

    public void fun1() {
        class Inner3 {

            public void print() {
                System.out.println(Outer.this.msg);
            }
        }
        new Inner3().print();
    }

    class Inner {

        public void print() {
            System.out.println(Outer.this.msg);
        }
    }

    private class Inner1 {

        public void print() {
            System.out.println(Outer.this.msg);
        }
    }

    static class Inner2 {

        public void print() {
            System.out.println("1");
        }
    }

}

/**
 * Test
 *
 * @author WangChao
 * @date 2020/04/10
 */
public class Test {

    public static void main(String[] args) {
        Outer outer = new Outer();
        outer.fun();

        Outer.Inner oi = new Outer().new Inner();
        oi.print();

        Outer.Inner2 in = new Outer.Inner2();
        in.print();

        new Outer().fun1();
		
		/*IMessage msg = new IMessage() {
			@Override
			public void send(String str) {
				System.out.println(str);
			}
		};*/
        IMessage msg = (str) -> {
            System.out.println(str);
        };
        msg.send("aa");
    }

}
