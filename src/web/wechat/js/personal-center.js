/**
 * Created by fengziboboy on 2017/5/22.
 */
/*
 * 个人中心
 * */
$(function() {

    // 个人资料跳转
    $(function() {

        clickEvent();

        /*设置点击事件*/
        function clickEvent() {
            /*点击头像*/
            $(".personal-main-top-user .personal-main-top-user-img").off("click").on("click", function() {
                var id = $(this).attr("data-id");
                location.href = "user-show.html?id=" + id;
            });

            /*点击学校*/
            $("#user_school").off("click").on("click", function() {
                var id = $(this).attr("data-id");
                location.href = "school-picker.html?id=" + id;
            });

            /*点击账户值*/
            $(".personal-main-top-money").off("click").on("click", function() {
                var id = $(this).attr("data-id");
                location.href = "money.html?id=" + id;
            });
        }

    })

    /* 切换 任务和发布 */
    var ITEM_ON = "personal-main-top-navbar-item-on";

    var showTab = function(a) {
        var $a = $(a);
        if ($a.hasClass(ITEM_ON)) return;
        var href = $a.attr("href");

        if (!/^#/.test(href)) return;

        $a.parent().find("." + ITEM_ON).removeClass(ITEM_ON);
        $a.addClass(ITEM_ON);

        var bd = $a.parents(".weui-tab").find(".personal-main-bottom");

        bd.find(".personal-task--active").removeClass("personal-task--active");

        $(href).addClass("personal-task--active");
    }

    $.showTab = showTab;

    $(document).on("click", ".personal-main-top-navbar-item", function(e) {
        var $a = $(e.currentTarget);
        var href = $a.attr("href");
        if ($a.hasClass(ITEM_ON)) return;
        if (!/^#/.test(href)) return;

        e.preventDefault();

        showTab($a);
    });



    /*
     * 我的任务列表加载
     * */
    $(function() {

        var page = 1;
        var page_size = 5;
        var loading = true; //状态标记

        /*初始化任务列表*/
        initTaskList();

        /*初始化任务列表*/
        function initTaskList() {
            /*初始化*/
            initTaskGetTaskByPage();

            $("#tab3").infinite(200).on("infinite", function() {
                if (loading) return;
                page += 1;
                initTaskGetTaskByPage();
            });

        }

        function initTaskGetTaskByPage() {
            loading = true;
            if (page == 1) {
                $("#user-task-list").empty();
            }
            $("#user-task-load-more").show();
            getTaskByPage(page, page_size, function() {
                loading = false;
                $("#user-task-load-more").hide();
            });
        }


        function getTaskByPage(page, size, callback) {

            setTimeout(function() {
                /*联网获取数据*/
                var stutusTypes = [
                    { typeId: 1, typeImage: "../img/personCenter/wancheng.png", typeName: "已完成" },
                    { typeId: 2, typeImage: "../img/personCenter/jinxingzhong.png", typeName: "进行中" },
                    { typeId: 3, typeImage: "../img/index/task-type-3.png", typeName: "应用" },
                    { typeId: 4, typeImage: "../img/index/task-type-4.png", typeName: "生活" },
                    { typeId: 5, typeImage: "../img/index/task-type-5.png", typeName: "其他" },
                ];
                var datas = [{
                        status_image: "../img/personCenter/wancheng.png",
                        status_name: "已完成",
                        task_type: "生活",
                        task_title: "取快递gg取快递gg取快递gg取快递gg取快递gg取快递gg",
                        task_publish_time: "2017-04-16 12:20",
                        task_describe: "中午25点后在西东门口，编号616，联系电话：110",
                        task_place: "西大楼zvs fgzg sdv fwf",
                    },
                    {
                        status_image: "../img/personCenter/wancheng.png",
                        status_name: "已完成",
                        task_type: "生活",
                        task_title: "取快递",
                        task_publish_time: "2017-04-16 15:20",
                        task_describe: "中午25点后在西东门口，中通镖局，联系电话：110",
                        task_place: "西大楼",
                    },
                    {
                        status_image: "../img/personCenter/jinxingzhong.png",
                        status_name: "进行中",
                        task_type: "生活",
                        task_title: "取快递",
                        task_publish_time: "2017-04-16 18:20",
                        task_describe: "中午25点后在西东门口，中午25点后在西东门口，" +
                            "中午25点后在西东门口，中午25点后在西东门口，中通镖局，编号616",
                        task_place: "西大楼vs我国",
                    },
                    {
                        status_image: "../img/personCenter/wancheng.png",
                        status_name: "已完成",
                        task_type: "生活",
                        task_title: "取快递",
                        task_publish_time: "2017-04-16 11:20",
                        task_describe: "中通镖局，编号616，联系电话：110",
                        task_place: "西大楼申达股份",
                    },
                    {
                        status_image: "../img/personCenter/wancheng.png",
                        status_name: "已完成",
                        task_type: "生活",
                        task_title: "取快递",
                        task_publish_time: "2017-04-16 18:20",
                        task_describe: "中午25点后在西东门口，中午25点后在西东门口，" +
                            "中午25点后在西东门口，中午25点后在西东门口，中通镖局，编号616",
                        task_place: "西大楼vs我国",
                    },
                    {
                        status_image: "../img/personCenter/jinxingzhong.png",
                        status_name: "进行中",
                        task_type: "生活",
                        task_title: "取快递",
                        task_publish_time: "2017-04-16 18:20",
                        task_describe: "中午25点后在西东门口，中午25点后在西东门口，" +
                            "中午25点后在西东门口，中午25点后在西东门口，中通镖局，编号616",
                        task_place: "西大楼vs我国",
                    },
                    {
                        status_image: "../img/personCenter/wancheng.png",
                        status_name: "已完成",
                        task_type: "生活",
                        task_title: "取快递",
                        task_publish_time: "2017-04-16 11:20",
                        task_describe: "中通镖局，编号616，联系电话：110",
                        task_place: "西大楼申达股份",
                    },
                    {
                        status_image: "../img/personCenter/jinxingzhong.png",
                        status_name: "进行中",
                        task_type: "生活",
                        task_title: "取快递",
                        task_publish_time: "2017-04-16 11:20",
                        task_describe: "中通镖局，编号616，联系电话：110",
                        task_place: "西大楼申达股份",
                    },
                    {
                        status_image: "../img/personCenter/wancheng.png",
                        status_name: "已完成",
                        task_type: "生活",
                        task_title: "取快递",
                        task_publish_time: "2017-04-16 18:20",
                        task_describe: "中午25点后在西东门口，中午25点后在西东门口，" +
                            "中午25点后在西东门口，中午25点后在西东门口，中通镖局，编号616",
                        task_place: "西大楼vs我国",
                    },
                    {
                        status_image: "../img/personCenter/jinxingzhong.png",
                        status_name: "进行中",
                        task_type: "生活",
                        task_title: "取快递",
                        task_publish_time: "2017-04-16 11:20",
                        task_describe: "中通镖局，编号616，联系电话：110",
                        task_place: "西大楼申达股份",
                    },
                    {
                        status_image: "../img/personCenter/wancheng.png",
                        status_name: "已完成",
                        task_type: "生活",
                        task_title: "取快递",
                        task_publish_time: "2017-04-16 18:20",
                        task_describe: "中午25点后在西东门口，中午25点后在西东门口，" +
                            "中午25点后在西东门口，中午25点后在西东门口，中通镖局，编号616",
                        task_place: "西大楼vs我国",
                    },
                    {
                        status_image: "../img/personCenter/jinxingzhong.png",
                        status_name: "进行中",
                        task_type: "生活",
                        task_title: "取快递",
                        task_publish_time: "2017-04-16 11:20",
                        task_describe: "中通镖局，编号616，联系电话：110",
                        task_place: "西大楼申达股份",
                    },
                ];
                var types = [
                    { typeId: 1, typeImage: "../img/index/task-type-1.png", typeName: "学习" },
                    { typeId: 2, typeImage: "../img/index/task-type-2.png", typeName: "娱乐" },
                    { typeId: 3, typeImage: "../img/index/task-type-3.png", typeName: "应用" },
                    { typeId: 4, typeImage: "../img/index/task-type-4.png", typeName: "生活" },
                    { typeId: 5, typeImage: "../img/index/task-type-5.png", typeName: "其他" },
                    { typeId: 6, typeImage: "../img/index/task-type-2.png", typeName: "学习" },
                    { typeId: 7, typeImage: "../img/index/task-type-1.png", typeName: "娱乐" },
                    { typeId: 8, typeImage: "../img/index/task-type-3.png", typeName: "应用" },
                    { typeId: 9, typeImage: "../img/index/task-type-4.png", typeName: "生活" },
                    { typeId: 10, typeImage: "../img/index/task-type-5.png", typeName: "其他" }
                ];

                var $goods = $("#user-task-list");
                /*刷新*/
                if (page == 1) $goods.empty();

                for (var i = 0; i < datas.length; i++) {
                    /*需要将数据传给build()*/
                    var item = build(datas[i]);
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
            var $html = $("#userTaskData");
            $html.find(".status-image img").attr("src", data.status_image);
            $html.find(".status-name").text(data.status_name);
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
            $("#userTaskWrapper .user-goods-wrapper .personal-item-content .personal-item-content-body").off("click").on("click", function() {
                var id = $(this).attr("data-id");
                location.href = "task.html?id=" + id;
            });

            /*点击头像*/
            $("#userTaskWrapper .user-goods-wrapper .personal-item-content .personal-item-content-body .bottom .operation").off("click").on("click", function() {
                alert("删除？");
            });
        }
    });


    /* * * * * * * * * * *
     * 我的发布列表加载
     * * * * * * * * * */
    $(function() {

        var page = 1;
        var page_size = 5;
        var loading = true; //状态标记

        /*初始化发布列表*/
        initPublishList();

        /*初始化发布列表*/
        function initPublishList() {
            /*初始化*/
            initPublishGetPublishByPage();

            $("#tab3").infinite(200).on("infinite", function() {
                if (loading) return;
                page += 1;
                initPublishGetPublishByPage();
            });

        }

        function initPublishGetPublishByPage() {
            loading = true;
            if (page == 1) {
                $("#user-publish-list").empty();
            }
            $("#user-publish-load-more").show();
            getPublishByPage(page, page_size, function() {
                loading = false;
                $("#user-publish-load-more").hide();
            });
        }


        function getPublishByPage(page, size, callback) {

            setTimeout(function() {
                /*联网获取数据*/
                var stutusTypes = [
                    { typeId: 1, typeImage: "../img/personCenter/wancheng.png", typeName: "已完成" },
                    { typeId: 2, typeImage: "../img/personCenter/jinxingzhong.png", typeName: "进行中" },
                    { typeId: 3, typeImage: "../img/index/task-type-3.png", typeName: "应用" },
                    { typeId: 4, typeImage: "../img/index/task-type-4.png", typeName: "生活" },
                    { typeId: 5, typeImage: "../img/index/task-type-5.png", typeName: "其他" },
                ];
                var datas = [{
                        status_image: "../img/personCenter/wancheng.png",
                        status_name: "已完成",
                        task_type: "生活",
                        task_title: "取快递gg取快递gg取快递gg取快递gg取快递gg取快递gg",
                        task_publish_time: "2017-04-16 12:20",
                        task_describe: "中午25点后在西东门口，编号616，联系电话：110",
                        task_place: "西大楼zvs fgzg sdv fwf",
                    },
                    {
                        status_image: "../img/personCenter/wancheng.png",
                        status_name: "已完成",
                        task_type: "生活",
                        task_title: "取快递",
                        task_publish_time: "2017-04-16 15:20",
                        task_describe: "中午25点后在西东门口，中通镖局，联系电话：110",
                        task_place: "西大楼",
                    },
                    {
                        status_image: "../img/personCenter/jinxingzhong.png",
                        status_name: "进行中",
                        task_type: "生活",
                        task_title: "取快递",
                        task_publish_time: "2017-04-16 18:20",
                        task_describe: "中午25点后在西东门口，中午25点后在西东门口，" +
                            "中午25点后在西东门口，中午25点后在西东门口，中通镖局，编号616",
                        task_place: "西大楼vs我国",
                    },
                    {
                        status_image: "../img/personCenter/wancheng.png",
                        status_name: "已完成",
                        task_type: "生活",
                        task_title: "取快递",
                        task_publish_time: "2017-04-16 11:20",
                        task_describe: "中通镖局，编号616，联系电话：110",
                        task_place: "西大楼申达股份",
                    },
                    {
                        status_image: "../img/personCenter/wancheng.png",
                        status_name: "已完成",
                        task_type: "生活",
                        task_title: "取快递",
                        task_publish_time: "2017-04-16 18:20",
                        task_describe: "中午25点后在西东门口，中午25点后在西东门口，" +
                            "中午25点后在西东门口，中午25点后在西东门口，中通镖局，编号616",
                        task_place: "西大楼vs我国",
                    },
                    {
                        status_image: "../img/personCenter/jinxingzhong.png",
                        status_name: "进行中",
                        task_type: "生活",
                        task_title: "取快递",
                        task_publish_time: "2017-04-16 18:20",
                        task_describe: "中午25点后在西东门口，中午25点后在西东门口，" +
                            "中午25点后在西东门口，中午25点后在西东门口，中通镖局，编号616",
                        task_place: "西大楼vs我国",
                    },
                    {
                        status_image: "../img/personCenter/wancheng.png",
                        status_name: "已完成",
                        task_type: "生活",
                        task_title: "取快递",
                        task_publish_time: "2017-04-16 11:20",
                        task_describe: "中通镖局，编号616，联系电话：110",
                        task_place: "西大楼申达股份",
                    },
                    {
                        status_image: "../img/personCenter/jinxingzhong.png",
                        status_name: "进行中",
                        task_type: "生活",
                        task_title: "取快递",
                        task_publish_time: "2017-04-16 11:20",
                        task_describe: "中通镖局，编号616，联系电话：110",
                        task_place: "西大楼申达股份",
                    },
                    {
                        status_image: "../img/personCenter/wancheng.png",
                        status_name: "已完成",
                        task_type: "生活",
                        task_title: "取快递",
                        task_publish_time: "2017-04-16 18:20",
                        task_describe: "中午25点后在西东门口，中午25点后在西东门口，" +
                            "中午25点后在西东门口，中午25点后在西东门口，中通镖局，编号616",
                        task_place: "西大楼vs我国",
                    },
                    {
                        status_image: "../img/personCenter/jinxingzhong.png",
                        status_name: "进行中",
                        task_type: "生活",
                        task_title: "取快递",
                        task_publish_time: "2017-04-16 11:20",
                        task_describe: "中通镖局，编号616，联系电话：110",
                        task_place: "西大楼申达股份",
                    },
                    {
                        status_image: "../img/personCenter/wancheng.png",
                        status_name: "已完成",
                        task_type: "生活",
                        task_title: "取快递",
                        task_publish_time: "2017-04-16 18:20",
                        task_describe: "中午25点后在西东门口，中午25点后在西东门口，" +
                            "中午25点后在西东门口，中午25点后在西东门口，中通镖局，编号616",
                        task_place: "西大楼vs我国",
                    },
                    {
                        status_image: "../img/personCenter/jinxingzhong.png",
                        status_name: "进行中",
                        task_type: "生活",
                        task_title: "取快递",
                        task_publish_time: "2017-04-16 11:20",
                        task_describe: "中通镖局，编号616，联系电话：110",
                        task_place: "西大楼申达股份",
                    },
                ];
                var types = [
                    { typeId: 1, typeImage: "../img/index/task-type-1.png", typeName: "学习" },
                    { typeId: 2, typeImage: "../img/index/task-type-2.png", typeName: "娱乐" },
                    { typeId: 3, typeImage: "../img/index/task-type-3.png", typeName: "应用" },
                    { typeId: 4, typeImage: "../img/index/task-type-4.png", typeName: "生活" },
                    { typeId: 5, typeImage: "../img/index/task-type-5.png", typeName: "其他" },
                    { typeId: 6, typeImage: "../img/index/task-type-2.png", typeName: "学习" },
                    { typeId: 7, typeImage: "../img/index/task-type-1.png", typeName: "娱乐" },
                    { typeId: 8, typeImage: "../img/index/task-type-3.png", typeName: "应用" },
                    { typeId: 9, typeImage: "../img/index/task-type-4.png", typeName: "生活" },
                    { typeId: 10, typeImage: "../img/index/task-type-5.png", typeName: "其他" }
                ];

                var $goods = $("#user-publish-list");
                /*刷新*/
                if (page == 1) $goods.empty();

                for (var i = 0; i < datas.length; i++) {
                    /*需要将数据传给build()*/
                    var item = build(datas[i]);
                    $goods.append(item);
                }

                /*设置点击事件*/
                clickEvent();

                callback();
            }, 1000);
        }

        /*在这里组装item*/
        /*为了解耦和，将发布item放到了html中，在这里把数据放到模板中，然后再把模板放到适当的位置*/
        function build(data) {
            /*对data进行处理*/
            var $html = $("#userPublishData");
            $html.find(".status-image img").attr("src", data.status_image);
            $html.find(".status-name").text(data.status_name);
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
            $("#userPublishWrapper .user-goods-wrapper .personal-item-content .personal-item-content-body").off("click").on("click", function() {
                var id = $(this).attr("data-id");
                location.href = "publish.html?id=" + id;
            });

            /*点击头像*/
            $("#userPublishWrapper .user-goods-wrapper .personal-item-content .personal-item-content-body .bottom .operation").off("click").on("click", function() {
                alert("删除？");
            });
        }
    });

});