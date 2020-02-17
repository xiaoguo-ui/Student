package com.guoyun.student.dao;

import com.guoyun.student.domain.Admin;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDao extends BaseDao {
    /**
     *  管理员登录
     * @param name 管理员姓名
     * @param password 密码
     * @return admin
     */
    public Admin login(String name, String password) {
        //定义查询的Sql语句
        String sql = "select * from s_admin where name = '" + name + "' and password = '" + password + "'";
        //结果集
        ResultSet resultSet = query(sql);

        try {
            if (resultSet.next()) {
                Admin admin = new Admin();
                admin.setId(resultSet.getInt("id"));
                admin.setName(resultSet.getString("name"));
                admin.setPassword(resultSet.getString("password"));
                admin.setStatus(resultSet.getInt("status"));
                return admin;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
