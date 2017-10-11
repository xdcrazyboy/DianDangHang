package com.alisure.controller;

import com.alisure.entity.Result;
import com.alisure.service.CommonService;
import com.alisure.service.SearchService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;

/**
 * Created by ALISURE on 2017/9/10.
 *
 * 主要包含搜索历史
 * 1.获取常用搜索
 * 2.获取搜索历史
 * 3.根据关键字获取搜索内容(此时应该插入一条搜索数据)
 */
@Controller
@RequestMapping("/search")
@Api(value = "/search",description = "about search of user")
public class SearchController {

    @Autowired
    SearchService searchService;

    @Autowired
    CommonService commonService;

    @Autowired
    private HttpServletRequest request;

    @ResponseBody
    @RequestMapping(value = "popularHistory", method = RequestMethod.GET)
    @ApiOperation(value="get popular history", httpMethod="GET", response = Result.class, nickname = "ALISURE")
    public Result getCommonSearch(
            @ApiParam(required = false,name = "number",value = "number", defaultValue = "12")
            @RequestParam(required = false, defaultValue = "12") String number){
        return new Result(searchService.getCommonSearch(Integer.parseInt(number)));
    }

    @ResponseBody
    @RequestMapping(value = "searchHistory", method = RequestMethod.GET)
    @ApiOperation(value="get search history", httpMethod="GET", response = Result.class, nickname = "ALISURE")
    public Result getHistorySearch(
            @ApiParam(required = false, name = "number", value = "number", defaultValue = "12")
            @RequestParam(required = false, defaultValue = "12") String number){
        return new Result(searchService.getHistorySearch(commonService.getUserId(request), Integer.parseInt(number)));
    }

    @ResponseBody
    @RequestMapping(value = "search", method = RequestMethod.GET)
    @ApiOperation(value = "get all search result by key word", httpMethod = "GET", response = Result.class, nickname = "ALISURE")
    public Result getSearch(
            @ApiParam(required = true, name = "keyWord", value = "keyWord")
            @RequestParam String keyWord,
            @ApiParam(required = true, name = "page", value = "page")
            @RequestParam int page) throws Exception{
        int pageSize = 10;
        System.out.println(keyWord);
        keyWord = URLDecoder.decode(keyWord, "UTF-8");
        System.out.println(keyWord);
        return new Result(searchService.getSearchResult(keyWord, page, pageSize, commonService.getUserId(request)));
    }
}
