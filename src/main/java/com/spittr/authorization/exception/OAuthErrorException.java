package com.spittr.authorization.exception;

import com.spittr.exception.BaseException;
import static com.spittr.config.StatusCodeConf.*;

public class OAuthErrorException extends BaseException{

	public OAuthErrorException() {
		// TODO Auto-generated constructor stub
		super(ActionFail);
	}
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return String.format(
				"授权失败, 请重新尝试或者直接账号密码登录"
				);
	}
	
}
