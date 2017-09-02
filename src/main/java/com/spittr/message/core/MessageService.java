package com.spittr.message.core;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import com.spittr.image.model.MessageImage;
import com.spittr.location.model.Location;
import com.spittr.message.model.Message;
import com.spittr.user.model.User;


public interface MessageService {

	public static Message newInstance(String content, User user, Location location){
		if (user == null || location == null)
			throw new NullPointerException();
		
		return new Message(content, user, location);
	}
	
	void createWhichImage(Message message, Set<MessageImage> imageSet);
	
	void create(Message message);
	
	void save(Message message);
	
	void adcNextCommentVal(Message message);
	
	void adcCommentCount(Message message);
	
	void decCommentCount(Message message);
	
	Message get(Long mid);
	
	void delete(Message message);
	
	Map<String, Object> getMessageByPageNumber(Integer pageNumber);
	
	Map<String, Object> getLocaleMessageByPageNumber(Long lid, Integer pageNumber);
	
	Map<String, Object> getMessageBeforeTime(Date time);
	
	Map<String, Object> getMessageAfterTime(Date time);

	
}
