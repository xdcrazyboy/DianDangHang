package com.alisure.service;

import com.alisure.dao.CommentDao;
import com.alisure.entity.InfoComment;
import com.alisure.tool.core.CoreString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("commentService")
public class CommentService {

    @Autowired
    CommentDao commentDao;

    public boolean comment(int tid, String commentContent, int commentGrade, int commentType) {
        if(tid <= 0 || CoreString.isNull(commentContent) || (commentGrade > 10 && commentGrade < 0) || (commentType > 2 && commentType < 1)) return false;
        return commentDao.comment(tid, commentContent, commentGrade, commentType);
    }

    public List<InfoComment> getComment(int tid) {
        if(tid <= 0) return null;
        return commentDao.getComment(tid);
    }

    public boolean complaint(int tid, String commentContent, int complaintType) {
        if(tid <= 0 || CoreString.isNull(commentContent) || (complaintType > 2 && complaintType < 1)) return false;
        return commentDao.complaint(tid, commentContent, complaintType);
    }
}
