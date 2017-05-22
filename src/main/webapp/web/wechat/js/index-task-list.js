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
        $("#indexSort div").click(function () {
            var sort_id_now = $(this).attr("data-id");
            if(sort_id_now == sort_id) return;
            sort_id = sort_id_now;
            page = 1;
            $(this).addClass("current").siblings().removeClass("current");
            initTaskGetTaskByPage();
        });
        $("#indexSortRight .page-refresh").click(function () {
            page = 1;
            initTaskGetTaskByPage();
        });
    }

    /*初始化类型*/
    function initTaskType(callback_initTaskList, callback_typeClick){
        /*获取类型数据*/
        var types = [
            {typeId:1,typeImage:"../img/index/task-type-1.png",typeName:"学习"},
            {typeId:2,typeImage:"../img/index/task-type-2.png",typeName:"娱乐"},
            {typeId:3,typeImage:"../img/index/task-type-3.png",typeName:"应用"},
            {typeId:4,typeImage:"../img/index/task-type-4.png",typeName:"生活"},
            {typeId:5,typeImage:"../img/index/task-type-5.png",typeName:"其他"},
            {typeId:6,typeImage:"../img/index/task-type-2.png",typeName:"学习"},
            {typeId:7,typeImage:"../img/index/task-type-1.png",typeName:"娱乐"},
            {typeId:8,typeImage:"../img/index/task-type-3.png",typeName:"应用"},
            {typeId:9,typeImage:"../img/index/task-type-4.png",typeName:"生活"},
            {typeId:10,typeImage:"../img/index/task-type-5.png",typeName:"其他"}
        ];

        /*初始化类型*/
        var $sorHtml = $("#indexTaskTypeData");
        var $desHtml = $("#indexTaskTypeDataWrapper").empty();
        for (var i in types){
            $sorHtml.children("div").attr("data-id", types[i].typeId);
            $sorHtml.find("img").attr("src", types[i].typeImage);
            $sorHtml.find("p").text(types[i].typeName);
            $desHtml.append($sorHtml.html());
        }
        $desHtml.children().eq(0).addClass("current");
        type_id = types[0].typeId;

        /*初始化类型之后，执行回调*/
        callback_initTaskList();
        /*设置类型点击事件*/
        callback_typeClick();
    }

    /*设置点击事件*/
    function initTypeClick() {
        $("#indexTaskTypeDataWrapper").children("div").on("click", function () {
            var id = $(this).attr("data-id");
            if(id == type_id) return;
            type_id = id;
            $(this).addClass("current").siblings().removeClass("current");
            /*刷新*/
            page = 1;
            initTaskGetTaskByPage();
        });
    }

    /*初始化任务列表*/
    function initTaskList() {
        /*初始化*/
        initTaskGetTaskByPage();

        $("#tab1").infinite().on("infinite", function() {
            if(loading) return;
            page += 1;
            initTaskGetTaskByPage();
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


    function getTaskByPage(page, size, type_id, sort_id, callback) {

        setTimeout(function () {
            /*联网获取数据*/
            var datas = [
                {
                    user_image:"../../resources/image/userHead/user_head_img_12.png",
                    user_money:"65",
                    user_name:"会",
                    task_type:"生活",
                    task_title:"取快递gg取快递gg取快递gg取快递gg取快递gg取快递gg",
                    task_publish_time:"2017-04-16 12:20",
                    task_describe:"中午25点后在西东门口，编号616，联系电话：110",
                    task_place:"西大楼zvs fgzg sdv fwf",
                },
                {
                    user_image:"../../resources/image/userHead/user_head_img_11.png",
                    user_money:"634",
                    user_name:"会跑",
                    task_type:"生活",
                    task_title:"取快递",
                    task_publish_time:"2017-04-16 15:20",
                    task_describe:"中午25点后在西东门口，中通镖局，联系电话：110",
                    task_place:"西大楼",
                },
                {
                    user_image:"../../resources/image/userHead/user_head_img_1.png",
                    user_money:"65",
                    user_name:"会跑的",
                    task_type:"生活",
                    task_title:"取快递",
                    task_publish_time:"2017-04-16 18:20",
                    task_describe:"中午25点后在西东门口，中午25点后在西东门口，" +
                    "中午25点后在西东门口，中午25点后在西东门口，中通镖局，编号616",
                    task_place:"西大楼vs我国",
                },
                {
                    user_image:"../../resources/image/userHead/user_head_img_2.png",
                    user_money:"65",
                    user_name:"会跑的鱼",
                    task_type:"生活",
                    task_title:"取快递",
                    task_publish_time:"2017-04-16 11:20",
                    task_describe:"中通镖局，编号616，联系电话：110",
                    task_place:"西大楼申达股份",
                },
                {
                    user_image:"../../resources/image/userHead/user_head_img_3.png",
                    user_money:"65",
                    user_name:"会跑的",
                    task_type:"生活",
                    task_title:"取快递",
                    task_publish_time:"2017-04-16 18:20",
                    task_describe:"中午25点后在西东门口，中午25点后在西东门口，" +
                    "中午25点后在西东门口，中午25点后在西东门口，中通镖局，编号616",
                    task_place:"西大楼vs我国",
                },
                {
                    user_image:"../../resources/image/userHead/user_head_img_4.png",
                    user_money:"65",
                    user_name:"会跑的",
                    task_type:"生活",
                    task_title:"取快递",
                    task_publish_time:"2017-04-16 18:20",
                    task_describe:"中午25点后在西东门口，中午25点后在西东门口，" +
                    "中午25点后在西东门口，中午25点后在西东门口，中通镖局，编号616",
                    task_place:"西大楼vs我国",
                },
                {
                    user_image:"../../resources/image/userHead/user_head_img_5.png",
                    user_money:"65",
                    user_name:"会跑的鱼",
                    task_type:"生活",
                    task_title:"取快递",
                    task_publish_time:"2017-04-16 11:20",
                    task_describe:"中通镖局，编号616，联系电话：110",
                    task_place:"西大楼申达股份",
                },
                {
                    user_image:"../../resources/image/userHead/user_head_img_6.png",
                    user_money:"65",
                    user_name:"会跑的鱼",
                    task_type:"生活",
                    task_title:"取快递",
                    task_publish_time:"2017-04-16 11:20",
                    task_describe:"中通镖局，编号616，联系电话：110",
                    task_place:"西大楼申达股份",
                },
                {
                    user_image:"../../resources/image/userHead/user_head_img_7.png",
                    user_money:"65",
                    user_name:"会跑的",
                    task_type:"生活",
                    task_title:"取快递",
                    task_publish_time:"2017-04-16 18:20",
                    task_describe:"中午25点后在西东门口，中午25点后在西东门口，" +
                    "中午25点后在西东门口，中午25点后在西东门口，中通镖局，编号616",
                    task_place:"西大楼vs我国",
                },
                {
                    user_image:"../../resources/image/userHead/user_head_img_8.png",
                    user_money:"65",
                    user_name:"会跑的鱼",
                    task_type:"生活",
                    task_title:"取快递",
                    task_publish_time:"2017-04-16 11:20",
                    task_describe:"中通镖局，编号616，联系电话：110",
                    task_place:"西大楼申达股份",
                },
                {
                    user_image:"../../resources/image/userHead/user_head_img_9.png",
                    user_money:"65",
                    user_name:"会跑的",
                    task_type:"生活",
                    task_title:"取快递",
                    task_publish_time:"2017-04-16 18:20",
                    task_describe:"中午25点后在西东门口，中午25点后在西东门口，" +
                    "中午25点后在西东门口，中午25点后在西东门口，中通镖局，编号616",
                    task_place:"西大楼vs我国",
                },
                {
                    user_image:"../../resources/image/userHead/user_head_img_10.png",
                    user_money:"65",
                    user_name:"会跑的鱼",
                    task_type:"生活",
                    task_title:"取快递",
                    task_publish_time:"2017-04-16 11:20",
                    task_describe:"中通镖局，编号616，联系电话：110",
                    task_place:"西大楼申达股份",
                },
            ];
            var types = [
                {typeId:1,typeImage:"../img/index/task-type-1.png",typeName:"学习"},
                {typeId:2,typeImage:"../img/index/task-type-2.png",typeName:"娱乐"},
                {typeId:3,typeImage:"../img/index/task-type-3.png",typeName:"应用"},
                {typeId:4,typeImage:"../img/index/task-type-4.png",typeName:"生活"},
                {typeId:5,typeImage:"../img/index/task-type-5.png",typeName:"其他"},
                {typeId:6,typeImage:"../img/index/task-type-2.png",typeName:"学习"},
                {typeId:7,typeImage:"../img/index/task-type-1.png",typeName:"娱乐"},
                {typeId:8,typeImage:"../img/index/task-type-3.png",typeName:"应用"},
                {typeId:9,typeImage:"../img/index/task-type-4.png",typeName:"生活"},
                {typeId:10,typeImage:"../img/index/task-type-5.png",typeName:"其他"}
            ];

            var $goods = $("#task-list");
            /*刷新*/
            if(page == 1)$goods.empty();

            for (var i = 0; i < datas.length; i++) {
                /*需要将数据传给build()*/
                datas[(i + type_id) % datas.length].task_type = types[type_id -1].typeName;
                var item = build(datas[(i + type_id) % datas.length]);
                $goods.append(item);
            }

            /*设置点击事件*/
            clickEvent();

            callback();
        }, 1000);
    }

    /*在这里组装item*/
    /*为了解耦和，将任务item放到了html中，在这里把数据放到模板中，然后再把模板放到适当的位置*/
    function build(data) {
        /*对data进行处理*/
        var $html = $("#taskData");
        $html.find(".image img").attr("src",data.user_image);
        $html.find(".money span").text(data.user_money);
        $html.find(".name").text(data.user_name);
        $html.find(".type").text(data.task_type);
        $html.find(".title").text(data.task_title.length > 10 ? data.task_title.substr(0, 10) + "..." : data.task_title);
        $html.find(".time-content").text(data.task_publish_time);
        $html.find(".describe p").text(data.task_describe);
        $html.find(".place span").text(data.task_place);
        return $html.html();
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
            alert("点击用户：" + user);
        });
    }
});