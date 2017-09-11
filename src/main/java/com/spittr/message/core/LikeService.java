package com.spittr.message.core;

import java.util.Map;

import com.spittr.message.model.Like;
import com.spittr.message.model.Message;
import com.spittr.user.model.User;

public interface LikeService {

	static Like newInstance(Message message, User user){
		return new Like(message, user);
	}
	
	void create(Like entity);
	
	Like get(long mid, long uid);
	
	Like get(Message message, User user);
	
	Map<String, Object>  like(Like like);
	
	Map<String, Object>  dislike(Like like);
}
