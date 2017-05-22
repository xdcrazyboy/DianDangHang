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