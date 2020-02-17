<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <link rel="shortcut icon" href="favicon.ico"/>
    <link rel="bookmark" href="favicon.ico"/>
    <link href="h-ui/css/H-ui.min.css" rel="stylesheet" type="text/css"/>
    <link href="h-ui/css/H-ui.login.css" rel="stylesheet" type="text/css"/>
    <link href="h-ui/lib/icheck/icheck.css" rel="stylesheet" type="text/css"/>
    <link href="h-ui/lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet" type="text/css"/>

    <link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">

    <script type="text/javascript" src="easyui/jquery.min.js"></script>
    <script type="text/javascript" src="h-ui/js/H-ui.js"></script>
    <script type="text/javascript" src="h-ui/lib/icheck/jquery.icheck.min.js"></script>

    <script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>

    <script type="text/javascript">
        $(function () {

            //点击二维码切换图片
            $("#vCodeImg").click(function () {
                this.src = "CapServlet?method=loginCap&t=" + new Date().getTime();
            });
            //登录请求
            $("#submitBtn").click(function () {
                //将表单中的数据序列化为字符串
                const data = $("#form").serialize();
                //发送请求
                $.ajax({
                    type: "POST",
                    url: "LoginServlet?method=login",
                    //发送数据
                    data: data,
                    //返回的数据类型
                    dataType: "text",

                    /**
                     * 成功的回调函数
                     * @param msg 后端返回的选择信息
                     */
                    success:function (msg) {
                        //响应信息
                        const informationStr = {
                            //二维码错误
                            vCodeError:"vCodeError",
                            //用户名或密码错误
                            loginError: "loginError",
                            //成功的状态码
                            loginSuccess:"loginSuccess"

                        }
                        //验证码错误
                        if (informationStr.vCodeError === msg) {
                            $.messager.alert("消息提示", "验证码错误", "warning");
                            //切换验证码
                            $("#vCodeImg").click();

                            $("input[name='vcode']").val("");
                        }else if(informationStr.loginError === msg){
                            //用户名或密码错误
                            $.messager.alert("消息提示", "用户名或密码错误", "warning");
                            //清除输入框
                            $("#vCodeImg").click();
                            //清除输入框
                            $("input[name='vcode']").val("");
                        }else if(informationStr.loginSuccess === msg){
                            $.messager.alert("消息提示", "登录成功", "success");
                            //登录成功
                            //跳转路由页面
                            window.location.href = "SystemServlet?method=toAdminView";
                        }else {
                            alert(msg);
                        }
                    }
                });

            })
        })
    </script>
    <title>登录|学生信息管理系统</title>
    <meta name="keywords" content="学生信息管理系统">
</head>
<body>

<div class="header" style="padding: 0;">
    <h2 style="color: white; width: 400px; height: 60px; line-height: 60px; margin: 0 0 0 30px; padding: 0;">
        学生信息管理系统</h2>
</div>
<div class="loginWraper">
    <div id="loginform" class="loginBox">
        <form id="form" class="form form-horizontal" method="post">
            <%--账户--%>
            <div class="row cl">
                <label class="form-label col-3"><i class="Hui-iconfont">&#xe60d;</i></label>
                <div class="formControls col-8">
                    <input name="account" type="text" placeholder="账户" class="input-text size-L">
                </div>
            </div>
            <%--密码--%>
            <div class="row cl">
                <label class="form-label col-3"><i class="Hui-iconfont">&#xe60e;</i></label>
                <div class="formControls col-8">
                    <input name="password" type="password" placeholder="密码" class="input-text size-L">
                </div>
            </div>
            <%--验证码--%>
            <div class="row cl">
                <div class="formControls col-8 col-offset-3">
                    <input class="input-text size-L" name="vCode" type="text" placeholder="请输入验证码"
                           style="width: 200px;">
                    <img title="点击图片切换验证码" id="vCodeImg" src="CapServlet?method=loginCap"></div>
            </div>
            <%--单选框--%>
            <div class="mt-20 skin-minimal" style="text-align: center;">
                <div class="radio-box">
                    <input type="radio" id="radio-2" name="type" checked value="2"/>
                    <label for="radio-1">学生</label>
                </div>
                <div class="radio-box">
                    <input type="radio" id="radio-3" name="type" value="3"/>
                    <label for="radio-2">老师</label>
                </div>
                <div class="radio-box">
                    <input type="radio" id="radio-1" name="type" value="1"/>
                    <label for="radio-3">管理员</label>
                </div>
            </div>
            <%--登录按钮--%>
            <div class="row">
                <div class="formControls col-8 col-offset-3">
                    <input id="submitBtn" type="button" class="btn btn-success radius size-L"
                           value="&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;">
                </div>
            </div>
        </form>
    </div>
</div>
<div class="footer">Copyright &nbsp; artisan</div>


</body>
</html>