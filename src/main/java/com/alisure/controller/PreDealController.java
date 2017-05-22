package com.alisure.controller;

import com.alisure.result.Result;
import com.alisure.result.ResultObject;
import com.alisure.result.Signature;
import com.alisure.result.Status;
import com.alisure.weixin.check.JSSDKSignUtil;
import com.alisure.weixin.image.ImageUtil;
import com.alisure.weixin.task.GetWeiXinInf;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * Created by ALISURE on 2017/4/30.
 */
@Controller
@RequestMapping("/predeal")
public class PreDealController {

    /**
     * 请求服务器下载用户上传的图片
     * @param mediaId
     * @return
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping("/image")
    public Result downImage(@RequestParam String mediaId) throws IOException {
        if(mediaId == null || "".equals(mediaId)){
            return new Result().setStatus(Status.Status_Parameter_Error);
        }
        ImageUtil imageUtil = new ImageUtil();
        if(imageUtil.getWeiXinImage(new GetWeiXinInf().getAccessToken(), mediaId)){
            return new ResultObject<String>(Status.Status_OK, imageUtil.getImageName());
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
    @RequestMapping("signature")
    public Result signature(@RequestParam String url){
        if(url == null || "".equals(url)){
            return new Result().setStatus(Status.Status_Parameter_Error);
        }
        String signature = JSSDKSignUtil.getSignature(new GetWeiXinInf().getTicket(), url);
        if(signature == null || "".equals(signature)){
            return new Result(Status.Status_Error);
        }else{
            return new ResultObject<Signature>(Status.Status_OK, new Signature(signature));
        }
    }
}
