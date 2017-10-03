<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/pintuer.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/admin.css">
    <script src="${pageContext.request.contextPath}/statics/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/statics/js/pintuer.js"></script>
    <script src="${pageContext.request.contextPath}/statics/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/statics/js/layer/layer.js"></script>

    <%-- 表单序列成json串 --%>
    <script src="//cdn.bootcss.com/jquery.serializeJSON/2.8.1/jquery.serializejson.min.js"></script>
    <script src="//cdn.bootcss.com/jquery.serializeJSON/2.8.1/jquery.serializejson.js"></script>

    <%-- jQuery扁平弹出对话框 --%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/js/flavr/animate.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/js/flavr/flavr.css"/>
    <script src="${pageContext.request.contextPath}/statics/js/flavr/flavr.min.js"></script>
    <script src="${pageContext.request.contextPath}/statics/js/flavr/common.js"></script>

    <%-- MD5加密 --%>
    <script src="${pageContext.request.contextPath}/statics/js/MD5.js"></script>
</head>
<body onkeydown="clickEnter()">
<div class="bg"></div>
<div class="container">
    <div class="line bouncein">
        <div class="xs6 xm4 xs3-move xm4-move">
            <div style="height:150px;"></div>
            <div class="media media-y margin-big-bottom">
            </div>
            <form id="login_form">
                <div class="panel loginbox">
                    <div class="text-center margin-big padding-big-top"><h1>后台管理中心</h1></div>
                    <div class="panel-body" style="padding:30px; padding-bottom:10px; padding-top:10px;">
                        <div class="form-group">
                            <div class="field field-icon-right">
                                <input type="text" class="input input-big" id="name" name="name" placeholder="登录账号"
                                       value="${cookie.userName.value}" data-validate="required:请填写账号"/>
                                <span class="icon icon-user margin-small"></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="field field-icon-right">
                                <input type="password" class="input input-big" id="password" name="password"
                                       placeholder="登录密码" data-validate="required:请填写密码"/>
                                <span class="icon icon-key margin-small"></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="field">
                                <input type="text" class="input input-big" name="code" id="code" placeholder="填写右侧的验证码"
                                       data-validate="required:请填写右侧的验证码"/>
                                <img id="img_code" src="http://localhost:8080/destiny/verificationCode/getCode" alt=""
                                     width="100" height="32" class="passcode" style="height:43px;cursor:pointer;"
                                     onclick="this.src=this.src+'?'">

                            </div>
                        </div>
                    </div>
                    <div style="padding:30px;">
                        <input id="login_btn" type="button" class="button button-block bg-main text-big input-big"
                                onclick="logIn()" value="登录">
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
<%--<script src="${pageContext.request.contextPath}/statics/js/login.js"></script>--%>
<script>
    $(function () {
        jQuery(function ($) {

        });
    });

    /***
     * 监听回车键 （添加到body onkeydown方法）
     */
    function clickEnter() {
        if (event.keyCode == 13) {
            $("#login_btn").click();
        }
    }

    /***
     * 登录
     */
    function logIn() {

        if (!checkText("name")) {
            //alert("用户名不能为空！");
            messageBorder("用户名不能为空！");
            return;
        }

        if (!checkText("password")) {
            //alert("密码不能为空！");
            messageBorder("密码不能为空！");
            return;
        }

        if (!checkText("code")) {
            //alert("密码不能为空！");
            messageBorder("请输入验证码！");
            return;
        }

        var input_name = $("#name").val();
        var input_password = $("#password").val();
        var input_code = $("#code").val();
        var MD5Password = hex_md5(input_password); //加密密码
        //提交表单
        //alert(JSON.stringify($("#"+form_id).serializeJSON())); //返回json字符串
        //alert($("#"+form_id).serializeJSON()); //返回json Object
        loadingBorder();
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/destiny/account_login/login",
            contentType: "application/json",//application/x-www-form-urlencoded
            //data: $("#" + form_id).serialize(), //key/value方式提交表单
            dataType: "json",
            data: JSON.stringify({'name': input_name, 'password': MD5Password, 'code': input_code}), //json串提交表单
            success: function (data) {
                if (data.success) {
                    layer.closeAll();
                    layer.msg(data.message);
                    setTimeout(function () {
                        window.location.href = "http://localhost:8080/destiny/wap/index?userName=" + data.value.userName;
                    }, 1000)
                    //toJsp();
                } else {

                    layer.msg(data.message);
                    $("#img_code").click()
                }
            }
        });
        //sub_form(input_name, input_password, input_code);
    }

    /**
     *检查是否为空
     */
    function checkText(text_id) {
        if ($("#" + text_id).val() === "" || $("#" + text_id).val() === null) {
            return false;
        }
        return true;
    }

    /**
     * 提交表单
     * @param form_id
     */
    function sub_form(name, pwd, code) {
        //alert(JSON.stringify($("#"+form_id).serializeJSON())); //返回json字符串
        //alert($("#"+form_id).serializeJSON()); //返回json Object
        loadingBorder();
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/destiny/account_login/login",
            contentType: "application/json",//application/x-www-form-urlencoded
            //data: $("#" + form_id).serialize(), //key/value方式提交表单
            data: JSON.stringify($("#" + form_id).serializeJSON()), //json串提交表单
            success: function (data) {
                if (data.success) {
                    layer.msg(data.message);
                    layer.closeAll();
                    window.location.href = "http://localhost:8080/destiny/wap/index?userName=" + data.value.name;
                    //toJsp();
                } else {

                    layer.msg(data.message);
                    $("#img_code").click()
                }
            }
        });
    }

    function loadingBorder() {
        layer.msg('加载中', {
            icon: 16
            , shade: 0.01
        });
    }


    function messageBorder(val) {
        layer.open({
            title: '提示'
            ,icon: 2
            ,shade: 0 // 取消遮罩
            ,anim: 6 //动画效果-晃动
            ,content: val
        });
    }
    /***
     * 通过url获取参数
     */
    // $("#msg_test_url").html("这是通过url传递得到的数据：" + $.getUrlParam("message"));
</script>
</html>