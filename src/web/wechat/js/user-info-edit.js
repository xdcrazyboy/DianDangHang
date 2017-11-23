/**
 * Created by fengziboboy on 2017/11/17.
 */
$(function() {

    clickEvent();
    //获取省份列表
    getAllprovince();

    /* 提交事件 */
    submit_form();

    /*设置点击事件*/
    function clickEvent() {
        /*改变地区省份时，学校选项列表改变*/
        $("#user-address-edit").change(function() {
            var provinceId = $("#user-address-edit").val();
            if ((provinceId != "") && (provinceId != null)) {
                getSchoolList(provinceId);
            }
        });
        //只改学校时，避免出现因为没电省份修改而没加载出学校列表
        $("#user-school-edit").focus(function() {
            var provinceId = $("#user-address-edit").val();
            if ((provinceId != "") && (provinceId != null)) {
                getSchoolList(provinceId);
            }
        });

    }

    /*获取编辑页面数据*/
    $.get(ServerUrl + "my/user", function(datas) {
        //处理展示个人资料
        if (datas.status == Status.Status_OK) {
            $.toast("数据获取成功");
            // 展示个人资料界面
            editUserInfo(datas.data);
        } else {
            $.toast("数据获取错误");
        }
    });

    //编辑个人数据（若原本就有数据需默认填写）
    function editUserInfo(datas) {
        //加载默认数据

        init_edit_data(datas);

    }

    /**修改个人信息数据初始化 */
    function init_edit_data(datas) {

        $("#user-info-edit").attr("user-id", datas[0].uid);
        $(".headimg").attr("src", datas[0].headimgurl);
        $("#user-name-edit").val(datas[0].nickname);
        // 性别暂时只有男女
        $("input:radio[value='" + datas[0].sex + "']").attr('checked', 'true');
        $("#user-address-edit").val(datas[1].provinceId);
        // 设置了省份之后，获取学校列表
        var provinceId = $("#user-address-edit").val();
        //haveSchoolId为了显示修改前的学校
        var haveSchoolId = datas[1].schoolId;
        if ((provinceId != "") && (provinceId != null)) {
            getSchoolList(provinceId, haveSchoolId);
        }
        // $("#user-school-edit").val(datas[1].schoolId);
        $("#user-tel-edit").val(datas[0].phone);
        $("#user-qq-edit").val(datas[0].qq);
        $("#user-weixin-edit").val(datas[0].weixin);
        //如果用户没有上传学生证，使用默认提示图（也就是不变）
        if (datas.card != null) {
            $("#uploaderFiles").append(
                '<li class="weui-uploader__file" data-for-delete="1" style="background-image:url(' + datas.card + ')"></li>');
        }
        /*设置图片*/
        dealImage();
    }


    /*获取省份列表 */
    function getAllprovince() {
        $.get(ServerUrl + "school/allProvinces", function(datas) {
            if (datas.status == Status.Status_OK) {
                var $link = $("#user-address-edit").empty();
                //添加一个选项
                for (var i = 0; i < datas.data.length; i++) {
                    $link.append("<option value = '" + datas.data[i].provinceId + "'>" + datas.data[i].province + "</option>");
                }
            } else {
                $.toast("数据获取错误");
            }
        })
    }

    /*根据省份取值获取学校列表*/
    function getSchoolList(provinceId, haveSchoolId) {
        $.get(ServerUrl + "school/getSchoolByPid/" + provinceId, function(datas) {
            if (datas.status == Status.Status_OK) {
                var $link = $("#user-school-edit").empty();
                var selectSchoolList = datas.data;
                for (var item in selectSchoolList) {
                    if (selectSchoolList[item].schoolId == haveSchoolId) { /*设置默认值*/
                        $link.append("<option value='" + selectSchoolList[item].schoolId + "' selected='selected'>" + selectSchoolList[item].schoolName + "</option>");
                    } else {
                        $link.append("<option value = '" + selectSchoolList[item].schoolId + "'>" + selectSchoolList[item].schoolName + "</option>");
                    }

                }
            } else {
                $.toast("数据获取错误");
            }

        })
    }

    /*提交表单*/
    function submit_form() {
        $("#user-info-submit").click(function() {
            var images = [];
            var rawStr = $("#uploaderFiles li").css("background-image");
            if (!rawStr) {
                alert("请上传学生证");
            } else {
                // 拆分地址为 weixin：一堆字符串  的格式
                images[0] = rawStr.substring(rawStr.indexOf("(") + 1, rawStr.indexOf(")"));
            }

            /*获取并组装表单项*/
            var datas = {
                nickname: $("#user-name-edit").val(),
                sex: $('input:radio:checked').val(),
                qq: $("#user-qq-edit").val(),
                phone: $("#user-tel-edit").val(),
                weixin: $("#user-weixin-edit").val(),
                schoolId: $("#user-school-edit").val(),
                card: images
            };

            /*检查表单项*/
            if (datas.nickname.length == 0) {
                $.alert("用户名不能为空");
                $("#user-name-edit").focus();
                return;
            }
            if (datas.nickname.length > 16) {
                $.alert("用户名的长度不能超过" + 16 + "个字");
                $("#user-name-edit").focus();
                return;
            }
            if (datas.sex.length == 0) {
                $.alert("性别不能为空");
                $("#user-gender").focus();
                return;
            }
            if (datas.schoolId.length == 0) {
                $.alert("学校不能为空");
                $("#user-school-edit").focus();
                return;
            }


            /*上传服务器*/
            $.confirm("确定修改个人信息吗？", function() {
                uploadData(datas);
            });

        });
    }

    /*-------------------------------------------------------*/
    /*---------------------- 提交表单 -----------------------*/
    /*
     * 数据上传思路：
     * 1.先上传图片
     * 2.再上传数据
     *
     * 图片上传思路：
     * 1.用户选择图片,获取localIds;
     * 2.根据localIds上传图片至微信的服务器,返回mediaId;
     * 3.前端根据mediaId请求自己的服务器去微信的服务器下载图片,返回资源地址;
     * 4.前端根据资源地址获取图片;
     */
    /*上传数据到服务器*/
    function uploadData(datas) {
        /**
         * 先上传图片。
         * 成功后，再提交表单。
         **/
        $.showLoading("资料上传中");
        uploadImageByFor(datas, uploadOtherData);
    }
    /*上传其他数据的回调函数*/
    function uploadOtherData(datas) {
        console.log(datas.valueOf());
        $.post(ServerUrl + "my/edit", datas, function(data) {
            $.hideLoading();
            if (data.status == Status.Status_OK) {
                $.alert("信息上传成功", function() {
                    var userId = $("#user-info-edit").attr("user-id");
                    location.href = ServerUrl + "web/wechat/view/user-show.html?id=" + userId;
                });
            } else {
                $.alert("信息上传失败", function() {

                });
            }
        });
    }
    /*上传图片：并行上传*/
    function uploadImageByFor(datas, callback) {
        var image_urls = [];
        /*错误标识：标识是否是第一次出现错误*/
        var had_first_error_flog = false;
        var localIds = datas.card;

        /*没有图片，直接上传*/
        if (localIds.length == 0) callback(datas);

        for (var i = 0; i < localIds.length; i++) {
            wx.uploadImage({
                localId: localIds[i], // 需要上传的图片的本地ID，由chooseImage接口获得
                isShowProgressTips: 0, // 默认为1，显示进度提示
                success: function(res) {
                    var serverId = res.serverId; // 返回图片的服务器端ID
                    /*如果之前没有出现过错误，继续。*/
                    if (!had_first_error_flog) {
                        /*请求服务器下载图片*/
                        downImage(serverId, function(data) {
                            /*图片上传成功*/
                            if (data.status == Status.Status_OK) {
                                /*保存图片地址*/
                                var image_url = data.data;
                                image_urls[image_urls.length] = image_url;
                                /*若全部图片上传成功，上传非图片数据*/
                                if (image_urls.length == datas.card.length) {
                                    /*添加图片地址后上传数据*/
                                    datas.card = image_urls;
                                    /*通过回调上传数据*/
                                    callback(datas);
                                }
                            } else {
                                /*第一次出现错误*/
                                if (!had_first_error_flog) {
                                    had_first_error_flog = true;
                                    $.alert("数据上传出现错误1：" + data.status, function() {
                                        $.hideLoading();
                                    });
                                }
                            }
                        });
                    }
                },
                fail: function(res) {
                    /*第一次出现错误*/
                    if (!had_first_error_flog) {
                        had_first_error_flog = true;
                        $.alert("数据上传出现错误fail--：" + JSON.stringify(res), function() {
                            $.hideLoading();
                        });
                    }
                }
            });
        }
    }
    /*上传图片:串行上传*/
    function uploadImageBySync(datas, callback, image_urls) {
        if (typeof image_urls == "undefined" || image_urls == undefined || image_urls.length == 0) image_urls = [];

        /*没有图片，直接上传*/
        if (datas.card.length == 0) callback(datas);

        var localId = datas.card.pop();
        wx.uploadImage({
            localId: localId, // 需要上传的图片的本地ID，由chooseImage接口获得
            isShowProgressTips: 1, // 默认为1，显示进度提示
            success: function(res) {
                var serverId = res.serverId; // 返回图片的服务器端ID
                /*请求服务器下载图片*/
                downImage(serverId, function(data) {
                    /*图片上传成功*/
                    if (data.status == Status.Status_OK) {
                        /*保存图片地址*/
                        var image_url = data.data;
                        image_urls[image_urls.length] = image_url;

                        //其他对serverId做处理的代码
                        if (datas.card.length > 0) {
                            uploadImageBySync(datas, callback, image_urls);
                        } else if (datas.card.length == 0) {
                            /*添加图片地址后上传数据*/
                            datas.card = image_urls;
                            /*通过回调上传数据*/
                            callback(datas);
                        }
                    } else {
                        $.alert("数据上传出现错误：" + data.status, function() {
                            $.hideLoading();
                        });
                    }
                });
            },
            fail: function(res) {
                $.alert("数据上传出现错误：" + JSON.stringify(res), function() {
                    $.hideLoading();
                });
            }
        });
    }
    /*---------------------- 提交表单 -----------------------*/
    /*-------------------------------------------------------*/

    /*图片处理*/
    function dealImage() {

        /*关闭*/
        $("#uploaderFiles-gallery span").click(function() {
            $(this).parent().hide();
        });
        /*删除*/
        $("#uploaderFiles-gallery i").click(function() {
            /*隐藏*/
            $("#uploaderFiles-gallery").hide();
            /*删除*/
            var data_for_delete = $("#uploaderFiles-gallery span").attr("data-for-delete");
            $("#uploaderFiles li[data-for-delete=" + data_for_delete + "]").remove();
            /*修改数字*/
            var image_number = $("#uploaderFiles li").size();

            /*若未上传校园卡，则有添加按钮*/
            if (image_number == 0) {
                $("#uploaderInput").parent().show();
            }
        });
        /*添加*/
        $("#uploaderInput").click(function() {
            var count = 1 - $("#uploaderFiles li").size();
            if (count <= 0) return;
            wx.chooseImage({
                count: 1, // 默认9
                sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
                sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
                success: function(res) {
                    // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
                    var localIds = res.localIds;
                    for (var i in localIds) {
                        /*设置界面*/
                        var li_number = $("#uploaderFiles li").size() + 1;
                        $("#uploaderFiles").append(
                            '<li class="weui-uploader__file" data-for-delete="' + li_number +
                            '" style="background-image:url(' + localIds[i] + ')"></li>');

                        /*数量达到上限就禁止添加图片*/
                        if (li_number == 1) {
                            $("#uploaderInput").parent().hide();
                            break;
                        }

                        /*绑定事件*/
                        imageClickEvent();
                    }
                }
            });
        });


        /*绑定图片点击事件*/
        function imageClickEvent() {
            /*显示某一张图片*/
            $("#uploaderFiles li").off("click").on("click", function() {
                var img = $(this).css("background-image");
                var $span = $("#uploaderFiles-gallery span");
                $span.css("background-image", img).hide();
                $span.parent().fadeIn();
                $span.delay(300);
                $span.fadeIn("slow");
                /*用于删除*/
                $span.attr("data-for-delete", $(this).attr("data-for-delete"));
            });
        }

    }


})