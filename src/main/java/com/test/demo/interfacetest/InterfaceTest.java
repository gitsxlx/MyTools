package com.test.demo.interfacetest;

interface IMessage {
    //完整定义
    //public static final String INFO = "www.95081.com";
    //public abstract String getInfo();

    //简化定义
    String INFO = "www.95081.com";

    String getInfo();
}

interface IChannel {

    public abstract boolean connect();
}

interface IDataBase {

    public abstract boolean connection();

    //在jdk1.8开始，为了解决接口设计的缺陷，所以在接口之中允许开发者定义普通方法。但必须追加default声明
    public default boolean dbConnect() {
        System.out.println("db消息发送通道连接");
        return true;
    }

    //在jdk1.8开始,接口增加静态方法
    public static IDataBase getInstance() {
        return new MessageImpl();
    }
}

interface IService extends IMessage, IChannel {

    public boolean service();
}

abstract class DataBaseAbstract {

    //接口中可以省略abstract，抽象类中不允许省略
    abstract boolean getDataBaseConnect();
}

class MessageImpl extends DataBaseAbstract implements IService, IDataBase {

    @Override
    boolean getDataBaseConnect() {
        if (this.dbConnect()) {
            System.out.println("dbConnect");
        }
        return true;
    }

    @Override
    public String getInfo() {
        if (this.connect() && this.getDataBaseConnect()) {
            return "得到一个秘密消息";
        }
        return "通道创建失败，无法获取消息";
    }

    @Override
    public boolean connect() {
        System.out.println("消息发送通道已经建立");
        return true;
    }

    @Override
    public boolean service() {
        return false;
    }

    @Override
    public boolean connection() {
        return false;
    }
}

/**
 * InterfaceTest
 *
 * @author WangChao
 * @date 2020/04/10
 */
public class InterfaceTest {

    public static void main(String[] args) {
        IMessage msg = new MessageImpl();
        System.out.println(msg.getInfo());
        System.out.println(IMessage.INFO);

        IChannel chl = (IChannel) msg;  //父接口转换
        System.out.println(chl.connect());

        Object obj = msg; //向上转型，但IMessage和Object没关系
        IChannel chan = (IChannel) obj;
        System.out.println(chan.connect());

        IDataBase db = IDataBase.getInstance();
        db.dbConnect();
    }
}
