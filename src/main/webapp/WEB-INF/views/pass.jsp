<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="renderer" content="webkit">
<title></title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/pintuer.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/admin.css">
  <script src="${pageContext.request.contextPath}/statics/js/jquery.js"></script>
  <script src="${pageContext.request.contextPath}/statics/js/pintuer.js"></script>
  <script src="${pageContext.request.contextPath}/statics/js/jquery.min.js"></script>
  <script src="${pageContext.request.contextPath}/statics/js/layer/layer.js"></script>
  <script src="${pageContext.request.contextPath}/statics/js/MD5.js"></script> <%-- MD5加密 --%>
</head>
<body>
<div class="panel admin-panel">
  <div class="panel-head"><strong><span class="icon-key"></span> 修改会员密码</strong></div>
  <div class="body-content">
    <form method="post" class="form-x" action="">
      <div class="form-group">
        <div class="label">
          <%--@declare id="sitename"--%><label for="sitename">管理员帐号： </label>
        </div>
        <div class="field">
          <label id="userName" style="line-height:33px; margin-left: 50px;">${message}</label>
        </div>
      </div>      
      <div class="form-group">
        <div class="label">
          <label for="sitename">原始密码：</label>
        </div>
        <div class="field">
          <input type="password" class="input w50" id="mpass" name="mpass" size="50" onblur="checkPass()" placeholder="请输入原始密码" data-validate="required:请输入原始密码" />
        </div>
      </div>      
      <div class="form-group">
        <div class="label">
          <label for="sitename">新密码：</label>
        </div>
        <div class="field">
          <input id="newpass" type="password" class="input w50" name="newpass" size="50" placeholder="请输入新密码" data-validate="required:请输入新密码,length#>=5:新密码不能小于5位" />
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label for="sitename">确认新密码：</label>
        </div>
        <div class="field">
          <input id="renewpass" type="password" class="input w50" name="renewpass" size="50" placeholder="请再次输入新密码" data-validate="required:请再次输入新密码,repeat#newpass:两次输入的密码不一致" />
        </div>
      </div>
      
      <div class="form-group">
        <div class="label">
          <label></label>
        </div>
        <div class="field">
          <button id="sub_renewpass" class="button bg-main icon-check-square-o" type="button"> 提交</button>
        </div>
      </div>      
    </form>
  </div>
</div>
</body>
<script>

    function checkPass() {
        var mpass = $("#mpass").val();
        var MD5Pass = hex_md5(mpass);

        if(mpass == "") {
            layer.tips("密码不能为空！", '#mpass', {
                tips: [2, '#FF0000']
            });
            return;
        }
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/destiny/user/checkPass?token=${access_key}",
            dataType: "json",
            contentType: "application/json",
            /*data: JSON.stringify({
                userName : $("#userName").html(),
                password : mpass
            }),*/
            data: JSON.stringify({'name':$("#userName").html(), 'password':MD5Pass
            }),
            beforeSend: function (request) {
                request.setRequestHeader("Authorization", ${access_key});
            },
            success : function (data) {
                if(data.success) {
                    layer.tips(data.message, '#mpass', {
                        tips: [2, '#78BA32']
                    });
                    return true;
                } else {
                    layer.tips(data.message, '#mpass', {
                        tips: [2, '#FF0000']
                    });
                    $("#mpass").val("");
                    return true;
                }
            }
        });

    }

    $("#sub_renewpass").click(function () {
        var mpass = $("#mpass").val();
        var newpass = $("#newpass").val();
        var renewpass = $("#renewpass").val();
        var MD5Pwd = hex_md5(renewpass);
        var sleepTime = null;
        
        if(mpass == "" || newpass == "" || renewpass =="") {
            return;
        }

        if(renewpass != newpass) {
            return;
        }

        $.ajax({
            type: "POST",
            url: "http://localhost:8080/destiny/user/modifyPass?token=${access_key}",
            dataType: "json",
            contentType: "application/json",
            data: JSON.stringify({'name':$("#userName").html(), 'password':MD5Pwd}),
            success : function (data) {
                if(data.success) {
                    layer.msg(data.message)
                    $("#mpass").val("");
                    sleepTime = setTimeout(function () {
                        //跳出框架，返回返回最顶层的先辈窗口 (返回当前窗口引用window.self相当于window)
                        window.top.location.href = "http://localhost:8080/destiny/account_login/logout";
                    }, 2000);
                } else {
                    layer.msg(data.message)
                }
            }
        });
    });
</script>
</html>