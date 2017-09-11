package com.spittr.message.dao;

import com.spittr.dao.BaseDao;
import com.spittr.message.model.Likee;
import com.spittr.message.model.Message;
import com.spittr.user.model.User;

public interface LikeeDao extends BaseDao<Likee>{
	
	Likee get(long mid, long uid);
	
	Likee get(Message message, User user);

	void create(Likee entity);
}
