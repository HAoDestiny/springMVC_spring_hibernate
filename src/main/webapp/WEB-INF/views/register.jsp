
<%--
  Created by IntelliJ IDEA.
  User: Destiny_hao
  Date: 2017/5/30
  Time: 20:23
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.destiny.work.common.MD5" %>
<html>
<head>
    <title>Destiny</title>
    <script src="${pageContext.request.contextPath}/statics/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/statics/js/MD5.js"></script>
    <%-- 表单序列成json串 --%>
    <script src="//cdn.bootcss.com/jquery.serializeJSON/2.8.1/jquery.serializejson.min.js"></script>
    <script src="//cdn.bootcss.com/jquery.serializeJSON/2.8.1/jquery.serializejson.js"></script>
</head>
<body>
<H2>Register</H2>
    <%--<form id="reg_form" action="addUser" method="post">--%>
        <%--姓名：<input type="text" name="name"/><br/>--%>
        <%--密码：<input type="password" name="password" /><br/>--%>
        <%--邮箱：<input type="text" name="email" /><br/>--%>
        <%--<input type="submit" value="注册"/>--%>
    <%--</form>--%>
<form id="reg_form">
    姓名：<input type="text" name="userName" id="userName"/><br/>
    密码：<input type="password" name="password" id="password"/><br/>
    确认密码：<input type="password" name="rq_password" id="rq_password"/><br/>
    手机：<input type="text" name="phone" id="phone"/><br/>
    邮箱：<input type="text" name="email" id="email"/><br/>
    <input type="button" id="sub_btn2" value="注册"/>
</form>
<button id="text_btn">test</button>
</body>
<script>
    $("#sub_btn2").click(function () {
        //alert(hex_md5("aaaaaa"));
        //alert(JSON.stringify({'name':'asdg', 'password':hex_md5('aaaaaa'), 'email':'aasdgasdg', 'code':''}));
        var input_name = $("#userName").val();
        var input_password = $("#password").val();
        var input_rq_password = $("#rq_password").val();
        var input_email = $("#email").val();
        var input_phone = $("#phone").val();

        if(input_name === "") {
            alert("用户名不能为空");
            return;
        }

        if(input_password === "") {
            alert("密码不能为空！");
            return;
        }

        if(input_rq_password !== input_password) {
            alert("两次输入的密码不相同，请重新输入！");
            return;
        }

        if(!checkEmail($("#email").val())) {
            return;
        };

       $.ajax({
           type: "POST",
           url: "http://localhost:8080/destiny/user/register",
           contentType: "application/json",
           data: JSON.stringify({'name':input_name, 'password':hex_md5(input_rq_password), 'phone':input_phone, 'email':input_email, 'code':''}),
           success : function (data) {
               if(data.success) {
                   alert(data.msg);
                   window.location.href  = "http://localhost:8080/destiny/wap/login";
               } else {
                    alert(data.msg);
               }
           }
       })
    });

    //邮箱验证
    function checkEmail(test_email) {
        var email= test_email;
        var myreg = /^([\.a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/;
        if(email == "") {
            alert("邮箱地址不能为空！");
            return false;
        } else if(!myreg.test(email)) {
            alert("请输入正确的邮箱地址！");
            return false;
        }
        return true;
    }
</script>
</html>
