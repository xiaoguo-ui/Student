package com.guoyun.student.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    //数据库的基本配置信息
    private String dbUrl = "jdbc:mysql://localhost:3306/db_student";
    private String dbUser = "root";
    private String dbPassword = "root";
    private String jdbcName = "com.mysql.jdbc.Driver";
    //连接对象
    private Connection connection = null;

    /**
     * 获取连接对象
     *
     * @return 返回connection
     */
    public Connection getConnection() {
        try {
            //加载驱动
            Class.forName(jdbcName);
            //获取连接
            connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            System.out.println("数据库连接成功");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("数据库连接失败");
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * 关闭数据库连接
     */
    public void closeConnection() {
        if (connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("关闭失败");
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        //实例化连接
        DBUtil dbUtil = new DBUtil();
        dbUtil.getConnection();
    }

}
