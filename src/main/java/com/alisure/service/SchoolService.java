package com.alisure.service;

import com.alisure.dao.SchoolDao;
import com.alisure.entity.InfoSchool;
import com.alisure.tool.core.CoreString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("schoolService")
public class SchoolService {

    @Autowired
    private SchoolDao schoolDao;

    public List<InfoSchool> getAllSchoolInfo(){
        return  schoolDao.getAllSchoolInfo();
    }

    public boolean setSchoolBySchoolId(String openid, int schoolId) {
        return !(schoolId <= 0 || CoreString.isNull(openid)) && schoolDao.setSchool(openid, schoolId) > 0;
    }

    public InfoSchool getUserSchool(String openid) {
        if(CoreString.isNull(openid)) return null;
        return schoolDao.getUserSchool(openid);
    }

    public InfoSchool getUserSchool(int uid) {
        if(uid <= 0) return null;
        return schoolDao.getUserSchool(uid);
    }
}
