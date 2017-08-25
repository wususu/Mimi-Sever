package com.spittr.websocket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.spittr.websocket.model.ChattingMsg;
import com.spittr.websocket.model.Msg;

@Controller
public class MessageHandleController {

	@Autowired
	private SimpMessagingTemplate messageingTemplate;
	
	@MessageMapping("/socketServer")
	@SendTo("/topic/message")
	public ChattingMsg send(Msg msg) throws Exception{
		return new ChattingMsg("wussuus", "add", "hello");
	}
	
	public ChattingMsg sendToUser(){
		
	}
}
