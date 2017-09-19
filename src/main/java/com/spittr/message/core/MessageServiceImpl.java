package com.spittr.message.core;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spittr.config.StaticConfig;
import com.spittr.image.dao.MessageImageDao;
import com.spittr.image.model.MessageImage;
import com.spittr.message.dao.MessageDao;
import com.spittr.message.exception.MessageNotFoundException;
import com.spittr.message.model.Likee;
import com.spittr.message.model.Message;
import com.spittr.tools.page.Page;
import com.spittr.user.model.User;

import java.util.List;

import static com.spittr.core.JSONConstants.*;


@Service
public class MessageServiceImpl implements MessageService{

	@Autowired
	@Qualifier("messageDaoImpl")
	private MessageDao messageDao;
	
	@Autowired
	@Qualifier("commentServiceImpl")
	private CommentService commentService;
	
	@Autowired
	@Qualifier("likeeServiceImpl")
	private LikeeService likeeService;
	
	@Autowired
	@Qualifier("messageImageDaoImpl")
	private MessageImageDao messageImageDao;
	
	@Override
	@Transactional
	public void create(Message message) {
		// TODO Auto-generated method stub

		save(message);
	}
	
	@Override
	@Transactional
	public void createWhichImage(Message message, Set<MessageImage> imageSet){
		create(message);
		for (MessageImage messageImage : imageSet) {
			messageImage.setMessage(message);
			messageImage.setMid(message.getMid());
			messageImageDao.update(messageImage);
		}
		message.setMessageImageSet(imageSet);
	}

	@Override
	public void save(Message message) {
		// TODO Auto-generated method stub
		messageDao.save(message);
	}

	@Override
	public Message get(Long mid) {
		// TODO Auto-generated method stub
		Message message = messageDao.get(Message.class, mid);
		
		if (message == null) 
			throw new MessageNotFoundException(mid);
		
		return message;
	}
	
	@Override
	@Transactional
	public void delete(Message message) {
		// TODO Auto-generated method stub
		if (message == null) 
			throw new NullPointerException();
		
		if (message.isDelete()) 
			throw new MessageNotFoundException();
		
		message.setDelete(true);
		message.setTmDelete(new Date());
		
		messageDao.update(message);
		
		commentService.deleteUnderMessage(message);
	}
	
	@Override
	public void adcNextCommentVal(Message message){
		if (message == null) 
			throw new NullPointerException();
		
		message.setCommentNextVal( message.getCommentNextVal()+1 );
		messageDao.update(message);
	}

	@Override
	public void adcCommentCount(Message message) {
		// TODO Auto-generated method stub
		if (message == null) 
			throw new NullPointerException();
		
		message.setCommentCount( (message.getCommentCount() + 1) );
		messageDao.update(message);
	}

	@Override
	public void decCommentCount(Message message) {
		// TODO Auto-generated method stub
		if (message == null) 
			throw new NullPointerException();
		
		message.setCommentCount( (message.getCommentCount() - 1) );
		messageDao.update(message);
	}
	
	@Override
	public Map<String, Object> getMessageByPageNumber(Integer pageNumber){
		Long items = messageDao.count();
		Page page = Page.newInstance(items);
		page.setPage(pageNumber);
		
		List<Message> messageList = messageDao.get(page);
		messageList = MessageIssues.generateFakeMessageList(messageList);
		
		page.setItemInThisPage(messageList.size());
		
		Map<String, Object> map = getMap();
		map.put(PAGE, page);
		map.put(MESSAGE_LIST, messageList);

		return map;
	}

	@Override
	public Map<String, Object> getLocaleMessageByPageNumber(Long lid, Integer pageNumber) {
		// TODO Auto-generated method stub
		Long items = messageDao.coutByLid(lid);
		Page page = Page.newInstance(items);
		page.setPage(pageNumber);
		
		List<Message> messageList = messageDao.getByLid(page, lid);
		messageList = MessageIssues.generateFakeMessageList(messageList);

		page.setItemInThisPage(messageList.size());
		
		Map<String, Object> map = getMap();
		map.put(PAGE, page);
		map.put(MESSAGE_LIST, messageList);
		return map;
	}
	
	@Override
	public Map<String, Object> beforeTimeMessage(Date time, User user){
		List<Message> messageList =  user == null? getMessageBeforeTime(time) : getMessageBeforeTime(time, user);
		
		Map<String, Object> map = getMap();
		map.put(BEFORE_TIME, time);
		map.put(NUM_PER_PAGE, StaticConfig.ITEM_PER_PAGE);
		map.put(NUM_THIS_PAGE, messageList.size());
		map.put(MESSAGE_LIST, messageList);
		return map;
	}

	public List<Message> getMessageBeforeTime(Date time, User user) {
		// TODO Auto-generated method stub
		List<Message> messageList = getMessageBeforeTime(time);
		
		if (user != null) 
			for (int i=0; i<messageList.size(); i++) 
				generateLikee(messageList.get(i), user);
				
		return messageList;
	}
	
	public List<Message> getMessageBeforeTime(Date time){
		Integer num = StaticConfig.ITEM_PER_PAGE;
		List< Message> messageList = messageDao.getBeforeTime(time, num);

		messageList = MessageIssues.generateFakeMessageList(messageList);
		
		return messageList;
	}

	@Override
	public Map<String, Object> afterTimeMessage(Date time, User user){
		List<Message> messageList = user == null? getMessageAfterTime(time) : getMessageAfterTime(time, user);
		
		Map<String, Object> map = getMap();
		map.put(AFTER_TIME, time);
		map.put(NUM_PER_PAGE, StaticConfig.ITEM_PER_PAGE);
		map.put(NUM_THIS_PAGE, messageList.size());
		map.put(MESSAGE_LIST, messageList);
		return map;
	}
	
	public List<Message> getMessageAfterTime(Date time, User user) {
		// TODO Auto-generated method stub
		List<Message> messageList = getMessageAfterTime(time);
		
		if (user != null) 
			for (int i=0; i<messageList.size(); i++) 
				generateLikee(messageList.get(i), user);

		return messageList;
	}
	
	public List<Message> getMessageAfterTime(Date time){
		Integer num = StaticConfig.ITEM_PER_PAGE;
		List< Message> messageList = messageDao.getAfterTime(time, num);
		
		messageList = MessageIssues.generateFakeMessageList(messageList);
		return messageList;
	}
	
	private void generateLikee(Message message, User user){
		if (message == null )
			return ;
		if (message.getLikeCount() == 0) 
			return ;
		
		Likee likee = likeeService.get(message, user);
		
		if (likee == null) 
			return ;

		message.setLikee(likee);
	}

	@Override
	public Map<String, Object> userMessage(Date time, User user, boolean isCurrentUser) {
		// TODO Auto-generated method stub
		int num = StaticConfig.ITEM_PER_PAGE;
		List<Message> messages = messageDao.getByUid(user.getUid(), time, num);
		
		if (isCurrentUser) 
			messages = MessageIssues.generateFakeMessageList(messages);
		else 
			messages = MessageIssues.removeFakeMessage(messages);
		
		Map<String, Object> map = getMap();
		map.put(BEFORE_TIME, time);
		map.put(NUM_PER_PAGE, StaticConfig.ITEM_PER_PAGE);
		map.put(NUM_THIS_PAGE, messages.size());
		map.put(MESSAGE_LIST, messages);
		
		return map;
	}
}
