package com.guoyun.student.dao;

import com.guoyun.student.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseDao {
    //获取连接
    private DBUtil dbUtil = new DBUtil();

    //关闭连接
    public void closeCon(){
        dbUtil.closeConnection();
    }

    /**
     *  基础查询,多条查询
     * @param sql
     * @return
     */
    public ResultSet query(String sql) {
        try {
            //执行sql
            PreparedStatement preparedStatement = dbUtil.getConnection().prepareStatement(sql);
            //返回查询结果
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 改变数据库内容操作
     * @param sql
     * @return
     */
    public boolean update(String sql){
        try {
            return dbUtil.getConnection().prepareStatement(sql).executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 为了继承，获取connection
     * @return
     */
    public Connection getConnection(){
        return dbUtil.getConnection();
    }

}
