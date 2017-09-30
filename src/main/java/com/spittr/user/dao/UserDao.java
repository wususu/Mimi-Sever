package com.spittr.user.dao;

import org.springframework.stereotype.Repository;

import com.spittr.dao.BaseDao;
import com.spittr.user.model.User;


public interface UserDao extends BaseDao<User>{

	User get(String uname);
	
	User getByNname(String nname);
}
