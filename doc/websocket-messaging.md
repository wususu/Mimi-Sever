# websocket 消息通知
----

####  一.消息类型:

- 1. 其他用户给`message`点赞时通知`message`用户
- 2. 其他用户给`comment`点赞时通知`comment`用户
- 3. 其他用户发表评论时通知`message`用户
- 4. 其他用户回复评论时通知`被回复的comment`用户和`message`用户



#### 二.交互流程:

同样采用类TCP三次握手交互流程:
 1. 当触发操作,服务器发送一定格式的消息到被通知用户
 2. 被通知用户接收到消息后,向服务器发送确认接收消息
 3. 当服务器接收到确认接收消息后,返回操作成功通知

##### 1.地址:

- 操作1的订阅地址: /user/+`{uname}`+/notice
- 操作2的发送地址: /socket/ntc/rcv
- 操作3的订阅地址: /user/queue/rcv/status

##### 2.实体:

- 操作1的接收通知实体:

a.  消息(一.1)的接收实体:

		{
			"ntcType":"mLikee",             	// 通知类型
			"ntcBody":{				// 通知对象
				"id":417,			// 通知ID
				"mid":407,			// messageID
				"mUid":407,			// 发送message的用户(通知对象)的ID
				"lkUid":2,			// 点赞对象ID
				"mUname":"wususu",		// 通知对象uname
				"lkUname":"liaojiekx",		// 点赞对象uname
				"lkNname":"liaojie",		// 点赞对象昵称
				"mShrtCntnt":"测试用户的测试mes", // 点赞的message的部分内容(截取)
				"tmCreated":1507131869000	// 不用管
				}
		}

b. 消息(一.2)的接收实体:

		{
			"ntcType":"cLikee",
			"ntcBody":{
			"id":438,
			"cUid":1,
			"cUname":"wususu",
			"cid":426,
			"lkUid":2,
			"lkUname":"liaojiekx",
			"lkNname":"liaojie",
			"cShrtCntnt":"测试用户的测试com",
			"tmCreated":1507137589000
			}
		}

c. 消息(一.3)的接收实体:

		{
			"ntcType":"Cmmnt",
			"ntcBody":{
				"id":419,
				"cid":418,			
				"mid":407,			
				"cUid":2,			// 发送comment的用户ID
				"mUid":1,			// 发送message的用户(通知对象)ID
				"cUname":"liaojie",		
				"mUname":"wususu",
				"cNname":"liaojiekx",		
				"cShrtCntnt":"测试用户的测试com",
				"mShrtCntnt":"测试用户的测试mes",
				"tmCreated":1507131987248
				}
		}

d. 消息(一.4)的接收实体:

		{
			"ntcType":"rCmmnt",
			"ntcBody":{
			"id":120,
			"cid":118,							// 评论
			"rcid":47,							// 被回复的评论
			"mid":22,
			"cUid":2,
			"rcUid":1,
			"mUid":1,
			"cUname":"liaojiekx",				// 评论的用户名
			"mUname":"wususu",					
			"rcUname":"wususu",					// 被回复的评论的用户名
			"cNname":"啊哈哈哈",						// 评论的用户昵称
			"cShrtCntnt":"的评论22",
			"rcShrtCntnt":"的评论22",
			"tmCreated":1507640232000
			}
		}

- 操作2的发送确认消息实体:

		{
			"ntcType":"cLikee",		// cLikee mLikee Cmmnt rCmmnt 四种类型
			"id":438			// 通知ID
		}
		
- 操作3的成功消息实体:

		{
			"msgID":"438",			// 通知ID
			"code":200,
			"status":"成功",
			"content":"(=ↀωↀ=)"
		}



		
