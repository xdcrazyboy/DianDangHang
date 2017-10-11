package com.alisure.dao;

import com.alisure.entity.InfoComment;
import com.alisure.tool.core.CoreTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("commentDao")
public class CommentDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public boolean comment(int tid, String commentContent, int commentGrade, int commentType) {
        String sql = "insert into t_comment(tid, commentContent, commentTime, commentGrade, commentType) values(?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, new Object[]{tid, commentContent, CoreTime.getDataTime(), commentGrade, commentType}) > 0;
    }

    public List<InfoComment> getComment(int tid) {
        String sql = "select * from t_comment where tid=?";
        return jdbcTemplate.query(sql, new Object[]{tid}, new InfoComment());
    }

    public boolean complaint(int tid, String commentContent, int complaintType) {
        String sql = "insert into complaint(tid, commentContent, commentTime, complaintType) values(?, ?, ?, ?)";
        return jdbcTemplate.update(sql, new Object[]{tid, commentContent, CoreTime.getDataTime(), complaintType}) > 0;
    }
}
