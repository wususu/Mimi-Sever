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
