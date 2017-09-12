package com.spittr.authorization.resolvers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.spittr.authorization.annotation.AutoCurrentUser;
import com.spittr.authorization.core.Constants;
import com.spittr.user.core.UserService;
import com.spittr.user.model.User;


@Component
public class CurrentUserArgumentResolver implements HandlerMethodArgumentResolver{

	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	
	@Override
	public Object resolveArgument(
			MethodParameter parameter, 
			ModelAndViewContainer mavController, 
			NativeWebRequest webRequest,
			WebDataBinderFactory binderFactory 
			) throws Exception {
		// TODO Auto-generated method stub
		Long currentUserId = (Long)webRequest.getAttribute(Constants.CURRENT_USER_ID, RequestAttributes.SCOPE_REQUEST);
		
		if (currentUserId != null) 
			return userService.get(currentUserId);
		
		return null;
	}

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		// TODO Auto-generated method stub
		if (parameter.getParameterType().isAssignableFrom(User.class) && parameter.hasParameterAnnotation(AutoCurrentUser.class)) 
			return true;
		
		return false;
	}
}
