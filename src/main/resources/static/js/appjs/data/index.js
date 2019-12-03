/**
 * 资产详情页
 */
function propertyInfo(id) {
    window.location.href = "/dd/propertyInfo?id="+id;
}
/**
 * 扫描资产条码
 * */
function scanCode () {
    dd.biz.util.scan({
        type: 'all',
        onSuccess: function(data) {
            var propertyId = data.text;
            window.location.href = "/dd/scanInfo?propertyId="+propertyId;
        },
        onFail: function(){
            console.log("扫码失败");
        }
    })
}
/**
 * 输入条码搜索数据
 * */
function searchData(){
    var data = $("#data").val();
    if (data == "") {
        return layer.msg("请输入要搜索的内容！");
    }
    window.location.href = "/dd/scanInfo?propertyId="+data;
}