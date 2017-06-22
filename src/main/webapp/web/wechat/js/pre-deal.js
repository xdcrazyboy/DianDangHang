/**
 * Created by ALISURE on 2017/4/30.
 */

/* 暂时定义成全局变量 */
var project = "timeseller_v0.2";
//var ServerUrl = "http://timeseller.fantasy512.cn/timeseller_v0.2/";
var ServerUrl = "http://115.159.153.114/timeseller_v0.2/";
var SignatureUrl = ServerUrl + "image/signature";
var DownImageUrl = ServerUrl + "image/downImage";

/* 配置数据 */
var ErrorInfo = {
    SignatureError: "未获取签名",
    CheckJsApiError: "不支持JsApi",
    WXCheckError: "验证失败",
};
var LogInfo = {
    CheckReady: "验证成功",
};

$(function () {

    /*配置微信*/
    var url = location.href;
    var jsApiList = ["chooseImage","uploadImage","downloadImage"];

    /* 获取签名 */
    getSignature(url, function (appId, timestamp, nonceStr, signature) {
        /* 配置签名等信息 */
        config(appId, timestamp, nonceStr, signature, jsApiList);
    });

    /* 通过ready接口处理成功验证 */
    ready(function () {
        console.log(LogInfo.CheckReady);
    });

    /* 发生错误时调用 */
    error(function () {
        console.log(ErrorInfo.WXCheckError);
    });

    /* 检查是否支持指定的API */
    check(jsApiList, function (res) {
        /* 不支持指定JS接口 */
        console.log(ErrorInfo.CheckJsApiError);
    });

    /* ajax 异常*/
    $(document).ajaxError(function(){
        $.hideLoading();
        $.toast("发生异常", "text");
    });
});

/**
 * 获取网页的签名
 * @param url
 * @param callback 配置签名等信息
 */
function getSignature(url, callback) {
    $.get(SignatureUrl, {url:url},
        function (data) {
            if (data.statusCode == 3000) {
                var object = data.object;

                /*成功后执行回调*/
                callback(object.appId, object.timestamp, object.nonceStr, object.signature);
            } else {

                /* 没有获取签名，发生异常 */
                console.log(ErrorInfo.SignatureError);
            }
        }
    );
}

/**
 * 配置签名等信息
 * @param appId
 * @param timestamp
 * @param nonceStr
 * @param signature
 * @param jsApiList
 */
function config(appId,timestamp, nonceStr, signature, jsApiList) {
    /*通过config接口注入权限验证配置*/
    wx.config({
        debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
        appId: appId, // 必填，公众号的唯一标识
        timestamp: timestamp, // 必填，生成签名的时间戳
        nonceStr: nonceStr, // 必填，生成签名的随机串
        signature: signature,// 必填，签名，见附录1
        jsApiList: jsApiList // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
    });
}
/**
 * 通过ready接口处理成功验证
 * @param callback
 */
function ready(callback) {
    wx.ready(function () {
        if(typeof callback == "function") callback();
    });
}
/**
 * 发生错误时调用
 * @param callback
 */
function error(callback) {
    /*通过error接口处理失败验证*/
    wx.error(function () {
        if(typeof callback == "function") callback();
    });
}

/**
 * 检查是否支持指定的API
 * @param jsApiList
 * @param failCallBack
 */
function check(jsApiList,failCallBack) {
    /*判断当前客户端版本是否支持指定JS接口*/
    wx.checkJsApi({
        jsApiList: jsApiList,
        success: function(res) {
            // 以键值对的形式返回，可用的api值true，不可用为false
            // 如：{"checkResult":{"chooseImage":true},"errMsg":"checkJsApi:ok"}
        },
        fail: failCallBack,
    });
}


/**
 * 通知服务器去下载图片
 * @param mediaId
 * @param callback
 */
function downImage(mediaId,callback) {
    $.get(DownImageUrl, {mediaId:mediaId}, callback);
}


/* 获取URL 根地址 */
function getRoot() {
    var href = location.href;
    href = href.split(project);
    if(href.length >= 2){
        return href[0] + project + "/";
    }else{
        return location.host + "/";
    }
}


/* url search */
function getSearchValue(search, key) {
    if(search && key){
        search = search.charAt(0) == "?" ? search.substring(1) : search;
        var values = search.split("&");
        var i = 0, len = values.length;
        for(i; i < len; i++){
            var value = values[i].split("=");
            if(value[0] === key){
                return value[1];
            }
        }
    }
    return "";
}


/* 截短字符串 */
function subString(str, i, end) {
    if(!str) return "";
    return str.length > i ? str.substr(0, i) + end : str;
}


/*获取增加hour小时后的时间当前时间*/
function getTimeAddHour(hour) {
    if(hour == undefined) hour = 0;
    var timestamp=new Date().getTime();
    var date = new Date(timestamp + hour * 60 * 60 * 1000);

    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    var strHours = date.getHours();
    var strMinutes = date.getMinutes();

    if (month >= 1 && month <= 9) month = "0" + month;
    if (strDate >= 0 && strDate <= 9) strDate = "0" + strDate;
    if (strHours >= 0 && strHours <= 9) strHours = "0" + strHours;
    if (strMinutes >= 0 && strMinutes <= 9) strMinutes = "0" + strMinutes;

    return date.getFullYear() + "-" + month + "-" + strDate + " " + strHours + ":" + strMinutes;
}
