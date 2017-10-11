package com.alisure.dao;

import com.alisure.entity.InfoMoneyChange;
import com.alisure.entity.InfoTask;
import com.alisure.tool.core.CorePrint;
import com.alisure.tool.core.CoreTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userDao")
public class UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<InfoTask> getMyTasks(int page, int pageSize, int userId) {
        String sql = "select * from t_task where recId = ? order by pubTime desc limit ?, ?";
        return jdbcTemplate.query(sql, new Object[]{userId, (page - 1) * pageSize, pageSize}, new InfoTask());
    }

    public List<InfoTask> getMyPublish(int page, int pageSize, int userId) {
        String sql = "select * from t_task where pubId = ? order by pubTime desc limit ?, ?";
        return jdbcTemplate.query(sql, new Object[]{userId, (page - 1) * pageSize, pageSize}, new InfoTask());
    }

    public boolean editUserInfo(String nickname, String sex, String qq, String phone, String weixin, int schoolId, int uid) {
        String sql = "update t_user set nickname=?, sex=?, qq=?, phone=?, weixin=?, schoolId=? where uid=?";
        return jdbcTemplate.update(sql, new Object[]{nickname, sex, qq, phone, weixin, schoolId, uid}) > 0;
    }
    public int insertUserInfo(String openid, String nickname, String sex, String province, String city, String country, String headimgurl, String time) {
        // 需要判断一下用户是否存在，在外面处理。。。。
        String sql = "insert t_user(openid, nickname, sex, province, city, country, headimgurl, time) values(?, ?, ?, ?, ?, ?, ?, ?)";
        boolean result = jdbcTemplate.update(sql, new Object[]{openid, nickname, sex, province, city, country, headimgurl, time}) > 0;
        try {
            sql = "select uid from t_user where openid=?";
            return result ? jdbcTemplate.queryForObject(sql, new Object[]{openid}, Integer.class) : 0;
        }catch (EmptyResultDataAccessException e){
            CorePrint.printlnTime("EmptyResultDataAccessException: UserDao.insertUserInfo:"
                    + openid + ":" + nickname + ":" + sex + ":" + province + ":" + city + ":" + country + ":" + headimgurl);
            return 0;
        }
    }
    public boolean updateUserInfo(String openid, String nickname, String sex, String province, String city, String country, String headimgurl, String time, int uid) {
        String sql = "update t_user set openid=?, nickname=?, sex=?, province=?, city=?, country=?, headimgurl=?, time=? where uid=?";
        return jdbcTemplate.update(sql, new Object[]{openid, nickname, sex, province, city, country, headimgurl, time, uid}) > 0;
    }

    /*金币相关------------------------------------------------*/
    /**
     * 改变用户的金币数，并记录金币的变化：
     * goldCoins < 0 : 减少
     * goldCoins > 0 : 增加
     * 如果 tid <= 0 : 和任务无关的金币更新
     * 如果 tid 》 0 : 和任务有关
     * @return boolean
     */
    public boolean updateUserCoins(int uid, int tid, int goldCoins, String reason){
        int nowCoins = getUserCoins(uid);
        if(nowCoins + goldCoins < 0) return false;
        String sql = "update t_user set goldCoins = ? where uid = ?";
        int result = jdbcTemplate.update(sql, new Object[]{nowCoins + goldCoins, uid});
        // 记录金币的变化
        if(result > 0){
            if(tid > 0){
                return recordCoins(goldCoins, uid, tid, CoreTime.getDataTime(), reason);
            }else{
                return recordCoins(goldCoins, uid, CoreTime.getDataTime(), reason);
            }
        }
        return false;
    }

    /**
     * 获取用户当前的金币
     * @return int
     */
    public int getUserCoins(int uid){
        try {
            String sql = "select goldCoins from t_user where uid = ?";
            return jdbcTemplate.queryForObject(sql, new Object[]{uid}, Integer.class);
        }catch (EmptyResultDataAccessException e){
            CorePrint.printlnTime("EmptyResultDataAccessException: UserDao.getUserCoins:" + uid );
            return 0;
        }
    }

    /**
     * 记录金币的变化:
     *  调用该方法的唯一入口为updateUserCoins()
     * @return
     */
    private boolean recordCoins(int changeCoins, int uid, int tid, String changeTime, String changeReason){
        String sql = "insert into t_money_change(changeCoins,uid,tid,changeTime,changeReason) values(?,?,?,?,?)";
        return jdbcTemplate.update(sql, new Object[]{changeCoins, uid, tid, changeTime, changeReason}) > 0;
    }
    private boolean recordCoins(int changeCoins, int uid, String changeTime, String changeReason){
        String sql = "insert into t_money_change(changeCoins,uid,changeTime,changeReason) values(?,?,?,?)";
        return jdbcTemplate.update(sql, new Object[]{changeCoins, uid, changeTime, changeReason}) > 0;
    }

    /**
     * 获取所有的金币变化记录
     * */
    public List<InfoMoneyChange> getMoneyRecord(int uid) {
        String sql = "select * from t_money_change where uid = ? order by changeId desc";
        return jdbcTemplate.query(sql, new Object[]{uid}, new InfoMoneyChange());
    }
    /*金币相关------------------------------------------------*/
}
