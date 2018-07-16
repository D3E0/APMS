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
    <title>Register</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/layui.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/signIn.css">
    <script src="${pageContext.request.contextPath}/static/js/jquery-3.3.1.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/jquery.transit.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/register.js"></script>
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
            <div style="color: #009688; text-align: center; font-size: 24px; padding: 20px;margin-top: 100px">
                人脸信息录入
            </div>
            <div style="color: #999; text-align: center; font-size: 14px;">
                上传图片名为学号，如 1160299001.jpg
            </div>
            <div class="layui-form" style="margin: 20px 30px">
                <div class="layui-form-item">
                    <label class="layui-form-label">个人图片</label>
                    <button type="button" class="layui-btn" id="image" name="image">
                        <i class="layui-icon">&#xe67c;</i>上传图片
                    </button>
                </div>

                <div class="layui-form-item" id="preview" hidden>
                    <div class="layui-input-block">
                        <img id="demo1" style="width: 113px">
                    </div>
                </div>

                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <button class="layui-btn layui-btn-lg" lay-filter="*" id="imgSubmit">提交修改</button>
                        <%--<button type="reset" class="layui-btn layui-btn-primary">重置</button>--%>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div id="divPreview">
    <img id="updatePreview" style="width: 100%"/>
</div>
</body>
</html>