package com.alisure.entity;

import org.springframework.jdbc.core.RowMapper;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InfoUser implements RowMapper<InfoUser>, Serializable {

    private int id;
    private String card;
    private int goldCoins;
    private String icon;
    private String location;
    private String nickname;
    private String openid;
    private String phone;
    private String qq;
    private String school;
    private String sex;
    private String weixin;
    private int areaId;
    private String taskSchool;

    public class Table{
        public final static String tableName = "user";

        public final static String id = "id";
        public final static String card = "card";
        public final static String goldCoins = "goldCoins";
        public final static String icon = "icon";
        public final static String location = "location";
        public final static String nickname = "nickname";
        public final static String openid = "openid";
        public final static String phone = "phone";
        public final static String qq = "qq";
        public final static String school = "school";
        public final static String sex = "sex";
        public final static String weixin = "weixin";
        public final static String taskSchool = "taskSchool";
        public final static String areaId = "areaId";
    }

    public InfoUser() {

    }

    public InfoUser(int id, String card, int goldCoins,
                    String icon, String location, String nickname,
                    String openid, String phone, String qq,
                    String school, String sex, String weixin, String taskSchool,
                    int areaId) {
        this.id = id;
        this.card = card;
        this.goldCoins = goldCoins;
        this.icon = icon;
        this.location = location;
        this.nickname = nickname;
        this.openid = openid;
        this.phone = phone;
        this.qq = qq;
        this.school = school;
        this.sex = sex;
        this.weixin = weixin;
        this.areaId = areaId;
        this.taskSchool = taskSchool;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public int getGoldCoins() {
        return goldCoins;
    }

    public void setGoldCoins(int goldCoins) {
        this.goldCoins = goldCoins;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin;
    }

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public String getTaskSchool() {
        return taskSchool;
    }

    public void setTaskSchool(String taskSchool) {
        this.taskSchool = taskSchool;
    }

    public InfoUser mapRow(ResultSet resultSet, int i) throws SQLException {
        return new InfoUser(
                resultSet.getInt(Table.id), resultSet.getString(Table.card), resultSet.getInt(Table.goldCoins),
                resultSet.getString(Table.icon), resultSet.getString(Table.location), resultSet.getString(Table.nickname),
                resultSet.getString(Table.openid), resultSet.getString(Table.phone), resultSet.getString(Table.qq),
                resultSet.getString(Table.school), resultSet.getString(Table.sex), resultSet.getString(Table.weixin),
                resultSet.getString(Table.taskSchool), resultSet.getInt(Table.areaId));
    }
}
