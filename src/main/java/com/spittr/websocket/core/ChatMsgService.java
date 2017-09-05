package com.spittr.websocket.core;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spittr.user.model.User;
import com.spittr.websocket.model.ChatMsg;
import com.spittr.websocket.model.SendMsg;

@Service
public interface ChatMsgService {

	public static ChatMsg newInstance(User sender, User reciver, SendMsg sendMsg){
		return new ChatMsg(sender, reciver, sendMsg);
	}
	
	void save(ChatMsg chatMsg);
	
	void update(ChatMsg chatMsg);
	
	void sended(ChatMsg chatMsg);
	
	void recived(ChatMsg chatMsg);

	ChatMsg get(String chatID);
	
	List<ChatMsg> getNotRecivedChatMsg(Long userId);
}
