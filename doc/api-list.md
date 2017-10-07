# Mimi api document

- 本系统目前共有`user`, `image`,`location`, `message` 四个模块, `websocket`点对点通讯模块正在开发中.
-  api示例中url默认忽略应用地址: `http://ip:port/应用名`, 实际使用过程中自行添加
- 符号说明: 

		url:			请求地址
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

		- url: /api/user/me
		
		- method: GET
		
		- login: true
			     
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
		
		- method: GET
		
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
		            }
		          
		        ]
		    }
		}




### message api

- create message

 图片可以设置1~3张,多张照片时,只要加多一个`imageidList`
 ![请求示例](../pic/示例(create%20message).png)

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
		
		- method: GET
		
		- login: false
			     
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

### Update (2017.9.5):  get message/comment by times 

 根据时间戳获取message/comment, {time}为Unix timestamp, 按日期从大到小排序

- 获取指定时间之前的10条message
 
		- url: /api/message/tmbefore/{time}

		- method: GET

		- login: false

		- response(success):
			{
			    "code": 200,
			    "message": "成功",
			    "content": {
				"tmAfter": 1502856242000,
				"messageList": [
				    {
					"mid": 150,
					"uid": 4,
					"lid": 24,
					"user": {
					    "uid": 4,
					    "uname": "testuser1",
					    "nname": "测试用户1",
					    "tmCreated": 1502693156000
					},
					"content": "吴叔叔的心情",
					"tmCreated": 1502856242000,
					"messageImageSet": [
					    {
						"imageid": 148,
						"webPath": "/images/2017-08-16/12:03:01_1502856181514.png",
						"mid": 150
					    },
					    {
						"imageid": 149,
						"webPath": "/images/2017-08-16/12:03:06_1502856186349.png",
						"mid": 150
					    }
					],
					"isFake": false,
					"fakeName": null,
					"likeCount": 0,
					"commentCount": 0,
					"commentNextVal": 1,
					"location": {
					    "lid": 24,
					    "locale": "图书馆"
					}
				    },
				    {
					"mid": 168,
					"uid": 1,
					"lid": 25,
					"user": {
					    "uid": 1,
					    "uname": "wususu",
					    "nname": "wususu",
					    "tmCreated": 1501604357000
					},
					"content": "测试用户的测试message",
					"tmCreated": 1503737006000,
					"messageImageSet": [
					    {
						"imageid": 166,
						"webPath": "/images/2017-08-26/04:41:00_1503736860047.png",
						"mid": 168
					    },
					    {
						"imageid": 165,
						"webPath": "/images/2017-08-26/04:40:56_1503736856930.png",
						"mid": 168
					    }
					],
					"isFake": false,
					"fakeName": null,
					"likeCount": 0,
					"commentCount": 0,
					"commentNextVal": 1,
					"location": {
					    "lid": 25,
					    "locale": "教四"
					}
				    },
				],
				"numThisPage": 3,
				"numPerPage": 15
			    }
			}

- 获取指定日期之后的10条message
	
		- url: /api/message/tmafter/{time}
		
		- method: GET
		
		- login: false
			     
		- response(success):
			略

- 获取指定某一message下指定日期前的10条comment

		- url: /api/comment/message/{mid}/tmbefore/{time}
		
		- method: GET
		
		- login: false
			     
		- response(success):

			  {
			    "code": 200,
			    "message": "成功",
			    "content": {
				"commentList": [
				    {
					"cid": 277,
					"rcuid": 2,
					"mid": 222,
					"rcid": 237,
					"rcUname": "liaojiekx",
					"commentVal": 25,
					"user": {
					    "uid": 2,
					    "uname": "liaojiekx",
					    "nname": "liaojie",
					    "tmCreated": 1501605274000,
					    "site": "华山",
					    "signature": "个性签名",
					    "gender": true
					},
					"content": "廖狗的测试replay comment",
					"tmCreated": 1505228123000,
					"likeCount": 0,
					"fakeName": null,
					"delete": false,
					"fake": false
				    },
				   
				],
				"tmBefore": 1505233506778,
				"numThisPage": 15,
				"numPerPage": 15,
				"message": {
				    "mid": 222,
				    "uid": 2,
				    "lid": 26,
				    "user": {
					"uid": 2,
					"uname": "liaojiekx",
					"nname": "liaojie",
					"tmCreated": 1501605274000,
					"site": "华山",
					"signature": "个性签名",
					"gender": true
				    },
				    "content": "廖狗的测试message1",
				    "tmCreated": 1504453626000,
				    "messageImageSet": [],
				    "fakeName": null,
				    "likeCount": 1,
				    "likee": null,
				    "commentCount": 25,
				    "commentNextVal": 26,
				    "location": {
					"lid": 26,
					"locale": "教三"
				    },
				    "delete": false,
				    "fake": false
				}
			    }
			  }
	

- 获取指定某一message下指定日期后的10条comment

		- url: /api/comment/message/{mid}/tmafter/{time}
		
		- method: GET
		
		- login: false
			     
		- response(success):
			略


### Update(2017.9.13):
> `/api/comment/message/{mid}/tmbefore/{time}`内容更新在上面(已经改了response)

#### 1.用户信息模块更新

- 查看个人信息: 

		- url: /api/user/me
		
		- method: GET
		
		- login: true
		
		- response:
		{
		    "code": 200,
		    "message": "成功",
		    "content": {
			"uid": 2,
			"uname": "liaojiekx",
			"nname": "liaojie",
			"tmCreated": 1501605274000,
			"faculty": "faculty111",
			"grade": 2015,
			"site": "五山",
			"signature": "帅气的人",
			"gender": true
		    }
		}


- 修改个人信息:

		- url: /api/user/profile/edit
		
		- method: POST
		
		- login: true
		
		-argument: 
			           |   key       |  isrequired | defaultValue |    type                      |
			-----------+-------------+-------------+--------------+------------------------------+
		 	    性别    |   gender    |     false    |    null      |   Boolean(true:男/false:女) |
		 	    宿舍区  |   site      |     false    |    null      |   String                    |
			    心情    |   signature |     false    |    null      |   String                    |
			    入学年份 |   grade     |     false    |    null      |   Integer                  |
			    学院    |   faculty   |     false    |    null      |   String                    |


		- response(success):
		{
		    "code": 200,
		    "message": "成功",
		    "content": {
			"uid": 2,
			"uname": "liaojiekx",
			"nname": "liaojie",
			"tmCreated": 1501605274000,
			"faculty": "faculty111",
			"grade": 2015,
			"site": "五山",
			"signature": "帅气的人",
			"gender": true
		    }
		}

- 查看其他用户个人信息:
`isAttention`:代表当前登录用户是否关注此用户


		- url: /api/user/profile/{uname}
		
		- method: GET
		
		- login: true
		
		- response:
			{
			    "code": 200,
			    "message": "成功",
			    "content": {
			        "isAttention": true,
			        "user": {
			            "uid": 16,
			            "uname": "liaojiekx",
			            "nname": "liaojie",
			            "tmCreated": 1504930854000,
			            "userPic": null,
			            "faculty": null,
			            "grade": null,
			            "site": null,
			            "signature": null,
			            "gender": null
			        }
			    }
			}

#### 2.新增点赞接口
 注意: 点赞和取消点赞都是同一个接口

		- url: /api/message/likee/{mid}
		
		- method: POST
		
		- login: true
		
		- response(点赞):
		{
		    "code": 200,
		    "message": "成功",
		    "content": {
			"isLike": true,
			"likeCount": 2
		    }
		}
		
		-response(取消点赞):
		{
		    "code": 200,
		    "message": "成功",
		    "content": {
			"isLike": false,
			"likeCount": 1
		    }
		}		
		
#### 3.新增关注用户功能:
`mainUser`为关注操作的发起者, `objectUser`为操作接受者

- 关注用户:

		- url: /api/user/relationship/add
		
		- method: POST
		
		- login: true
		
		- argument:
			           |   key       |  isrequired | defaultValue |    type   |
			-----------+-------------+-------------+--------------+-----------+
		 	  接受用户  | objectUname |     true    |    null      |  String   |

		
		- response(success):
		{
		    "code": 200,
		    "message": "成功",
		    "content": {
			"id": 283,
			"mainUser": {
			    "uid": 2,
			    "uname": "liaojiekx",
			    "nname": "liaojie",
			    "tmCreated": 1501605274000,
			    "faculty": "faculty111",
			    "grade": 2015,
			    "site": "五山",
			    "signature": "帅气的人",
			    "gender": true
			},
			"objectUser": {
			    "uid": 3,
			    "uname": "201527010324",
			    "nname": "吴培坚",
			    "tmCreated": 1501833774000,
			    "faculty": null,
			    "grade": null,
			    "site": null,
			    "signature": null,
			    "gender": null
			},
			"tmCreated": 1505306762679,
			"delete": false
		    }
		}
		
- 取关用户:

		- url: /api/user/relationship/cancel
		
		- method: POST
		
		- login: true
		
		- argument:
			           |   key       |  isrequired | defaultValue |    type   |
			-----------+-------------+-------------+--------------+-----------+
		 	  接受用户  | objectUname |     true    |    null      |  String   |

		
		- response(success):
		{
		    "code": 200,
		    "message": "成功",
		    "content": "(=ↀωↀ=)"
		}
		
- 查看当前用户所关注的用户:
参数为时间,显示某时间之前关注的15个用户

		- url: /api/user/relationship/asmain/{time}
		
		- method: GET
		
		- login: true
		
		- response(success):
		{
		    "code": 200,
		    "message": "成功",
		    "content": {
			"mainUserList": [
			    {
				"uid": 3,
				"uname": "201527010324",
				"nname": "吴培坚",
				"tmCreated": 1501833774000,
				"faculty": null,
				"grade": null,
				"site": null,
				"signature": null,
				"gender": null
			    },
			    {
				"uid": 1,
				"uname": "wususu",
				"nname": "wususu",
				"tmCreated": 1501604357000,
				"faculty": null,
				"grade": null,
				"site": null,
				"signature": null,
				"gender": null
			    }
			],
			"tmBefore": 1505307265295,
			"numThisPage": 2,
			"numPerPage": 15
		    }
		}
		
- 查看所有关注当前用户的用户:
 (同上)
 
		- url: /api/user/relationship/asobject/{time}
		
		- method: GET
		
		- login: true
		
		- response(success):
		{
		    "code": 200,
		    "message": "成功",
		    "content": {
			"mainUserList": [
			    {
				"uid": 1,
				"uname": "wususu",
				"nname": "wususu",
				"tmCreated": 1501604357000,
				"faculty": null,
				"grade": null,
				"site": null,
				"signature": null,
				"gender": null
			    }
			],
			"tmBefore": 1505307493230,
			"numThisPage": 1,
			"numPerPage": 15
		    }
		}

#### 4.查看当前用户所发过的message:

		- url: /api/message/me/{time}
		
		- method: GET
		
		- login: true
		
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
				    "tmCreated": 1501604357000,
				    "faculty": null,
				    "grade": null,
				    "site": null,
				    "signature": null,
				    "gender": null
				},
				"content": "测试用户的测试message",
				"tmCreated": 1502727291000,
				"messageImageSet": [],
				"fakeName": null,
				"likeCount": 0,
				"likee": null,
				"commentCount": 0,
				"commentNextVal": 1,
				"location": {
				    "lid": 23,
				    "locale": "华山"
				},
				"delete": false,
				"fake": false
			    },
			    {
				"mid": 128,
				"uid": 1,
				"lid": 23,
				"user": {
				    "uid": 1,
				    "uname": "wususu",
				    "nname": "wususu",
				    "tmCreated": 1501604357000,
				    "faculty": null,
				    "grade": null,
				    "site": null,
				    "signature": null,
				    "gender": null
				},
				"content": "测试用户的测试message",
				"tmCreated": 1502725914000,
				"messageImageSet": [],
				"fakeName": null,
				"likeCount": 0,
				"likee": null,
				"commentCount": 8,
				"commentNextVal": 18,
				"location": {
				    "lid": 23,
				    "locale": "华山"
				},
				"delete": false,
				"fake": false
			    }
			],
			"tmBefore": 1502727291000,
			"numThisPage": 2,
			"numPerPage": 15
		    }
		}

		
### Update(2017.9.20)

#### 1.查看某用户发过的历史message:

		- url: /api/message/user/{uname}/{tmbefore}
		
		- method: GET
		
		- login: false
		
		- response(success):
		
			{
			    "code": 200,
			    "message": "成功",
			    "content": {
			        "messageList": [
			            {
			                "mid": 1206,
			                "uid": 1,
			                "lid": 3,
			                "user": {
			                    "uid": 1,
			                    "uname": "wususu",
			                    "nname": "吴叔叔",
			                    "tmCreated": 1503126951000,
			                    "userPic": null,
			                    "faculty": "外国语学院",
			                    "grade": 2016,
			                    "site": "华山区",
			                    "signature": "哈哈哈哈",
			                    "gender": true
			                },
			                "content": "test",
			                "tmCreated": 1505533770000,
			                "messageImageSet": [],
			                "fakeName": null,
			                "likeCount": 0,
			                "likee": null,
			                "commentCount": 0,
			                "commentNextVal": 1,
			                "location": {
			                    "lid": 3,
			                    "locale": "图书馆"
			                },
			                "fake": false,
			                "delete": false
			            },
			           
			        ],
			        "tmBefore": 1505991552502,
			        "numThisPage": 6,
			        "numPerPage": 15
			    }
			}
			
#### 2.用户头像修改/更新:

		- url: /api/image/user/create
		
		- method: POST
		
		- login: true
		
		- argument:
			           |   key       |  isrequired | defaultValue |    type   |
		   ----------------+-------------+-------------+--------------+-----------+
		    图片base64编码  |    image    |     true    |      -       |  String   |

		- response(success):
				{
		    "code": 200,
		    "message": "成功",
		    "content": {
		        "webPath": "/Mimi-3.1/images/2017-09-21/07:19:15_1505992755141.png",
		        "delete": false,
		        "imageid": 1568
		    }
			}

用户有头像的话, 会在个人信息出自动添加一个`userPic`

		{
		    "code": 200,
		    "message": "成功",
		    "content": {
		        "isAttention": false,
		        "user": {
		            "uid": 16,
		            "uname": "liaojiekx",
		            "nname": "liaojie",
		            "tmCreated": 1504930854000,
		            "userPic": {
		                "webPath": "/images/2017-09-21/07:17:21_1505992641649.png",
		                "delete": false,
		                "imageid": 1568
		            },
		            "faculty": null,
		            "grade": null,
		            "site": null,
		            "signature": null,
		            "gender": null
		        }
		    }
		}


### Update(2017.10.5)

#### 1.查看所有关注用户的message:

		- url: /api/message/attention/tmbefore/{time}
		
		- method: GET
		
		- login: false
		
		- response(success):
		{
		    "code": 200,
		    "message": "成功",
		    "content": {
			"messageList": [
			    {
				"mid": 222,
				"uid": 2,
				"lid": 26,
				"user": {
				    "uid": 2,
				    "uname": "liaojiekx",
				    "nname": "liaojie",
				    "tmCreated": 1501605274000,
				    "userPic": null,
				    "faculty": "faculty111",
				    "grade": 2015,
				    "site": "五山",
				    "signature": "帅气的人",
				    "gender": true
				},
				"content": "廖狗的测试message1",
				"tmCreated": 1504453626000,
				"messageImageSet": [],
				"fakeName": null,
				"likeCount": 2,
				"likee": {
				    "id": 264,
				    "mid": 222,
				    "uid": 1,
				    "like": true
				},
				"commentCount": 32,
				"location": {
				    "lid": 26,
				    "locale": "教三"
				},
				"delete": false,
				"fake": false
			    }
			    .....
			    ..
			
			],
			"tmBefore": 1507175540866,
			"numThisPage": 1,
			"numPerPage": 15
		    }
		}

#### 2.按时间查看某地点message:

		- url: /api/message/lid/{lid}/tmbefore/{time}
		
		- method: GET
		
		- login: false
		
		- response:略
		
		
		- url: /api/message/lid/{lid}/tmafter/{time}
		
		- method: GET
		
		- login: false
		
		- response:略


#### 3.评论点赞:

		- url: /api/likee/comment/{cid}
		
		- method: POST
		
		- login: true
		
		- response:
		{
		    "code": 200,
		    "message": "成功",
		    "content": {
			"isLike": true,
			"likeCount": 1
		    }
		}
