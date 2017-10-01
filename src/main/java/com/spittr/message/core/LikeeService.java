package com.spittr.message.core;

import java.util.Map;

import com.spittr.message.model.CLikee;
import com.spittr.message.model.Comment;
import com.spittr.message.model.MLikee;
import com.spittr.message.model.Message;
import com.spittr.user.model.User;

public interface LikeeService {

	static MLikee newInstance(Message message, User user){
		return new MLikee(message, user);
	}
	
	void create(MLikee entity);
	
	void create(CLikee entity);
	
	MLikee getMlikee(long mid, long uid);
	
	CLikee getClikee(long cid, long uid);
	
	MLikee get(Message message, User user);
	
	CLikee get(Comment comment, User user);
	
	Map<String, Object>  likee(MLikee likee);
	
	Map<String, Object> likee(CLikee likee);
	
	void generateLikee(Message message, User user);
	
	void generateLikee(Comment comment, User user);

}
