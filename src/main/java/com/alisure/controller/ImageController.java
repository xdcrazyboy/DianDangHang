package com.alisure.controller;

import com.alisure.entity.Result;
import com.alisure.entity.Signature;
import com.alisure.entity.Status;
import com.alisure.weixin.check.JSSDKSignUtil;
import com.alisure.weixin.image.ImageUtil;
import com.alisure.weixin.task.GetWeiXinInf;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
@RequestMapping("/image")
@Api(value = "/image",description = "down image, get signature")
public class ImageController {

    /**
     * 请求服务器下载用户上传的图片
     * @param mediaId
     * @return
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping(value = "downImage", method = RequestMethod.GET)
    @ApiOperation(value="request server download the image which upload by user", httpMethod="GET", response = Result.class, nickname = "ALISURE")
    public Result downImage(
            @ApiParam(required = true,name = "mediaId",value = "mediaId")
            @RequestParam String mediaId) throws IOException {
        if(mediaId == null || "".equals(mediaId)){
            return new Result().setStatus(Status.Status_Parameter_Error);
        }
        ImageUtil imageUtil = new ImageUtil();
        if(imageUtil.getWeiXinImage(new GetWeiXinInf().getAccessToken(), mediaId)){
            return new Result(Status.Status_OK, imageUtil.getImageName());
        }else{
            return new Result(Status.Status_Error);
        }
    }

    /**
     * 请求指定url的签名
     * @param url
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "signature", method = RequestMethod.GET)
    @ApiOperation(value="request the signature by url", httpMethod="GET", response = Result.class, nickname = "ALISURE")
    public Result signature(
            @ApiParam(required = true,name = "url",value = "url")
            @RequestParam String url){
        if(url == null || "".equals(url)){
            return new Result().setStatus(Status.Status_Parameter_Error);
        }
        String signature = JSSDKSignUtil.getSignature(new GetWeiXinInf().getTicket(), url);
        if(signature == null || "".equals(signature)){
            return new Result(Status.Status_Error);
        }else{
            return new Result(Status.Status_OK, new Signature(signature));
        }
    }
}
