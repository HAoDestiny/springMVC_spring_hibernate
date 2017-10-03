<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>后台管理中心</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/pintuer.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/admin.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/iziToast.min.css"> <%--//弹出窗口--%>
    <script src="${pageContext.request.contextPath}/statics/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/statics/js/pintuer.js"></script>
    <script src="${pageContext.request.contextPath}/statics/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/statics/js/layer/layer.js"></script>
  <script src="${pageContext.request.contextPath}/statics/js/iziToast.min.js" type="text/javascript"></script> <%--//弹出窗口--%>
</head>
<body style="background-color:#f2f9fd;">
<div class="header bg-main">
  <div class="logo margin-big-left fadein-top">
    <h1><img src="${pageContext.request.contextPath}/statics/images/y.jpg" class="radius-circle rotate-hover" height="50" alt="" />后台管理中心</h1>
  </div>
  <div class="head-l"><a class="button button-little bg-green" href="" target="_blank"><span class="icon-home"></span> 前台首页</a> &nbsp;&nbsp;<a href="##" class="button button-little bg-blue"><span class="icon-wrench"></span> 清除缓存</a> &nbsp;&nbsp;<a id="setUp" class="button button-little bg-red" href="http://localhost:8080/destiny/account_login/logout"><span class="icon-power-off"></span> 退出登录</a> </div>
</div>
<div class="leftnav">
  <div class="leftnav-title"><strong><span class="icon-list"></span>菜单列表</strong></div>
  <h2><span class="icon-user"></span>基本设置</h2>
  <ul style="display:block">
    <li><a href="http://localhost:8080/destiny/wap/info?token=${access_key}" target="right" class="on"><span class="icon-caret-right"></span>网站设置</a></li>
    <li><a href="http://localhost:8080/destiny/wap/pass?userName=${userName}&token=${access_key}" target="right"><span class="icon-caret-right"></span>修改密码</a></li>
    <li><a href="http://localhost:8080/destiny/wap/page" target="right"><span class="icon-caret-right"></span>单页管理</a></li>
    <li><a href="http://localhost:8080/destiny/wap/adv" target="right"><span class="icon-caret-right"></span>首页轮播</a></li>
    <li><a href="http://localhost:8080/destiny/wap/book" target="right"><span class="icon-caret-right"></span>留言管理</a></li>
    <li><a href="http://localhost:8080/destiny/wap/column" target="right"><span class="icon-caret-right"></span>栏目管理</a></li>
    <li><a href="http://localhost:8080/destiny/pageList?pageCode=1&token=${access_key}" target="right"><span class="icon-caret-right"></span>会员管理</a></li>
    <li><a href="http://localhost:8080/destiny/items/finalPageItem?pageCode=1&token=${access_key}" target="right"><span class="icon-caret-right"></span>商品管理</a></li>
  </ul>
  <h2><span class="icon-pencil-square-o"></span>栏目管理</h2>
  <ul>
    <li><a href="http://localhost:8080/destiny/wap/list" target="right"><span class="icon-caret-right"></span>内容管理</a></li>
    <li><a href="http://localhost:8080/destiny/wap/add" target="right"><span class="icon-caret-right"></span>添加内容</a></li>
    <li><a href="http://localhost:8080/destiny/wap/cate" target="right"><span class="icon-caret-right"></span>分类管理</a></li>
  </ul>  
</div>
<script type="text/javascript">
$(function(){
    jQuery(function ($) {
        if(!window.name){
            /**
             * 第一次打开操作 window.name为空
             * */
            //alert("第一次开这个窗口！name值"+ window.name);
            iziToast.show({
                class: 'test',
                color: 'dark',
              /*      icon: 'icon-contacts',*/
                title: '登录成功!',
                message: 'WELCOME ${userName}!',
                position: 'topCenter',
                transitionIn: 'flipInX',
                transitionOut: 'flipOutX',
                progressBarColor: 'rgb(0, 255, 184)',
              /*        image: 'img/avatar.jpg',*/
                imageWidth: 70,
                layout:1,
                onClose: function(){
                    console.info('onClose');
                },
                iconColor: 'rgb(0, 255, 184)'
            });

            /**
             * 登录后 window.name为 destiny
             * */
            window.name = 'destiny';
        }else{
            //刷新操作
            //alert('刷新操作 name值：'+ window.name);
        }
    });

    /**
     * 退出登录后 window.name为空
     * */
    $("#setUp").click(function () {
        window.name = "";

    });

  $(".leftnav h2").click(function(){
	  $(this).next().slideToggle(200);	
	  $(this).toggleClass("on"); 
  })
  $(".leftnav ul li a").click(function(){
	    $("#a_leader_txt").text($(this).text());
  		$(".leftnav ul li a").removeClass("on");
		$(this).addClass("on");
  })
});
</script>
<ul class="bread">
  <li><a href="{:U('Index/info')}" target="right" class="icon-home"> 首页</a></li>
  <li><a href="##" id="a_leader_txt">网站信息</a></li>
  <li><b>当前语言：</b><span style="color:red;">中文</span>
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;切换语言：<a href="##">中文</a> &nbsp;&nbsp;<a href="##">英文</a> </li>
</ul>
<div class="admin">
  <iframe scrolling="auto" rameborder="0" src="http://localhost:8080/destiny/wap/info?token=${access_key}" name="right" width="100%" height="100%"></iframe>
</div>
<div style="text-align:center;">
</div>
</body>
</html>