layui.use(['laydate', 'form', 'layer', 'upload'], function () {
    var laydate = layui.laydate, form = layui.form
        , layer = layui.layer, upload = layui.upload;
    var $ = layui.jquery;

    $("#updatePreview").hide();

    var SUCCESS = '#4caf50';
    var FAIL = '#d16d62';

    upload.render({
        elem: '#image' //绑定元素
        , url: contextPath+'/user/upload' //上传接口
        , done: function (res) {
            //上传完毕回调
            console.info(res)
            if (res.msg === "SUCCESS") {
                layer.msg("修改成功");
            } else {
                layer.msg("似乎失败了呢，再试一次吧", {icon: 5});
                console.info(res.msg)
            }
        }
        , error: function () {
            //请求异常回调
            layer.msg("请求异常回调，再试一次吧", {icon: 5});
        }
        , field: 'image'
        , accept: 'images'
        , acceptMime: 'image/*'
        , auto: false
        , bindAction: '#imgSubmit'
        , choose: function (obj) {
            obj.preview(function (index, file, result) {
                $("#preview").show();
                $('#updatePreview').attr('src', result);
                $("#demo1").attr('src', result);
            });
        }
    });

    $("#demo1").click(function () {
        parent.layer.open({
            type: 1,
            title: false,
            closeBtn: 0,
            shadeClose: true,
            content: $('#updatePreview').show(),
            end: function () {
                $('#updatePreview').hide();
                console.info("end");
            }
        });
    });

});
