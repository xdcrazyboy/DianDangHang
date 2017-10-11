package com.alisure.controller;

import com.alisure.entity.InfoUser;
import com.alisure.exception.LoginException;
import com.alisure.service.CommonService;
import com.alisure.tool.common.CommonLog;
import com.alisure.tool.core.CoreReflect;
import com.alisure.tool.core.CoreString;
import com.alisure.weixin.AllURL;
import com.alisure.service.WeixinService;
import com.wordnik.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 和微信相关：授权、菜单等
 * 思路：
 *      当用户点击菜单时获取用户openId
 *      若数据库中没有该用户的信息，需要进行授权，然后获取用户信息。
 *      若数据库中有该用户的信息，不需要授权，直接从数据库中获取用户信息。
 */
@Controller
@RequestMapping("/menu")
@Api(value = "/menu",description = "about wen xin")
public class WeiXinController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private CommonService commonService;

    @Autowired
    private WeixinService weixinService;

    @RequestMapping(value = "auth", method = RequestMethod.GET)
    public String auth(@RequestParam(required =false) String code, @RequestParam(required =false) String state, Model model) throws Exception{
        if(!CoreString.isNull(code) && !CoreString.isNull(state)){
            if(state.equals(AllURL.AuthState_UserInfo)){ /*授权*/
                CommonLog.writeAlisureLog(AllURL.PathLog, "开始进行授权:" +  code + ":" + state);
                Map<String, String> nodeValues = weixinService.getAuthOpenId_AccessToken(code); /*获取授权access_token和openid*/
                if(nodeValues != null){
                    String access_token = nodeValues.get(WeixinService.access_token);
                    String openid = nodeValues.get(WeixinService.openid);
                    CommonLog.writeAlisureLog(AllURL.PathLog, "授权成功:" +  access_token + ":" + openid);
                    boolean result = weixinService.getAuthUserInfo(access_token, openid);/*拉取信息*/
                    if(result){ /*成功拉取信息*/
                        InfoUser infoUser = commonService.getUser(openid);
                        commonService.setLogin(request, infoUser);/*设置session*/
                        CommonLog.writeAlisureLog(AllURL.PathLog, "成功拉取用户信息:" +  infoUser.toString());
                        return "redirect:" + AllURL.UserPageURL; /*重定向到用户中心*/
                    }
                }
            }else{ /*点击菜单，下面的代码的目的是设置登录状态*/
                String openId = weixinService.getAuthOpenId(code);
                CommonLog.writeAlisureLog(AllURL.PathLog, "用户点击菜单:" +  openId + ":" + state);
                if(!CoreString.isNull(openId)) { /*得到了openId*/
                    /*判断是否授过权：数据库中含有该用户的信息则表明已经授过权了*/
                    InfoUser infoUser = commonService.getUser(openId);
                    if(infoUser == null){/*没有该用户:重定向进行授权*/
                        CommonLog.writeAlisureLog(AllURL.PathLog, "没有该用户，需要进行授权:" +  openId);
                        return "redirect:" + AllURL.URLAuthGetUserInfo.replace(AllURL.AuthState, AllURL.AuthState_UserInfo);
                    }else{ /*已经授过权了*/
                        commonService.setLogin(request, infoUser);/*设置session*/
                        CommonLog.writeAlisureLog(AllURL.PathLog, "点击菜单，一切正常，进行重定向:" +  commonService.getLogin(request).toString());
                        /*重定向*/
                        if(state.equals(AllURL.AuthState_Menu1)){
                            return "redirect:" + AllURL.IndexPageURL;
                        }else if(state.equals(AllURL.AuthState_Menu2)){
                            return "redirect:" + AllURL.PublishPageURL;
                        }else if(state.equals(AllURL.AuthState_Menu3)){
                            return "redirect:" + AllURL.UserPageURL;
                        }
                    }
                }
            }
        }
        return "redirect:" + AllURL.ErrorPageURL;
    }

}
