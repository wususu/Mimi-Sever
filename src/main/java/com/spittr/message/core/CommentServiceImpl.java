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
import com.spittr.message.dao.MessageDao;
import com.spittr.message.exception.CommentNotFoundException;
import com.spittr.message.model.Comment;
import com.spittr.message.model.Likee;
import com.spittr.message.model.Message;
import com.spittr.tools.Mapper;
import com.spittr.tools.page.Page;
import com.spittr.user.model.User;
import com.sun.org.glassfish.gmbal.Description;

import static com.spittr.core.JSONConstants.*;

@Service
public class CommentServiceImpl implements CommentService{

	@Autowired
	@Qualifier("commentDaoImpl")
	private CommentDao commentDao;

	@Autowired
	@Qualifier("messageServiceImpl")
	private MessageService messageService;
	
	@Autowired
	@Qualifier("messageDaoImpl")
	private MessageDao messageDao;
	
	@Autowired
	@Qualifier("likeeServiceImpl")
	private LikeeService likeeService;
	
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
		
		comment = CommentIssue.generateFakeComment(comment);
		
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
	public Map<String, Object> getByMidAndTmBefore(Long mid, Date tmbefore, User currentUser) {
		// TODO Auto-generated method stub
		Integer num = StaticConfig.ITEM_PER_PAGE;
		
		Message message = messageDao.get(mid);
		MessageIssues.checkIsDelete(message);
		message = MessageIssues.generateFakeMessage(message);
		
		likeeService.generateLikee(message, currentUser);
		
		List<Comment> comments = commentDao.getBeforeTime(mid, tmbefore, num);
		DynamicFilter fakeFilter = DynamicFilter.getInstance()
				.addFilteFields("user")
				.addFilteFields("uid");
		comments = CommentIssue.generateFakeCommentList(comments, fakeFilter);
		
		return Mapper.newInstance(getMap())
				.add(MESSAGE, message)
				.add(COMMENT_LIST, comments)
				.add(BEFORE_TIME, tmbefore)
				.add(NUM_PER_PAGE, num)
				.add(NUM_THIS_PAGE, comments.size())
				.getMap();
	}
	
	@Deprecated
	@Override
	public List<Comment> getByMid(Long mid) {
		// TODO Auto-generated method stub
		List<Comment> comments = commentDao.get(mid);
		DynamicFilter fakeFilter = DynamicFilter.getInstance()
				.addFilteFields("user")
				.addFilteFields("uid");
		comments = CommentIssue.generateFakeCommentList(comments, fakeFilter);
		return comments;
	}

	@Override
	public Map<String, Object> getCommentBeforeTime(Long mid, Date tmbefore, User currentUser) {
		// TODO Auto-generated method stub
		Message message = messageService.get(mid);
		likeeService.generateLikee(message, currentUser);
		
		Integer num = StaticConfig.ITEM_PER_PAGE;
		List<Comment> comments = commentDao.getBeforeTime(mid, tmbefore, num);
		
		// comment添加详细信息
		for (int i=0; i<comments.size(); i++)
			CommentIssue.generateDetailComment(comments.get(i));
		
		DynamicFilter fakeFilter = DynamicFilter.getInstance()
				.addFilteFields("user")
				.addFilteFields("uid");
		comments = CommentIssue.generateFakeCommentList(comments, fakeFilter);
		
		return Mapper.newInstance(getMap())
				.add(BEFORE_TIME, tmbefore)
				.add(NUM_PER_PAGE, num)
				.add(NUM_THIS_PAGE, comments.size())
				.add(MESSAGE, message)
				.add(COMMENT_LIST, comments)
				.getMap();
	}

	@Override
	public Map<String, Object> getCommentAfterTime(Long mid, Date tmafter, User currentUser) {
		// TODO Auto-generated method stub
		Message message = messageService.get(mid);
		likeeService.generateLikee(message,currentUser);
		
		Integer num = StaticConfig.ITEM_PER_PAGE;
		List<Comment> comments = commentDao.getAfterTime(mid, tmafter, num);
		
		// comment添加Reply comment详细信息
		for (int i=0; i<comments.size(); i++)
			CommentIssue.generateDetailComment(comments.get(i));
		
		// 选择屏蔽发送人信息
		DynamicFilter fakeFilter = DynamicFilter.getInstance()
				.addFilteFields("user")
				.addFilteFields("uid");
		comments = CommentIssue.generateFakeCommentList(comments, fakeFilter);
		
		return Mapper.newInstance(getMap())
				.add(AFTER_TIME, tmafter)
				.add(NUM_PER_PAGE, num)
				.add(NUM_THIS_PAGE, comments.size())
				.add(MESSAGE, message)
				.add(COMMENT_LIST, comments)
				.getMap();
	}
}
