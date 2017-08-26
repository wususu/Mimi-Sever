package com.spittr.websocket.core;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spittr.websocket.dao.ChatMsgDao;
import com.spittr.websocket.model.ChatMsg;

@Service
public class ChatMsgServiceImpl implements ChatMsgService{

	@Autowired
	@Qualifier("chatMsgDaoImpl")
	private ChatMsgDao chatMsgDao;
	
	@Override
	public void save(ChatMsg chatMsg) {
		// TODO Auto-generated method stub
		chatMsgDao.save(chatMsg);
	}

	@Override
	public void sended(ChatMsg chatMsg) {
		// TODO Auto-generated method stub
		save(chatMsg);
	}
	
	@Override
	public void update(ChatMsg chatMsg) {
		// TODO Auto-generated method stub
		chatMsgDao.update(chatMsg);
	}

	@Transactional
	@Override
	public void recived(ChatMsg chatMsg) {
		// TODO Auto-generated method stub
		chatMsg.setIsRecived(true);
		chatMsg.setTmRecived(new Date());
		
		update(chatMsg);
	}

	@Override
	public ChatMsg get(Long chatId) {
		// TODO Auto-generated method stub
		ChatMsg chatMsg = chatMsgDao.get(ChatMsg.class, chatId);
		return chatMsg;
	}

}
