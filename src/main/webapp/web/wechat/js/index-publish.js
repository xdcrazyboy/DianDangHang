/**
 * Created by ALISURE on 2017/5/16.
 */
$(function () {

    /*表单的一些参数*/
    var task_params = {
        /*任务名称的最大长度*/
        task_name_length: 10,
        /*任务描述的最大长度*/
        task_describe_length: 200,
        /*私密信息的最大长度*/
        task_secret_length: 200,
        /*图片的最大数量*/
        task_image_max_length: 3,

        /*设置时间*/
        task_time_min_time:"2017-01-01",
        task_time_max_time:"2020-12-31",

        /*用户金币数量*/
        task_money_number:50,

        /*联系方式预选*/
        task_link_id:234,
        task_link_max_length:11
    };
    var link_params = [
        { link_id:123, link_text:"微信号", link_input_type:"text"},
        { link_id:12, link_text:"QQ号", link_input_type:"number"},
        { link_id:234, link_text:"手机号", link_input_type:"number"}
    ];
    var type_params = [
        {type_id:11, type_text:"学习"},
        {type_id:132, type_text:"娱乐"},
        {type_id:121, type_text:"跑腿"}
    ];

    init_form();
    check_form();
    save_form();
    submit_form();

    /*初始化表单*/
    function init_form() {
        /*设置任务名称的限制*/
        $("#task-name").attr("placeholder","不超过" + task_params.task_name_length + "个字")

        /*设置任务描述的限制*/
        $("#task-describe").attr("placeholder","不超过" + task_params.task_describe_length + "个字" )
        $("#task-describe-length").text("0");
        $("#task-describe-max-length").text(task_params.task_describe_length);

        /*设置私密信息的限制*/
        $("#task-secret").attr("placeholder","该信息只对任务接收者可见。如果任务中不需要，可以选择不填。不能超过" + task_params.task_secret_length + "个字" )
        $("#task-secret-length").text("0");
        $("#task-secret-max-length").text(task_params.task_secret_length);

        /*设置图片*/
        dealImage();

        /*设置类型*/
        initType();

        /*设置时间*/
        $("#task-time-begin").datetimePicker({
            rotateEffect:true,
            min:task_params.task_time_min_time,
            max:task_params.task_time_max_time,
            value:getTimeAddHour(),
            onClose:function () {
                var time = $("#task-time-begin").val();
                var now_time = getTimeAddHour();
                if(now_time > time){
                    $.alert("开始时间大于当前时间，如果存在错误请修改");
                }
            }
        });
        $("#task-time-end").datetimePicker({
            rotateEffect:true,
            min:task_params.task_time_min_time,
            max:task_params.task_time_max_time,
            value:getTimeAddHour(2),
            onClose:function () {
                var time = $("#task-time-end").val();
                var now_time = getTimeAddHour();
                if(now_time > time){
                    $("#task-time-end").val(getTimeAddHour(2));
                    $.alert("结束时间小于当前时间，存在错误请修改");
                }
            }
        });

        /*设置金币*/
        $("#task-money").attr("placeholder","总金币数量为: " + task_params.task_money_number);

        /*设置联系方式*/
        initLink();
    }

    /*处理表单，keyup事件*/
    function check_form() {
        var task_name_length_too_long_msg = "任务名称不能超过" + task_params.task_name_length + "个字";
        var task_describe_length_too_long_msg = "任务描述不能超过" + task_params.task_describe_length + "个字";
        var task_secret_length_too_long_msg = "私密信息不能超过" + task_params.task_describe_length + "个字";
        var task_money_less_than_have_msg = "总金币只有" + task_params.task_money_number + ",请重新输入";
        var task_money_less_than_0_msg = "不要调皮了，正常点";
        var task_link_length_too_long_msg = "联系方式的长度不能超过" + task_params.task_link_max_length + "";

        /*检查 任务名称*/
        $("#task-name").keyup(function () {
            var value = $(this).val();
            if(value.length > task_params.task_name_length){
                $(this).val(value.substr(0, task_params.task_name_length));
                $.alert(task_name_length_too_long_msg);
            }
        });
        /*检查 任务描述*/
        $("#task-describe").keyup(function () {
            var value = $(this).val();
            /*实时设置长度*/
            $("#task-describe-length").text(value.length);
            /*检查长度是否超过限制*/
            if(value.length > task_params.task_describe_length){
                $(this).val(value.substr(0, task_params.task_describe_length));
                $("#task-describe-length").text(task_params.task_describe_length);
                $.alert(task_describe_length_too_long_msg);
            }
        });
        /*检查 私密信息*/
        $("#task-secret").keyup(function () {
            var value = $(this).val();
            /*实时设置长度*/
            $("#task-secret-length").text(value.length);
            /*检查长度是否超过限制*/
            if(value.length > task_params.task_secret_length){
                $(this).val(value.substr(0, task_params.task_secret_length));
                $("#task-secret-length").text(task_params.task_secret_length);
                $.alert(task_secret_length_too_long_msg);
            }
        });

        /*检查图片*/

        /*检查时间*/
        /*检查时间已经在初始化中完成*/

        /*检查金币*/
        $("#task-money").keyup(function () {
            var value = $(this).val();
            /*大于最大值*/
            if(value > task_params.task_money_number){
                $(this).val(0);
                $.alert(task_money_less_than_have_msg);
            }
            /*小于0*/
            if(value < 0){
                $(this).val(0);
                $.alert(task_money_less_than_0_msg);
            }
        });

        /*检查酬劳*/

        /*检查联系方式*/
        $("#task-link-input").keyup(function () {
            /*联系方式的值*/
            var link = $(this).val();
            /*若联系方式不是微信号，则检查长度*/
            if($(this).attr("type") == "number"){
                if(link.length > task_params.task_link_max_length){
                    $(this).val(link.substr(0, task_params.task_link_max_length));
                    $.alert(task_link_length_too_long_msg);
                }
            }
        });

    }

    /*临时保存数据, change/blur事件*/
    function save_form() {

        /*响应选择联系方式，同时保存值*/
        $("#task-link-select").change(function () {
            setLinkMethod($(this).val());
        });
    }

    /*提交表单*/
    function submit_form() {
        $("#submit").click(function () {

            var task_images = [];
            /*图片*/
            $("#uploaderFiles li").each(function () {
                task_images[task_images.length] = $(this).css("background-image").split("\"",3)[1];
            });
            /*获取并组装表单项*/
            var datas = {
                task_name: $("#task-name").val(),
                task_describe: $("#task-describe").val(),
                task_secret: $("#task-secret").val(),
                task_time_begin: $("#task-time-begin").val(),
                task_time_end: $("#task-time-end").val(),
                task_type: $("#task-type").val(),
                task_place: $("#task-place").val(),
                task_money: $("#task-money").val(),
                task_other_money: $("#task-other-money").val(),
                task_link_select: $("#task-link-select").val(),
                task_link_input: $("#task-link-input").val(),
                task_images: task_images
            }

            /*检查表单项*/
            if(datas.task_name.length == 0){
                $.alert("任务名称不能为空");
                $("#task-name").focus();
                return;
            }
            if(datas.task_name.length > task_params.task_name_length) {
                $.alert("任务名称的长度不能超过" + task_params.task_name_length + "个字");
                $("#task-name").focus();
                return;
            }
            if(datas.task_describe.length == 0){
                $.alert("任务描述不能为空");
                $("#task-describe").focus();
                return;
            }
            if(datas.task_describe.length > task_params.task_describe_length) {
                $.alert("任务描述的长度不能超过" + task_params.task_describe_length + "个字");
                $("#task-describe").focus();
                return;
            }
            if(datas.task_secret.length > task_params.task_secret_length) {
                $.alert("私密信息的长度不能超过" + task_params.task_secret_length + "个字");
                $("#task-secret").focus();
                return;
            }
            if($("#task-time-begin").val() > $("#task-time-end").val()){
                $.alert("结束时间早于开始时间，存在错误请修改");
                $("#task-time-end").focus();
                return;
            }
            if(datas.task_money > task_params.task_money_number){
                $.alert("总金币只有" + task_params.task_money_number + ",请重新输入");
                $("#task-describe").focus();
                return;
            }
            if(datas.task_link_input.length == 0){
                $.alert("联系方式不能为空");
                $("#task-link-input").focus();
                return;
            }

            /*检查是否有金币或酬劳，没问题（有金币或酬劳/用户确认没有金币或酬劳）后上传数据到服务器*/
            var is_money_ok = true;
            if((datas.task_money.length == 0 || datas.task_money <= 0 ) && datas.task_other_money.length == 0){
                is_money_ok = false;
                $.confirm("确定发布任务不给金币或酬劳吗？", function() {
                    is_money_ok = true;
                }, function() {
                    if(datas.task_money.length == 0 || datas.task_money == 0){
                        $("#task-money").focus();
                    }
                });
            }
            if(is_money_ok){
                /*上传服务器*/
                uploadData(datas);
            }

        });
    }


    /*上传数据到服务器*/
    function uploadData(datas) {
        /**
         * 先上传图片。
         * 成功后，再提交表单。
         **/
        $.showLoading("任务上传中");
        /*for循环*/
        uploadImageByFor(datas,uploadOtherData);
        /*串行：数组pop*/
        //uploadImageBySync(datas,uploadOtherData);
    }
    /*上传其他数据的回调函数*/
    function uploadOtherData(datas) {
        /*上传数据*/
        var result_id = 132;

        $.hideLoading();
        $.toast("任务上传成功");
        /*反馈*/
        /*$.alert(datas.task_name + "/" + datas.task_describe  + "/" + datas.task_secret + "/" + datas.task_images + "/" +
         datas.task_time_begin + "/" + datas.task_time_end + "/" + datas.task_type + "/" +
         datas.task_place + "/" + datas.task_money + "/" + datas.task_other_money + "/" +
         datas.task_link_select + "/" + datas.task_link_input);*/

        /*跳转*/
        location.href = "task.html?taskId=" + result_id;
    }
    /*
     * 图片上传思路：
     * 1.用户选择图片,获取localIds;
     * 2.根据localIds上传图片至微信的服务器,返回mediaId;
     * 3.前端根据mediaId请求自己的服务器去微信的服务器下载图片,返回资源地址;
     * 4.前端根据资源地址获取图片;
     */
    /*上传图片：并行上传*/
    function uploadImageByFor(datas,callback) {
        var image_urls = [];
        /*错误标识：标识是否是第一次出现错误*/
        var had_first_error_flog = false;
        var localIds = datas.task_images;

        /*没有图片，直接上传*/
        if(localIds.length == 0) callback(datas);

        for(var i = 0; i < localIds.length; i++){
            wx.uploadImage({
                localId: localIds[i], // 需要上传的图片的本地ID，由chooseImage接口获得
                isShowProgressTips: 0, // 默认为1，显示进度提示
                success: function (res) {
                    var serverId = res.serverId; // 返回图片的服务器端ID
                    /*如果之前没有出现过错误，继续。*/
                    if(!had_first_error_flog){
                        /*请求服务器下载图片*/
                        downImage(serverId,function (data) {
                            /*图片上传成功*/
                            if (data.status == 1) {
                                /*保存图片地址*/
                                var image_url = data.object;
                                image_urls[image_urls.length] = image_url;
                                /*若全部图片上传成功，上传非图片数据*/
                                if(image_urls.length == datas.task_images.length){
                                    /*添加图片地址后上传数据*/
                                    datas.task_images = image_urls;
                                    /*通过回调上传数据*/
                                    callback(datas);
                                }
                            } else {
                                /*第一次出现错误*/
                                if(!had_first_error_flog){
                                    had_first_error_flog = true;
                                    alert("数据上传出现错误：" + data.status);
                                }
                            }
                        });
                    }
                },
                fail: function(res) {
                    /*第一次出现错误*/
                    if(!had_first_error_flog){
                        had_first_error_flog = true;
                        alert("数据上传出现错误fail--：" + JSON.stringify(res));
                    }
                }
            });
        }
    }
    /*上传图片:串行上传*/
    function uploadImageBySync(datas,callback,image_urls) {
        if(typeof image_urls == "undefined" || image_urls == undefined || image_urls.length == 0) image_urls = [];

        /*没有图片，直接上传*/
        if(datas.task_images.length == 0) callback(datas);

        var localId = datas.task_images.pop();
        wx.uploadImage({
            localId: localId, // 需要上传的图片的本地ID，由chooseImage接口获得
            isShowProgressTips: 1, // 默认为1，显示进度提示
            success: function (res) {
                var serverId = res.serverId; // 返回图片的服务器端ID
                /*请求服务器下载图片*/
                downImage(serverId,function (data) {
                    /*图片上传成功*/
                    if (data.status == 1) {
                        /*保存图片地址*/
                        var image_url = data.object;
                        image_urls[image_urls.length] = image_url;

                        //其他对serverId做处理的代码
                        if(datas.task_images.length > 0){
                            uploadImageBySync(datas,callback,image_urls);
                        }else if(datas.task_images.length == 0){
                            /*添加图片地址后上传数据*/
                            datas.task_images = image_urls;
                            /*通过回调上传数据*/
                            callback(datas);
                        }
                    } else {
                        $.alert("数据上传出现错误：" + data.status);
                    }
                });
            },
            fail: function(res) {
                $.alert("数据上传出现错误：" + JSON.stringify(res));
            }
        });
    }

    /*获取增加hour小时后的时间当前时间*/
    function getTimeAddHour(hour) {
        if(hour == undefined) hour = 0;
        var timestamp=new Date().getTime();
        var date = new Date(timestamp + hour * 60 * 60 * 1000);

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

    /*设置类型*/
    function initType() {
        /*设置联系方式下拉列表*/
        for (var index in type_params){
            if(index == 0){
                /*设置默认值*/
                $("#task-type").append(
                    "<option value='" + type_params[index].type_id + "' selected='selected'>" + type_params[index].type_text + "</option>");
            }else {
                $("#task-type").append("<option value='" + type_params[index].type_id + "'>" + type_params[index].type_text + "</option>");
            }
        }
    }

    /*设置联系方式*/
    function initLink() {
        /*设置联系方式下拉列表*/
        for (var index in link_params){
            if(link_params[index].link_id == task_params.task_link_id){
                /*设置默认值*/
                $("#task-link-select").append(
                    "<option value='" + link_params[index].link_id + "' selected='selected'>" + link_params[index].link_text + "</option>");
            }else {
                $("#task-link-select").append(
                    "<option value='" + link_params[index].link_id + "'>" + link_params[index].link_text + "</option>");
            }
        }

        /*设置联系方式*/
        setLinkMethod(task_params.task_link_id);
    }

    /*初始化联系方式/响应选择联系方式*/
    function setLinkMethod(link_id) {
        /*获取要设置的列表的索引*/
        for (var index in link_params){
            if(link_params[index].link_id == link_id){
                /*设置*/
                $("#task-link-method").text(link_params[index].link_text);
                $("#task-link-input").attr("type", link_params[index].link_input_type);
                $("#task-link-input").attr("placeholder","请填写" + link_params[index].link_text);
                $("#task-link-input").val("");
                break;
            }
        }
    }

    /*图片处理*/
    function dealImage() {
        /*初始化*/
        $("#uploaderFiles-max-number").text(task_params.task_image_max_length);
        $("#uploaderFiles-number").text(0);

        /*关闭*/
        $("#uploaderFiles-gallery span").click(function () {
            $(this).parent().hide();
        });
        /*删除*/
        $("#uploaderFiles-gallery i").click(function () {
            /*隐藏*/
            $("#uploaderFiles-gallery").hide();
            /*删除*/
            var data_for_delete = $("#uploaderFiles-gallery span").attr("data-for-delete");
            $("#uploaderFiles li[data-for-delete=" + data_for_delete + "]").remove();
            /*修改数字*/
            var image_number = $("#uploaderFiles li").size();
            $("#uploaderFiles-number").text(image_number);

            /*数量未达到上限就解禁添加图片*/
            if(image_number == task_params.task_image_max_length){
                $("#uploaderInput").parent().show();
            }
        });
        /*添加*/
        $("#uploaderInput").click(function () {
            var count = task_params.task_image_max_length - $("#uploaderFiles li").size();
            if(count <= 0) return;
            wx.chooseImage({
                count: count, // 默认9
                sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
                sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
                success: function (res) {
                    // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
                    var localIds = res.localIds;
                    for (var i in localIds){
                        /*设置界面*/
                        var li_number = $("#uploaderFiles li").size() + 1;
                        $("#uploaderFiles").append(
                            '<li class="weui-uploader__file" data-for-delete="' + li_number +
                            '" style="background-image:url('+ localIds[i] + ')"></li>');
                        $("#uploaderFiles-number").text(li_number);

                        /*数量达到上限就禁止添加图片*/
                        if(li_number == task_params.task_image_max_length){
                            $("#uploaderInput").parent().hide();
                            break;
                        }

                        /*绑定事件*/
                        imageClickEvent();
                    }
                }
            });
        });

    }

    /*绑定图片点击事件*/
    function imageClickEvent() {
        /*显示某一张图片*/
        $("#uploaderFiles li").off("click").on("click", function () {
            var img = $(this).css("background-image");
            var $span = $("#uploaderFiles-gallery span");
            $span.css("background-image",img).hide();
            $span.parent().fadeIn();
            $span.delay(300);
            $span.fadeIn("slow");
            /*用于删除*/
            $span.attr("data-for-delete", $(this).attr("data-for-delete"));
        });
    }
});