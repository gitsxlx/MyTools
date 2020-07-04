package com.tool.demo.thread.example.thr1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * MyDataSource
 *
 * @author WangChao
 * @date 2020/04/15
 */
public class MyDataSource {

    //也可以使用阻塞队列等等
    private LinkedList<Connection> pool = new LinkedList<>(); //查询少，增删多

    private static final int INIT_CONNECTIONS = 10;

    private static final String URL = "jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false";

    private static final String USER = "root";

    private static final String PASSWORD = "root";

    private Lock lock = new ReentrantLock();

    private Condition c1 = lock.newCondition();

    static {
        try {
            //加载驱动
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public MyDataSource() {
        for (int i = 0; i < INIT_CONNECTIONS; i++) {
            try {
                //获得连接
                Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                pool.addLast(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public Connection getConnect() {
        Connection result = null;
        lock.lock();
        try {
            while (pool.size() <= 0) {
                try {
                    c1.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if (!pool.isEmpty()) {
                result = pool.removeFirst();
            }

            return result;
        } finally {
            lock.unlock();
        }
    }

    public void release(Connection conn) {
        if (conn != null) {
            lock.lock();
            try {
                pool.addLast(conn);
                c1.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

}
