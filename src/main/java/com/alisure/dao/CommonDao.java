package com.alisure.dao;

import com.alisure.entity.InfoUser;
import com.alisure.tool.core.CorePrint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("commonDao")
public class CommonDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public InfoUser getUser(int uid){
        try {
            String sql = "select * from t_user where uid=?";
            return jdbcTemplate.queryForObject(sql, new Object[]{uid},new InfoUser());
        }catch (EmptyResultDataAccessException e){
            CorePrint.printlnTime("EmptyResultDataAccessException: CommonDao.getUser:" + uid);
            return null;
        }
    }

    public InfoUser getUser(String openid){
        try {
            String sql = "select * from t_user where openid=?";
            return jdbcTemplate.queryForObject(sql, new Object[]{openid},new InfoUser());
        }catch (EmptyResultDataAccessException e){
            CorePrint.printlnTime("EmptyResultDataAccessException: CommonDao.getUser:" + openid);
            return null;
        }
    }

    public int getUserId(String openid){
        try {
            String sql = "select id from t_user where openid=?";
            return jdbcTemplate.queryForObject(sql, new Object[]{openid},Integer.class);
        }catch (EmptyResultDataAccessException e){
            CorePrint.printlnTime("EmptyResultDataAccessException: CommonDao.getUserId:" + openid);
            return 0;
        }
    }

    public String getUserOpenid(int uid){
        try {
            String sql = "select openid from t_user where uid=?";
            return jdbcTemplate.queryForObject(sql, new Object[]{uid},String.class);
        }catch (EmptyResultDataAccessException e){
            CorePrint.printlnTime("EmptyResultDataAccessException: CommonDao.getUserOpenid:" + uid);
            return null;
        }
    }

    public String getSchool(int uid){
        try {
            String sql = "select taskSchool from t_user where uid=?";
            return jdbcTemplate.queryForObject(sql, new Object[]{uid},String.class);
        }catch (EmptyResultDataAccessException e){
            CorePrint.printlnTime("EmptyResultDataAccessException: CommonDao.getSchool:" + uid);
            return null;
        }
    }
}
