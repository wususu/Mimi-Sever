package com.spittr.config;

public class StatusCodeConf {

	private StatusCodeConf(){
		throw new AssertionError();
	}
	
	public static final int SUCCESS = 200;		//成功
	
	
	public static final int TokenErrorCode = -200;		//token错误
	public static final int AuthorizationNotFoundCode = -201;		// headers 缺少authorization头
	public static final int TokenExpiredCode = -202;		// token超时
		
	
	public static final int ErrorCode = -300;		// 系统错误
	public static final int AuthorityErrorCode = -303;		//权限错误
	
	
	public static final int ForbiddenCode = -400;		//操作不允许
	public static final int DataErrorCode = -401;		// 数据错误
 	public static final int ResourceNotFoundCode = -404;		//资源不存在
	
}
