### status code 状态表

------

- 成功
SUCCESS = 200;		

------
	
- token错误
TokenErrorCode = -200;		

- headers 缺少authorization头
AuthorizationNotFoundCode = -201;

- token过期
TokenExpiredCode = -202;		
	
------
    
- 系统错误
ErrorCode = -300;		

- 权限错误
AuthorityErrorCode = -303;		

------

- 操作不允许
ForbiddenCode = -400;		

- 数据错误
DataErrorCode = -401;		

- 资源不存在
ResourceNotFoundCode = -404;		

-----------

####  返回示例:
已知错误: 有具体`message`提示, 异常类型:

			{
			    "code": -200,
			    "message": "Token error, check it again.",
			    "content": "TokenErrorException"
			}
			
			
系统未知错误:统一异常`code`为-300

		{
		    "code": -300,
		    "message": "失败",
		    "content": "(゜Д゜;)"
		}