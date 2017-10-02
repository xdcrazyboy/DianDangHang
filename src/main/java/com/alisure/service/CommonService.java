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

    /**
     * 获取用户的id
     * @param openid
     * @return
     */
    public int getUserId(String openid){
        return commonDao.getUserId(openid);
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
     * 获取用户ID
     * @return
     */
    public int getUserId(HttpServletRequest request){
        return ((InfoUser)(request.getSession().getAttribute(AllURL.Session_User))).getId();
    }

    /**
     * 返回OpenId
     * @param request
     * @return
     */
    public String getOpenId(HttpServletRequest request){
        InfoUser userInfo = (InfoUser)(request.getSession().getAttribute(AllURL.Session_User));
        return userInfo == null ? "" : userInfo.getOpenid();
    }

    /**
     * 返回OpenId
     * @param id
     * @return
     */
    public String getOpenId(int id){
        if(id <=0) return null;
        return commonDao.getUserOpenid(id);
    }

    /**
     * 获取用户信息
     * @param id
     * @return
     */
    public InfoUser getUser(int id){
        if(id <=0) return null;
        return commonDao.getUser(id);
    }

    /**
     * 获取用户信息
     * @param openid
     * @return
     */
    public InfoUser getUser(String openid){
        if(CoreString.isNull(openid)) return null;
        return commonDao.getUser(openid);
    }
}
