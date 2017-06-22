/**
 * Created by ALISURE on 2017/5/22.
 */
$(function () {

    var link_params = [
        { link_id:1, link_text:"微信号", link_input_type:"text"},
        { link_id:2, link_text:"QQ号", link_input_type:"number"},
        { link_id:3, link_text:"手机号", link_input_type:"number"}
    ];

    /* 状态参数 */
    var status = {
        statusCode:0, /* 返回的状态码 */
        notExist: 3003, /* 任务不存在 */
        except: 3001, /* 发生异常 */

        accept: 3200, /* 接手 */
        modify: 3100, /* 编辑 */
        execute: 3300, /* 进行中 */
        completing: 3302, /* 待确认状态 */
        completed: 3302 /* 已经完成 */
    };

    /* 获取任务ID */
    var taskId = getSearchValue(location.search, "id");

    /* 执行入口 */
    getData(function (datas) {

        /* 预处理数据：处理联系方式 */
        if(status.statusCode == status.accept){ /* 路人 */
            datas.secret_message = "********************";
            datas.task_link_select = link_params[0].link_text;
            datas.task_link_input = "***********";
        }else{ /* 其他情况完全显示联系方式 */
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
        }

        /* 初始化界面 */
        init_data(datas);
        /* 初始化按钮 */
        init_operation();
        /* 初始化图片 */
        init_image(datas.lists);

    });

    function getData(callback_deal_data) {
        $.get( ServerUrl + "task/task/" + taskId, function (data) {
            status.statusCode = data.statusCode;

            if(status.statusCode == status.notExist){
                $.alert("任务不存在");
                history.back();
            }else if(status.statusCode == status.except){
                $.toast(data.statusCode + ":" + data.message, "text");
            }else if(data.myTask){ /* 处理数据 */
                    callback_deal_data(data.myTask);
            }else{
                 $.alert("未知的statusCode：" + status.statusCode);
            }
        });
    }

    /* 初始化界面 */
    function init_data(datas, type) {
        $("#publish-wrapper").show();

        $("#task-name-detail").text(datas.title);
        $("#task-describe-detail").text(datas.content);
        $("#task-secret-detail").text(datas.secret_message);
        $("#task-time-begin-detail").text(datas.pubDate.length == 0 ? "无": datas.pubDate);
        $("#task-time-end-detail").text(datas.endDate);
        $("#task-type-detail").text(datas.type);
        $("#task-place-detail").text(datas.place);
        $("#task-money-detail").text(datas.goldCoins);
        $("#task-other-money-detail").text(datas.reward.length == 0 ? "无": datas.reward);

        /* 设置联系方式 */
        var $select = $("#task-link-select-detail");
        var $content = $("#task-link-content-detail");
        var $method = $("#task-link-method-detail");
        $select.text(datas.task_link_select).parent().show();
        $method.text(datas.task_link_select).parent().show();
        $content.text(datas.task_link_input).parent().show();
    }

    /* 初始化按钮 */
    function init_operation() {
        if(status.statusCode == status.modify){ /*发布人*/
            $("#modify-task").show().click(function () {
                $.confirm("确定要修改吗？", function () {
                    /* 确认是否可以编辑 */
                    service_to_edit(taskId, function () {
                        location.href = "modifyTask.html?id=" + taskId;
                    });
                });
            }).siblings().hide();
        }else if(status.statusCode == status.accept){ /*接收人*/
            $("#accept-task").show().click(function () {
                $.confirm("确定要接手任务吗？", function () {
                    service_accept(taskId);
                });
            }).siblings().hide();
        }else if(status.statusCode == status.execute){ /*进行中*/
            $("#execute-task").addClass("weui-btn_disabled").show().click(function () {
                /*$.alert("进行中");*/
            }).siblings().hide();
        }else if(status.statusCode == status.completing){ /* 待确认状态 */
            $("#completing-task").show().click(function () {
                $.confirm("确定要确认吗？", function () {
                    service_ack(taskId);
                });
            }).siblings().hide();
        }else if(status.statusCode == status.completed){ /* 已完成 */
            $("#completed-task").show().click(function () {
                history.back();
            }).siblings().hide();
        }
    }

    /* 初始化图片 */
    function init_image(items){
        var html = "";
        for(var i in items){
            html += "<li class=\"weui-uploader__file\" style=\"background-image:url(" + items[i] + "\")></li>";
        }
        $("#uploaderFiles-num-detail").text(items.length);
        $("#uploaderFiles-detail").empty().append(html);

        /*设置查看图片*/
        look_image(items);
    }

    /* 查看图片 */
    function look_image(items) {
        /*设置图片浏览*/
        var look_image = $.photoBrowser({
            items: items,
            onSlideChange: function(index) {},
            onOpen: function() {},
            onClose: function() {}
        });

        /*点击图片，进入图片浏览模式*/
        $("#uploaderFiles-detail li").click(function () {
            var index = $(this).index();
            location.hash = "gallery";
            look_image.open(index);
        });

    }

    /* 接手任务 */
    function service_accept(id) {
        $.get(ServerUrl + "task/takeOver/" + id, function (data) {
            if(data.statusCode == 3000){ /* 接手成功 */
                $.toast("接手成功", "text");
                location.reload(); /* 重新加载页面 */
            }else if(data.statusCode == 3007){ /* 接手失败 */
                $.toast("接手失败", "text");
            }else if(data.statusCode == 3019){ /* 任务不存在 */
                $.toast("任务不存在", "text");
                history.back(); /* 返回上一页 */
            }else if(data.statusCode == 3008){ /* 任务已被接手 */
                $.toast("任务已被接手", "text");
                location.back(); /* 返回上一页 */
            }else{
                location.back();
            }
        });
    }
    /* 确认任务完成 */
    function service_ack(id) {
        $.get(ServerUrl + "task/taskStatus/" + id, {
            means: 0
        }, function (data) {
            if(data.statusCode == 3000){ /* 成功 */
                $.toast("确认成功", "text");
                location.reload(); /* 重新加载页面 */
            }else if(data.statusCode == 3001){ /* 失败 */
                $.toast("确认失败", "text");
            }else if(data.statusCode == 3003){ /* 任务不存在 */
                $.toast("任务不存在", "text");
                history.back(); /* 返回上一页 */
            }else if(data.statusCode == 3004){ /* 参数为空 */
                $.toast("确认失败", "text");
                location.back(); /* 返回上一页 */
            }else{
                location.back();
            }
        });
    }
    /* 能否进行编辑 */
    function service_to_edit(id, callback_for_edit) {
        $.get(ServerUrl + "task/toEdit/" + id, function (data) {
            if(data.statusCode == 3011){ /* 可以编辑 */
                callback_for_edit();
            }else if(data.statusCode == 3010){ /* 不能进行编辑 */
                $.toast("不能进行编辑", "text");
            }else if(data.statusCode == 3009){ /* 用户为空 */
                $.toast("用户为空", "text");
            }else if(data.statusCode == 3005){ /* 未使用正确的方式打开 */
                $.toast("未使用正确的方式打开", "text");
                location.back();
            }else{
                $.toast("未知错误原因：statusCode=", data.statusCode);
                location.back();
            }
        });
    }
});