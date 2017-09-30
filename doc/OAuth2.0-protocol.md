# Mimi接入红满堂OAuth2.0用户授权的请求流程
------


### 1.用户选择使用红满堂账号登录后将请求重定向到红满堂OAuth2.0授权页面:

url: hometown.scau.edu.cn/open/OAuth/authorize?client_id={client_id}&response_type=code&redirect_uri={redirect_uri}&state={state}&scope=

{client_id}: 授权的应用号
{redirect_uri}: 授权成功后的跳转地址
{state}: 随机字符串(不知道干嘛用的,应该是用来防止浏览器缓存?)

###2. 链接跳转:

用户第一次使用认证服务器需要询问是否授权, 否返回原来页面, 是则跳转到原先的`redirect_uri`,并发送一个`code`字符,前端接收到code后表示授权成功, 将`code`和`redirect_uri`发送给后端去请求用户数据.


###3. 后端处理用户数据

`code`和`redirect_uri`发送成功后会有两种情况:

		- url: /api/OAuth2/request.info
		
		- method: POST
		
		- login: false
		
		- argument:
			        |     key     |  isrequired |   type   |
			--------+------------ +-------------+----------+
		 	     -  |     code    |     true    |  String  |
			     -  | redirectUri |     true    |  String  |
   
			   

- a. 该用户是第一次授权登录: <br/>
返回数据为该红满堂用户的信息(`uid`, 用户名: `username`, 头像: `avatar`)<br/>
此时客户端需要引导用户提交表单进行账户绑定(`绑定已有Mimi账户`或者`新建Mimi账户`)<br/>

		{
		    "code": 200,
		    "message": "成功",
		    "content": {
		        "uid": 644080,
		        "username": "吴叔叔",
		        "avatar": "http://hometown.scau.edu.cn/bbs/uc_server/avatar.php?uid=644080"
		    }
		}



- b.不是第一次授权登录: <br/>
此时该红满堂用户是已经绑定过Mimi账户的了, 直接登录放回Mimi用户信息和`token`<br/>
之后直接跳转入用户主页

		{
		    "code": 200,
		    "message": "成功",
		    "content": {
		        "secret": "0abce3cdc3c1498db4902c9a8c2880d5",
		        "user": {
		            "uid": 25,
		            "uname": "wususuaaaa",
		            "nname": "吴叔叔啊aaaa",
		            "tmCreated": 1506788405000,
		            "userPic": null,
		            "faculty": null,
		            "grade": null,
		            "site": null,
		            "signature": null,
		            "gender": null
		        },
		        "tmCreated": 1506788437982,
		        "tmExpire": 1506795637982
		    }
		}


###4. 用户绑定

第一次使用第三方授权登录后, 需要`绑定已有Mimi账户`或者`新建Mimi账户`( 绑定新建分为两个页面处理)才能登入应用, 
- a.绑定已有账户:
 
		- url: /api/OAuth2/bind
		
		- method: POST
		
		- login: false
		
		- argument:
			        |    key    |  isrequired |  type   |
			--------+-----------+-------------+---------+
		 	 用户名  |   uname   |    true    |  String  |
			  密码   |   passwd  |    true    |  String  |
	     第三方用户id |   hmtUid  |    true    |  Long    |  (3.a里面放回数据的uid)
			        
		- response	     
		 {
		    "code": 200,
		    "message": "成功",
		    "content": {
		        "secret": "537c8a107b82410181921930232e908f",
		        "user": {
		            "uid": 25,
		            "uname": "wususuaaaa",
		            "nname": "吴叔叔啊aaaa",
		            "tmCreated": 1506788405362,
		            "userPic": null,
		            "faculty": null,
		            "grade": null,
		            "site": null,
		            "signature": null,
		            "gender": null
		        },
		        "tmCreated": 1506788405730,
		        "tmExpire": 1506795605730
		    }
		}
		
- b. 创建并绑定用户:
		- url: /api/OAuth2/bind
		
		- method: POST
		
		- login: false
		
		- argument:
			        |    key    |  isrequired |   type   |
			--------+-----------+-------------+----------+
		 	 用户名  |   uname   |     true    |  String  |
		 	  昵称   |   nname   |     true    |  String  |
			  密码   |   passwd  |     true    |  String  |
		 	 重复密码 |   rpasswd |     true    |  String  |		 	 			  
		 第三方用户id |   hmtUid  |     true    |  Long    |  (3.a里面放回数据的uid)
			     
		- response	     
		 {
		    "code": 200,
		    "message": "成功",
		    "content": {
		        "secret": "537c8a107b82410181921930232e908f",
		        "user": {
		            "uid": 25,
		            "uname": "wususuaaaa",
		            "nname": "吴叔叔啊aaaa",
		            "tmCreated": 1506788405362,
		            "userPic": null,
		            "faculty": null,
		            "grade": null,
		            "site": null,
		            "signature": null,
		            "gender": null
		        },
		        "tmCreated": 1506788405730,
		        "tmExpire": 1506795605730
		    }
		}




### 5.例子:

1. 当用户选择红满堂帐号登录, 客户端将用户重定向到:<br/>
`hometown.scau.edu.cn/open/OAuth/authorize?client_id=8&response_type=code&redirect_uri=http://localhost:8080/Mimi/OAuth/login&state=aaqweqweqwe&scope=`


2. 之后进入红满堂授权页面,当用户同意授权,就会自动跳转到如下链接:<br/>
`http://localhost:8080/Mimi/OAuth/login?state=aaqweqweqwe&code=75c2ca310c412950f5ac50e1f129bb06`


3. 提取`{redirect_uri}`(即`http://localhost:8080/Mimi/OAuth/login`)和`{code}`(即`75c2ca310c412950f5ac50e1f129bb06`)<br/>
将`{redirect_uri}``{code}` POST 到`api/OAuth2/request.info`,<br/>


4. 之后根据返回数据做逻辑



### 6.涨姿势:
[红满堂Oauth2.0开放平台接口文档](http://hometown.scau.edu.cn/blog/%E7%BA%A2%E6%BB%A1%E5%A0%82oauth2-0%E5%BC%80%E6%94%BE%E5%B9%B3%E5%8F%B0%E6%8E%A5%E5%8F%A3%E6%96%87%E6%A1%A3)

[理解OAuth2.0](http://www.ruanyifeng.com/blog/2014/05/oauth_2_0.html)

[OAuth2.0](https://oauth.net/2/)
