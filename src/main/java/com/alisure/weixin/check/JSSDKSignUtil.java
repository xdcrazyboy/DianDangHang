package com.alisure.weixin.check;

import com.alisure.tool.core.CoreNetwork;
import com.alisure.weixin.AllURL;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by ALISURE on 2017/4/27.
 */
public class JSSDKSignUtil {

    /**
     * 获得签名
     * @param jsapi_ticket
     * @param url
     * @return
     */
    public static String getSignature(String jsapi_ticket, String url) {
        String content = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + AllURL.Noncestr
                + "&timestamp=" + AllURL.Timestamp + "&url=" + url;

        MessageDigest md;
        String tmpStr = "";

        try {
            md = MessageDigest.getInstance("SHA-1");
            byte[] digest = md.digest(content.getBytes());
            tmpStr = byteToStr(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return tmpStr.toLowerCase();
    }

    /**
     * 将字节数组转换为十六进制字符串
     *
     * @param byteArray
     * @return
     */
    private static String byteToStr(byte[] byteArray) {
        String strDigest = "";
        for (int i = 0; i < byteArray.length; i++) {
            strDigest += byteToHexStr(byteArray[i]);
        }
        return strDigest;
    }

    /**
     * 将字节转换为十六进制字符串
     * @param mByte
     * @return
     */
    private static String byteToHexStr(byte mByte) {
        char[] digit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] tempArr = new char[2];
        tempArr[0] = digit[(mByte >>> 4) & 0X0F];
        tempArr[1] = digit[mByte & 0X0F];

        return new String(tempArr);
    }


    /**
     * 获取access_token
     * @return
     */
    public static String getAccessToken(){
        String accessToken = getStringByUrl(AllURL.URLAccessToken, "access_token");
        System.out.println("accessToken:\n" + accessToken);
        return accessToken;
    }

    /**
     * 获取ticket
     * @return
     */
    public static String getTicket(String accessToken){
        String ticket = getStringByUrl(AllURL.URLTicket.replace(AllURL.AccessToken, accessToken), "ticket");
        System.out.println("ticket:\n" + ticket);
        return ticket;
    }

    private static String getStringByUrl(String url, String nodeName){
        if(url == null || nodeName == null || "".equals(url) || "".equals(nodeName)){
            return "";
        }
        try {
            CoreNetwork coreNetwork = new CoreNetwork(CoreNetwork.Utf8);
            JsonParser jsonParser = new JsonFactory().createParser(coreNetwork.getResult(url));

            while (!jsonParser.isClosed()){
                jsonParser.nextValue();
                if(nodeName.equals(jsonParser.getCurrentName())){
                    return  jsonParser.getText();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }


    public static void main(String[] args) {

        String accessToken = getAccessToken();
        System.out.println("accessToken:\n" + accessToken);

        String ticket = getTicket(accessToken);
        System.out.println("ticket:\n" + ticket);

        String url = "http://www.alisure.xyz/ddh/web/wechat/view/image.html";
        System.out.println(url);
        String signature = getSignature(ticket, url);
        System.out.println("signature:\n" + signature);

        url = "http://www.alisure.xyz/ddh/web/wechat/demo/Choose-image.html";
        System.out.println(url);
        signature = getSignature(ticket, url);
        System.out.println("signature:\n" + signature);
    }
}
