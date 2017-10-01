package com.spittr.message.dao;

import com.spittr.dao.BaseDao;
import com.spittr.message.model.MLikee;
import com.spittr.message.model.Message;
import com.spittr.user.model.User;

public interface MlikeeDao extends BaseDao<MLikee>{
	
	MLikee get(long mid, long uid);
	
	MLikee get(Message message, User user);

	void create(MLikee entity);
}
