/**
 * Created by ALISURE on 2017/5/17.
 */
/*
* 任务类型和任务列表
* */
$(function () {

    var page = 1;
    var page_size = 12;
    var type_id = 0;
    var sort_id = 1;
    var loading = true;  //状态标记
    var over = false;  // 当前类型没有任务了

    /*初始化排序*/
    initSort();
    /*先初始化类型，完成后初始化任务列表*/
    initTaskType(function () {
        initTaskList();
    },function () {
        initTypeClick();
    });

    /*初始化排序*/
    function initSort() {
        /* 点击排序 */
        $("#indexSort div").click(function () {
            var sort_id_now = $(this).attr("data-id");
            if(sort_id_now == sort_id) return;
            sort_id = sort_id_now;
            $(this).addClass("current").siblings().removeClass("current");
            page = 1;
            over = false;
            initTaskGetTaskByPage();
        });

        /* 点击刷新 */
        $("#indexSortRight .page-refresh").click(function () {
            page = 1;
            over = false;
            initTaskGetTaskByPage();
        });
    }

    /*初始化类型*/
    function initTaskType(callback_initTaskList, callback_typeClick){
        /*获取类型数据*/
        $.get(ServerUrl + "task/category", function (data) {
            if(data.statusCode == 3000){
                var types = data.myCategories;
                /*初始化类型*/
                var $sorHtml = $("#indexTaskTypeData");
                var $desHtml = $("#indexTaskTypeDataWrapper").empty();
                for (var i in types){
                    $sorHtml.children("div").attr("data-id", types[i].id);
                    //$sorHtml.find("img").attr("src", "../img/index/task-type-" + (parseInt(i) + 1) + ".png");
                    $sorHtml.find("img").attr("src", types[i].catgory_icon);
                    $sorHtml.find("p").text(types[i].catgory);
                    $desHtml.append($sorHtml.html());
                }
                $desHtml.children().eq(0).addClass("current");
                type_id = types[0].id;

                /*初始化类型之后，执行回调*/
                callback_initTaskList();
                /*设置类型点击事件*/
                callback_typeClick();
            }else{
                $.toast("数据获取错误");
            }
        });
    }

    /*设置点击事件*/
    function initTypeClick() {
        /* 点击类型 */
        $("#indexTaskTypeDataWrapper").children("div").on("click", function () {
            var id = $(this).attr("data-id");
            if(id == type_id) return;
            type_id = id;
            $(this).addClass("current").siblings().removeClass("current");
            /*刷新*/
            page = 1;
            over = false;
            initTaskGetTaskByPage();
        });
    }

    /*初始化任务列表*/
    function initTaskList() {
        /*初始化*/
        initTaskGetTaskByPage();

        $("#tab1").infinite().on("infinite", function() {
            /*如果当前类型没有结束，则继续*/
            if(!over){
                if(loading) return;
                page += 1;
                initTaskGetTaskByPage();
            }
        });

    }

    function initTaskGetTaskByPage() {
        loading = true;
        if(page == 1){
            $("#task-list").empty();
        }
        $("#task-load-more").show();
        getTaskByPage(page, page_size, type_id, sort_id, function () {
            loading = false;
            $("#task-load-more").hide();
        });
    }

    /* 获取数据并显示 */
    function getTaskByPage(page, size, type_id, sort_id, callback) {
        if(over){
            callback();
        }
        
        /*联网获取数据*/
        $.get( ServerUrl + "task/taskhall", {
            page: page,
            pageSize: size,
            type: type_id,
            sortType: sort_id
        }, function (data) {
            if(data.statusCode == 3000){
                var datas = data.myTaskList;

                var $goods = $("#task-list");
                /*刷新*/
                if(page == 1)$goods.empty();

                for (var i = 0; i < datas.length; i++) {
                    /*需要将数据传给build()*/
                    var item = build(datas[i]);
                    $goods.append(item);
                }

                over = false;
                /*设置点击事件*/
                clickEvent();

            }else/* if(data.statusCode == 3002)*/{
                /* 显示没有任务了 */
                /* data.message */
                $.toast("没有更多任务了", "text");
                over = true;
            }
            callback();
        });
    }

    /*在这里组装item*/
    /*为了解耦和，将任务item放到了html中，在这里把数据放到模板中，然后再把模板放到适当的位置*/
    function build(data) {
        /*对data进行处理*/
        var $html = $("#taskData");
        $html.find(".image img").attr("src",data.pub_icon);
        $html.find(".money span").text(data.goldCoins);
        $html.find(".name").text(subString(data.pub_nickname, 4, "..."));
        $html.find(".type").text(data.type);
        $html.find(".title").text(subString(data.title, 10, "..."));
        $html.find(".time-content").text(parseTime(data.pubDate));
        $html.find(".describe p").text(data.content);
        $html.find(".place span").text(subString(data.place, 20, "..."));
        $html.find(".right-goods").attr("data-id", data.id);
        $html.find(".left-user").attr("data-user", data.userid);
        return $html.html();
    }

    function parseTime(time) {
        var date = new Date(time);

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

    /*设置点击事件*/
    function clickEvent() {
        /*点击goods*/
        $("#taskWrapper .goods-wrapper .content .right-goods").off("click").on("click", function () {
            var id = $(this).attr("data-id");
            location.href = "task.html?id=" + id;
        });

        /*点击头像*/
        $("#taskWrapper .goods-wrapper .content .left-user .image").off("click").on("click", function () {
            var user = $(this).parent().attr("data-user");
            location.href = "user-show.html?id=" + user;
        });
    }
});