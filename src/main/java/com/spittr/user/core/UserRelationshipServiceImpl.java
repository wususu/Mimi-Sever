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

	public List<User> getAttentionsByObjectUser(User objectUser, Date bfTime) {
		// TODO Auto-generated method stub
		List<User> mainUsers = new LinkedList<>();
		List<UserRelationship> userRelationships = userRelationshipDao.getObjectUserRelationship(objectUser, bfTime, ITEM_PER_PAGE);
		
		for (UserRelationship userRelationship : userRelationships) 
			mainUsers.add(userRelationship.getMainUser());
		
		return mainUsers;
	}
	
	@Override
	public Map<String, Object> attentionsByObjectUser(User objectUser, Date bfTime) {
		// TODO Auto-generated method stub
		List<User> users = getAttentionsByObjectUser(objectUser, bfTime);
		
		Map<String, Object> map = getMap();
		map.put(MAIN_USER_LIST, users);
		map.put(BEFORE_TIME, bfTime);
		map.put(NUM_THIS_PAGE, users.size());
		map.put(NUM_PER_PAGE, ITEM_PER_PAGE);
		
		return map;
	}

	public List<User> getAttentionsByMainUser(User mainUser, Date bfTime) {
		// TODO Auto-generated method stub
		List<User> objectUsers = new LinkedList<>();
		List<UserRelationship> userRelationships = userRelationshipDao.getMainUserRelationship(mainUser, bfTime, ITEM_PER_PAGE);
		
		for (UserRelationship userRelationship : userRelationships) 
			objectUsers.add(userRelationship.getObjectUser());
		
		return objectUsers;
	}
	
	@Override
	public Map<String, Object> attentionsByMainUser(User mainUser, Date bfTime) {
		// TODO Auto-generated method stub
		List<User> users = getAttentionsByMainUser(mainUser, bfTime);
		
		Map<String, Object> map = getMap();
		map.put(MAIN_USER_LIST, users);
		map.put(BEFORE_TIME, bfTime);
		map.put(NUM_THIS_PAGE, users.size());
		map.put(NUM_PER_PAGE, ITEM_PER_PAGE);
		
		return map;
	}
	
}
