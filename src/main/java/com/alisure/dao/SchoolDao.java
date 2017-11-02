package com.alisure.dao;

import com.alisure.entity.InfoSchool;
import com.alisure.entity.InfoUser;
import com.alisure.tool.core.CorePrint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository("schoolDao")
public class SchoolDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 获取所有的学校信息
     * @return
     */
    public List<InfoSchool> getAllSchoolInfo(){
        String sql = "select * from t_school";
        return jdbcTemplate.query(sql,new InfoSchool());
    }

    /**
     * 设置选择的学校
     * @return
     */
    public int setSchool(final String openid, final int schoolId) {
        String sql = "UPDATE t_user SET schoolId = ? WHERE openid = ?";
        return jdbcTemplate.update(sql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setInt(1, schoolId);
                preparedStatement.setString(2, openid);
            }
        });
    }

    /**
     * 获取指定学校的信息
     * @param schoolId
     * @return
     */
    public InfoSchool getSchool(int schoolId) {
        try {
            String sql = "select * from t_school where schoolId = ?";
            return jdbcTemplate.queryForObject(sql,new Object[]{schoolId}, new InfoSchool());
        }catch (EmptyResultDataAccessException e){
            CorePrint.printlnTime("EmptyResultDataAccessException: SchoolDao.getSchool:" + schoolId);
            return null;
        }
    }

    /**
     * 获取用户的学校信息
     * @param openid
     * @return
     */
    public InfoSchool getUserSchool(String openid) {
        try {
            String sql = "select t_school.* from t_user,t_school where t_user.openid = ? and t_school.schoolId = t_user.schoolId";
            return jdbcTemplate.queryForObject(sql,new Object[]{openid}, new InfoSchool());
        }catch (EmptyResultDataAccessException e){
            CorePrint.printlnTime("EmptyResultDataAccessException: SchoolDao.getUserSchool:" + openid);
            return null;
        }
    }
    /**
     * 获取用户的学校信息
     * @param uid
     * @return
     */
    public InfoSchool getUserSchool(int uid) {
        try {
            String sql = "select t_school.* from t_user,t_school where t_user.uid = ? and t_school.schoolId = t_user.schoolId";
            return jdbcTemplate.queryForObject(sql,new Object[]{uid}, new InfoSchool());
        }catch (EmptyResultDataAccessException e){
            CorePrint.printlnTime("EmptyResultDataAccessException: SchoolDao.getUserSchool:" + uid);
            return null;
        }
    }

}
