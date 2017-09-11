package com.spittr.message.dao;

import com.spittr.dao.BaseDao;
import com.spittr.message.model.Like;
import com.spittr.message.model.Message;
import com.spittr.user.model.User;

public interface LikeDao extends BaseDao<Like>{
	
	Like get(long mid, long uid);
	
	Like get(Message message, User user);

	void create(Like entity);
}
