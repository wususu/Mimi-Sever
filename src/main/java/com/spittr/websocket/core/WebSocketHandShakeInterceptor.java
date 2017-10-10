package com.spittr.websocket.core;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import com.spittr.authorization.core.TokenIssues;
import com.spittr.authorization.manager.TokenManager;
import com.spittr.authorization.model.Token;
import com.spittr.config.StaticConfig;
import com.spittr.user.model.User;
import com.spittr.websocket.config.WebSocketConstant;

@Component
public class WebSocketHandShakeInterceptor extends HttpSessionHandshakeInterceptor{

	@Autowired
	TokenManager tokenManager;
	
	@Override
	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Map<String, Object> attributes) throws Exception {
		// TODO Auto-generated method stub

		if (request instanceof ServletServerHttpRequest) {
			ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
			String srect = servletRequest.getServletRequest().getParameter(StaticConfig.WEBSOCKET_WEB_PARAM);
			
			if (srect == null) 
				return false;
			
			Token token = tokenManager.getToken(srect);
			
			if (token == null && TokenIssues.getInstance().checkToken(token) == false) {
				return false;
			}
			
			tokenManager.updateToken(token);
			User user = token.getUser();
			
			attributes.put(WebSocketConstant.CURRENT_USER_OBJ, user);

		}
		return super.beforeHandshake(request, response, wsHandler, attributes);

	}
	
	@Override
	public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Exception ex) {
		// TODO Auto-generated method stub
		super.afterHandshake(request, response, wsHandler, ex);
	}
	
}
