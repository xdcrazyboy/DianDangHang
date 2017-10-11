package com.alisure.entity;

import org.springframework.jdbc.core.RowMapper;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InfoSchool implements RowMapper<InfoSchool>,Serializable {
    private int schoolId;
    private int provinceId;
    private String province;
    private String schoolName;

    public InfoSchool() {

    }

    public InfoSchool(int schoolId, int provinceId, String province, String schoolName) {
        this.schoolId = schoolId;
        this.provinceId = provinceId;
        this.province = province;
        this.schoolName = schoolName;
    }

    public int getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(int schoolId) {
        this.schoolId = schoolId;
    }

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public InfoSchool mapRow(ResultSet resultSet, int i) throws SQLException {
        return new InfoSchool(
                resultSet.getInt("schoolId"),
                resultSet.getInt("provinceId"),
                resultSet.getString("province"),
                resultSet.getString("schoolName")
        );
    }
}
