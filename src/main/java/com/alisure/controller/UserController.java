package com.alisure.controller;

import com.alisure.entity.Result;
import com.alisure.service.CommonService;
import com.alisure.service.UserService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ALISURE on 2017/9/10.
 *
 * 1、我的任务
 * 2、我的发布
 * 3、获取用户信息
 * 4、修改用户信息
 * 5、获得用户的积分记录
 */
@Controller
@RequestMapping("/my")
@Api(value = "/my",description = "about user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    CommonService commonService;

    @Autowired
    private HttpServletRequest request;

    @ResponseBody
    @RequestMapping(value = "myTask")
    @ApiOperation(value="get all my task", httpMethod="GET", response = Result.class, nickname = "ALISURE")
    public Result getMyTasks(
            @ApiParam(name = "page", value = "page") @PathVariable int page){
        int pageSize = 10;
        return new Result(userService.getMyTasks(page, pageSize, commonService.getUserId(request)));
    }

    @ResponseBody
    @RequestMapping(value = "myPub")
    @ApiOperation(value="get all my Publish", httpMethod="GET", response = Result.class, nickname = "ALISURE")
    public Result getMyPublish(
            @ApiParam(name = "page", value = "page") @PathVariable int page){
        int pageSize = 10;
        return new Result(userService.getMyPublish(page, pageSize, commonService.getUserId(request)));
    }

    @ResponseBody
    @RequestMapping(value = "user")
    @ApiOperation(value="get user info", httpMethod="GET", response = Result.class, nickname = "ALISURE")
    public Result getUserInfo(){
        return new Result(userService.getUserInfoSelf(commonService.getUserId(request)));
    }

    @ResponseBody
    @RequestMapping(value = "showinfo")
    @ApiOperation(value="get user info", httpMethod="GET", response = Result.class, nickname = "ALISURE")
    public Result getUserInfo(
            @ApiParam(name = "uid", value = "uid") @PathVariable Integer uid){
        if(uid == null || uid == commonService.getUserId(request)){
            return new Result(userService.getUserInfoSelf(uid));
        }else{
            return new Result(userService.getUserInfoOther(uid));
        }
    }

    @ResponseBody
    @RequestMapping(value = "edit")
    @ApiOperation(value="edit info", httpMethod="GET", response = Result.class, nickname = "ALISURE")
    public Result editUserInfo(
            @ApiParam(name = "nickname", value = "userInfo") @PathVariable String nickname,
            @ApiParam(name = "sex", value = "userInfo") @PathVariable String sex,
            @ApiParam(name = "qq", value = "userInfo") @PathVariable String qq,
            @ApiParam(name = "phone", value = "userInfo") @PathVariable String phone,
            @ApiParam(name = "weixin", value = "userInfo") @PathVariable String weixin,
            @ApiParam(name = "schoolId", value = "schoolId") @PathVariable int schoolId){
        return new Result(userService.editUserInfo(nickname, sex, qq, phone, weixin, schoolId, commonService.getUserId(request)));
    }

    @ResponseBody
    @RequestMapping(value = "moneyChange")
    @ApiOperation(value="get money change history", httpMethod="GET", response = Result.class, nickname = "ALISURE")
    public Result getMoneyRecord(){
        return new Result(userService.getMoneyRecord(commonService.getUserId(request)));
    }


}
