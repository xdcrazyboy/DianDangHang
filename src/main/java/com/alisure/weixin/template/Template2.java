package com.alisure.weixin.template;

import com.alisure.entity.InfoTask;
import com.alisure.tool.core.CoreNetwork2;
import com.alisure.weixin.AllURL;
import com.alisure.weixin.task.GetWeiXinInf;

/**
 * Created by ALISURE on 2017/9/26.
 * 给用户发送信息
 */
public class Template2 {

    /*发布任务成功*/
    private static final String TemplateID_PubTask = "ayaYvsoVHLldVTfTqE8jbDnkoSyj9AOd8CZxFydt_iY";
    /*接手任务成功:发布者*/
    private static final String TemplateID_RecTask_pub = "4rvT3b7Wn_y4znsgydT32bS_eoVC1axY10wP6doe9xo";
    /*接手任务成功:接手者*/
    private static final String TemplateID_RecTask_rec = "PtdoAxL_XjtUxyIVYh3ik-ioawQkPa3mMvbZsRq7lnE";
    /*任务被取消*/
    private static final String TemplateID_CancelTask = "nklpPV-WgTOQM9KTbM__I5FA3c7Qgm4tgUe6rvGHeG8";
    /*任务完成*/
    private static final String TemplateID_FinshTask = "JsIvaaHyAnpvWX1-MaYrG6jyYRYjE90FKhQX9SM6zfY";
    /*任务结束*/
    private static final String TemplateID_EndTask = "1FKAiQMMyJdnnr7ljCF3vxlq36ahT5kI6zKGbbX5-VE";

    private static boolean sendTemplate(String body){
        try{
//            String result = CoreNetwork2.sendPost(AllURL.URLSendTemplate.replace(AllURL.AccessToken, AllURL.accessTokenTest), body);
            String result = CoreNetwork2.sendPost(AllURL.URLSendTemplate.replace(AllURL.AccessToken, new GetWeiXinInf().getAccessToken()), body);
            System.out.println(result);
            return result.contains("ok");
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    private static boolean setTemplate(){
        String body = "{\"touser\": \"oNf5J0lRJ49tBtj3Ox8PfAOcxRTo\"," +
                "\"template_id\": \"WwVDqrpRVw9cMRZh2oiWJXW_xdp_AeqnTEYpDrpRF_A\"," +
                "\"topcolor\": \"#FF0000\"}";
        return sendTemplate(body);
    }

    /* 发布任务成功 */
    public static boolean pubTaskOK(String toUser, InfoTask task){
        String body = "{\"touser\": \"" + toUser + "\",\"template_id\": \"" + TemplateID_PubTask + "\",\"url\": \"" + AllURL.Template_Task_URL.replace(AllURL.TID, String.valueOf(task.getTid())) + "\",\"topcolor\": \"#FF0000\"," +
                "\"data\":{\"task_name\": {\"value\":\"" + task.getTitle() + "\",\"color\":\"#173177\"}}}";
        return sendTemplate(body);
    }
    /* 接手任务成功:发布者 */
    public static boolean recTaskOK_pub(String toUser, InfoTask task, String recName){
        String body = "{\"touser\": \"" + toUser + "\",\"template_id\": \"" + TemplateID_RecTask_pub + "\",\"url\": \"" + AllURL.Template_Task_URL.replace(AllURL.TID, String.valueOf(task.getTid())) + "\",\"topcolor\": \"#FF0000\"," +
                "\"data\":{\"task_name\": {\"value\":\"" + task.getTitle() + "\",\"color\":\"#173177\"},\"rec_name\": {\"value\":\"" + recName + "\",\"color\":\"#173177\"}}}";
        return sendTemplate(body);
    }
    /* 接手任务成功:接手者 */
    public static boolean recTaskOK_rec(String toUser, InfoTask task, String pubName){
        String body = "{\"touser\": \"" + toUser + "\",\"template_id\": \"" + TemplateID_RecTask_rec + "\",\"url\": \"" + AllURL.Template_Task_URL.replace(AllURL.TID, String.valueOf(task.getTid())) + "\",\"topcolor\": \"#FF0000\"," +
                "\"data\":{\"task_name\": {\"value\":\"" + task.getTitle() + "\",\"color\":\"#173177\"},\"pub_name\": {\"value\":\"" + pubName + "\",\"color\":\"#173177\"}}}";
        return sendTemplate(body);
    }
    /* 任务被取消 */
    public static boolean cancelTaskOK(String toUser, InfoTask task){
        String body = "{\"touser\": \"" + toUser + "\",\"template_id\": \"" + TemplateID_CancelTask + "\",\"url\": \"" + AllURL.Template_Task_URL.replace(AllURL.TID, String.valueOf(task.getTid())) + "\",\"topcolor\": \"#FF0000\"," +
                "\"data\":{\"task_name\": {\"value\":\"" + task.getTitle() + "\",\"color\":\"#173177\"}}}";
        return sendTemplate(body);
    }
    /* 任务完成 */
    public static boolean finshTaskOK(String toUser, InfoTask task){
        String body = "{\"touser\": \"" + toUser + "\",\"template_id\": \"" + TemplateID_FinshTask + "\",\"url\": \"" + AllURL.Template_Task_URL.replace(AllURL.TID, String.valueOf(task.getTid())) + "\",\"topcolor\": \"#FF0000\"," +
                "\"data\":{\"task_name\": {\"value\":\"" + task.getTitle() + "\",\"color\":\"#173177\"}}}";
        return sendTemplate(body);
    }
    /* 任务结束 */
    public static boolean endTaskOK(String toUser, InfoTask task){
        String body = "{\"touser\": \"" + toUser + "\",\"template_id\": \"" + TemplateID_EndTask + "\",\"url\": \"" + AllURL.Template_Task_URL.replace(AllURL.TID, String.valueOf(task.getTid())) + "\",\"topcolor\": \"#FF0000\"," +
                "\"data\":{\"task_name\": {\"value\":\"" + task.getTitle() + "\",\"color\":\"#173177\"}}}";
        return sendTemplate(body);
    }

    public static void main(String[] args) {
        System.out.println(Template2.setTemplate());
    }
}
