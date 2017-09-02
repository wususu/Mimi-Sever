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
import com.spittr.message.model.Message;
import com.spittr.tools.page.Page;

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
		
		if (message.getIsDelete()) 
			throw new MessageNotFoundException();
		
		message.setIsDelete(true);
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
	public Map<String, Object> getMessageBeforeTime(Date time) {
		// TODO Auto-generated method stub
		Integer num = StaticConfig.ITEM_PER_PAGE;
		List< Message> messageList = messageDao.getBeforeTime(time, num);
		System.out.println(messageList);

		messageList = MessageIssues.generateFakeMessageList(messageList);
		
		
		Map<String, Object> map = getMap();
		
		map.put(MESSAGE_LIST, messageList);
		return map;
	}

	@Override
	public Map<String, Object> getMessageAfterTime(Date time) {
		// TODO Auto-generated method stub
		Integer num = StaticConfig.ITEM_PER_PAGE;
		List< Message> messageList = messageDao.getAfterTime(time, num);
		System.out.println(messageList);
		messageList = MessageIssues.generateFakeMessageList(messageList);
		
		Map<String, Object> map = getMap();
		
		map.put(MESSAGE_LIST, messageList);
		return map;
	}

}
