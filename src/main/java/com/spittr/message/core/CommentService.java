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
	
	Comment need(Long cid);

	
	/**
	 * 根据mid和时间获取message详细信息, 包括message的comment
	 * @param mid
	 * @param pageNumber
	 * @return
	 */
	Map<String, Object> getByMidAndTmBefore(Long mid, Date tmbefore, User currentUser);
	
	Map<String, Object> getCommentBeforeTime(Long mid, Date time, User currentUser);
	
	Map<String, Object> getCommentAfterTime(Long mid, Date time, User currentUser);
	
	List<Comment> getByMid(Long mid);
	
	void delete(Comment comment);
	
	void deleteUnderMessage(Message message);
	
}
