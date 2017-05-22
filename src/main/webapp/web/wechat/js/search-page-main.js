/**
 * Created by ALISURE on 2017/5/22.
 */
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

$(function () {
    var datas = {
        prompts_history:["取快递", "取快递啊取快递", "取快递哈哈", "取快value递", "取快额去泰国递"],
        prompts_common:["取快递", "取快递啊取快递", "取快递哈哈", "取快value递", "取快额去泰国递", "取快递", "取快递", "取快递", "取快递"]
    }

    /*初始化*/
    initPromptCommon(datas.prompts_common);
    initPromptHistory(datas.prompts_history);

    /*初始化*/
    function initPromptCommon(prompts) {
        var $prompt = $("#search-prompt-common");
        for(var prompt in prompts){
            $prompt.append('<div class="item" data-search="' + prompts[prompt] + '">' + prompts[prompt] + '</div>');
        }
    }
    function initPromptHistory(prompts) {
        var $prompt = $("#search-prompt-history");
        for(var prompt in prompts){
            $prompt.append('<div class="item" data-search="' + prompts[prompt] + '">' + prompts[prompt] + '</div>');
        }
    }

    /*点击提示的内容*/
    $("#search-prompt .item").click(function () {
        var search = $(this).attr("data-search");
        location.href="searchResult.html?search=" + search;
    });
});