/**
 * Created by ALISURE on 2017/4/18.
 */
$(function () {

    var page = 1;
    var page_size = 12;
    var loading = true;  //状态标记
    var over = false;  // 当前类型没有任务了
    var searchText = getSearchValue(location.search, "search"); /* 获取搜索关键字 */

    /*初始化 第一页：page=1*/
    Load();

    /* 加载 */
    $(document.body).infinite().on("infinite", function() {
        /* 如果没有加载完且没有正在加载，则继续加载，否则直接返回*/
        if(!over && !loading){
            Load();
        }
    });

    /* 加载下一页*/
    function Load() {
        loading = true;
        if(page == 1)$("#wrapper ul").empty();
        $("#task-load-more").show();
        getTaskByPage(page, page_size, function () {
            page += 1;
            loading = false;
            $("#task-load-more").hide();
        });
    }

    /* 获取第page页，并显示*/
    function getTaskByPage(page, size, callback) {
        /*联网获取数据*/
        $.get(ServerUrl + "task/search/" + searchText,{
            page: page,
            size: size
        },function (data) {
            if(data.statusCode == 3000){
                if(data.myTaskList.length == 0){ /* 表明已经没有任务了 */
                    if(page == 1){
                        $("#search-empty").show();
                    }else{
                        $.toast("没有更多了", "text")
                    }
                    over = true;
                }else{ /* 有任务 */
                    showResult(data.myTaskList);
                }
            }else{ /* 表明出现问题... */
                over = true;
                $.alert(data.statusCode + " " + data.message);
            }

            /* 执行完成后的回掉 */
            callback();
        });
    }

    /* 获取数据后展示 */
    function showResult(datas) {
        var $goods = $("#wrapper ul");
        for (var i = 0; i < datas.length; i++) {
            var item = build(datas[i]);
            $goods.append(item);
        }
        /*设置点击事件*/
        clickEvent();
    }

    /*在这里组装item*/
    /*为了解耦和，将任务item放到了html中，在这里把数据放到模板中，然后再把模板放到适当的位置*/
    function build(data) {
        /*对data进行处理*/
        var $html = $("#data");
        $html.find(".image img").attr("src",data.pub_icon);
        $html.find(".money span").text(data.goldCoins);
        $html.find(".name").text(subString(data.pub_nickname, 4, "..."));
        $html.find(".type").text(data.type);
        $html.find(".title").text(subString(data.title, 10, "..."));
        $html.find(".time-content").text(data.pubDate);
        $html.find(".describe p").text(data.content);
        $html.find(".place span").text(subString(data.place, 20, "..."));
        $html.find(".right-goods").attr("data-id", data.id);
        $html.find(".left-user").attr("data-user", data.userid);
        return $html.html();
    }

    /*设置点击事件*/
    function clickEvent() {
        /*点击goods*/
        $("#wrapper .goods-wrapper .content .right-goods").off("click").on("click", function () {
            var id = $(this).attr("data-id");
            location.href = "task.html?id=" + id;
        });

        /*点击头像*/
        $("#wrapper .goods-wrapper .content .left-user .image").off("click").on("click", function () {
            var user = $(this).parent().attr("data-user");
            location.href = "user-show.html?id=" + user;
        });
    }

    /* 返回搜索 */
    $("#continue-search").click(function () {
        history.back();
    });
});