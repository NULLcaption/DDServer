$().ready(function() {
    validateRule();
});

$.validator.setDefaults({
    submitHandler : function() {
        submitData();
    }
});
/**
 * 传输数据验证
 */
function validateRule() {
    var icon = "<i class='fa fa-times-circle'></i> ";
    $("#submitData").validate({
        rules : {
            pnum : "required",
            personInCharge : "required",
            address: "required"
        },
        messages : {
            pnum : icon+"请填需要盘点数量",
            personInCharge:icon+"请填需要盘点人",
            address:icon+"请填需要存放地址"
        }
    });
}
/**
 * 提交修改数据
 */
function submitData() {
    var icon = "<i class='fa fa-times-circle'></i> ";
    if($("#pnum").val()=="") {
        return layer.msg("请填需要盘点数量");
    }
    if($("#personInCharge").val()=="") {
        return layer.msg(icon+"请填需要盘点人");
    }
    if($("#address").val()=="") {
        return layer.msg("请填需要存放地址");
    }
    $.ajax({
        type: "POST",
        url: "/dd/submitData",
        data : $("#submitData").serialize(),
        success: function (r) {
            if(r.code == 0) {
                layer.msg("数据提交出成功");
                window.location.reload();
            } else {
                layer.msg("盘点失败");
            }
        }
    })
}


/**
 * 上传图片
 */
function btnUploadFile(e) {
    var imgFile = e.target.files[0]; //获取图片文件
    var formData = new FormData();  // 创建form对象
    var id = $("#id").val();
    formData.append('file', imgFile);  // 通过append向form对象添加数据
    formData.append('other', 'other')  // 如果还需要传替他参数的话
    $.ajax({
        url: '/dd/uploadFile?id='+id, //请求的接口地址
        type: 'POST',
        cache: false, //上传文件不需要缓存
        data: formData,
        processData: false, // 不要去处理发送的数据
        contentType: false, // 不要去设置Content-Type请求头
        success: function (data) {
            layer.msg("图片上传成功");
        },
        error: function (data) {
            layer.msg(data.msg)
        }
    })
}