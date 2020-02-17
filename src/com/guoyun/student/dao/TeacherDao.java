package com.guoyun.student.dao;

import com.guoyun.student.domain.Teacher;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TeacherDao extends BaseDao {
    public Teacher login(String name, String password) {
        String sql = "select * from s_teacher where name = '" + name + "' and password = '" + password + "'";
        ResultSet resultSet = query(sql);
        try {
            if (resultSet.next()) {
                Teacher teacher = new Teacher();
                teacher.setId(resultSet.getInt("id"));
                teacher.setName(resultSet.getString("name"));
                teacher.setPassword(resultSet.getString("password"));
                teacher.setClazzId(resultSet.getInt("clazz_id"));
                teacher.setMobile(resultSet.getString("mobile"));
                teacher.setPhoto(resultSet.getBinaryStream("photo"));
                teacher.setQq(resultSet.getString("qq"));
                teacher.setSex(resultSet.getString("sex"));
                teacher.setSn(resultSet.getString("sn"));
                return teacher;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }

}
