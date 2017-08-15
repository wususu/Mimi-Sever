package com.spittr.message.dao;

import java.util.List;

import com.spittr.dao.BaseDao;
import com.spittr.message.model.Message;
import com.spittr.tools.page.Page;

public interface MessageDao extends BaseDao<Message>{

	List<Message> get(Page page);
	
	Long count();
}
