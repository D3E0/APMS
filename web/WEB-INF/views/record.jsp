<%--
  Created by IntelliJ IDEA.
  User: 60384
  Date: 2018/7/16
  Time: 18:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/layui.css">
    <script src="${pageContext.request.contextPath}/static/js/jquery-3.3.1.js"></script>
    <script src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
    <script>
        var contextPath = '${pageContext.request.contextPath}';
    </script>
    <style>
        body {
            background-color: #eee;
            margin-top: 15px;
        }
    </style>
</head>
<body>
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-header">签到情况查看</div>
                <div class="layui-card-body">
                    <div class="layui-input-inline">
                        <input class="layui-input" id="searchVal">
                    </div>
                    <button class="layui-btn" id="search">
                        <i class="layui-icon">&#xe615;</i> 搜索
                    </button>
                    <!--<button class="layui-btn" id="add">-->
                    <!--<i class="layui-icon">&#xe61f;</i> 添加人员-->
                    <!--</button>-->
                    <div id="userTable" lay-filter="userTable"></div>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    layui.use(['jquery', 'laypage', 'table', 'layer', 'element', 'laydate'], function () {
        var $ = layui.$, laypage = layui.laypage
            , table = layui.table, layer = layui.layer
            , element = layui.element, laydate = layui.laydate;

        laydate.render({
            elem: '#searchVal'
            , min: "2018-07-15"
            , max: 0
            , value: new Date()
            , isInitValue: true
            , showBottom: false
            , done: function (value, date, endDate) {
                $("#searchVal").val(value);
            }
        });

        var userTable = table.render({
            elem: '#userTable'
            , url: contextPath + '/api'
            , cols: [[
                {field: 'Id', title: '序号', align: "center"}
                ,{field: 'username', title: '姓名', align: "center"}
                , {field: 'startDate', title: '签到', align: "center"}
                , {field: 'endDate', title: '签退', align: "center"}
            ]]
            , id: "userTable"
        });

        $("#search").click(function () {
            var data = $("#searchVal").val();
            console.info(data);
           userTable.reload({
                where: { //设定异步数据接口的额外参数，任意设
                    data:  $("#searchVal").val()
                }
                , page: {
                    curr: 1 //重新从第 1 页开始
                }
            });
        });
    });
</script>

</body>
</html>
