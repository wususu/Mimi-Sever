package com.spittr.message.dao;

import com.spittr.dao.BaseDao;
import com.spittr.message.model.*;
import com.spittr.user.model.User;

public interface ClikeeDao extends BaseDao<CLikee>{

	CLikee get(long cid, long uid);
	
	CLikee get(Comment comment, User user);

	void create(CLikee entity);
}
