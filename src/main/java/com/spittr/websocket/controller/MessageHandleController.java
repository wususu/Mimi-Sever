package com.spittr.websocket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spittr.user.core.UserService;
import com.spittr.user.model.User;
import com.spittr.websocket.model.SendMsg;

import com.spittr.websocket.core.ChatMsgService;
import com.spittr.websocket.model.ChatMsg;
import com.spittr.websocket.model.ReciveMsg;

@Controller
public class MessageHandleController {
	
	@Autowired
	private SimpMessagingTemplate messageingTemplate;
	
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	
	@Autowired
	@Qualifier("chatMsgServiceImpl")
	private ChatMsgService chatMsgService;
	
	@RequestMapping("/test")
	public  void name() {
		User sender = userService.get((long)1);
		User reciver = userService.get((long)2);
		ChatMsg chatMsg =  ChatMsgService.newInstance(sender, reciver, "测试");
		chatMsgService.sended(chatMsg);
	}
	/**
	 * 聊天stomp接口
	 * @param msg
	 * @throws Exception
	 */
	@MessageMapping("/socketServer/chatSender")
	public void sendToUser(SendMsg msg) throws Exception{
		Long senderId = msg.getSenderId();
		Long reciverId = msg.getReciverId();
		
		if (senderId == null || reciverId == null ) 
			return ;
		
		User sender = userService.get(senderId);
		User reciver = userService.get(reciverId);
		
		if (sender == null || reciver == null) 
			return ;
		
		ChatMsg chatMsg =  ChatMsgService.newInstance(sender, reciver, msg.getContent());
		chatMsgService.sended(chatMsg);
		
		messageingTemplate.convertAndSendToUser(reciver.getUname(), "/recive", chatMsg);
		messageingTemplate.convertAndSendToUser(String.valueOf( reciver.getUid() ), "/recive", chatMsg);

	}
	
	@MessageMapping(value="/socketServer/chatReciver")
	public void recivedConfirm(ReciveMsg msg){

		if (msg.getChatId() == null || (msg.getReciverId() == null && msg.getReciver() == null)) 
			return ;
		
		ChatMsg chatMsg = chatMsgService.get(msg.getChatId());
		chatMsgService.recived(chatMsg);
	}
		
	@MessageExceptionHandler
	public void handleExceptions(Throwable t) {  
		System.err.println("Error handling message: " + t.getMessage());  
	}  
}
