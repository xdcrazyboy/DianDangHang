package com.alisure.controller;

import com.alisure.weixin.check.SignUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by ALISURE on 2017/4/27.
 */
@Controller
@RequestMapping("/token")
public class HandleTokenController {

    @Autowired
    private HttpServletRequest request;

    @RequestMapping("/token")
    public void handleToken(HttpServletResponse response) throws IOException {
        // 微信加密签名
        String signature = request.getParameter("signature");
        // 时间戮
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        // 随机字符串
        String echostr = request.getParameter("echostr");

        PrintWriter out = response.getWriter();
        // 通过检验 signature 对请求进行校验，若校验成功则原样返回 echostr，表示接入成功，否则接入失败
        if(SignUtil.checkSignature(signature, timestamp, nonce)){
            out.print(echostr);
        }

        out.close();
        out = null;
    }

}
