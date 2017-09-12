package com.spittr.message.dao;

import java.util.Date;
import java.util.List;

import com.spittr.dao.BaseDao;
import com.spittr.message.model.Message;
import com.spittr.tools.page.Page;
import com.spittr.user.model.User;

public interface MessageDao extends BaseDao<Message>{

	List<Message> get(Page page);
	
	List<Message> getByLid(Page page, Long lid);
	
	List<Message> getBeforeTime(Date time, int num);
	
	List<Message> getAfterTime(Date time, int num);
	
	List<Message> getByUid(long uid, Date time, int num);
	
	Long count();
	
	Long coutByLid(Long lid);
}
