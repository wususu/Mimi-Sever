package com.spittr.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.StompWebSocketEndpointRegistration;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.spittr.websocket.core.PresenceChannelInterceptor;
import com.spittr.websocket.core.SocketHandler;
import com.spittr.websocket.core.StompHandShakeHandler;
import com.spittr.websocket.core.WebSocketHandShakeInterceptor;
import com.spittr.websocket.core.WebSocketInterceptor;

@Configuration
@EnableWebSocketMessageBroker
@ComponentScan(basePackages={
		"com.spittr.websocket.core"
})
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer{

	@Autowired
	private SocketHandler socketHandler;
	
	@Autowired
	private WebSocketInterceptor webSocketInterceptor;
	
	@Autowired
	private StompHandShakeHandler stompHandShakeHandler;
	
	@Autowired
	private WebSocketHandShakeInterceptor webSocketHandShakeInterceptor;
	
	@Autowired
	private PresenceChannelInterceptor presenceChannelInterceptor;
	
//	@Override
//	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//		// TODO Auto-generated method stub
//		registry..addHandler(socketHandler, "/socketServer").addInterceptors(webSocketInterceptor);
//		
//		registry.addHandler(socketHandler, "/sockjs/socketServer").addInterceptors(webSocketInterceptor).withSockJS();
//	}

	
	@Override  
    public void configureMessageBroker(MessageBrokerRegistry registry) {  
        // queue、topic、user代理  
        registry.enableSimpleBroker("/queue", "/topic");  
	   registry.setApplicationDestinationPrefixes("/socket");  
        registry.setUserDestinationPrefix("/user/");  
      
    }  
	
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		// TODO Auto-generated method stub

		registry.addEndpoint("/socketServer")
		.setAllowedOrigins("*")
		.setHandshakeHandler(stompHandShakeHandler)
		.addInterceptors(webSocketHandShakeInterceptor)
		.withSockJS();
		
		registry.addEndpoint("/client")
		.setAllowedOrigins("*")
		.setHandshakeHandler(stompHandShakeHandler)
		.addInterceptors(webSocketHandShakeInterceptor);
	
		}
	
	
	
	@Override
	public void configureClientInboundChannel(ChannelRegistration registration) {
		// TODO Auto-generated method stub
		registration.taskExecutor()
		.corePoolSize(4)
		.maxPoolSize(6)
		.keepAliveSeconds(60);
		
		registration.setInterceptors(presenceChannelInterceptor);
	}


}
