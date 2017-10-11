package com.alisure.entity;

import org.springframework.jdbc.core.RowMapper;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InfoSearchRecord implements RowMapper<InfoSearchRecord>,Serializable {
    private int searchId;
    private String searchRecord;
    private String searchTime;
    private int uid;

    public InfoSearchRecord() {

    }

    public InfoSearchRecord(int searchId, String searchRecord, String searchTime, int uid) {
        this.searchId = searchId;
        this.searchRecord = searchRecord;
        this.searchTime = searchTime;
        this.uid = uid;
    }

    public int getSearchId() {
        return searchId;
    }

    public void setSearchId(int searchId) {
        this.searchId = searchId;
    }

    public String getSearchRecord() {
        return searchRecord;
    }

    public void setSearchRecord(String searchRecord) {
        this.searchRecord = searchRecord;
    }

    public String getSearchTime() {
        return searchTime;
    }

    public void setSearchTime(String searchTime) {
        this.searchTime = searchTime;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public InfoSearchRecord mapRow(ResultSet resultSet, int i) throws SQLException {
        return  new InfoSearchRecord(
                resultSet.getInt("searchId"),
                resultSet.getString("searchRecord"),
                resultSet.getString("searchTime"),
                resultSet.getInt("uid")
        );
    }
}
