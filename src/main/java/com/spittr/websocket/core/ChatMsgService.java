package com.spittr.websocket.core;

import org.springframework.stereotype.Service;

import com.spittr.user.model.User;
import com.spittr.websocket.model.ChatMsg;

@Service
public interface ChatMsgService {

	public static ChatMsg newInstance(User sender, User reciver, String content){
		return new ChatMsg(sender, reciver, content);
	}
	
	ChatMsg get(Long chatId);
	
	void save(ChatMsg chatMsg);
	
	void update(ChatMsg chatMsg);
	
	void sended(ChatMsg chatMsg);
	
	void recived(ChatMsg chatMsg);
}
