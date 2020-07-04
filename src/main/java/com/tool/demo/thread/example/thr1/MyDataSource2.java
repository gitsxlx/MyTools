package com.tool.demo.thread.example.thr1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * MyDataSource2
 *
 * @author WangChao
 * @date 2020/04/15
 */
public class MyDataSource2 {

    private LinkedList<Connection> pool = new LinkedList<>(); //查询少，增删多

    private static final int INIT_CONNECTIONS = 10;

    private static final String URL = "jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false";

    private static final String USER = "root";

    private static final String PASSWORD = "root";

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public MyDataSource2() {
        for (int i = 0; i < INIT_CONNECTIONS; i++) {
            try {
                Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                pool.addLast(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public Connection getConnect() {
        Connection result = null;
        synchronized (pool) {
            while (pool.size() <= 0) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if (!pool.isEmpty()) {
                result = pool.removeFirst();
            }
        }
        return result;
    }

    public void release(Connection conn) {
        if (conn != null) {
            synchronized (pool) {
                pool.addLast(conn);
                notifyAll();
            }
        }
    }

}
