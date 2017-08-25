package com.spittr.websocket.controller;

import javax.servlet.http.HttpSession;

import org.hibernate.loader.custom.Return;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.TextMessage;

import com.spittr.websocket.core.SocketHandler;
import com.spittr.websocket.model.ChattingMsg;

@RestController
@CrossOrigin(origins="*")
@RequestMapping(value="/socket")
public class SocketController {

	@Autowired
	private SocketHandler socketHandler;
	
	@RequestMapping(value="/test")
	public Object name() {
		return "ok";
	}
}
