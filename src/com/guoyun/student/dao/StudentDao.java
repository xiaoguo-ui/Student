package com.guoyun.student.dao;

import com.guoyun.student.domain.Student;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentDao extends BaseDao{
    /**
     * 登录方法
     * @param name 用户名
     * @param password 密码
     * @return 用户
     */
    public Student login(String name, String password){
        String sql = "select * from s_student where name = '" + name + "' and password = '" + password + "'";
        /**
         * 封装的方法，查询
         */
        ResultSet resultSet = query(sql);
        try {
            if(resultSet.next()){
                Student student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setName(resultSet.getString("name"));
                student.setPassword(resultSet.getString("password"));
                student.setClazzId(resultSet.getInt("clazz_id"));
                student.setMobile(resultSet.getString("mobile"));
                student.setPhoto(resultSet.getBinaryStream("photo"));
                student.setQq(resultSet.getString("qq"));
                student.setSex(resultSet.getString("sex"));
                student.setSn(resultSet.getString("sn"));
                return student;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        return null;
    }

}
