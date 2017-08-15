package com.spittr.message.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.spittr.message.exception.AuthorityNotAllowException;
import com.spittr.message.exception.MessageNotFoundException;
import com.spittr.message.model.Message;
import com.spittr.user.model.User;


public class MessageIssues {
	
	public static Boolean checkAuthority(Message message, User login){
		if (message.getUser() == null || login == null){
			throw new NullPointerException();
		}
		
		if (! message.getUser().equals(login)) 
			throw new AuthorityNotAllowException(login.getUname());
		
		return true;
	}
	
	public static Message generateFakeName(Boolean isFake, Message message){
		if (isFake != null && isFake.equals(true)){
			message.setIsFake(true);
			message.setFakeName("匿名");
		}
		return message;
	}
	
	public static Boolean checkIsDelete(Message message) throws MessageNotFoundException{
		if ( message.getIsDelete()) 
			throw new MessageNotFoundException();
		return true;
	}
	
	public static Message generateFakeMessage(Message message){
		if (!message.getIsFake()) {
			return message;
		}
		try {
			return (Message)DynamicFilter.getInstance().addFilteFields("user").filter(message);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static List<Message> generateFakeMessageList(List<Message> messageList){
		List< Message> fakeMessageList = new ArrayList<>();
		for (Message message : messageList) {
			fakeMessageList.add(generateFakeMessage(message));
		}
		return fakeMessageList;
	}
}
