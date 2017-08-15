package com.spittr.image.core;

import java.util.List;

import com.spittr.image.model.MessageImage;


public interface MessageImageService {
	
	static MessageImage newInstance(String realPath,String webPath){
		return new MessageImage(realPath, webPath);
	}
	
	MessageImage get(Long imageid);
	
	List<MessageImage> getByMid(Long mid);
	
	MessageImage get(String webPath);
	
	void save(MessageImage messageImage);
	
	void delete(MessageImage messageImage);
	
	String saveImageFromBase64(String fatherPath, String base64String);
}
