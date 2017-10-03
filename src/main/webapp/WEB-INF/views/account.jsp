<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title>网站信息</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/pintuer.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/admin.css">
    <script src="${pageContext.request.contextPath}/statics/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/statics/js/pintuer.js"></script>
    <script src="${pageContext.request.contextPath}/statics/js/jquery.min.js"></script>
</head>
<body>
<div class="panel admin-panel">
    <div class="panel-head"><strong class="icon-reorder"> 内容列表</strong></div>
    <div class="padding border-bottom">
        <a class="button border-yellow" href=""><span class="icon-plus-square-o"></span> 添加用户</a>
    </div>

    <%--计算页码列表--%>
    <%--页码列表每次显示五页，begin = pageCode - 2，end = pageCode + 2--%>
    <%--计算页码的begin和end--%>
    <c:choose>
        <%--当总页数不够5页时，把页数全部显示--%>
        <c:when test="${pageEntity.totalPages <= 5}">
            <c:set var="begin" value="1"/>
            <c:set var="end" value="${pageEntity.totalPages}"/>
        </c:when>
        <%--当总页数>5页时--%>
        <c:otherwise>
            <c:set var="begin" value="${pageEntity.pageCode - 2}"/>
            <c:set var="end" value="${pageEntity.pageCode + 2}"/>

            <%--当begin溢出时（小于1）--%>
            <c:if test="${begin < 1}">
                <c:set var="begin" value="1"/>
                <c:set var="end" value="5"/>
            </c:if>

            <%--当end溢出时（大于总页数）--%>
            <c:if test="${end > pageEntity.totalPages}">
                <c:set var="begin" value="${pageEntity.totalPages - 4}"/>
                <c:set var="end" value="${pageEntity.totalPages}"/>
            </c:if>
        </c:otherwise>
    </c:choose>


    <table class="table table-hover text-center">
        <tr>
            <th width="5%">ID</th>
            <th>名称</th>
            <th>Email</th>
            <th width="250">操作</th>
        </tr>
        <c:forEach items="${pageEntity.entityList}" var="user">
            <tr>
                <td>${user.id}</td>
                <td>${user.userName}</td>
                <td>${user.email}</td>
                <td>
                    <div class="button-group">
                        <a type="button" class="button border-main" href="#"><span class="icon-edit"></span>修改</a>
                        <a class="button border-red" href="javascript:void(0)" onclick="return del(17)"><span class="icon-trash-o"></span> 删除</a>
                    </div>
                </td>
            </tr>
        </c:forEach>
        <tr>
            <td colspan="8">
                <div class="pagelist">
                    <a href="pageList?pageCoed=1&token=${access_key}">首页</a>
                    <c:if test="${pageEntity.pageCode > 1}">
                        <a href="pageList?pageCoed=${pageEntity.pageCode-1}&token=${access_key}">上一页</a>
                    </c:if>

                    <c:forEach var="i" begin="${begin}" end="${end}">
                        <c:choose>
                            <%--当前页--%>
                            <c:when test="${i eq pageEntity.pageCode}">
                                <span class="current">${i}</span>
                            </c:when>
                            <%--其他未点击页--%>
                            <c:otherwise>
                                <a href="pageList?pageCoed=${i}&token=${access_key}">${i}</a>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                 <%--   <span class="current">1</span>
                    <a href="">2</a>
                    <a href="">3</a>--%>

                    <c:if test="${pageEntity.pageCode < pageEntity.totalPages}">
                        <a href="pageList?pageCoed=${pageEntity.pageCode+1}&token=${access_key}">下一页</a>
                    </c:if>
                    <a href="pageList?pageCoed=${pageEntity.totalPages}&token=${access_key}">尾页</a>
                </div>
            </td>
        </tr>
    </table>
</div>
<script>
    jQuery(function($) {
        $.md5
    });

    function del(id){
        if(confirm("您确定要删除吗?")){

        }
    }
</script>
</body></html>