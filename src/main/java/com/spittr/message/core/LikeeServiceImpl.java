package com.spittr.message.core;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.spittr.core.JSONConstants.*;
import com.spittr.message.dao.LikeeDao;
import com.spittr.message.dao.MessageDao;
import com.spittr.message.exception.LikeCountErrorException;
import com.spittr.message.model.Likee;
import com.spittr.message.model.Message;
import com.spittr.user.model.User;

@Service
public class LikeeServiceImpl implements LikeeService{

	@Autowired
	@Qualifier("likeeDaoImpl")
	private LikeeDao likeeDao;
	
	@Autowired
	@Qualifier("messageDaoImpl")
	private MessageDao messageDao;
	

	@Override
	public void create(Likee entity) {
		// TODO Auto-generated method stub
		likeeDao.create(entity);
	}
	
	@Override
	public Likee get(long mid, long uid) {
		// TODO Auto-generated method stub
		return likeeDao.get(mid, uid);
	}

	@Override
	public Likee get(Message message, User user) {
		// TODO Auto-generated method stub
		return likeeDao.get(message, user);
	}

	@Override
	public Map<String, Object> likee(Likee like){
		
		if (like == null) 
			throw new NullPointerException("like");
		
		return like.isLike() == true ? dislike(like) : like(like);
	}
	
	@Transactional
	private Map<String, Object> like(Likee like) {

		Map<String, Object> result = getMap();
		Message message = like.getMessage();
		
		message.setLikeCount(message.getLikeCount() + 1);
		messageDao.update(message);
		like.setLike(true);
		likeeDao.update(like);
		
		result.put(LIKE_COUNT, message.getLikeCount());
		result.put(ISLIKE, like.isLike());

		return result;
	}

	@Transactional
	private Map<String, Object> dislike(Likee like) {

		Map<String, Object> result = getMap();
		Message message = like.getMessage();
		
		if (message.getLikeCount() < 1) 
			throw new LikeCountErrorException();
		
		message.setLikeCount( message.getLikeCount() - 1);
		messageDao.update(message);
		like.setLike(false);
		likeeDao.update(like);
		
		result.put(LIKE_COUNT, message.getLikeCount());
		result.put(ISLIKE, like.isLike());
		
		return result;
	}

}
