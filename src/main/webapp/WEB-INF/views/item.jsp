<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <meta name="renderer" content="webkit">
    <title>商品管理</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/pintuer.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/admin.css">
    <script src="${pageContext.request.contextPath}/statics/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/statics/js/pintuer.js"></script>
    <script src="${pageContext.request.contextPath}/statics/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/statics/js/layer/layer.js"></script>
</head>

<body>
<div class="panel admin-panel">
    <div class="panel-head"><strong class="icon-reorder">商品列表</strong></div>
    <div class="padding border-bottom">
        <button type="button" class="button border-yellow" onclick="window.location.href='#add'">
            <span class="icon-plus-square-o"></span> 添加内容
        </button>
    </div>

    <table class="table table-hover text-center">
        <tr>
            <th width="10%">ID</th>
            <th width="20%">图片</th>
            <th width="15%">标题</th>
            <th width="20%">描述</th>
            <th width="10%">价格</th>
            <th width="10%">数量</th>
        </tr>

        <%-- 计算页码列表 每页十条--%>
        <c:choose>
            <c:when test="${ItemPageEntity.totalPages < 10}">
                <c:set var="begin" value="1"/>
                <c:set var="end" value="${ItemPageEntity.totalPages}"/>
            </c:when>

            <c:otherwise>
                <c:set var="begin" value="${ItemPageEntity.pageCode - 4}"/>
                <c:set var="end" value="${ItemPageEntity.pageCode + 5}"/>
                <%-- begin溢出 --%>
                <c:if test="${begin < 1}">
                    <c:set var="begin" value="1"/>
                    <c:set var="end" value="10"/>
                </c:if>
                <%-- end溢出 --%>
                <c:if test="${end > ItemPageEntity.totalPages}">
                    <c:set var="begin" value="${ItemPageEntity.pageCode - 4}"/>
                    <c:set var="end" value="${ItemPageEntity.totalPages}"/>
                </c:if>
            </c:otherwise>
        </c:choose>

        <c:forEach items="${ItemPageEntity.entityList}" var="item">
            <tr>
                <td>${item.id}</td>
                    <%-- 添加tomcat虚拟文件目录 --%>
                <td><img src="/pic/${item.image}" alt="" width="120" height="50"/></td>
                <td>${item.title}</td>
                <td>${item.sellPoint}</td>
                <td>${item.price/100} 元</td>
                <td>${item.num}</td>
                <td>
                    <div class="button-group">
                        <button class="button border-main"><span class="icon-edit"></span> 修改</button>
                        <button class="button border-red" type="button" onclick="del(${item.id},1)"><span
                                class="icon-trash-o"></span> 删除
                        </button>
                    </div>
                </td>
            </tr>
        </c:forEach>
        <tr>
            <td colspan="8">
                <div class="pagelist">
                    <a href="finalPageItem?pageCode=1&token=${access_key}">首页</a>
                    <c:if test="${ItemPageEntity.pageCode > 1}">
                        <a href="finalPageItem?pageCode=${ItemPageEntity.pageCode - 1}&token=${access_key}">上一页</a>
                    </c:if>
                    <c:forEach begin="${begin}" end="${end}" var="i">

                        <c:choose>
                            <c:when test="${i eq ItemPageEntity.pageCode}">
                                <span class="current">${i}</span>
                            </c:when>
                            <c:otherwise>
                                <a href="finalPageItem?pageCode=${i}&token=${access_key}">${i}</a>
                            </c:otherwise>
                        </c:choose>

                    </c:forEach>
                    <c:if test="${ItemPageEntity.pageCode < ItemPageEntity.totalPages}">
                        <a href="finalPageItem?pageCode=${ItemPageEntity.pageCode + 1}&token=${access_key}">下一页</a>
                    </c:if>
                    <a href="finalPageItem?pageCode=${ItemPageEntity.totalPages}&token=${access_key}">尾页</a>
                </div>
            </td>
        </tr>
    </table>
</div>
<script type="text/javascript">
    $('#button1').click(function () {
        alert("onclick")
        layer.msg('灵活运用offset', {
            offset: '200px', //设置显示位置坐标
            anim: 6 //动画
        });
    });

    function delItem(id, mid) {
        layer.open({
            content: 'test'
            , offset: '150px'
            , btn: ['确定', '取消']
            , yes: function (index, layero) {
                //按钮【确定】的回调
            }
            , btn2: function (index, layero) {
                //按钮【取消】的回调
                //return false 开启该代码可禁止点击该按钮关闭
            }
            , cancel: function () {
                //右上角关闭回调
                //return false 开启该代码可禁止点击该按钮关闭
            }
        });
    }

    function del(id, mid) {
        layer.open({
            content: '是否确定要删除？'
            , offset: '200px'
            , btn: ['确定', '取消']
            , yes: function (index, layero) {
                //按钮【确定】的回调
                $.ajax({
                    type: "GET",
                    url: "http://localhost:8080/destiny/items/delItem/" + id + "&token=" + ${access_key},
                    data: "",
                    dataType: "json",
                    success: function (data) {
                        if (data.success) {
                            layer.msg(data.message, {
                                offset: '200px' //设置显示位置坐标
                            });
                            setTimeout(function () {
                                window.location.href = "http://localhost:8080/destiny/items/finalPageItem?pageCode=${ItemPageEntity.pageCode}&token=${access_key}";
                            }, 500);
                        } else {
                            tip(data.message, 2, 6);
                        }
                    },
                    error: function () {
                        tip("未知错误！", 2, 6);
                    }
                });
            }
            , btn2: function (index, layero) {
                //按钮【取消】的回调
                //return false 开启该代码可禁止点击该按钮关闭
            }
            , cancel: function () {
                //右上角关闭回调
                //return false 开启该代码可禁止点击该按钮关闭
            }
        });
    }
</script>

<div class="panel admin-panel margin-top" id="add">
    <div class="panel-head"><strong><span class="icon-pencil-square-o"></span> 增加内容</strong></div>
    <div class="body-content">
        <form id="addForm" class="form-x" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <div class="label">
                    <label>编号：</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50" value="" name="id" id="id" data-validate="required:请输入编号"/>
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>标题：</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50" value="" name="title" id="title"
                           data-validate="required:请输入标题"/>
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>价格：</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50" id="price" name="price" value=""/>
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>图片：</label>
                </div>
                <div class="field">
                    <input type="file" id="up_pic" name="up_pic" hidden="hidden" onchange="uploadImage(this)"/>
                    <input type="text" id="url1" name="img" class="input tips" style="width:30%; float:left;" value=""
                           data-toggle="hover" data-place="right" data-image=""/>
                    <input type="button" class="button bg-blue margin-left" id="image1" value="+ 浏览上传"
                           style="float:left;">
                    <div class="tipss">图片尺寸：1920*500</div>
                    <img src="" id="preview" style="width: 500px; height: 200px;" hidden="hidden"/>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>描述：</label>
                </div>
                <div class="field">
                    <textarea type="text" class="input" name="sell_point" style="height:120px;" value=""></textarea>
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>数量：</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50" name="num" id="num" value="0"
                           data-validate="required:,number:排序必须为数字"/>
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>类别：</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50" name="cid" value="0" data-validate="required:,number:排序必须为数字"/>
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label></label>
                </div>
                <div class="field">
                    <button id="addItem" class="button bg-main icon-check-square-o" type="button"> 提交</button>
                </div>
            </div>
        </form>
    </div>
</div>
<script>
    /*将上传文件的事件绑定在按钮上*/
    $("#image1").click(function () {
        $("#up_pic").click();
    });

    function uploadImage(fileList) {
        //判断是否支持FileReader
        if (window.FileReader) {
            var reader = new FileReader();
        } else {
            alert("您的设备不支持图片预览功能，如需该功能请升级您的设备！");
        }

        var file = fileList.files[0];
        var imageType = /^image\//;
        //alert(file.name);

        if (!imageType.test(file.type)) {
            alert("请选择图片！");
            return;
        }
        $("#url1").val(file.name);
        reader.onload = function (e) {
//          //获取图片dom
//          var img = document.getElementById("preview");
//          //图片路径设置为读取的图片
//          img.src = e.target.result;
            $('#preview').attr("src", e.target.result);
            $('#preview').show();
        };
        reader.readAsDataURL(file);
    }

    $("#addItem").click(function () {
        layer.msg("onclick");

        if (!checkText("id")) {
            tip("编号不能为空！", 2, 6);
            return
        }

        if (!checkText("title")) {
            tip("标题不能为空！", 2, 6);
            return
        }

        if (!checkText("price")) {
            tip("商品价格不能为空！", 2, 6);
            return
        }

        if (!checkText("num")) {
            tip("商品数量不能为空！", 2, 6);
            return
        }

        var formData = new FormData($("#addForm")[0]);
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/destiny/items/addItem?token=${access_key}",
            data: formData,
            dataType: "json", //返回数据data的类型
            async: false,
            contentType: false, //则不会为jqXHR设置请求头Content-Type
            processData: false, //代表jQuery不会将穿到服务器的data解析为字符串
            success: function (data) {
                if (data.success) {
                    layer.msg(data.message, {
                        offset: '200px' //设置显示位置坐标
                    });
                    setTimeout(function () {
                        window.location.href = "http://localhost:8080/destiny/items/finalPageItem?pageCode=1&token=${access_key}";
                    }, 500);
                } else {
                    tip(data.message, 2, 6);
                }
            },
            error: function () {
                tip("未知错误！", 2, 6);
            }
        });
    });

    function tip(val, icon, anim) {
        layer.open({
            title: '提示',
            offset: '200px', //设置显示位置坐标
            icon: icon,
            shade: 0, // 取消遮罩
            anim: anim, //动画效果-晃动
            content: val
        });
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
</script>
</body>
</html>