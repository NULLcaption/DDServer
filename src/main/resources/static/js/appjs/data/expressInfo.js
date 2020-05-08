/**
 * Created by Administrator on 2020/4/16.
 */
/**
 * 扫描条码
 */
function scanCode () {
    dd.ready(function () {
        dd.biz.util.scan({
            type: 'barCode',// type 为 all、qrCode、barCode，默认是all。
            onSuccess: function(data) {
                var expressNum = data.text;
                layer.alert(expressNum);
                $("#expressNum").val(expressNum);
            },
            onFail: function(){
                console.log("请扫条形码");
            }
        });
    })
}
/**
 * 保存数据
 * @returns {*}
 */
function submitData() {
    var icon = "<i class='fa fa-times-circle'></i> ";
    if($("#expressCompany").val()=="") {
        return layer.msg("快递公司未选择");
    }
    if($("#expressNum").val()=="") {
        return layer.msg(icon+"快递单号未填");
    }
    $.ajax({
        type: "POST",
        url: "/dd/saveData",
        data : $("#submitData").serialize(),
        success: function (r) {
            if(r.code == 0) {
                layer.msg("保存成功");
                window.location.href = "/dd/expressInfo?index=2";
            } else if (r.code == -1) {
                layer.msg(r.msg);
            } else {
                layer.msg(r.msg);
            }
        }
    })
}