
/***
 * 登录
 */
$("#login_btn").click(function () {

    if(!checkText("name")) {
        //alert("用户名不能为空！");
        messageBorder("用户名不能为空！");
        return;
    }

    if(!checkText("password")) {
        //alert("密码不能为空！");
        messageBorder("密码不能为空！");
        return;
    }

    //提交表单
    sub_form("login_form");
});

/**
 *检查是否为空
 */
function checkText(text_id) {
    if($("#" + text_id).val() == "" || $("#" + text_id).val() == null) {
        return false;
    }
    return true;
}

/**
 * 提交表单
 * @param form_id
 */
function sub_form(form_id) {
    loadingBorder();
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/destiny/account_login/login",
        contentType: "application/x-www-form-urlencoded",
        data: $("#" + form_id).serialize(),
        success : function (data) {
            if(data.success) {
                layer.msg(data.message);
                layer.closeAll();
                window.location.href  = "wap/index?userName=" + data.value.name;
                //toJsp();
            } else {

                layer.msg(data.message);
            }
        }
    });
}

function loadingBorder() {
    layer.msg('加载中', {
        icon: 16
        ,shade: 0.01
    });
}


function messageBorder(val) {
    layer.open({
        title: '提示',
        shade: 0, // 取消遮罩
        anim: 6, //动画效果-晃动
        content: val
    });
}
/***
 * 通过url获取参数
 */
// $("#msg_test_url").html("这是通过url传递得到的数据：" + $.getUrlParam("message"));