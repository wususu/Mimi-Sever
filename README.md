 
# Mimi 华南农业大学匿名朋友圈(Sever Side)

 <img src="./pic/logo.png" style="text-align:center;" width="40%" height="60%">

- web客户端: [web client](https://github.com/Q-Zhan/IT-Farm)
- 安卓客户端: [Android client](https://github.com/Arsun/Mimi)

## 当前功能:

- 用户登录
- 用户关注/取关
- 发送可匿名可附图片可定位的`message`
- 发表评论
- 私聊
- 用户资料修改查看
- 分类查看`message`
- 用户消息通知
- Oauth2.0第三方登录

## 功能展示

！[点对点聊天与消息推送](./pic/gif/消息_通知_聊天.gif)

## 相关文档

- [api 文档](./doc/api-list.md)
- [status code 返回码状态表](./doc/code-status-doc.md)
- [OAuth2.0接入流程](./doc/OAuth2.0-protocol.md)
- [消息通知接入流程](./doc/websocket-messaging.md)

## 更新日志:

##### 2017.8.15  Mimi-1.0

- [api文档](./doc/api-list.md)

##### 2017.8.21 Mimi-1.1

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

#### 2017.9.20 Mimi-3.1

- 添加用户头像的修改更新
- 添加查看某用户发过的历史`message`
- 修复进入用户个人主页时,没有显示是否关注的bug
- 接口获取`comment`新增`rcNname`
- 添加异常状态表`[status code 返回码状态表](./doc/code-status-doc.md)`
- 应用测试地址: http://104.224.174.146:8080/Mimi-3.1/

#### 2017.10.5 Mimi-4.0

- 聊天接口加入用户昵称
- 引入EHcahce缓存框架
- 添加红满堂Oauth2.0登录: [OAuth2.0接入流程](./doc/OAuth2.0-protocol.md)
- 添加用户消息通知: [消息通知接入流程](./doc/websocket-messaging.md)
- 增加接口查看所有关注用户的`message`
- 增加接口按时间获取某一地点的`message`
- /api/message/get/{mid}接口返回点赞likee的信息
- 关注/被关注列表增加关注.被关注的时间 - 应用测试地址: http://104.224.174.146:8080/Mimi-4.0
- 接口文档已经更新:[接口文档](./doc/api-list.md)


## TODO:

-[x] `message` `comment`点赞

-[x] 用户添加好友功能

-[x] 点对点聊天

-[x] 用户详细资料,头像

-[x] 接入红满堂OAuth2.0授权登录

-[x] 系统消息通知

-[x] 信息验证(正方爬虫认证)
