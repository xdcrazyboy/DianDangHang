/**
 * Created by ALISURE on 2017/5/22.
 */
$(function () {

    /*数据*/
    /*
    var datas = {
        task_name: "帮取快递啦",
        task_describe: "西电北校区西南门，中通快递。",
        task_secret: "*******************",
        task_time_begin: "2017-05-08 15:30",
        task_time_end: "2017-05-08 16:00",
        task_type: "跑腿",
        task_place: "西电北校区西南门",
        task_money: 5,
        task_other_money: "一袋辣条",
        task_link_select: "手机",
        task_link_input: "***********",
        task_images:[
            "../img/ui/u6.png",
            "../img/ui/u8.png",
            "../img/ui/u12.png"
        ]
    };
    */

    var link_params = [
        { link_id:1, link_text:"微信号", link_input_type:"text"},
        { link_id:2, link_text:"QQ号", link_input_type:"number"},
        { link_id:3, link_text:"手机号", link_input_type:"number"}
    ];

    /* 获取任务ID */
    var taskId = getSearchValue(location.search, "id");

    /* 执行入口 */
    getData(function (statusCode, datas) {

        var type = 2;

        if(statusCode == 3100){ // type = 1
            type = 1;
            /* 设置联系方式 */
            if(datas.weixin){
                datas.task_link_select = link_params[0].link_text;
                datas.task_link_input = datas.weixin;
            }else if(datas.qq){
                datas.task_link_select = link_params[1].link_text;
                datas.task_link_input = datas.qq;
            }else if(datas.telephone){
                datas.task_link_select = link_params[2].link_text;
                datas.task_link_input = datas.telephone;
            }
        }else if(statusCode == 3200){ // type = 2
            type = 2;
            datas.secret_message = "********************";
        }else if(statusCode == 3300){ // type = 3
            type = 3;
        }

        /*初始化界面*/
        init_data(datas, type);
        /*初始化图片*/
        init_image(datas.lists);

    });

    function getData(callback) {
        $.get( ServerUrl + "task/task/" + taskId, function (data) {
            if(data.statusCode == 3003){
                $.alert("任务不存在，返回");
                history.back();
            }else if(data.statusCode == 3100 || data.statusCode == 3200 || data.statusCode == 3300){
                if(data.myTask){
                    callback(data.statusCode, data.myTask);
                }else{
                    $.alert("出错：data.myTask不存在！");
                }
            }else{
                $.toast(data.statusCode + ":" + data.message, "text");
                // history.back();
            }
        });
    }

    /*初始化界面*/
    function init_data(datas, type) {

        $("#publish-wrapper").show();

        $("#task-name").text(datas.title);
        $("#task-describe").text(datas.content);
        $("#task-secret").text(datas.secret_message);
        $("#task-time-begin").text(datas.pubDate.length == 0 ? "无": datas.pubDate);
        $("#task-time-end").text(datas.endDate);
        $("#task-type").text(datas.type);
        $("#task-place").text(datas.place);
        $("#task-money").text(datas.goldCoins);
        $("#task-other-money").text(datas.reward.length == 0 ? "无": datas.reward);

        /* 设置联系方式 */
        var $select = $("#task-link-select");
        var $content = $("#task-link-content");
        var $method = $("#task-link-method");
        if(type == 1){
            $select.text(datas.task_link_select).parent().show();
            $method.text(datas.task_link_select).parent().show();
            $content.text(datas.task_link_input).parent().show();
        }else if(type == 2){
            $select.parent().hide();
            $content.parent().hide();
        }else if(type == 3){

        }

        /* 设置按钮 */
        if(type == 1){
            /*发布人*/
            $("#modify-task").show().click(function () {
                $.alert("编辑");
            }).siblings().hide();
        }else if(type == 2){
            /*接收人*/
            $("#accept-task").show().click(function () {
                $.alert("接手");
            }).siblings().hide();
        }else if(type == 3){
            /*进行中*/
            $("#execute-task").addClass("weui-btn_disabled").show().click(function () {
                $.alert("进行中");
            }).siblings().hide();
        }else if(type == 4){
            /* 确认完成 */
            $("#completing-task").show().click(function () {
                $.alert("确认完成");
                history.back();
            }).siblings().hide();
        }else if(type == 5){
            /* 已完成 */
            $("#completed-task").show().click(function () {
                history.back();
            }).siblings().hide();
        }
    }

    /*初始化图片*/
    function init_image(items){
        var html = "";
        for(var i in items){
            html += "<li class=\"weui-uploader__file\" style=\"background-image:url(" + items[i] + "\")></li>";
        }
        $("#uploaderFiles-num").text(items.length);
        $("#uploaderFiles").empty().append(html);

        /*设置查看图片*/
        look_image(items);
    }

    /*查看图片*/
    function look_image(items) {
        /*设置图片浏览*/
        var look_image = $.photoBrowser({
            items: items,
            onSlideChange: function(index) {},
            onOpen: function() {},
            onClose: function() {}
        });

        /*点击图片，进入图片浏览模式*/
        $("#uploaderFiles li").click(function () {
            var index = $(this).index();
            location.hash = "gallery";
            look_image.open(index);
        });

    }

});