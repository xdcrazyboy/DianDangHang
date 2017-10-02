package com.alisure.controller;

import com.alisure.entity.Result;
import com.alisure.service.CommentService;
import com.alisure.service.CommonService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 1、评价
 * 2、获取评价(也有可能有两个评价)
 * 3、投诉
 */
@Controller
@RequestMapping("/comment")
@Api(value = "/comment",description = "about comment and complaint")
public class CommentController {

    @Autowired
    CommentService commentService;
    @Autowired
    CommonService commonService;
    @Autowired
    private HttpServletRequest request;

    @ResponseBody
    @RequestMapping(value = "comment")
    @ApiOperation(value="comment the task", httpMethod="GET", response = Result.class, nickname = "ALISURE")
    public Result comment(
            @ApiParam(name = "task id", value = "tid") @RequestParam int tid,
            @ApiParam(name = "content", value = "commentContent") @RequestParam String commentContent,
            @ApiParam(name = "comment grade:0 - 10", value = "commentGrade", defaultValue = "10") @RequestParam String commentGrade,
            @ApiParam(name = "comment type:1 - publisher to receiver, 2 - receiver to publisher", value = "commentType", defaultValue = "1") @RequestParam String commentType){
        return new Result(commentService.comment(tid, commentContent, Integer.parseInt(commentGrade), Integer.parseInt(commentType)));
    }

    @ResponseBody
    @RequestMapping(value = "getComment")
    @ApiOperation(value="get comment", httpMethod="GET", response = Result.class, nickname = "ALISURE")
    public Result getComment(@ApiParam(name = "task id", value = "tid") @RequestParam int tid){
        return new Result(commentService.getComment(tid));
    }


    @ResponseBody
    @RequestMapping(value = "complaint")
    @ApiOperation(value="complaint the task", httpMethod="GET", response = Result.class, nickname = "ALISURE")
    public Result complaint(
            @ApiParam(name = "task id", value = "tid") @RequestParam int tid,
            @ApiParam(name = "content", value = "commentContent") @RequestParam String commentContent,
            @ApiParam(name = "complaint type:1 - publisher to receiver, 2 - receiver to publisher", value = "complaintType", defaultValue = "1") @RequestParam String complaintType){
        return new Result(commentService.complaint(tid, commentContent, Integer.parseInt(complaintType)));
    }
}
