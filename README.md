# Mimi 
### 华南农业大学匿名朋友圈

  <img src="./pic/logo.png" style="text-align:center;" width="40%" height="60%">
  
## 当前功能:
- 用户登录
- 发送可匿名可附图片可定位的`message`
- 发表评论

## 更新日志:
##### 2017.8.15  Mimi-1.0 <br/>
 - [api文档](./doc/api-list.md)

##### 2017.8.21 Mimi-1.1 <br/> 
 - 增加跨域支持和创建帐号时重复用户名/昵称提示 <br/>
 - 请求地址: http://104.224.174.146:8080/Mimi-1.1/
 
##### 2017.9.5 Mimi-2.0 
 - 增加用户websocket通讯功能
 - 修复了可获端获取message重复出现的bug
 - 新增了根据Unix timestamp 获取message/comment的接口, api文档已更新
 - 应用测试地址: http://104.224.174.146:8080/Mimi-2.0/
 - [websocket客户端连接协议](./doc/websocket-wechat-protocol.md)
 
##### 2017.9.13 Mimi-3.0
 - 新增message点赞和关注其他用户功能
 - 用户个人信息增加一些条目
 - 增加修改个人信息和查看他人个人信息的功能
 - 修复因默认`equals`方法导致回复评论时出现`message不同`的Exception
 - 接口获取的comment新增`rcUname(回复目标评论的用户名)`
 - 具体接入方式请查看:[接口文档](./doc/api-list.md)
 - 应用测试地址: http://104.224.174.146:8080/Mimi-3.0/

 
## TODO:
- [x] `message` `comment`点赞

- [x] 用户添加好友功能

- [x] 点对点聊天

- [ ] 用户详细资料,头像

- [ ] 系统消息通知