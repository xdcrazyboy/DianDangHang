package com.alisure.service;

import com.alisure.dao.UserDao;
import com.alisure.entity.InfoMoneyChange;
import com.alisure.entity.InfoUser;
import com.alisure.tool.core.CoreNetwork;
import com.alisure.tool.core.CorePrint;
import com.alisure.tool.core.CoreString;
import com.alisure.tool.core.CoreTime;
import com.alisure.weixin.AllURL;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("weixinService")
public class WeixinService {

    @Autowired
    UserDao userDao;
    @Autowired
    CommonService commonService;
    @Autowired
    UserService userService;

    public static final String openid = "openid";
    public static final String access_token = "access_token";
    public static final String nickname = "nickname";
    public static final String sex = "sex";
    public static final String province = "province";
    public static final String city = "city";
    public static final String country = "country";
    public static final String headimgurl = "headimgurl";

    /**
     * 获取OpenId:用于登录
     */
    public String getAuthOpenId(String code){
        List<String> nodeNames = new ArrayList<String>();
        nodeNames.add(openid);
        Map<String, String> nodeValues = getStringByUrl(AllURL.URLAuthAccessToken.replace(AllURL.Auth_Code, code), nodeNames);
        if (nodeValues != null) {
            return nodeValues.get(openid);
        }
        return null;
    }

    /**
     * 用于授权
     */
    public Map<String, String> getAuthOpenId_AccessToken(String code){
        List<String> nodeNames = new ArrayList<String>();
        nodeNames.add(openid);
        nodeNames.add(access_token);
        return getStringByUrl(AllURL.URLAuthAccessToken.replace(AllURL.Auth_Code, code), nodeNames);
    }

    /**
     * 获取用户的信息
     */
    public boolean getAuthUserInfo(String accessToken, String openId){
        List<String> nodeNames = new ArrayList<String>();
        nodeNames.add(openid);
        nodeNames.add(nickname);
        nodeNames.add(sex);
        nodeNames.add(province);
        nodeNames.add(city);
        nodeNames.add(country);
        nodeNames.add(headimgurl);
        Map<String, String> nodeValues = getStringByUrl(AllURL.URLGetUserInfo.replace(AllURL.AuthOpenID, openId)
                .replace(AllURL.AccessToken, accessToken), nodeNames);
        if(nodeValues != null){
            String sex_s = nodeValues.get(sex);
            if("1".equals(sex_s)) sex_s = "男";
            else if("2".equals(sex_s)) sex_s = "女";
            else if("0".equals(sex_s)) sex_s = "未知";
            InfoUser infoUser = new InfoUser(nodeValues.get(openid), nodeValues.get(headimgurl), nodeValues.get(province),
                    nodeValues.get(nickname), sex_s, nodeValues.get(city), nodeValues.get(country), CoreTime.getDataTime());

            InfoUser oldInfoUser = commonService.getUser(nodeValues.get(openid));
            if(oldInfoUser != null && oldInfoUser.getUid() > 0){ // 用户存在
                infoUser.setUid(oldInfoUser.getUid());
                return userDao.updateUserInfo(infoUser.getOpenid(), infoUser.getNickname(), infoUser.getSex(), infoUser.getProvince(),
                        infoUser.getCity(), infoUser.getCountry(), infoUser.getHeadimgurl(), infoUser.getTime(), infoUser.getUid());
            }else{ // 用户不存在
                int uid = userDao.insertUserInfo(infoUser.getOpenid(), infoUser.getNickname(), infoUser.getSex(), infoUser.getProvince(),
                        infoUser.getCity(), infoUser.getCountry(), infoUser.getHeadimgurl(), infoUser.getTime());
                // 此时需要给用户增加金币
                if(uid > 0) {
                    return userService.updateCoinsByOther(uid, 10, InfoMoneyChange.ChangeReason.Reason_New_User);
                }
            }
        }
        return false;
    }

    private static Map<String, String> getStringByUrl(String url, List<String> nodeNames){
        if(CoreString.isNull(url) || nodeNames == null || nodeNames.size() == 0)return null;
        try {
            Map<String, String> nodeValues = new HashMap<String, String>();
            JsonParser jsonParser = new JsonFactory().createParser(new CoreNetwork(CoreNetwork.Utf8).getResult(url));
            while (!jsonParser.isClosed()){
                jsonParser.nextValue();
                for (String nodeName : nodeNames) {
                    if(nodeName.equals(jsonParser.getCurrentName())){
                        nodeValues.put(nodeName, jsonParser.getText());
                    }
                }
            }
            return nodeValues;
        } catch (IOException e) {
            CorePrint.printlnTime("出错：getAuthOpenId-》getStringByUrl：" + url);
            e.printStackTrace();
        }
        return null;
    }

}
