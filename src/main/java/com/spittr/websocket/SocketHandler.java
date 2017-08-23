//package com.spittr.websocket;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Service;
//import org.springframework.web.socket.CloseStatus;
//import org.springframework.web.socket.TextMessage;
//import org.springframework.web.socket.WebSocketHandler;
//import org.springframework.web.socket.WebSocketMessage;
//import org.springframework.web.socket.WebSocketSession;
//
//
//@Service
//public class SocketHandler implements WebSocketHandler{
//
//	private static final  Logger logger; 
//	
//	private static final List<WebSocketSession> users;
//	
//	static{
//	    users = new ArrayList<WebSocketSession>();
//	    logger = LoggerFactory.getLogger(SocketHandler.class);
//	  }
//	
//	@Override
//	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
//		// TODO Auto-generated method stub
//	}
//
//	@Override
//	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
//		// TODO Auto-generated method stub
//		logger.info("connection successfully");
//		users.add(session);
//		String username = session.getAttributes().get("user").toString();
//		if (username != null) {
//			session.sendMessage(new TextMessage("我们已经建立连接了"));
//		}
//	}
//
//	@Override
//	public void handleMessage(WebSocketSession session, WebSocketMessage<?> arg1) throws Exception {
//		// TODO Auto-generated method stub
//		logger.debug("连接已关闭");
//		users.remove(session);
//	}
//
//	@Override
//	public void handleTransportError(WebSocketSession session, Throwable error) throws Exception {
//		// TODO Auto-generated method stub
//		if (session.isOpen()) {
//			session.close();
//		}
//		logger.error("连接出错: "+error.toString());
//		users.remove(session);
//	}
//
//	@Override
//	public boolean supportsPartialMessages() {
//		// TODO Auto-generated method stub
//		return false;
//	}
//	
//	public void sendMsgToAll(TextMessage msg) {
//		for(WebSocketSession uSession: users){
//			try{
//				if (uSession.isOpen()) {
//					uSession.sendMessage(msg);
//				}
//			}catch (IOException  e) {
//				// TODO: handle exception
//				e.printStackTrace();
//			}
//		}
//	}
//	
//	public void sendMsgToUsr(String uname, TextMessage msg){
//		for (WebSocketSession user : users) {
//			if (user.getAttributes().get("user").equals(uname)) {
//				try{
//					if (user.isOpen()) {
//						user.sendMessage(msg);
//					}
//				}catch (IOException e) {
//					// TODO: handle exception
//					e.printStackTrace();
//				}
//				break;
//			}
//		}
//	}
//
//}
