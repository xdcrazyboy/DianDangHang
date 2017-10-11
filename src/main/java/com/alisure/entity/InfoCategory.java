package com.alisure.entity;

import org.springframework.jdbc.core.RowMapper;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InfoCategory implements RowMapper<InfoCategory>,Serializable {
    private int catId;
    private String category;
    private String categoryIcon;

    public InfoCategory() {

    }

    public InfoCategory(int catId, String category, String categoryIcon) {
        this.catId = catId;
        this.category = category;
        this.categoryIcon = categoryIcon;
    }

    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategoryIcon() {
        return categoryIcon;
    }

    public void setCategoryIcon(String categoryIcon) {
        this.categoryIcon = categoryIcon;
    }

    public InfoCategory mapRow(ResultSet resultSet, int i) throws SQLException {
        return new InfoCategory(
                resultSet.getInt("catId"),
                resultSet.getString("category"),
                resultSet.getString("categoryIcon")
        );
    }
}