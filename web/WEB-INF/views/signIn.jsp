<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ACM-PC
  Date: 2018/5/31
  Time: 20:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="<c:url value="/static/layui/css/layui.css"/>">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/signIn.css">
    <script src="${pageContext.request.contextPath}/static/js/jquery-3.3.1.js"></script>
    <script src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/jquery.transit.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/capture.js"></script>
    <script>
        var contextPath = '${pageContext.request.contextPath}';
        var type = '${type}';
    </script>
</head>
<body>
<div class="layui-container" style="margin-top: 50px; height: 500px;">
    <div class="layui-row layui-col-space10">
        <div class="layui-col-md7">
            <div id="box">
                <img src="<c:url value="/static/images/door.png"/>" id="img">
            </div>
        </div>
        <div class="layui-col-md5">
            <div style="color: #009688; text-align: center; font-size: 24px; padding: 20px;margin-top: 20px">
                签到
            </div>
            <div style="color: #999; text-align: center; font-size: 14px;">
                每天签到时间段为 08:00--09:00，签退时间段为 21:00--23:00
            </div>
            <video id="video" width=100% height="300" ></video>
            <c:choose>
                <c:when test="${type == 'IN'}">
                    <button id="start" class="layui-btn layui-btn-fluid">
                        人脸签到
                    </button>
                </c:when>
                <c:when test="${type == 'OUT'}">
                    <button id="start" class="layui-btn layui-btn-primary layui-btn-fluid">
                        签退
                    </button>
                </c:when>
                <c:otherwise>
                    <button class="layui-btn layui-btn-disabled layui-btn-fluid" disabled>
                        非签到、签退时间段
                    </button>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>

<canvas id="canvas" hidden width="400" height="360"></canvas>
<script>
    var contextPath = '${pageContext.request.contextPath}';
    layui.use(['form', 'laydate', 'jquery', 'layer'], function () {
        var form = layui.form, $ = layui.jquery, layer = layui.layer;
    });
</script>
</body>
</html>