package com.spittr.websocket.core;


import java.security.Principal;
import java.util.Map;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

@Component
public class StompHandShakeHandler extends DefaultHandshakeHandler{

	@Override
	protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler,
			Map<String, Object> attributes) {
		// TODO Auto-generated method stub
		return super.determineUser(request, wsHandler, attributes);
	}
	
}
