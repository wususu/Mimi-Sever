package com.spittr.image.dao;

import java.util.List;

import com.spittr.dao.BaseDao;
import com.spittr.image.model.MessageImage;


public interface MessageImageDao extends BaseDao<MessageImage>{

	MessageImage get(String webPath);
	
	List<MessageImage> getByMid(Long mid);
	
}
