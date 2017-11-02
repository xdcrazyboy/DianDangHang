package com.alisure.service;

import com.alisure.dao.CommonDao;
import com.alisure.dao.SearchDao;
import com.alisure.dao.UserDao;
import com.alisure.entity.InfoSearchRecord;
import com.alisure.entity.InfoTask;
import com.alisure.tool.core.CoreString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("searchService")
public class SearchService {
    @Autowired
    SearchDao searchDao;
    @Autowired
    UserDao userDao;
    @Autowired
    CommonDao commonDao;

    public List<String> getCommonSearch(int number) {
        if(number <= 0){
            return null;
        }
        return searchDao.getCommonSearch(number);
    }

    public List<InfoSearchRecord> getHistorySearch(int userId, int number) {
        if(userId <= 0 || number <= 0){
            return null;
        }
        return searchDao.getHistorySearch(userId, number);
    }

    public List<InfoTask> getSearchResult(String keyWord, int page, int pageSize, int userId) {
        if(userId <= 0 || CoreString.isNull(keyWord)){
            return null;
        }
        searchDao.insertSearch(keyWord, userId);
        List<InfoTask> infoTasks = searchDao.getSearchResult(keyWord, page, pageSize);
        for(InfoTask infoTask: infoTasks){
            infoTask.setPub(commonDao.getUser(infoTask.getPubId()));
        }
        return infoTasks;
    }

}
