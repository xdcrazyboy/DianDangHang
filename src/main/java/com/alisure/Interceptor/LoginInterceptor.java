package com.alisure.Interceptor;

import com.alisure.entity.InfoUser;
import com.alisure.exception.LoginException;
import com.alisure.service.CommonService;
import com.alisure.tool.common.CommonLog;
import com.alisure.tool.core.CoreReflect;
import com.alisure.tool.core.CoreString;
import com.alisure.weixin.AllURL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class LoginInterceptor  extends HandlerInterceptorAdapter {

    @Autowired
    CommonService commonService;

    private List<String> excludedUrls;
    public List<String> getExcludedUrls() {
        return excludedUrls;
    }
    public void setExcludedUrls(List<String> excludedUrls) {
        this.excludedUrls = excludedUrls;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /*排除不需要登录的URL*/
        String  requestUrl = request.getServletPath();
        for (String url : excludedUrls) {
            if (requestUrl.equals(url) || requestUrl.contains(url)){
                /*不需要登录*/
                System.out.println("preHandle:this URL not check:"+requestUrl);
                return super.preHandle(request, response, handler);
            }
        }
        System.out.println("preHandle:checking URL:" + requestUrl);

        /*测试*/
        if(!CoreString.isNull(AllURL.OpenId)){ // 测试
            commonService.setLogin(request, commonService.getUser(AllURL.OpenId));
            CommonLog.writeAlisureLog(AllURL.PathLog, "测试账号:" +  commonService.getLogin(request).toString());
        }

        /*检查是否登录*/
        if(commonService.getLogin(request) == null){
            CommonLog.writeAlisureLog(AllURL.PathLog, "没有登录:" + requestUrl);
            throw new LoginException();
        }

        // 执行到此说明已经登录成功
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }
}

