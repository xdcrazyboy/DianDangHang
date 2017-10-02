package com.alisure.controller;

import com.alisure.weixin.check.SignUtil;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 微信验证服务器的真实性
 */
@Controller
public class HandleToken2Controller {
    @Autowired
    private HttpServletRequest request;

    @RequestMapping(value = "/weixin", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value="处理Token",httpMethod="GET")
    public void handleToken(HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        try{
            if(SignUtil.checkSignature(request.getParameter("signature"), request.getParameter("timestamp"), request.getParameter("nonce"))){
                out.print(request.getParameter("echostr"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        out.close();
    }

}
