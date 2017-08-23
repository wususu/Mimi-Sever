//package com.spittr.config;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//import org.springframework.web.socket.config.annotation.EnableWebSocket;
//import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
//import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
//
//import com.spittr.websocket.SocketHandler;
//import com.spittr.websocket.WebSocketInterceptor;
//
//@Configuration
//@EnableWebSocket
//public class WebSocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer{
//
//	@Autowired
//	private SocketHandler socketHandler;
//	
//	@Override
//	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//		// TODO Auto-generated method stub
//		registry.addHandler(socketHandler, "/socketServer").addInterceptors(new WebSocketInterceptor());
//		
//		registry.addHandler(socketHandler, "/sockjs/socketServer").addInterceptors(new WebSocketInterceptor());
//	}
//
//}
