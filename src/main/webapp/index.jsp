<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="http://libs.baidu.com/jquery/1.8.3/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/sockjs/1/sockjs.min.js"></script>
<script  src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.js"></script>
</head>
<body>
<h1>Wellcome to Mimi.</h1>
<div id="message">
<input id="srect" type="text"/> 
<input id="user" type="text"/> 
<button id="connect">connect</button>
<button id="send" value="send">send</button>
</div>
</body>
<script type="text/javascript">
	
	$(document).ready(function(){
		var sock;
		var stomp;
		$("#send").click(function () {
			sendMsg();
		});
		
		$("#connect").click(function () {
			var srect = $("#srect").val();
			console.info(srect);
			
			if(window.WebSocket){
				sock = new SockJS("/Mimi/socketServer?srect=" + srect);
				socketHandler();
				stomp = Stomp.over(sock);
				stomp.connect(
						{},
						function (frame) {
							console.info("Connected: " + frame);
							stomp.subscribe("/topic/message", function (message) {
								console.info("message: " + message);
								console.info("message body: " + message.body);
							});
							
							stomp.subscribe("/user/"+$("#user").val()+"/recive", function (message) {
								console.info("message: "+ message);
								console.info("message body: "+message.body);
								var chatMsg = JSON.parse(message.body);
								reciveMsg(chatMsg.chatId, chatMsg.reciverId);
							});
						}
				);
			}else{
				alert("错误,不支持websocket")
			}
		})

		
		function socketHandler() {
			sock.onopen = function () {
				console.info("---连接成功---");
			};
			
			sock.onmessage = function (event) {
				console.info("---Recived: " + event.data);
			};
			
			sock.onclose = function (event) {
				console.info("---Info: connection closed---");
			};
			
			sock.onerror = function () {
				alert("连接错误");
				disconnect();
			};
		}
		
		function disconnect(){
			if (sock != null){
				sock.close();
				sock = null;
			}
		}
		
		function  reciveMsg(chatId, reciverId) {
			stomp.send("/socket/socketServer/chatReciver",{},
					  JSON.stringify(
	                		  {
	                			  'chatId': chatId,
	                			  'reciverId':reciverId,
	                		  }
	                		  ));
			
		}
		
		function sendMsg() {
			 stomp.send("/socket/socketServer/chatSender", {}, 
	                  JSON.stringify(
	                		  {
	                			  'senderId': 1,
	                			  'reciverId':2,
	                			  'content':'聊天啦啦啦啦啦啦啦啦啦类'
	                		  }
	                		  ));
	                		  
		}
	})
	
</script>
</html>