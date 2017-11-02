package com.alisure.service;

import com.alisure.dao.CommonDao;
import com.alisure.entity.InfoUser;
import com.alisure.tool.core.CoreString;
import com.alisure.weixin.AllURL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service("commonService")
public class CommonService {

    @Autowired
    CommonDao commonDao;

    public int getUserId(String openid){
        return commonDao.getUserId(openid);
    }
    public int getUserId(HttpServletRequest request){
        InfoUser userInfo = getLogin(request);
        return userInfo == null ? 0 : userInfo.getUid();
    }

    public String getOpenId(int id){
        if(id <=0) return null;
        return commonDao.getUserOpenid(id);
    }
    public String getOpenId(HttpServletRequest request){
        InfoUser userInfo = getLogin(request);
        return userInfo == null ? "" : userInfo.getOpenid();
    }

    public InfoUser getUser(int id){
        if(id <=0) return null;
        return commonDao.getUser(id);
    }
    public InfoUser getUser(String openid){
        if(CoreString.isNull(openid)) return null;
        return commonDao.getUser(openid);
    }

    /**
     * 获取用户的任务学校
     * @param userId
     * @return
     */
    public String getSchool(int userId){
        return commonDao.getSchool(userId);
    }

    /**
     * 设置登录
     */
    public void setLogin(HttpServletRequest  request, InfoUser infoUser){
        request.getSession().setAttribute(AllURL.Session_Login, infoUser);
    }

    /**
     * 获取登录
     */
    public InfoUser getLogin(HttpServletRequest  request){
        return (InfoUser) request.getSession().getAttribute(AllURL.Session_Login);
    }
}
