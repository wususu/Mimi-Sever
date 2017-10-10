package com.spittr.websocket.core;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import com.spittr.authorization.core.TokenIssues;
import com.spittr.authorization.manager.TokenManager;
import com.spittr.authorization.model.Token;
import com.spittr.config.StaticConfig;
import com.spittr.user.model.User;


@Component
public class WebSocketInterceptor implements HandshakeInterceptor{

	protected Logger logger = LoggerFactory.getLogger(WebSocketInterceptor.class);
	
	@Autowired
	@Qualifier("mySQLTokenManager")
	private TokenManager manger;
	
	@Override
	public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler handler, Exception exception) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler handler,
			Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		if (request instanceof ServerHttpRequest) {
			ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;		
			String srect = servletRequest.getServletRequest().getParameter(StaticConfig.WEBSOCKET_WEB_PARAM);
			
			User user = null;
						
			if (srect != null) {
				Token token = manger.getToken(srect);		
				if (token != null && TokenIssues.getInstance().checkToken(token)){
					manger.updateToken(token);
					user = token.getUser();
				}
			}
			
			if (user == null) 
				return false;
			
			map.put("user", user.getUname());
		}
		return true;
	}
}
