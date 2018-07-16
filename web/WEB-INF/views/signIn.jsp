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
    <script src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/jquery-3.3.1.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/jquery.transit.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/capture.js"></script>
    <script>
        var contextPath = '${pageContext.request.contextPath}';
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
            <div class="scene">
                <div class="cube">
                    <div class="side front" id="Front"></div>
                    <div class="side back" id="Back"></div>
                    <div class="side bottom" id="Bottom"></div>

                    <div class="side top" id="Top" style="padding: 0">
                        <video id="video" width=100% height="300"></video>
                        <div class="navi">
                            <%--<a href="javascript:myTransition(1, 300);myTransition(1, 300)">手机号登陆</a>--%>
                            <%--<a href="javascript:myTransition(1, 500)">账号密码登陆</a>--%>
                            <a href="<c:url value="/register"/>">立即注册</a>
                        </div>
                        <button id="start" class="layui-btn layui-btn-fluid ">开始签到</button>
                    </div>


                </div>
            </div>
        </div>
    </div>
</div>
<canvas id="canvas" hidden width="400" height="360"></canvas>
<script>
    var contextPath = '${pageContext.request.contextPath}';
    layui.use(['form', 'laydate', 'jquery', 'layer'], function () {
        var form = layui.form, $ = layui.jquery, layer = layui.layer;
    });
    var $bottom = $("#Bottom");
    var $front = $("#Front");
    var $top = $("#Top");
    var $back = $("#Back");
    var myStatus = {top: 90, front: 0, bottom: -90, back: 0};

    /**
     *
     * @param direction  动画方向，向上转 1；向下转 -1
     * @param speed 动画速度
     */
    function myTransition(direction, speed) {

        for (i in myStatus) {
            myStatus[i] += 90 * direction;
        }

        $bottom.transition({
            transform: "perspective(1200px) rotateX(" + myStatus.bottom + "deg)"
        }, speed, 'linear');

        $front.transition({
            transform: "perspective(1200px) rotateX(" + myStatus.front + "deg)"
        }, speed, 'linear');

        $top.transition({
            transform: "perspective(1200px) rotateX(" + myStatus.top + "deg)"
        }, speed, 'linear');

        $back.transition({
            transform: "perspective(1200px) rotateX(" + myStatus.back + "deg) rotateY(180deg) rotateZ(180deg) "
        }, speed, 'linear');
    }
</script>
</body>
</html>