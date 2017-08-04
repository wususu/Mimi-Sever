package com.spittr.user.dao;

import com.spittr.dao.BaseDao;
import com.spittr.user.model.Passwd;

public interface PasswdDao extends BaseDao<Passwd>{

	Passwd isPasswd(String loginName);
	
	Passwd passwdCreat(String loginName);
	
}
