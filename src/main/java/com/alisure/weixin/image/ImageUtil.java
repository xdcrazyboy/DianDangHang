package com.alisure.weixin.image;

import com.alisure.tool.core.CoreAlisure;
import com.alisure.tool.core.CoreImage;
import com.alisure.tool.core.CoreTime;
import com.alisure.weixin.task.GetWeiXinInf;

/**
 * Created by ALISURE on 2017/4/28.
 */
public class ImageUtil {

    private String imageName;
    private final String TypeJPG = ".jpg";
    private final String Path = "web\\resources\\image\\upload\\";

    private static final String mediaId = "AeKuTo0FLW1_244QvfEGeTpm1cESDuhZ3oAX__YYRDyvcVnyE2iGTW2k_GMqaT25";

    /**
     * 从微信服务器上下载图片
     * @param accessToken
     * @param mediaId
     */
    public boolean getWeiXinImage(String accessToken, String mediaId){
        /*组装URL*/
        String url = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=" + accessToken + "&media_id=" + mediaId;
        /*组装图片路径*/
        String path = setImageName(mediaId, TypeJPG);
        boolean isOk = false;
        try {
            System.out.println("--------------------------------------");
            System.out.println(CoreTime.getDataTime());
            System.out.println("download image from weixin server");
            System.out.println(url);
            System.out.println(path);

            isOk = CoreImage.downImage(url,path);

            if(isOk) System.out.println("download ok");
            else System.out.println("download error");
            System.out.println("--------------------------------------");
            System.out.println();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return isOk;
    }

    /**
     * 组装图片名称
     * @param mediaId
     * @param type
     * @return
     */
    private String setImageName(String mediaId, String type){
        this.imageName = Path + mediaId + type;
        return CoreAlisure.getPeath() + this.imageName;
    }

    public String getImageName(){
        return imageName;
    }

    public static void main(String[] args) {
        new ImageUtil().getWeiXinImage(new GetWeiXinInf().getAccessToken(), mediaId);
    }
}
