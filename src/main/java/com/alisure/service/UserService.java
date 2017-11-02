package com.alisure.service;

import com.alisure.dao.SchoolDao;
import com.alisure.dao.UserDao;
import com.alisure.entity.InfoMoneyChange;
import com.alisure.entity.InfoSchool;
import com.alisure.entity.InfoTask;
import com.alisure.entity.InfoUser;
import com.alisure.tool.core.CoreString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
public class UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    SchoolService schoolService;
    @Autowired
    CommonService commonService;

    public List<InfoTask> getMyTasks(int page, int pageSize, int userId) {
        if(page <= 0) page = 1;
        return userDao.getMyTasks(page, pageSize, userId);
    }

    public List<InfoTask> getMyPublish(int page, int pageSize, int userId) {
        if(page <= 0) page = 1;
        return userDao.getMyPublish(page, pageSize, userId);
    }

    public List<Object> getUserInfoSelf(Integer uid) {
        List<Object> result = new ArrayList<Object>();
        InfoUser userInfo = commonService.getUser(uid);
        result.add(userInfo);
        InfoSchool schoolInfo = schoolService.getUserSchool(userInfo.getUid());
        result.add(schoolInfo);
        return result;
    }

    public List<Object> getUserInfoOther(Integer uid) {
        return getUserInfoSelf(uid);
    }

    public boolean editUserInfo(String nickname, String sex, String qq, String phone, String weixin, int schoolId, int userId) {
        return userDao.editUserInfo(nickname, sex, qq, phone, weixin, schoolId, userId);
    }

    /**
     * 更新金币：和任务无关，如关注公众号、用户资料完善等和任务无关的行为
     * @param uid
     * @param goldCoins
     * @param reason
     * @return
     */
    public boolean updateCoinsByOther(int uid, int goldCoins, String reason){
        if(uid <= 0 || goldCoins == 0 || CoreString.isNull(reason)){
            return false;
        }
        return userDao.updateUserCoins(uid, 0, goldCoins, reason);
    }

    /**
     * 更新金币：和任务有关，如发布任务、评价、任务完成、接手者被投诉、发布者被投诉
     * @param uid
     * @param tid
     * @param goldCoins : 为正表示加积分，为负表示减积分
     * @param reason
     * @return
     */
    public boolean updateCoinsByTask(int uid, int tid, int goldCoins, String reason){
        if(uid <= 0 || tid <= 0 || goldCoins == 0 || CoreString.isNull(reason)){
            return false;
        }
        return userDao.updateUserCoins(uid, tid, goldCoins, reason);
    }

    public List<InfoMoneyChange> getMoneyRecord(int userId) {
        if(userId <= 0){
            return null;
        }
        return userDao.getMoneyRecord(userId);
    }
}
