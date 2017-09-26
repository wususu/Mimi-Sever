package com.spittr.message.core;

import java.sql.Time;
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
import com.spittr.tools.Mapper;
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
		
		MessageIssues.checkIsDelete(message);
		
		message = MessageIssues.generateFakeMessage(message);
		
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
	
	@Deprecated
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

	@Deprecated
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
	
	// 判断是否登录状态
	private List<Message> judgeLikee(List<Message> messages, User user) {
		// TODO Auto-generated method stub
		if (user == null) 
			return messages;
		
		if (messages.size() == 0) 
			return messages;
		
		for (int i=0; i<messages.size(); i++) 
			likeeService.generateLikee(messages.get(i), user);
				
		return messages;
	}
	
	// 所有before time message
	@Override
	public Map<String, Object> beforeTimeMessages(Date tmbefore, User currentUser){
		Integer num = StaticConfig.ITEM_PER_PAGE;
		List< Message> messages = messageDao.getBeforeTime(tmbefore, num);
		
		messages = judgeLikee(MessageIssues.generateFakeMessageList(messages), currentUser);
		
		return Mapper.newInstance(getMap()).
		add(BEFORE_TIME, tmbefore).
		add(NUM_PER_PAGE, StaticConfig.ITEM_PER_PAGE).
		add(NUM_THIS_PAGE, messages.size()).
		add(MESSAGE_LIST, messages)
		.getMap();
	}

	// 所有after time message
	@Override
	public Map<String, Object> afterTimeMessages(Date tmafter, User currentUser){
		Integer num = StaticConfig.ITEM_PER_PAGE;
		List< Message> messages = messageDao.getAfterTime(tmafter, num);
		
		messages = judgeLikee(MessageIssues.generateFakeMessageList(messages), currentUser);
		
		return Mapper.newInstance(getMap()).
		add(AFTER_TIME, tmafter).
		add(NUM_PER_PAGE, StaticConfig.ITEM_PER_PAGE).
		add(NUM_THIS_PAGE, messages.size()).
		add(MESSAGE_LIST, messages)
		.getMap();
	}
	
	// 某一地点的before time message
	@Override
	public Map<String, Object> beforeTimeMessages(Date tmbefore, User currentUser, Long lid) {
		// TODO Auto-generated method stub
		int num = StaticConfig.ITEM_PER_PAGE;
		List<Message> messages = messageDao.getBeforeTime(tmbefore, lid, num);
			
		messages = judgeLikee(MessageIssues.generateFakeMessageList(messages), currentUser);
		
		return Mapper.newInstance(getMap()).
		add(BEFORE_TIME, tmbefore).
		add(NUM_PER_PAGE, num).
		add(NUM_THIS_PAGE, messages.size()).
		add(MESSAGE_LIST, messages)
		.getMap();
	}

	// 某一地点的after time message
	@Override
	public Map<String, Object> afterTimeMessages(Date tmafter, User currentUser, Long lid) {
		// TODO Auto-generated method stub
		int num = StaticConfig.ITEM_PER_PAGE;
		List<Message> messages = messageDao.getAfterTime(tmafter, lid, num);
		
		messages = judgeLikee(MessageIssues.generateFakeMessageList(messages), currentUser);

		return Mapper.newInstance(getMap()).
		add(AFTER_TIME, tmafter).
		add(NUM_PER_PAGE, num).
		add(NUM_THIS_PAGE, messages.size()).
		add(MESSAGE_LIST, messages)
		.getMap();
	}

	// 某一用户的message
	@Override
	public Map<String, Object> userMessages(Date tmbefore, User objectUser, User currentUser) {
		// TODO Auto-generated method stub
		
		int num = StaticConfig.ITEM_PER_PAGE;
		List<Message> messages = messageDao.getByUid(objectUser.getUid(), tmbefore, num);
		
		if (objectUser.equals(currentUser)) 
			messages = MessageIssues.generateFakeMessageList(messages); 
		else 
			// 移除匿名message
			messages = MessageIssues.removeFakeMessage(messages);
		
		messages = judgeLikee(messages, currentUser);
		
		return Mapper.newInstance(getMap()).
		add(BEFORE_TIME, tmbefore).
		add(NUM_PER_PAGE, num).
		add(NUM_THIS_PAGE, messages.size()).
		add(MESSAGE_LIST, messages)
		.getMap();
	}


}
