package com.alisure.controller;

import com.alisure.entity.*;
import com.alisure.service.SchoolService;
import com.alisure.service.CommonService;
import com.alisure.weixin.AllURL;
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

/**
 * 主要包含选择学校的功能：
 * 1、获取所有学校
 * 2、设置选择的学校
 * 3、根据openID获取其学校信息
 */

@Controller
@RequestMapping("/school")
@Api(value = "/school",description = "about select school")
public class SchoolController {

    @Autowired
    private SchoolService schoolService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private HttpServletRequest request;

    @ResponseBody
    @RequestMapping(value = "allSchool", method = RequestMethod.GET)
    @ApiOperation(value="get all school", httpMethod="GET", response = Result.class, nickname = "ALISURE")
    public Result getAllSchoolInfo(){
        return new Result(schoolService.getAllSchoolInfo());
    }

    @ResponseBody
    @RequestMapping(value = "setSchool", method = RequestMethod.GET)
    @ApiOperation(value="set school by schoolId", httpMethod="GET", response = Result.class, nickname = "ALISURE")
    public Result setSchoolByAreaId(
            @ApiParam(required = true,name = "schoolId",value = "schoolId")
            @RequestParam int schoolId){
        InfoUser infoUser = commonService.getLogin(request);
        return new Result(schoolService.setSchoolBySchoolId(infoUser.getOpenid(), schoolId));
    }


    @ResponseBody
    @RequestMapping(value = "getSchool", method = RequestMethod.GET)
    @ApiOperation(value="get school", httpMethod="GET", response = Result.class, nickname = "ALISURE")
    public Result getSchool(){
        return new Result(schoolService.getUserSchool(commonService.getOpenId(request)));
    }

}
