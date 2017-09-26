package com.spittr.message.dao;

import java.util.Date;
import java.util.List;

import com.spittr.dao.BaseDao;
import com.spittr.message.model.Message;
import com.spittr.tools.page.Page;

public interface MessageDao extends BaseDao<Message>{

	List<Message> get(Page page);
	
	Message get(Long mid);
	
	@Deprecated
	List<Message> getByLid(Page page, Long lid);
	
	List<Message> getBeforeTime(Date tmbefore, int num);
	
	List<Message> getAfterTime(Date tmafter, int num);
	
	List<Message> getBeforeTime(Date tmbefore, Long lid, int num);
	
	List<Message> getAfterTime(Date tmafter, Long lid, int num);
	
	List<Message> getByUid(long uid, Date time, int num);
	
	Long count();
	
	@Deprecated
	Long coutByLid(Long lid);
}
