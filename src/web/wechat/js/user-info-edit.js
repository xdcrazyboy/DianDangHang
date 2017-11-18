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
        if (datas.status == 1) {
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
        if (datas[0].openid == "orLZlwVdD7mj6uwJcBHO1BXM-FnQ") {
            init_edit_data(datas);
        } else {
            alert("你不是本人，无法进行修改操作");
        }
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
        //如果用户没有上传学生证，使用默认提示图（也就是不变）
        if (datas.card != null) {
            $("#user-card-show").css("display", "block");
            $("#user-card-show img").attr("src", datas.card);
        }
    }


    /*获取省份列表 */
    function getAllprovince() {
        $.get(ServerUrl + "school/allProvinces", function(datas) {
            if (datas.status == 1) {
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
            if (datas.status == 1) {
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

            /*获取并组装表单项*/
            var datas = {
                nickname: $("#user-name-edit").val(),
                sex: $('input:radio:checked').val(),
                qq: "5201314",
                phone: $("#user-tel-edit").val(),
                weixin: "testByBobo",
                schoolId: $("#user-school-edit").val(),
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

    function uploadData(datas) {
        console.log(datas.valueOf());

        $.post(ServerUrl + "my/edit", datas, function(data) {
            $.hideLoading();
            if (data.status == Status.Status_OK) {
                $.alert("信息上传成功", function() {
                    var userId = $("#user-info-edit").attr("user-id");
                    location.href = ServerUrl + "web/wechat/view/user-show.html?id=" + userId;
                });
            } else {
                $.alert("任务上传失败", function() {

                });
            }
        });
    }

});