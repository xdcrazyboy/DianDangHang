/**
 * Created by fengziboboy on 2017/5/22.
 */
/*
 * 个人中心
 * */
$(function() {

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

});