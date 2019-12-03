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
        data : {
            //这是你开发H5微应用的首页地址，一般由钉钉管理员配置
            //url: "http://10.3.25.11:8101/dd/index"
            url: "http://139.219.143.70:8101/dd/index"
        },
        success: function (data) {
            _config = data;
        },
        error: function() {
          alert("授权错误")
        },
        complete: function(data) {
            DDConfig(_config);
        }

    });
}
/**
 * 配置钉钉信息
 * @param _config
 * @constructor
 */
function DDConfig (_config) {
    dd.config({
        agentId : _config.agentId,
        corpId : _config.corpId,
        timeStamp : _config.timeStamp,
        nonceStr : _config.nonceStr,
        signature : _config.signature,
        jsApiList : [
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
            'biz.ding.post']
    });

    dd.ready(function() {
        //免登，获取钉钉用户的信息
        dd.runtime.permission.requestAuthCode({
            corpId : _config.corpId,
            onSuccess : function(info) {
                $.ajax({
                    url : '/dd/userinfo?code=' + info.code + '&corpid='
                    + _config.corpId,
                    type : 'GET',
                    success : function(data, status, xhr) {
                        //alert(data);
                        var info = JSON.parse(data);
                        document.getElementById("userid").innerHTML = info.userid;
                        document.getElementById("name").innerHTML = info.name;
                        document.getElementById("jobnumber").innerHTML = info.jobnumber;
                        document.getElementById("position").innerHTML = info.position;
                    },
                    error : function(xhr, errorType, error) {
                        logger.e("yinyien:" + _config.corpId);
                        alert(errorType + ', ' + error);
                    }
                });

            },
            onFail : function(err) {
                alert('fail: ' + JSON.stringify(err));
            }
        });
    });
    dd.error(function(err) {
        alert('dd error: ' + JSON.stringify(err));
    });

}

/**
 * 进入盘点信息首页
 */
function loginIndex () {
    var userid = document.getElementById("userid").innerHTML;
    var name = document.getElementById("name").innerHTML;
    var jobnumber = document.getElementById("jobnumber").innerHTML;
    var position =  document.getElementById("position").innerHTML;
    parent.location.href = '/dd/loginIndex?jobnumber='+jobnumber;
}

