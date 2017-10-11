package com.alisure.weixin.image;

import com.alisure.tool.core.CoreAlisure;
import com.alisure.tool.core.CoreImage;
import com.alisure.tool.core.CoreTime;
import com.alisure.weixin.AllURL;
import com.alisure.weixin.task.GetWeiXinInf;

/**
 * Created by ALISURE on 2017/4/28.
 */
public class ImageUtil {

    private String imageName;
    private final String TypeJPG = ".jpg";

    /**
     * 从微信服务器上下载图片
     * @param accessToken
     * @param mediaId
     */
    public boolean getWeiXinImage(String accessToken, String mediaId){
        /*组装URL*/
        String url = AllURL.URLDownloadMedia.replace(AllURL.AccessToken, accessToken).replace(AllURL.MediaId, mediaId);
        /*组装图片路径*/
        String path = setImageName(mediaId, TypeJPG);
        boolean isOk = false;

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

        return isOk;
    }

    /**
     * 组装图片名称
     * @param mediaId
     * @param type
     * @return
     */
    private String setImageName(String mediaId, String type){
        this.imageName = AllURL.PathUserUploadImage + mediaId + type;
        return AllURL.PathUseDir + this.imageName;
    }

    public String getImageName(){
        return imageName;
    }

    public static void main(String[] args) {
        String mediaId = "5ZLN74NyuFGNnmn_xcuab1In_LCfsZvAFf-7-LfwdKhEamlYRMmYmocg0Yh2O0Oa";
        new ImageUtil().getWeiXinImage("7MdMbbFOhJWiCVFZoZeR0boi09D8dVl2l7dG_hYZnNdo04RBSl_SrSdCixtiKSuMDN42FhwZ-aGYUH0lwZndRhn89BN_3jN_FtCtCu6I-LIAgtHX7GZSboMrwMqBVJ4NPHSiAFAFXI", mediaId);
    }
}
