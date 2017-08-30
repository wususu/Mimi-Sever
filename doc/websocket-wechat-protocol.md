## websocket 点对点通讯协议
	- websocket 不同于http, 前者更为底层,即时使用子协议stomp,还是不能满足点对点通讯需求,所以自定义了一个聊天通讯协议

	
- 客户端:
  用户每发出一条聊天信息, 除了必要的消息条目, 还要加上一个唯一序列码,用于后续的消息发送情况定位.<br/>
  序列码采用SHA1的加密方式生成: 唯一序列码 = SHA1(发送的主题内容 + 当前日期(毫秒数)) <br/>
  前端代码示例:
  
	<script src="http://cdn.rawgit.com/h2non/jsHashes/master/hashes.js"></script>
	var SHA1 = new Hashes.SHA1;
	var  data = {
				'senderId': 1,
				'reciverId':2,
				'content':'聊天内容'
			}
	var srect  = SHA1.hex(data+ (new Date()).getTime(););
	data.chatId = srect;	
	//data = {senderId: 1, reciverId: 2, content: "聊天内容", chatId: "edb1ca1101d9f3406e7e269c9a3d4fe0034ca48b"}			

