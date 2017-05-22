package com.alisure.result;

import com.alisure.weixin.check.JSSDKSignUtil;

/**
 * 签名信息相关类，用于返回给前端进行初始化
 * Created by ALISURE on 2017/4/30.
 */
public class Signature {

    private String appId; // 必填，公众号的唯一标识
    private String timestamp; // 必填，生成签名的时间戳
    private String nonceStr; // 必填，生成签名的随机串
    private String signature;// 必填，签名，见附录1

    public Signature(String signature) {
        this.signature = signature;

        this.appId = JSSDKSignUtil.AppId;
        this.timestamp = JSSDKSignUtil.Timestamp;
        this.nonceStr = JSSDKSignUtil.Noncestr;
    }

    public String getAppId() {
        return appId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}