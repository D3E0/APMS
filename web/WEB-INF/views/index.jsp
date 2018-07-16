<%--
  Created by IntelliJ IDEA.
  User: ACM-PC
  Date: 2018/5/20
  Time: 18:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/layui.css">
    <script src="<c:url value="/static/layui/layui.js"/>"></script>
    <title>导航界面</title>
    <style>
        .f {
            width: 500px;
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
        }
    </style>
</head>
<body>
<div style="width: 500px; margin: 100px auto">
    <fieldset class="layui-elem-field">
        <legend>导航页</legend>
        <div class="layui-field-box">
            <div class="layui-btn-container">
                <a href="<c:url value="/signIn"/>" class="layui-btn layui-btn-lg">签到/签退</a>
                <a href="<c:url value="/register"/>" class="layui-btn layui-btn-lg">人脸信息录入</a>
                <a href="<c:url value="/record"/>" class="layui-btn layui-btn-lg">签到记录</a>
            </div>
        </div>
    </fieldset>
</div>
<%--<fieldset class="layui-elem-field">--%>
<%--<legend>JSON</legend>--%>
<%--<div class="layui-field-box">--%>
<%--<div class="layui-btn-container">--%>
<%--<a href="<c:url value="/authorities"/>" class="layui-btn" target="_blank">Home</a>--%>
<%--<a href="<c:url value="/user"/>" class="layui-btn" target="_blank">Add</a>--%>
<%--</div>--%>
<%--</div>--%>
<%--</fieldset>--%>
<%--</div>--%>


</body>
</html>
