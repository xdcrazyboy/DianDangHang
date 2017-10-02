/**
 * Created by ALISURE on 2017/4/18.
 */
$(function () {

    var page = 1;
    var page_size = 12;

    /*初始化*/
    getTaskByPage(page, page_size,function () {

    });

    /*刷新初始化*/
    refresher.init({
        id: "wrapper",
        pullDownAction: Refresh,
        pullUpAction: Load
    });
    function Refresh() {
        page = 1;
        getTaskByPage(page, page_size, function () {
            /*第一页*/
            wrapper.refresh();
        });
    }
    function Load() {
        page += 1;
        getTaskByPage(page, page_size, function () {
            /*下一页*/
            wrapper.refresh();
        });
    }

    function getTaskByPage(page, size, callback) {
        /*联网获取数据*/
        var datas = [
            {
                time:"2017-05-08 10:10",
                type:"../img/user-image.png",
                number:"10",
                describe:"取快递获得的金币"
            },
            {
                time:"2017-05-08 10:10",
                type:"../img/user-image.png",
                number:"10",
                describe:"取快递获得的金币"
            },
            {
                time:"2017-05-08 10:10",
                type:"../img/user-image.png",
                number:"10",
                describe:"取快递获得的金币"
            },
            {
                time:"2017-05-08 10:10",
                type:"../img/user-image.png",
                number:"10",
                describe:"取快递获得的金币"
            },
            {
                time:"2017-05-08 10:10",
                type:"../img/user-image.png",
                number:"10",
                describe:"取快递获得的金币"
            },
            {
                time:"2017-05-08 10:10",
                type:"../img/user-image.png",
                number:"10",
                describe:"取快递获得的金币"
            },
            {
                time:"2017-05-08 10:10",
                type:"../img/user-image.png",
                number:"10",
                describe:"取快递获得的金币"
            },
            {
                time:"2017-05-08 10:10",
                type:"../img/user-image.png",
                number:"10",
                describe:"取快递获得的金币"
            },
            {
                time:"2017-05-08 10:10",
                type:"../img/user-image.png",
                number:"10",
                describe:"取快递获得的金币"
            },
            {
                time:"2017-05-08 10:10",
                type:"../img/user-image.png",
                number:"10",
                describe:"取快递获得的金币"
            },
        ];

        var $goods = $("#wrapper ul");
        /*刷新*/
        if(page == 1)$goods.empty();

        for (var i = 0; i < datas.length; i++) {
            /*需要将数据传给build()*/
            var item = build(datas[i]);
            $goods.append(item);
        }

        /*设置点击事件*/
        clickEvent();

        setTimeout(function () {
            callback();
        }, 1000);
    }

    /*在这里组装item*/
    /*为了解耦和，将任务item放到了html中，在这里把数据放到模板中，然后再把模板放到适当的位置*/
    function build(data) {
        /*对data进行处理*/
        var $html = $("#data");
        var time = data.time;
        if(time.length == 16){
            $html.find(".top").text(time.substr(5, 5));
            $html.find(".time").text(time.substr(11, 5));
        }
        $html.find(".type img").attr("src",data.type);
        $html.find(".type").text(data.task_type);
        $html.find(".number").text(data.number);
        $html.find(".describe").text(data.describe);
        return $html.html();
    }

    /*设置点击事件*/
    function clickEvent() {
        /*点击goods*/
        $("#wrapper .goods-wrapper .content .right-goods").off("click").on("click", function () {
            var id = $(this).attr("data-id");
            alert("点击任务：" + id);
        });

        /*点击头像*/
        $("#wrapper .goods-wrapper .content .left-user .image").off("click").on("click", function () {
            var user = $(this).parent().attr("data-user");
            alert("点击用户：" + user);
        });
    }

});
