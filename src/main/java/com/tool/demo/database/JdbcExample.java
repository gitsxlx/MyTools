package com.tool.demo.database;

//STEP 1. Import required packages

import java.sql.*;

/**
 * jdbc
 *
 * @author WangChao
 * @date 2020/04/12
 */
public class JdbcExample {

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://127.0.0.1/test";

    // Database credentials -- 数据库名和密码自己修改
    static final String USER = "root";
    static final String PASS = "root";

    public static void main1(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try {
            // STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            // STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            //conn.prepareStatement(sql);
            String sql;
            sql = "SELECT id, age, name, sex FROM employee";
            //表示数据库结果集的数据表，通常通过执行查询数据库的语句生成。
            //ResultSet 对象具有指向其当前数据行的光标。最初，光标被置于第一行之前。next 方法将光标移动到下一行；因为该方法在 ResultSet 对象没有下一行时返回 false，所以可以在 while 循环中使用它来迭代结果集。
            ResultSet rs = stmt.executeQuery(sql);
            //ps.executeUpdate();  //增删改时使用

            // STEP 5: Extract data from result set
            while (rs.next()) {
                // Retrieve by column name
                int id = rs.getInt("id");
                int age = rs.getInt("age");
                String first = rs.getString("name");
                String last = rs.getString("sex");

                // Display values
                System.out.print("ID: " + id);
                System.out.print(", Age: " + age);
                System.out.print(", Name: " + first);
                System.out.println(", Sex: " + last);
            }
            // STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
            } // nothing we can do
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            } // end finally try
        } // end try
        System.out.println("Goodbye!");
    }// end main
}// end FirstExample

class DataBase {

    public static void main(String[] args) {

        Connection connection = null; //连接
        PreparedStatement ps = null; //预编译的statement
        ResultSet rs = null; //结果集

        try {
            //1、注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            //2、通过驱动管理类获取数据库的连接
            connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/mybatis?characterEncoding=utf-8",
                            "root", "root");
            //3、建立sql语句，向数据库发起要求;获取预处理的statement
            String sql = "select * from user where username = ? ";
            ps = connection.prepareStatement(sql);
            ps.setString(1, "helloworld");
            //4、获取结果集
            rs = ps.executeQuery();
            //5、处理结果集
            while (rs.next()) {
                System.out.println(rs.getString("id") + " " + rs.getString("username"));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //6、释放资源
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
