package com.alisure.entity;

import org.springframework.jdbc.core.RowMapper;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InfoComment implements RowMapper<InfoComment>,Serializable {
    private int id;
    private int taskId;
    private String content;
    private String commentDate;
    private int commentGrade;
    private int commentType;

    public InfoComment() {

    }

    public InfoComment(int id, int taskId, String content, String commentDate, int commentGrade, int commentType) {
        this.id = id;
        this.taskId = taskId;
        this.content = content;
        this.commentDate = commentDate;
        this.commentGrade = commentGrade;
        this.commentType = commentType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(String commentDate) {
        this.commentDate = commentDate;
    }

    public int getCommentGrade() {
        return commentGrade;
    }

    public void setCommentGrade(int commentGrade) {
        this.commentGrade = commentGrade;
    }

    public int getCommentType() {
        return commentType;
    }

    public void setCommentType(int commentType) {
        this.commentType = commentType;
    }

    public InfoComment mapRow(ResultSet resultSet, int i) throws SQLException {
        return new InfoComment(
                resultSet.getInt("id"),
                resultSet.getInt("taskId"),
                resultSet.getString("content"),
                resultSet.getString("commentDate"),
                resultSet.getInt("commentGrade"),
                resultSet.getInt("commentType")
        );
    }
}