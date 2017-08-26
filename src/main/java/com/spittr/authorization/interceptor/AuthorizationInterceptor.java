package com.spittr.authorization.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.spittr.authorization.annotation.Authorization;
import com.spittr.authorization.core.Constants;
import com.spittr.authorization.core.TokenIssues;
import com.spittr.authorization.exception.AuthorizationNotFoundException;
import com.spittr.authorization.exception.TokenErrorException;
import com.spittr.authorization.manager.TokenManager;
import com.spittr.authorization.model.Token;


@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter{

	@Autowired
	@Qualifier("mySQLTokenManager")
	private TokenManager manager;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		

		if (!(handler instanceof HandlerMethod)) 
			return true;

		HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        
		String authorization = request.getHeader(Constants.AUTHORIZATION);
				
		if (method.getAnnotation(Authorization.class) != null) {
		
				if (authorization == null ) 
		        	throw new AuthorizationNotFoundException(Constants.AUTHORIZATION);
				
				Token token = manager.getToken(authorization);
				
				// 验证成功
				if (TokenIssues.getInstance().checkToken(token) == true) {
					manager.updateToken(token);
					request.setAttribute(Constants.CURRENT_USER_ID, token.getUser().getUid());
					return true;
				}
				
				// 验证失败
		
            	throw new TokenErrorException();
//            return false;
        }
		
		if (authorization != null) {
			try{
				Token token = manager.getToken(authorization);
			
				// 验证成功
				if (TokenIssues.getInstance().checkToken(token) == true) {
					manager.updateToken(token);
					request.setAttribute(Constants.CURRENT_USER_ID, token.getUser().getUid());
				}
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}

		// 游客
		return true;
	}
}
