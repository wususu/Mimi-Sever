package com.spittr.message.dao;

import java.util.List;

import com.spittr.dao.BaseDao;
import com.spittr.message.model.Comment;
import com.spittr.tools.page.Page;

public interface CommentDao extends BaseDao<Comment>{

	List<Comment> get(Long mid);
		
	List<Comment> get(Long mid, Page page);

	Long count(Long mid);

}
