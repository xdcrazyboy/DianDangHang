/**
 * Created by ALISURE on 2017/4/18.
 */
$(function () {

    var page = 1;
    var page_size = 12;
    var ServerUrl = "http://timeseller.fantasy512.cn/timeseller_v0.2/";
    /* 暂时这样写 */
    var searchText = decodeURI(location.search).split("search=");
    searchText = searchText[1];

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
        $.get(ServerUrl + "task/search/" + searchText,{
            page: page,
            size: size
        },function (data) {
            if(data.statusCode == 3000){
                var $goods = $("#wrapper ul");
                /*刷新*/
                if(page == 1)$goods.empty();

                var datas = data.myTaskList;
                for (var i = 0; i < datas.length; i++) {
                    /*需要将数据传给build()*/
                    var item = build(datas[i]);
                    $goods.append(item);
                }

                /*设置点击事件*/
                clickEvent();

                callback();
            }else{
                alert("空");
            }
        });
    }

    /*在这里组装item*/
    /*为了解耦和，将任务item放到了html中，在这里把数据放到模板中，然后再把模板放到适当的位置*/
    function build(data) {
        /*对data进行处理*/
        var $html = $("#data");

        $html.find(".image img").attr("src",data.pub_icon);
        $html.find(".money span").text(data.goldCoins);
        $html.find(".name").text(data.pub_nickname);
        $html.find(".type").text(data.type);
        $html.find(".title").text(data.title.length > 10 ? data.title.substr(0, 10) + "..." : data.title);
        $html.find(".time-content").text(parseTime(data.pubDate));
        $html.find(".describe p").text(data.content);
        $html.find(".place span").text(data.place);
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
            alert("点击用户：" + user);
        });
    }
});