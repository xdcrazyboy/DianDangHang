package com.alisure.weixin.template;

import java.util.Map;

/**
 * Created by ALISURE on 2017/10/2.
 */
public class TemplateContent {
    private String template_id;//模板消息id
    private String touser;//用户openid
    private String url;//url置空，则发送成功后，点击模板消息会进入一个空白页面，或无法点击
    private String topcolor;//标题颜色
    private Map<String, TemplateData> data;//详细内容

    public TemplateContent() {

    }

    public TemplateContent(String template_id, String touser, String url) {
        this.template_id = template_id;
        this.touser = touser;
        this.url = url;
        this.topcolor = "#173177";
    }

    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTopcolor() {
        return topcolor;
    }

    public void setTopcolor(String topcolor) {
        this.topcolor = topcolor;
    }

    public Map<String, TemplateData> getData() {
        return data;
    }

    public void setData(Map<String, TemplateData> data) {
        this.data = data;
    }
}