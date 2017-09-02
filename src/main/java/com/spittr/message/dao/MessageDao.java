package com.spittr.message.dao;

import java.util.Date;
import java.util.List;

import com.spittr.dao.BaseDao;
import com.spittr.message.model.Message;
import com.spittr.tools.page.Page;

public interface MessageDao extends BaseDao<Message>{

	List<Message> get(Page page);
	
	List<Message> getByLid(Page page, Long lid);
	
	List<Message> getBeforeTime(Date time, Integer num);
	
	List<Message> getAfterTime(Date time, Integer num);
	
	Long count();
	
	Long coutByLid(Long lid);
}
