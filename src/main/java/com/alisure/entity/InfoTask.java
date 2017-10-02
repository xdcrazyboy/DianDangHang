package com.alisure.entity;

import org.springframework.jdbc.core.RowMapper;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 注意：
 *      接手者和发布者的信息，不在这里给出，需要在外面适当的地方给出。
 */
public class InfoTask implements RowMapper<InfoTask>,Serializable {
    private int tid;
    private int coins;//金币数
    private String title;// 任务名称
    private String content;//任务内容
    private String pubTime;//发布时间
    private String startTime;//开始时间
    private String endTime;//截止时间
    private String place;//任务地点
    private String qq;
    private String weixin;
    private String telephone;
    private String rewards;//酬劳
    private int catId; // 类别
    private String words;//留言
    private String images;//图片
    private String school;//学校

    private int recId;//接手者ID
    private int recStateId;//接手状态
    private int pubId;//发布者ID
    private int pubStateId;//发布状态
    private String recTime;//接手时间
    private String recCompleteTime;//接手者完成时间
    private String pubCompleteTime;//发布者完成时间，即整个任务的结束时间
    private String recCancelTime;//接手者取消时间
    private String pubCancelTime;//发布者取消时间

    private InfoCategory category; //类别

    private InfoUser pub;//发布者信息
    private InfoUser rec;//接手者信息

    public InfoTask() {

    }

    public InfoTask(int tid, int coins, String title, String content, String pubTime,
                    String startTime, String endTime, String place, String qq, String weixin,
                    String telephone, String rewards, int catId, String words, String images,
                    String school, int recId, int recStateId, int pubId, int pubStateId,
                    String recTime, String recCompleteTime, String pubCompleteTime, String recCancelTime, String pubCancelTime) {
        this.tid = tid;
        this.coins = coins;
        this.title = title;
        this.content = content;
        this.pubTime = pubTime;
        this.startTime = startTime;
        this.endTime = endTime;
        this.place = place;
        this.qq = qq;
        this.weixin = weixin;
        this.telephone = telephone;
        this.rewards = rewards;
        this.catId = catId;
        this.words = words;
        this.images = images;
        this.school = school;
        this.recId = recId;
        this.recStateId = recStateId;
        this.pubId = pubId;
        this.pubStateId = pubStateId;
        this.recTime = recTime;
        this.recCompleteTime = recCompleteTime;
        this.pubCompleteTime = pubCompleteTime;
        this.recCancelTime = recCancelTime;
        this.pubCancelTime = pubCancelTime;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPubTime() {
        return pubTime;
    }

    public void setPubTime(String pubTime) {
        this.pubTime = pubTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getRewards() {
        return rewards;
    }

    public void setRewards(String rewards) {
        this.rewards = rewards;
    }

    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public int getRecId() {
        return recId;
    }

    public void setRecId(int recId) {
        this.recId = recId;
    }

    public int getRecStateId() {
        return recStateId;
    }

    public void setRecStateId(int recStateId) {
        this.recStateId = recStateId;
    }

    public int getPubId() {
        return pubId;
    }

    public void setPubId(int pubId) {
        this.pubId = pubId;
    }

    public int getPubStateId() {
        return pubStateId;
    }

    public void setPubStateId(int pubStateId) {
        this.pubStateId = pubStateId;
    }

    public String getRecTime() {
        return recTime;
    }

    public void setRecTime(String recTime) {
        this.recTime = recTime;
    }

    public String getRecCompleteTime() {
        return recCompleteTime;
    }

    public void setRecCompleteTime(String recCompleteTime) {
        this.recCompleteTime = recCompleteTime;
    }

    public String getPubCompleteTime() {
        return pubCompleteTime;
    }

    public void setPubCompleteTime(String pubCompleteTime) {
        this.pubCompleteTime = pubCompleteTime;
    }

    public String getRecCancelTime() {
        return recCancelTime;
    }

    public void setRecCancelTime(String recCancelTime) {
        this.recCancelTime = recCancelTime;
    }

    public String getPubCancelTime() {
        return pubCancelTime;
    }

    public void setPubCancelTime(String pubCancelTime) {
        this.pubCancelTime = pubCancelTime;
    }

    public InfoCategory getCategory() {
        return category;
    }

    public void setCategory(InfoCategory category) {
        this.category = category;
    }

    public InfoUser getPub() {
        return pub;
    }

    public void setPub(InfoUser pub) {
        this.pub = pub;
    }

    public InfoUser getRec() {
        return rec;
    }

    public void setRec(InfoUser rec) {
        this.rec = rec;
    }

    public InfoTask mapRow(ResultSet resultSet, int i) throws SQLException {
        InfoTask taskInfo = new InfoTask(
                resultSet.getInt("tid"),
                resultSet.getInt("coins"),
                resultSet.getString("title"),
                resultSet.getString("content"),
                resultSet.getString("pubTime"),
                resultSet.getString("startTime"),
                resultSet.getString("endTime"),
                resultSet.getString("place"),
                resultSet.getString("qq"),
                resultSet.getString("weixin"),
                resultSet.getString("telephone"),
                resultSet.getString("rewards"),
                resultSet.getInt("catId"),
                resultSet.getString("words"),
                resultSet.getString("images"),
                resultSet.getString("school"),
                resultSet.getInt("recId"),
                resultSet.getInt("recStateId"),
                resultSet.getInt("pubId"),
                resultSet.getInt("pubStateId"),
                resultSet.getString("recTime"),
                resultSet.getString("recCompleteTime"),
                resultSet.getString("pubCompleteTime"),
                resultSet.getString("recCancelTime"),
                resultSet.getString("pubCancelTime")
        );

        InfoCategory category = new InfoCategory(resultSet.getInt("catId"),
                resultSet.getString("category"),resultSet.getString("categoryIcon"));
        taskInfo.setCategory(category);
        return taskInfo;
    }
}
