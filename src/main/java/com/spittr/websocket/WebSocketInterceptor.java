//package com.spittr.websocket;
//
//import java.util.Map;
//
//import javax.servlet.http.HttpSession;
//
//import org.springframework.http.server.ServerHttpRequest;
//import org.springframework.http.server.ServerHttpResponse;
//import org.springframework.http.server.ServletServerHttpRequest;
//import org.springframework.web.socket.WebSocketHandler;
//import org.springframework.web.socket.server.HandshakeInterceptor;
//
//import com.spittr.authorization.core.Constants;
//
//
//public class WebSocketInterceptor implements HandshakeInterceptor{
//
//	@Override
//	public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler handler, Exception exception) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler handler,
//			Map<String, Object> map) throws Exception {
//		// TODO Auto-generated method stub
//		if (request instanceof ServerHttpRequest) {
//			ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
//			String scret = servletRequest.getServletRequest().getHeader(Constants.AUTHORIZATION);
//			if (scret != null) {
//				System.out.println(scret);
////				map.put("user", session.getAttribute("user"));
//			}
//		}
//		return true;
//	}
//
//
//}
