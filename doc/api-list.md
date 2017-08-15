# Mimi api document

- 本系统目前共有`user`, `image`,`location`, `message` 四个模块, `websocket`点对点通讯模块正在开发中.
-  api示例中url默认忽略应用地址: `http://ip:port/应用名`, 实际使用过程中自行添加
- 符号说明: 

		url:		请求地址
		method:		http请求方式
		login:		该请求是否需要登录(is required janke-authorization in headers ?)
		argument:		请求携带参数
		key:			参数名
		isrequired:		参数是否必须
		type:			参数类型
		response:		请求成功数据返回格式
		
- 注意:
  `GET`url中`{something}`表示将其替换为参数
  `DELETE`操作参数在url中以`?a=参数&b=参数`的形式发送

### user api

用户模块`注册` `登录` `登出` `获取用户信息`相关api.

- create: 用户注册

		- url: /api/user/create
		
		- method: POST
		
		- login: false
		
		- argument:
			          |   key     |  isrequired |   type   |
			----------+---------- +-------------+----------+
		 	     帐号  |   uname   |     true    |  String  |
			     昵称  |   nname   |     true    |  String  |
			     密码  |   passwd  |     true    |  String  |
			   重复密码 |   rpasswd |     true    |  String  |		   
			   
		- response(success):
			{
    			"code": 200,
    			"message": "成功",
    			"content": "(=ↀωↀ=)"
			}
		   
- login:用户登录

 `secret`为登录用户的登录凭证, 在header中加入`janke-authorization`: `secret`项后,可以当前登录用户进行请求操作.
 `user`为用户基本信息, `user.tmCreated` 为用户创建时间.
 `tmCreated`与`tmExpire`分别为`secret`的生成时间和过期时间.

		- url: /api/user/login
		
		- method: POST
		
		- login: false
		
		- argument:
			          |   key     |  isrequired |  type    |
			----------+---------- +-------------+----------+
		 	     帐号  |   uname   |     true    |  String  |
			     密码  |   passwd  |     true    |  String  |
			     
		- response(success):
			{
			    "code": 200,
			    "message": "成功",
			    "content": {
			        "secret": "066cb8e6a15b4df8ad49e6a871379a0e",
			        "user": {
			            "uid": 4,
			            "uname": "testuser1",
			            "nname": "测试用户1",
			            "tmCreated": 1502693156000
			        },
			        "tmCreate": 1502693932868,
			        "tmExpire": 1502701132868
			    }
			}

	
- 注销登录

		- url: /api/user/logout
		
		- method: POST
		
		- login: true
			     
		- response(success):
		{
		    "code": 200,
		    "message": "成功",
		    "content": "(=ↀωↀ=)"
		}
		
- 用户信息

		- url: /api/user/logout
		
		- method: GET
		
		- login: true
		
		- argument:
			          |   key     |  isrequired |  type    |
			----------+---------- +-------------+----------+
		 	     帐号  |   uname   |     true    |  String  |
			     昵称  |   nname   |     true    |  String  |
			     密码  |   passwd  |     true    |  String  |
			   重复密码 |   rpasswd |     true    |  String  |	
			     
		- response(success):
		{
		    "code": 200,
		    "message": "成功",
		    "content": {
		        "uid": 4,
		        "uname": "testuser1",
		        "nname": "测试用户1",
		        "tmCreated": 1502693156000
		    }
		}


### image api

- 图片上传接口.
  不单独暴露给用户, 一般在message上传和头像修改中异步处理
  `imageid` 图片唯一id .
  `webPath` 图片路径.
  `mid` 图片所属message.
   
		- url: /api/image/create
		
		- method: POST
		
		- login: true
		
		- argument:
			                |    key    |  isrequired |  type    |
			----------------+---------- +-------------+----------+
		 	 图片(base64编码) |   image   |     true   |  String  |
			     
		- response(success):
			{
			    "code": 200,
			    "message": "成功",
			    "content": {
			        "imageid": 126,
			        "webPath": "/spittr/images/2017-08-14/11:20:47_1502724047004.png",
			        "mid": null
			    }
			}
			
			
- 图片获取

			- url: /images/....
		
			- method: GET
		
			- login: false		

### location api

- create location
 
 新建地点
 
		- url: /api/locale/create
		
		- method: POST
		
		- login: TRUE
			     
		- response(success):
		{
		    "code": 200,
		    "message": "成功",
		    "content": "(=ↀωↀ=)"
		}

- get location
 
 获取某一地点信息
 
		- url: /api/locale/create
		
		- method: POST
		
		- login: TRUE
			     
		- response(success):
		{
		    "code": 200,
		    "message": "成功",
		    "content": {
		        "lid": 21,
		        "locale": "泰山"
		    }
		}
 
- list location
 
 列出所有地点
 
		- url: /api/locale/list
		
		- method: GET
		
		- login: false
			     
		- response(success):
		 {
		    "code": 200,
		    "message": "成功",
		    "content": {
		        "locationList": [
		            {
		                "lid": 21,
		                "locale": "泰山"
		            },
		            {
		                "lid": 23,
		                "locale": "华山"
		            },
		            {
		                "lid": 24,
		                "locale": "图书馆"
		            },
		            {
		                "lid": 25,
		                "locale": "教四"
		            },
		            {
		                "lid": 26,
		                "locale": "教三"
		            },
		            {
		                "lid": 132,
		                "locale": "测试地点"
		            }
		        ]
		    }
		}




### message api

- create message

 图片可以设置1~3张,多张照片时,只要加多一个`imageidList`

		- url: /api/message/create
		
		- method: POST
		
		- login: true
		
		- argument:
			          |   key       |  isrequired | defaultValue |    type         |
			----------+-------------+-------------+--------------+-----------------+
		 	     正文  |   content   |     true    |              |   String        |
			     地点  |   lid       |     true    |              |   Integer       |
			  是否匿名  |   isFake    |     false   |     false    |   Boolean       |
			     图片  | imageidList |     false   |              |   List<Integer> |	
			     
		- response(success):
			{
			    "code": 200,
			    "message": "成功",
			    "content": "(=ↀωↀ=)"
			}

- delete message
 
 删除`message`, 只有创建该`message`的用户才能删除 ,(请求参数在url中)
 
		- url: /api/message/delete
		
		- method: DELETE
		
		- login: true
		
		- argument:
			           |   key       |  isrequired | defaultValue |    type      |
			-----------+-------------+-------------+--------------+--------------+
		 	message编号 |   null      |     true    |    null      |   Integer    |
			     
		- response(success):
			{
			    "code": 200,
			    "message": "成功",
			    "content": "(=ↀωↀ=)"
			}
 
- get message

 获取一条message的详细信息, `{mid}` 替换为`message`id
 
		- url: /api/message/get/{mid}
		
		- method: GET
		
		- login: false
		
		- argument:
			           |   key       |  isrequired | defaultValue |    type      |
			-----------+-------------+-------------+--------------+--------------+
		 	message编号 |   null      |     true    |    null      |   Integer    |
		
		- response(success):
		 {
		    "code": 200,
		    "message": "成功",
		    "content": {
		        "mid": 131,
		        "uid": 1,
		        "user": {
		            "uid": 1,
		            "uname": "wususu",
		            "nname": "wususu",
		            "tmCreated": 1501604357000
		        },
		        "content": "测试用户的测试message",
		        "tmCreated": 1502727913000,
		        "messageImageSet": [
		            {
		                "imageid": 130,
		                "webPath": "/images/2017-08-15/12:25:00_1502727900911.png",
		                "mid": 131
		            }
		        ],
		        "isFake": false,
		        "fakeName": null,
		        "likeCount": 0,
		        "commentCount": 0,
		        "commentNextVal": 1,
		        "location": {
		            "lid": 23,
		            "locale": "华山"
		        }
		    }
		}
 
- get mesage by page

 按`page`页码获取`message`信息,每页默认15条`message`.
 `page`页码从0开始计数

		- url: /api/message/page/{page}
		
		- method: GET
		
		- login: false
		
		- argument:
			           |   key       |  isrequired | defaultValue |    type      |
			-----------+-------------+-------------+--------------+--------------+
		 	    页码    |   null      |     true    |    null      |   Integer    |
		
		- response(success):
			{
			    "code": 200,
			    "message": "成功",
			    "content": {
			        "messageList": [
			            {
			                "mid": 129,
			                "uid": 1,
			                "user": {
			                    "uid": 1,
			                    "uname": "wususu",
			                    "nname": "wususu",
			                    "tmCreated": 1501604357000
			                },
			                "content": "测试用户的测试message",
			                "tmCreated": 1502727291000,
			                "messageImageSet": [
			                    {
			                        "imageid": 126,
			                        "webPath": "/images/2017-08-14/11:20:47_1502724047004.png",
			                        "mid": null
			                    }
			                ],
			                "isFake": false,
			                "fakeName": null,
			                "likeCount": 0,
			                "commentCount": 0,
			                "commentNextVal": 1,
			                "location": {
			                    "lid": 23,
			                    "locale": "华山"
			                }
			            },
			            {
			                "mid": 128,
			                "uid": 1,
			                "user": {
			                    "uid": 1,
			                    "uname": "wususu",
			                    "nname": "wususu",
			                    "tmCreated": 1501604357000
			                },
			                "content": "测试用户的测试message",
			                "tmCreated": 1502725914000,
			                "messageImageSet": [],
			                "isFake": false,
			                "fakeName": null,
			                "likeCount": 0,
			                "commentCount": 0,
			                "commentNextVal": 1,
			                "location": {
			                    "lid": 23,
			                    "locale": "华山"
			                }
			            }
			        ],
			        "page": {
			            "itemPerPage": 15,
			            "pageNum": 1,
			            "item": 2,
			            "page": 0,
			            "firstItem": 0,
			            "itemInThisPage": 2,
			            "first": 0
			        }
			    }
			}
			
			
- 按页获取某一地点的`message`

		- url: /api/message/lid/{lid}/page/{pid}
		
		- method: GET
		
		- login: false
		
		- argument:
			           |   key       |  isrequired | defaultValue |    type      |
			-----------+-------------+-------------+--------------+--------------+
		 	    页码    |   null      |     true    |    null      |   Integer    |
		 	    地点ID  |   null      |     true    |    null      |   Integer    |
		
		- response(success):
		{
		    "code": 200,
		    "message": "成功",
		    "content": {
		        "messageList": [
		            {
		                "mid": 129,
		                "uid": 1,
		                "lid": 23,
		                "user": {
		                    "uid": 1,
		                    "uname": "wususu",
		                    "nname": "wususu",
		                    "tmCreated": 1501604357000
		                },
		                "content": "测试用户的测试message",
		                "tmCreated": 1502727291000,
		                "messageImageSet": [],
		                "isFake": false,
		                "fakeName": null,
		                "likeCount": 0,
		                "commentCount": 0,
		                "commentNextVal": 1,
		                "location": {
		                    "lid": 23,
		                    "locale": "华山"
		                }
		            },
		            {
		                "mid": 128,
		                "uid": 1,
		                "lid": 23,
		                "user": {
		                    "uid": 1,
		                    "uname": "wususu",
		                    "nname": "wususu",
		                    "tmCreated": 1501604357000
		                },
		                "content": "测试用户的测试message",
		                "tmCreated": 1502725914000,
		                "messageImageSet": [],
		                "isFake": false,
		                "fakeName": null,
		                "likeCount": 0,
		                "commentCount": 8,
		                "commentNextVal": 18,
		                "location": {
		                    "lid": 23,
		                    "locale": "华山"
		                }
		            }
		        ],
		        "page": {
		            "itemPerPage": 15,
		            "pageNum": 1,
		            "item": 2,
		            "page": 0,
		            "firstItem": null,
		            "itemInThisPage": 2,
		            "first": 0
		        }
		    }
		}

- 发表评论

		- url: /api/comment/create
		
		- method: POST
		
		- login: true
		
		- argument:
			              |   key       |  isrequired | defaultValue |    type         |
			--------------+-------------+-------------+--------------+-----------------+
		 	         正文  |   content   |     true    |              |   String        |
			 回复的message |   mid       |     true    |              |   Integer       |
			 回复的comment |   rcid      |     false   |     null     |   Integer       |	
			     
		- response(success):
			{
			    "code": 200,
			    "message": "成功",
			    "content": "(=ↀωↀ=)"
			}


- 删除评论


		- url: /api/comment/delete
		
		- method: DELETE
		
		- login: true
		
		- argument:
			              |   key       |  isrequired | defaultValue |    type         |
			--------------+-------------+-------------+--------------+-----------------+
			  commentID   |    cid      |     true    |              |   Integer       |	
			     
		- response(success):
			{
			    "code": 200,
			    "message": "成功",
			    "content": "(=ↀωↀ=)"
			}
			
			
- 按页获取某一`message`下的评论, 同样,页码从零开始计数

		- url: /api/comment/message/{mid}/page/{pageid}
		
		- method: DELETE
		
		- login: true
		
		- argument:
			              |   key       |  isrequired | defaultValue |    type         |
			--------------+-------------+-------------+--------------+-----------------+
			  commentID   |    cid      |     true    |              |   Integer       |	
			     
		- response(success):
		{
				"code": 200,
		    "message": "成功",
		    "content": {
		        "commentList": [
		            {
		                "cid": 136,
		                "mid": 128,
		                "rcid": null,
		                "commentVal": 3,
		                "user": {
		                    "uid": 1,
		                    "uname": "wususu",
		                    "nname": "wususu",
		                    "tmCreated": 1501604357000
		                },
		                "content": "回复128号评论",
		                "tmCreated": 1502791001000,
		                "likeCount": 0,
		                "isFake": false,
		                "fakeName": null
		            },
		            {
		                "cid": 137,
		                "mid": 128,
		                "rcid": null,
		                "commentVal": 5,
		                "user": {
		                    "uid": 1,
		                    "uname": "wususu",
		                    "nname": "wususu",
		                    "tmCreated": 1501604357000
		                },
		                "content": "回复128号评论",
		                "tmCreated": 1502791003000,
		                "likeCount": 0,
		                "isFake": false,
		                "fakeName": null
		            },
		            {
		                "cid": 139,
		                "mid": 128,
		                "rcid": 136,
		                "commentVal": 7,
		                "user": {
		                    "uid": 4,
		                    "uname": "testuser1",
		                    "nname": "测试用户1",
		                    "tmCreated": 1502693156000
		                },
		                "content": "回复128号评论",
		                "tmCreated": 1502792950000,
		                "likeCount": 0,
		                "isFake": false,
		                "fakeName": null
		            },
		            {
		                "cid": 140,
		                "mid": 128,
		                "rcid": 136,
		                "commentVal": 9,
		                "user": {
		                    "uid": 4,
		                    "uname": "testuser1",
		                    "nname": "测试用户1",
		                    "tmCreated": 1502693156000
		                },
		                "content": "回复134号评论",
		                "tmCreated": 1502793304000,
		                "likeCount": 0,
		                "isFake": false,
		                "fakeName": null
		            },
		            {
		                "cid": 141,
		                "mid": 128,
		                "rcid": 136,
		                "commentVal": 11,
		                "user": {
		                    "uid": 4,
		                    "uname": "testuser1",
		                    "nname": "测试用户1",
		                    "tmCreated": 1502693156000
		                },
		                "content": "回复134号评论",
		                "tmCreated": 1502793324000,
		                "likeCount": 0,
		                "isFake": false,
		                "fakeName": null
		            },
		            {
		                "cid": 142,
		                "mid": 128,
		                "rcid": 136,
		                "commentVal": 13,
		                "user": {
		                    "uid": 4,
		                    "uname": "testuser1",
		                    "nname": "测试用户1",
		                    "tmCreated": 1502693156000
		                },
		                "content": "回复134号评论",
		                "tmCreated": 1502793494000,
		                "likeCount": 0,
		                "isFake": false,
		                "fakeName": null
		            },
		            {
		                "cid": 143,
		                "mid": 128,
		                "rcid": 136,
		                "commentVal": 15,
		                "user": {
		                    "uid": 4,
		                    "uname": "testuser1",
		                    "nname": "测试用户1",
		                    "tmCreated": 1502693156000
		                },
		                "content": "回复134号评论",
		                "tmCreated": 1502793507000,
		                "likeCount": 0,
		                "isFake": false,
		                "fakeName": null
		            },
		            {
		                "cid": 144,
		                "mid": 128,
		                "rcid": 136,
		                "commentVal": 17,
		                "user": {
		                    "uid": 4,
		                    "uname": "testuser1",
		                    "nname": "测试用户1",
		                    "tmCreated": 1502693156000
		                },
		                "content": "回复134号评论",
		                "tmCreated": 1502793562000,
		                "likeCount": 0,
		                "isFake": false,
		                "fakeName": null
		            }
		        ],
		        "page": {
		            "itemPerPage": 15,
		            "pageNum": 1,
		            "item": 8,
		            "page": 0,
		            "firstItem": 0,
		            "itemInThisPage": 8,
		            "first": 0
		        },
		        "message": {
		            "mid": 128,
		            "uid": 1,
		            "user": {
		                "uid": 1,
		                "uname": "wususu",
		                "nname": "wususu",
		                "tmCreated": 1501604357000
		            },
		            "content": "测试用户的测试message",
		            "tmCreated": 1502725914000,
		            "messageImageSet": [],
		            "isFake": false,
		            "fakeName": null,
		            "likeCount": 0,
		            "commentCount": 8,
		            "commentNextVal": 18,
		            "location": {
		                "lid": 23,
		                "locale": "华山"
		            }
		        }
		    }
		}

