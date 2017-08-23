//package com.spittr.websocket.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.socket.TextMessage;
//
//import com.spittr.websocket.SocketHandler;
//
//@RestController
//@RequestMapping(value="/socket")
//public class SocketController {
//
//	@Autowired
//	private SocketHandler socketHandler;
//	
//	@RequestMapping(value="/test")
//	public void name() {
//		socketHandler.sendMsgToAll(new TextMessage("测试消息"));
//	}
//}
