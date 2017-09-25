package com.spittr.user.core;

import java.util.*;

import com.spittr.user.model.*;

public interface UserRelationshipService {

	public static UserRelationship newInstance(User mainUser, User objectUser){
		return new UserRelationship(mainUser, objectUser);
	}
	
	UserRelationship get(User mainUser, User objectUser);
		
	UserRelationship  addAttention(User mainUser, User objectUser);
	
	boolean cancelAttention(User mainUser, User objectUser);
		
	Map<String, Object> attentionsByObjectUser(User objectUser, Date bfTime);

	Map<String, Object> attentionsByMainUser(User mainUser, Date bfTime);

	
}
