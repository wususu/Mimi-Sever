package com.spittr.message.dao;

import java.util.Date;
import java.util.List;

import com.spittr.dao.BaseDao;
import com.spittr.message.model.Comment;
import com.spittr.tools.page.Page;

public interface CommentDao extends BaseDao<Comment>{

	List<Comment> get(Long mid);
		
	List<Comment> getByPage(Long mid, Page page);

	List<Comment> getBeforeTime(Long mid, Date time, Integer num);
	
	List<Comment> getAfterTime(Long mid, Date time, Integer num);
	
	Long count(Long mid);

}
