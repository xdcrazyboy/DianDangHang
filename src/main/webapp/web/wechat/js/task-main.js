/**
 * Created by ALISURE on 2017/5/22.
 */
$(function () {

    /*数据*/
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

    /*初始化界面*/
    init_data(datas);
    /*初始化图片*/
    init_image(datas.task_images);

    /*查看图片*/
    look_image(datas.task_images);

    /*接手*/
    $("#accept-task").click(function () {
        alert("接手");
    });

    /*初始化界面*/
    function init_data(datas) {
        $("#task-name").text(datas.task_name);
        $("#task-describe").text(datas.task_describe);
        $("#task-secret").text(datas.task_secret);
        $("#task-time-begin").text(datas.task_time_begin.length == 0 ? "无": datas.task_time_begin);
        $("#task-time-end").text(datas.task_time_end);
        $("#task-type").text(datas.task_type);
        $("#task-place").text(datas.task_place);
        $("#task-money").text(datas.task_money);
        $("#task-other-money").text(datas.task_other_money.length == 0 ? "无": datas.task_other_money);
        $("#task-link-select").text(datas.task_link_select);
        $("#task-link-method").text(datas.task_link_select);
        $("#task-link-content").text(datas.task_link_input);
    }

    /*操作html设置图片*/
    function init_image(items){
        var html = "";
        for(var i in items){
            html += "<li class=\"weui-uploader__file\" style=\"background-image:url(" + items[i] + "\")></li>";
        }
        $("#uploaderFiles-num").text(items.length);
        $("#uploaderFiles").empty().append(html);
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