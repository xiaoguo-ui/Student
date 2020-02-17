package com.guoyun.student.servlet;

import com.guoyun.student.dao.AdminDao;
import com.guoyun.student.dao.StudentDao;
import com.guoyun.student.dao.TeacherDao;
import com.guoyun.student.domain.Admin;
import com.guoyun.student.domain.Student;
import com.guoyun.student.domain.Teacher;
import com.guoyun.student.utils.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取用户信息
        String name = req.getParameter("account");
        //获取用户密码
        String password = req.getParameter("password");
        //获取二维码
        String vCode = req.getParameter("vCode");
        //获取单选框的值
        int type = Integer.parseInt(req.getParameter("type"));
        //获取此次对话中的loginCap
        String loginCap = req.getSession().getAttribute("loginCap").toString();
        //验证二维码字段是否为空
        if (StringUtil.isEmpty(vCode)) {
            //向前端响应，字段为空
            resp.getWriter().write("vCodeError");
            return;
        }
        //输入错误
        if(!vCode.toUpperCase().equals(loginCap.toUpperCase())){
            resp.getWriter().write("vCodeError");
            return;
        }
        //登录状态
        String loginStatus = "loginFail";
        switch (type){
            case 1:{
                AdminDao adminDao = new AdminDao();
                Admin admin = adminDao.login(name, password);
                adminDao.closeCon();

                if (admin == null) {
                    resp.getWriter().write("loginError");
                    return;
                }
                HttpSession session = req.getSession();
                session.setAttribute("user", admin);
                session.setAttribute("userType", type);
                loginStatus = "loginSuccess";
                break;

            }
            case 2:{
                StudentDao studentDao = new StudentDao();
                //查询到的信息
                Student student = studentDao.login(name, password);
                //关闭连接
                studentDao.closeCon();
                //查询失败
                if(student == null){
                    resp.getWriter().write("loginError");
                    return;
                }
                //获取Session对象
                HttpSession session = req.getSession();
                //将这一个放在session中，可以对某些情况进行限制
                session.setAttribute("user", student);
                session.setAttribute("userType", type);
                //成功
                loginStatus = "loginSuccess";
                break;

            }
            case 3:{
                TeacherDao TeacherDao = new TeacherDao();
                //查询到的信息
                Teacher teacher = TeacherDao.login(name, password);
                //关闭连接
                TeacherDao.closeCon();
                //查询失败
                if(teacher == null){
                    resp.getWriter().write("loginError");
                    return;
                }
                //获取Session对象
                HttpSession session = req.getSession();
                //将这一个放在session中，可以对某些情况进行限制
                session.setAttribute("user", teacher);
                session.setAttribute("userType", type);
                //成功
                loginStatus = "loginSuccess";
                break;
            }
            default:{
                break;
            }
        }
        resp.getWriter().write(loginStatus);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
