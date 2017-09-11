package com.spittr.message.core;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spittr.config.StaticConfig;
import com.spittr.message.dao.CommentDao;
import com.spittr.message.exception.CommentNotFoundException;
import com.spittr.message.model.Comment;
import com.spittr.message.model.Message;
import com.spittr.tools.page.Page;
import com.spittr.user.model.User;

import static com.spittr.core.JSONConstants.*;

@Service
public class CommentServiceImpl implements CommentService{

	@Autowired
	@Qualifier("commentDaoImpl")
	private CommentDao commentDao;

	@Autowired
	@Qualifier("messageServiceImpl")
	private MessageService messageService;
	
	@Override
	@Transactional
	public void create(Message underWhichMessage, User user, String content, Comment replayComment, Boolean isFake) {
		// TODO Auto-generated method stub
		Comment comment = CommentService.newInstance(content, user, underWhichMessage, replayComment);
		
		underWhichMessage.setCommentNextVal(underWhichMessage.getCommentNextVal());
		
		comment = CommentIssue.generateIsFake(comment, isFake);
		
		messageService.adcCommentCount(comment.getUnderWhichMessage());
		
		save(comment);
		
		messageService.adcNextCommentVal(comment.getUnderWhichMessage());

	}
	
	@Override
	public void save(Comment comment) {
		// TODO Auto-generated method stub
		commentDao.save(comment);
	}

	@Override
	public Comment get(Long cid) {
		// TODO Auto-generated method stub
		Comment comment = commentDao.get(Comment.class, cid);
		
		if (comment == null) 
			throw new CommentNotFoundException(cid);
		
		return comment;
	}

	@Override
	@Transactional
	public void delete(Comment comment) {
		// TODO Auto-generated method stub
		if (comment.isDelete()) 
			throw new CommentNotFoundException();
	
		comment.setDelete(true);
		comment.setTmDelete(new Date());
		
		commentDao.update(comment);
		
		messageService.decCommentCount(comment.getUnderWhichMessage());
	}

	@Override
	public void deleteUnderMessage(Message message) {
		// TODO Auto-generated method stub
		List<Comment> commentList = getByMid(message.getMid());
		for (Comment comment : commentList) {
			if (!comment.isDelete()) 
				delete(comment);
		}
	}

	@Override
	public Map<String, Object> getByMidAndPageNumber(Long mid, Integer pageNumber) {
		// TODO Auto-generated method stub
		Message message = messageService.get(mid);
		MessageIssues.checkIsDelete(message);
		message = MessageIssues.generateFakeMessage(message);
		
		Long items = commentDao.count(mid);
		
		Page page = Page.newInstance(items);
		page.setPage(pageNumber == null ? 0: pageNumber);
		
		List<Comment> commentList = commentDao.getByPage(mid, page);
		DynamicFilter fakeFilter = DynamicFilter.getInstance()
				.addFilteFields("user")
				.addFilteFields("uid");
		commentList = CommentIssue.generateFakeCommentList(commentList, fakeFilter);
		
		page.setItemInThisPage(commentList.size());
		
		Map<String, Object> map =  getMap();
		map.put(PAGE, page);
		map.put(MESSAGE, message);
		map.put(COMMENT_LIST, commentList);
		
		return map;
	}
	
	@Override
	public List<Comment> getByMid(Long mid) {
		// TODO Auto-generated method stub
		List<Comment> commentList = commentDao.get(mid);
		return commentList;
	}

	@Override
	public Map<String, Object> getCommentBeforeTime(Long mid, Date time) {
		// TODO Auto-generated method stub
		Message message = messageService.get(mid);
		MessageIssues.checkIsDelete(message);
		message = MessageIssues.generateFakeMessage(message);
		
		Integer num = StaticConfig.ITEM_PER_PAGE;
		List<Comment> comments = commentDao.getBeforeTime(mid, time, num);
		
		DynamicFilter fakeFilter = DynamicFilter.getInstance()
				.addFilteFields("user")
				.addFilteFields("uid");
		comments = CommentIssue.generateFakeCommentList(comments, fakeFilter);
		
		Map<String, Object> map = getMap();
		map.put(BEFORE_TIME, time);
		map.put(NUM_PER_PAGE, StaticConfig.ITEM_PER_PAGE);
		map.put(NUM_THIS_PAGE, comments.size());
		map.put(MESSAGE, message);
		map.put(COMMENT_LIST, comments);
		
		return map;
	}

	@Override
	public Map<String, Object> getCommentAfterTime(Long mid, Date time) {
		// TODO Auto-generated method stub
		Message message = messageService.get(mid);
		MessageIssues.checkIsDelete(message);
		message = MessageIssues.generateFakeMessage(message);
		
		Integer num = StaticConfig.ITEM_PER_PAGE;
		List<Comment> comments = commentDao.getAfterTime(mid, time, num);
		
		DynamicFilter fakeFilter = DynamicFilter.getInstance()
				.addFilteFields("user")
				.addFilteFields("uid");
		comments = CommentIssue.generateFakeCommentList(comments, fakeFilter);
		
		Map<String, Object> map = getMap();
		map.put(AFTER_TIME, time);
		map.put(NUM_PER_PAGE, StaticConfig.ITEM_PER_PAGE);
		map.put(NUM_THIS_PAGE, comments.size());
		map.put(MESSAGE, message);
		map.put(COMMENT_LIST, comments);
		
		return map;
	}
}
