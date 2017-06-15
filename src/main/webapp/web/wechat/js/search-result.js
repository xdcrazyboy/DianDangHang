/**
 * Created by ALISURE on 2017/4/18.
 */
$(function () {

    var page = 1;
    var page_size = 12;

    /* 获取搜索关键字 */
    var searchText = getSearchValue(location.search, "search");

    var loading = true;  //状态标记
    var over = false;  // 当前类型没有任务了

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
                var datas = data.myTaskList;
                /*
                var datas = JSON.parse('[' +
                    '{"id":7,"type":"生活","title":"取快递7","pubDate":"2017-06-12 10:18:19.0","content":"和胡歌约会","secret_message":null,"lists":null,"endDate":null,"place":null,"reward":null,"weixin":null,"qq":null,"telephone":null,"pub_nickname":"锋子","pub_icon":"http://wx.qlogo.cn/mmopen/RamveVcvq7MFxa8VOpOCBKOuHd2nt0mOwCPMmDX43rXqhrI0ppdgBaFqpp5R1sic6DfWx3neJPuibbSw5YbOrxjYyE1BcZldqU/0","goldCoins":2,"pubSituation":null,"rec_nickname":null,"rec_icon":null,"rec_telephone":null,"recsituation":null,"taskTime":null},' +
                    '{"id":5,"type":"娱乐","title":"取快递5","pubDate":"2017-06-02 10:12:20.0","content":"帮我取快递5","secret_message":null,"lists":null,"endDate":null,"place":null,"reward":null,"weixin":null,"qq":null,"telephone":null,"pub_nickname":"DuanJiaping","pub_icon":"http://wx.qlogo.cn/mmopen/Q3auHgzwzM4XOdn9D255P3fKgQEuwCVT4icSHB4ZJ3yfIYNx998grwBTpwLhVSe1UzkRDGRNVC6ibCicONyibKHTjA/0","goldCoins":1,"pubSituation":null,"rec_nickname":null,"rec_icon":null,"rec_telephone":null,"recsituation":null,"taskTime":null}]');
                */

                /* 表明已经没有任务了 */
                if(datas.length == 0){
                    $("#search-empty").show();
                    over = true;
                }else{
                    /* 有任务 */
                    var $goods = $("#wrapper ul");
                    for (var i = 0; i < datas.length; i++) {
                        /*需要将数据传给build()*/
                        var item = build(datas[i]);
                        $goods.append(item);
                    }

                    /*设置点击事件*/
                    clickEvent();
                }
            }else{
                /* 表明出现问题... */
                over = true;
                $.alert(data.statusCode + " " + data.message);
            }

            /* 执行完成后的回掉 */
            callback();
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
        $html.find(".time-content").text(data.pubDate);
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
            location.href = "user-show.html?id=" + user;
        });
    }

    /* 返回搜索 */
    $("#continue-search").click(function () {
        history.back();
    });
});