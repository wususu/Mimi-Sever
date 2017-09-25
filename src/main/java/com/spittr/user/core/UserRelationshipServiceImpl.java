package com.spittr.user.core;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spittr.user.dao.UserRelationshipDao;
import com.spittr.user.model.*;

import static com.spittr.config.StaticConfig.*;
import static com.spittr.core.JSONConstants.*;

@Service
public class UserRelationshipServiceImpl implements UserRelationshipService{

	@Autowired
	@Qualifier("userRelationshipDaoImpl")
	private UserRelationshipDao userRelationshipDao;

	@Override
	public UserRelationship get(User mainUser, User objectUser) {
		// TODO Auto-generated method stub
		return userRelationshipDao.get(mainUser, objectUser);
	}
	
	@Override
	@Transactional
	public UserRelationship addAttention(User mainUser, User objectUser) {
		// TODO Auto-generated method stub
		UserRelationship userRelationship = get(mainUser, objectUser);
		if (userRelationship != null){
			if (userRelationship.isDelete()) {
				userRelationship.setDelete(false);
				userRelationship.setTmCreated(new Date());
			}
		}else {
			userRelationship = UserRelationshipService.newInstance(mainUser, objectUser);
			userRelationshipDao.save(userRelationship);
		}
		
		return userRelationship;
	}

	@Override
	public boolean cancelAttention(User mainUser, User objectUser) {
		// TODO Auto-generated method stub
		UserRelationship userRelationship = get(mainUser, objectUser);
		if (userRelationship == null || userRelationship.isDelete() == true) 
			return true;
		
		userRelationship.setDelete(true);
		userRelationshipDao.update(userRelationship);
		
		return true;
	}

	private List<Map<String,Object>> getAttentionsByObjectUser(User objectUser, Date bfTime) {
		// TODO Auto-generated method stub
		Map<String, Object> mainUser = null;
		List<Map<String, Object>> mainUsers = new ArrayList<>();
		
		List<UserRelationship> userRelationships = userRelationshipDao.getObjectUserRelationship(objectUser, bfTime, ITEM_PER_PAGE);
		
		for (UserRelationship userRelationship : userRelationships) {
			mainUser = getMap();
			mainUser.put(USER, userRelationship.getMainUser());
			mainUser.put(ATTENTION_TIME, userRelationship.getTmCreated());
			mainUsers.add(mainUser);
		}
			
		return mainUsers;
	}
	
	@Override
	public Map<String, Object> attentionsByObjectUser(User objectUser, Date bfTime) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> mainUsers = getAttentionsByObjectUser(objectUser, bfTime);
		
		Map<String, Object> mainMap = getMap();
		mainMap.put(MAIN_USER_LIST, mainUsers);
		mainMap.put(BEFORE_TIME, bfTime);
		mainMap.put(NUM_THIS_PAGE, mainUsers.size());
		mainMap.put(NUM_PER_PAGE, ITEM_PER_PAGE);
		
		return mainMap;
	}

	private List<Map<String, Object>> getAttentionsByMainUser(User mainUser, Date bfTime) {
		// TODO Auto-generated method stub
		Map<String, Object> objectUser = null;
		List<Map<String, Object>> objectUsers = new ArrayList<>();
		List<UserRelationship> userRelationships = userRelationshipDao.getMainUserRelationship(mainUser, bfTime, ITEM_PER_PAGE);
		
		for (UserRelationship userRelationship : userRelationships) {
			objectUser = getMap();
			objectUser.put(USER,userRelationship.getObjectUser());
			objectUser.put(ATTENTION_TIME, userRelationship.getTmCreated());
			objectUsers.add(objectUser);
		}
		System.out.println("objectUsers: ");
		System.out.println(objectUsers);

		return objectUsers;
	}
	
	@Override
	public Map<String, Object> attentionsByMainUser(User mainUser, Date bfTime) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> objectUsers = getAttentionsByMainUser(mainUser, bfTime);
		
		Map<String, Object> objectMap = getMap();
		objectMap.put(MAIN_USER_LIST, objectUsers);
		objectMap.put(BEFORE_TIME, bfTime);
		objectMap.put(NUM_THIS_PAGE, objectUsers.size());
		objectMap.put(NUM_PER_PAGE, ITEM_PER_PAGE);
		
		return objectMap;
	}
	
}
