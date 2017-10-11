package com.alisure.dao;

import com.alisure.entity.InfoSearchRecord;
import com.alisure.entity.InfoTask;
import com.alisure.tool.core.CorePrint;
import com.alisure.tool.core.CoreTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("searchDao")
public class SearchDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<String> getCommonSearch(int number) {
        try {
            String sql = "select s.searchRecord  from ( select searchRecord,count(*) as count  from t_search_record  GROUP BY searchRecord  ORDER BY count desc limit 0, ?) s";
            return jdbcTemplate.queryForList(sql, new Object[]{number}, String.class);
        }catch (EmptyResultDataAccessException e){
            CorePrint.printlnTime("EmptyResultDataAccessException: SearchDao.getCommonSearch:" + number);
            return null;
        }
    }

    public List<InfoSearchRecord> getHistorySearch(int userId, int number) {
        String sql = "select * from (select * from t_search_record where uid=? order by searchTime desc) s group by searchRecord order by searchTime desc limit 0, ?";
        return jdbcTemplate.query(sql,new Object[]{userId, number}, new InfoSearchRecord());
    }

    public boolean insertSearch(String keyWord, int userId){
        String sql = "INSERT INTO t_search_record(searchRecord, searchTime, uid) VALUES (?, ?, ?)";
        String time = CoreTime.getDataTime();
        return jdbcTemplate.update(sql,new Object[]{keyWord, time, userId}) > 0;
    }

    public List<InfoTask> getSearchResult(String keyWord, int page, int pageSize) {
        String sql = "select * from t_task t, t_category c where t.pubStateId = ? and t.title like ?  and t.catId = c.catId order by t.pubTime desc limit ?, ?";
        return jdbcTemplate.query(sql, new Object[]{1, "%" + keyWord + "%", (page - 1) * pageSize, pageSize}, new InfoTask());
    }
}
