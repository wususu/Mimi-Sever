package com.spittr.user.dao;

import org.springframework.stereotype.Repository;

import com.spittr.authorization.model.HMTUserInfoModel;
import com.spittr.dao.BaseDao;
import com.spittr.user.model.HMTUser;
import com.spittr.user.model.User;


public interface HmtUserDao extends BaseDao<HMTUser>{
	
	HMTUser get(Long uid);
}
