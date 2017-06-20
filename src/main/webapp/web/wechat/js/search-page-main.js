/**
 * Created by ALISURE on 2017/5/22.
 */
/* 搜索框 */
$(function () {
    /*获取焦点*/
    $(document).ready(function () {
        $("#searchInput").focus();
    });
    /*点击软键的搜索按钮*/
    $("#search-form").submit(function () {
        var $search_input = $("#searchInput");
        var search = $search_input.val();
        if(search.length == 0) search = $search_input.attr("data-default");
        location.href="searchResult.html?search=" + search;
        return false;
    });
    /*点击清空按钮*/
    $("#searchClear").click(function () {
        $("#searchInput").val("");
        $("#searchInput").focus();
    });
    /*当内容改变时*/
    $("#searchClear").change(function () {
        var value = $("#searchInput").val();
        if(value.length == 0) $("#searchClear").hide();
        else $("#searchClear").show();
    });

    /*点击取消按钮*/
    $("#searchCancel").click(function () {
        history.back();
    });
});

/* 搜索的提示 */
$(function () {

    var datas = {

    };

    /*初始化*/
    init();

    /* 常用搜索 */
    $.get(ServerUrl + "record/popularHistory",function (data) {
        if(data.statusCode == 1000){
            initPromptCommon(data.mySearchRecordsList);
            /* 显示常用搜索 */
            $("#commonSearch").show();
            $("#commonSearchContent").show();
        }else{
            /*没有常用搜索*/
        }
    });

    /* 历史搜索 */
    $.get(ServerUrl + "record/searchHistory",function (data) {
        if(data.statusCode == 1000){
            initPromptHistory(data.mySearchRecordsList);
            /* 显示历史搜索 */
            $("#historySearch").show();
            $("#historySearchContent").show();
        }else{
            /*没有历史搜索*/
        }
    });

    /*初始化*/
    function init() {
        /* 默认不显示历史搜索 */
        $("#historySearch").hide();
        $("#historySearchContent").hide();
        /* 默认不显示常用搜索 */
        $("#commonSearch").hide();
        $("#commonSearchContent").hide();
    }

    function initPromptCommon(prompts) {
        var $prompt = $("#search-prompt-common");
        for(var prompt in prompts){
            $prompt.append('<div class="item" data-search="' + prompts[prompt] + '">' + prompts[prompt] + '</div>');
        }
        /*点击常用搜索的内容*/
        $("#search-prompt-common .item").click(function () {
            var search = $(this).attr("data-search");
            location.href="searchResult.html?search=" + search;
        });
    }

    function initPromptHistory(prompts) {
        var $prompt = $("#search-prompt-history");
        for(var prompt in prompts){
            $prompt.append('<div class="item" data-search="' + prompts[prompt] + '">' + prompts[prompt] + '</div>');
        }

        /*点击历史搜索的内容*/
        $("#search-prompt-history .item").click(function () {
            var search = $(this).attr("data-search");
            location.href="searchResult.html?search=" + search;
        });
    }
});