package com.spittr.websocket.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.spittr.dao.BaseDao;
import com.spittr.websocket.model.ChatMsg;

@Repository
public interface ChatMsgDao extends BaseDao<ChatMsg>{

	List<ChatMsg> getNotRecivedChatMsg(Long reciverId);
	
}
