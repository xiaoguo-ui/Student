package com.guoyun.student.servlet;

import com.guoyun.student.utils.CapUtil;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class CapServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求参数
        String method = req.getParameter("method");
        //确定二维码的请求的参数
        final String loginCap = "loginCap";
        if (loginCap.equals(method)) {
            //画出二维码
            drawCap(req, resp);
            return;
        }

    }

    /**
     * 画出二维码
     * @param req  请求参数
     * @param resp 响应参数
     */
    private void drawCap(HttpServletRequest req, HttpServletResponse resp) {
        //二维码对象
        CapUtil capUtil = new CapUtil();
        //生成二维码图片
        String generatorVCode = capUtil.generatorVCode();
        //将二维码添加到此次对话之中
        req.getSession().setAttribute("loginCap", generatorVCode);
        //获得旋转字体的验证码图片
        BufferedImage bufferedImage = capUtil.generatorRotateVCodeImage(generatorVCode, true);
        try {
            //将此二维码图片写出
            ImageIO.write(bufferedImage, "gif", resp.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
