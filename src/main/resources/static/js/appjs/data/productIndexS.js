var _config = null;
$(document).ready(function () {
    getConfig();
});
/**
 * 获取钉钉登录信息
 */
function getConfig() {
    $.ajax({
        type: "POST",
        url: "/dd/config",
        dataType: 'json',
        data: {
            //这是你开发H5微应用的首页地址，一般由钉钉管理员配置
            //url: "http://10.3.25.11:8101/dd/index"
            url: "http://139.219.143.70:8101/dd/productIndexD?index=1"
        },
        success: function (data) {
            _config = data;
        },
        error: function () {
            alert("授权错误")
        },
        complete: function (data) {
            DDConfig(_config);
        }

    });
}
var _lnglat = null;//经纬度
/**
 * 配置钉钉信息
 * @param _config
 * @constructor
 */
function DDConfig(_config) {
    dd.config({
        agentId: _config.agentId,
        corpId: _config.corpId,
        timeStamp: _config.timeStamp,
        nonceStr: _config.nonceStr,
        signature: _config.signature,
        jsApiList: [
            'runtime.info',
            'device.notification.prompt',
            'biz.chat.pickConversation',
            'device.notification.confirm',
            'device.notification.alert',
            'device.notification.prompt',
            'biz.chat.open',
            'biz.util.open',
            'biz.user.get',
            'biz.contact.choose',
            'biz.telephone.call',
            'biz.util.uploadImage',
            'biz.ding.post',
            'device.geolocation.get'
        ]
    });
    //获取经纬度
    dd.ready(function () {
        dd.device.geolocation.get({
            targetAccuracy: Number,
            coordinate: Number,
            withReGeocode: Boolean,
            useCache: false, //默认是true，如果需要频繁获取地理位置，请设置false
            onSuccess: function (data) {
                _lnglat = data.longitude + "," + data.latitude;
                if (_lnglat == null) {
                    layer.alert('亲，您的手机定为没开，请打开定位后重试~')
                    return;
                }
            },
            onFail: function (err) {
            }
        })
    });
    dd.error(function (err) {
        alert('dd error: ' + JSON.stringify(err));
    });

}

var map = new AMap.Map("container", {
    resizeEnable: true
});

var geocoder = new AMap.Geocoder({
    city: "010", //城市设为北京，默认：“全国”
    radius: 1000 //范围，默认：500
});
var marker = new AMap.Marker();

/**
 * 获取经纬度转换为定位
 */
function getLocalHost() {
    if (_lnglat == null) {
        layer.msg('亲，您的手机定为没开，请打开定位后重试~')
        return;
    }
    map.add(marker);
    marker.setPosition(_lnglat);
    geocoder.getAddress(_lnglat, function (status, result) {
        if (status === 'complete' && result.regeocode) {
            var address = result.regeocode.formattedAddress;
            layer.alert("获取到的名店地址：" + address);
            $("#address").val(address);
        } else {
            layer.alert('门店地址定位失败，请输入门店详细地址。')
            return;
        }
    });
}
/**
 * 获取查询信息
 * @returns {*}
 */
function searchData(){
    //查询的字符串
    var dataCode = $("#data").val();
    if(dataCode == '') {
        layer.msg("请输入要查询的信息！");
        return;
    }
    if(dataCode.length != 16
        && dataCode.length != 25
        && dataCode.length != 21) {
        layer.msg("请输入正确的信息！");
        return;
    }

    $.ajax({
        url : '/dd/standard?dataCode=' + dataCode,
        type : 'GET',
        success : function(data, status, xhr) {
            layer.alert(data);
        },
        error : function(xhr, errorType, error) {
            layer.alert(errorType + ', ' + error);
        }
    });

}

