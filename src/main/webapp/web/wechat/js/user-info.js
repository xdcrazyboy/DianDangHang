/**
 * Created by fengziboboy on 2017/6/12.
 */
$(function() {

    var ServerUrl = "http://timeseller.fantasy512.cn/timeseller_v0.2/";

    /*数据*/
    var datas = {
        id: "1",
        openid: "3214634",
        nickname: "bobo",
        sex: "0",
        icon: "../images/background.jpg",
        qq: "970121654",
        phone: "13978654556",
        weixin: "fengziboboy",
        location: "西电北校区西南门",
        card: "../img/ui/u6.png"
    };

    // 预处理href地址
    hrefPreproccess(location);

    // 展示个人资料界面
    showUserInfo(datas);

    /* * * *
     *  处理href地址？或者#号面的键值对 为json格式
     *  diff_str    区分不同类别的 #/?
     * * * * */
    function hrefPreproccess(href_datas) {
        var datas_raw = location.search;
        var datas_temp = datas_raw.substr(1).split("&");
        alert(datas_temp);

    };


    /*    $.get(ServerUrl + "my/showinfo?id="+userId, function(data) {
            //处理展示个人资料
             if (data.statusCode == 3000) {

            } else {
                $.toast("数据获取错误");
             }
        });
    */

    // 展示个人数据
    function showUserInfo(datas) {
        /*初始化界面*/
        // 判断访问页面者的是否为本人
        if (datas.openid == "321463") {
            init_data(datas, 1);
        } else {
            init_data(datas, 0);
        }

        /*接手*/
        $("#accept-task").click(function() {
            alert("接手");
        });

    };


    /*初始化界面*/
    function init_data(datas, isSelf) {
        $(".user-bg").css("background", "#000 url('" + datas.icon + "') no-repeat center center;")
        $(".headimg").attr("src", "../img/personCenter/person-img.jpg");
        $("#user-name").text(datas.nickname);
        // 性别暂时只有男女
        $("#user-gender").text(datas.sex == 0 ? "女" : "男");
        $("#user-gender").val(datas.sex);
        $("#user-address").text(datas.location);
        $("#user-school").text("西安电子科技大学北校区");
        $("#user-tel").text(datas.phone);
        $("#user-card-show img").attr("src", datas.card);
        if (isSelf) $(".user-edit-btn").css("display", "block");
    }

    // /*操作html设置图片*/
    // function init_image(items) {
    //     var html = "";
    //     for (var i in items) {
    //         html += "<li class=\"weui-uploader__file\" style=\"background-image:url(" + items[i] + "\")></li>";
    //     }
    //     $("#uploaderFiles-num").text(items.length);
    //     $("#uploaderFiles").empty().append(html);
    // }

    // /*查看图片*/
    // function look_image(items) {
    //     /*设置图片浏览*/
    //     var look_image = $.photoBrowser({
    //         items: items,
    //         onSlideChange: function(index) {},
    //         onOpen: function() {},
    //         onClose: function() {}
    //     });

    //     /*点击图片，进入图片浏览模式*/
    //     $("#uploaderFiles li").click(function() {
    //         var index = $(this).index();
    //         location.hash = "gallery";
    //         look_image.open(index);
    //     });

    // }

});