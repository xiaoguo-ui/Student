package com.guoyun.student.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录过滤器
 */
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //强制转型
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //获取此次对话中的session数据
        Object user = request.getSession().getAttribute("user");
        //用户为空
        if (user == null) {
            //重定向到登录页
            response.sendRedirect("index.jsp");
            return;
        }else {
            //放行
            filterChain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
