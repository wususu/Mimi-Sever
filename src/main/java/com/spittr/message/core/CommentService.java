package com.spittr.message.core;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.spittr.message.model.Comment;
import com.spittr.message.model.Message;
import com.spittr.user.model.User;


public interface CommentService {
	
	public static Comment newInstance(String content, User user, Message underMessage, Comment replayComment){
		if (user == null || underMessage == null) 
			throw new NullPointerException();
		if (replayComment == null) 
			return new Comment(content, user, underMessage);
		return new Comment(content, user, underMessage, replayComment);
	}

	void create(Message underWhichMessage, User user, String content, Comment replayComment, Boolean isFake);
	
	void save(Comment comment);
	
	Comment get(Long cid);
	
	Map<String, Object> getByMidAndPageNumber(Long mid, Integer pageNumber);
	
	Map<String, Object> getCommentBeforeTime(Long mid, Date time);
	
	Map<String, Object> getCommentAfterTime(Long mid, Date time);
	
	List<Comment> getByMid(Long mid);
	
	void delete(Comment comment);
	
	void deleteUnderMessage(Message message);
	
}
