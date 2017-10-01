package com.spittr.message.core;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spittr.message.dao.*;
import com.spittr.message.exception.LikeCountErrorException;
import com.spittr.message.model.*;
import com.spittr.user.model.User;

import static com.spittr.core.JSONConstants.*;


@Service
public class LikeeServiceImpl implements LikeeService{
	
	@Autowired
	@Qualifier("mlikeeDaoImpl")
	protected MlikeeDao mlikeeDao;
	
	@Autowired
	@Qualifier("clikeeDaoImpl")
	protected ClikeeDao clikeeDao;
	
	@Autowired
	@Qualifier("messageDaoImpl")
	protected MessageDao messageDao;
	
	@Autowired
	@Qualifier("commentDaoImpl")
	protected CommentDao commentDao;
	
	@Override
	public void create(MLikee entity) {
		// TODO Auto-generated method stub
		mlikeeDao.create(entity);
	}
	
	@Override
	public MLikee getMlikee(long mid, long uid) {
		// TODO Auto-generated method stub
		return mlikeeDao.get(mid, uid);
	}
	
	@Override
	public MLikee get(Message message, User user) {
		// TODO Auto-generated method stub
		return mlikeeDao.get(message, user);
	}
	
	@Override
	public Map<String, Object> likee(MLikee like){
		
		if (like == null) 
			throw new NullPointerException("like");
		
		return like.isLike() == true ? dislike(like) : like(like);
	}
	
	@Transactional
	private Map<String, Object> like(MLikee like) {

		Map<String, Object> result = getMap();
		Message message = like.getMessage();
		
		message.setLikeCount(message.getLikeCount() + 1);
		messageDao.update(message);
		like.setLike(true);
		mlikeeDao.update(like);
		
		result.put(LIKE_COUNT, message.getLikeCount());
		result.put(ISLIKE, like.isLike());

		return result;
	}
	
	@Transactional
	private Map<String, Object> dislike(MLikee like) {

		Map<String, Object> result = getMap();
		Message message = like.getMessage();
		
		if (message.getLikeCount() < 1) 
			throw new LikeCountErrorException();
		
		message.setLikeCount( message.getLikeCount() - 1);
		messageDao.update(message);
		like.setLike(false);
		mlikeeDao.update(like);
		
		result.put(LIKE_COUNT, message.getLikeCount());
		result.put(ISLIKE, like.isLike());
		
		return result;
	}
	
	// message是否被点赞
	public void generateLikee(Message message, User user){
		if (message == null ) 
			return ;
		if (message.getLikeCount() == 0) 
			return ;
		
		MLikee likee = get(message, user);
		
		if (likee == null) 
			return ;
		
		message.setLikee(likee);
	}

	@Override
	public void create(CLikee entity) {
		// TODO Auto-generated method stub
		clikeeDao.create(entity);
	}

	@Override
	public CLikee getClikee(long cid, long uid) {
		// TODO Auto-generated method stub
		return clikeeDao.get(cid, uid);
	}

	@Override
	public CLikee get(Comment comment, User user) {
		// TODO Auto-generated method stub
		return clikeeDao.get(comment, user);
	}

	@Override
	public Map<String, Object> likee(CLikee like){
		
		if (like == null) 
			throw new NullPointerException("like");
		
		return like.isLike() == true ? dislike(like) : like(like);
	}
	
	@Transactional
	private Map<String, Object> like(CLikee cLikee){
		Map<String, Object> result = getMap();
		Comment comment = cLikee.getComment();
		
		comment.setLikeCount(comment.getLikeCount() + 1);
		commentDao.update(comment);
		cLikee.setLike(true);
		clikeeDao.update(cLikee);
		
		result.put(LIKE_COUNT, comment.getLikeCount());
		result.put(ISLIKE, cLikee.isLike());

		return result;
	}
	
	@Transactional
	private Map<String, Object> dislike(CLikee cLikee) {
		Map<String, Object> result = getMap();
		Comment comment = cLikee.getComment();

		comment.setLikeCount(comment.getLikeCount() - 1);
		commentDao.update(comment);
		cLikee.setLike(false);
		clikeeDao.update(cLikee);
		
		result.put(LIKE_COUNT, comment.getLikeCount());
		result.put(ISLIKE, cLikee.isLike());
		
		return result;
	}

	@Override
	public void generateLikee(Comment comment, User user) {
		// TODO Auto-generated method stub
		if (comment == null) 
			return ;
		if (comment.getLikeCount() == 0) 
			return ;
		
		CLikee likee = get(comment, user);
		
		if (likee == null) 
			return ;
				
		comment.setLikee(likee);
	}
}
