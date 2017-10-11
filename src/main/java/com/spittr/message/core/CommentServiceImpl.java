package com.spittr.message.core;

import java.util.*;

import javax.swing.text.StyledEditorKit.BoldAction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spittr.config.StaticConfig;
import com.spittr.message.dao.CommentDao;
import com.spittr.message.dao.MessageDao;
import com.spittr.message.exception.CommentNotFoundException;
import com.spittr.message.model.Comment;
import com.spittr.message.model.Message;
import com.spittr.tools.Mapper;
import com.spittr.user.model.User;
import com.spittr.websocket.core.NtcService;
import com.spittr.websocket.model.NtcCmmnt;
import com.spittr.websocket.model.NtcMsg;
import com.spittr.websocket.model.NtcRCmmnt;
import com.spittr.websocket.model.NtcType;

import static com.spittr.core.JSONConstants.*;

@Service
public class CommentServiceImpl implements CommentService{
	
	protected Logger logger = LoggerFactory.getLogger(CommentServiceImpl.class);
	
	@Autowired
	SimpMessagingTemplate msgTplt;
	
	@Autowired
	@Qualifier("ntcServiceImpl")
	protected NtcService ntcService;
	
	@Autowired
	@Qualifier("commentDaoImpl")
	protected CommentDao commentDao;
	
	@Autowired
	@Qualifier("messageServiceImpl")
	protected MessageService messageService;
	
	@Autowired
	@Qualifier("messageDaoImpl")
	protected MessageDao messageDao;
	
	@Autowired
	@Qualifier("likeeServiceImpl")
	protected LikeeService likeeService;
	
	private List<Comment> judgeLikee(List<Comment> comments, User user) {
		// TODO Auto-generated method stub
		if (user == null) 
			return comments;
		
		if (comments.size() == 0) 
			return comments;
		
		for (int i=0; i<comments.size(); i++) 
			likeeService.generateLikee(comments.get(i), user);
		
		return comments;
	}
	
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
		
		ntcSend(comment);
	}
	
	@Override
	public void save(Comment comment) {
		// TODO Auto-generated method stub
		commentDao.save(comment);
	}

	@Override
	public Comment get(Long cid) {
		// TODO Auto-generated method stub
		return commentDao.get(Comment.class, cid);
	}
	
	@Override
	public Comment	 need(Long cid) {
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
	
		/**
		 * 删除消息
		 */
		NtcCmmnt ntcCmmnt = ntcService.getNtcCmmnt(comment);
		ntcCmmnt.setIsRecived(false);
		ntcService.update(ntcCmmnt);
		
		
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
		comments = judgeLikee(CommentIssue.generateFakeCommentList(comments, fakeFilter), currentUser);
		
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
		Message message = messageService.need(mid);
		likeeService.generateLikee(message, currentUser);
		
		Integer num = StaticConfig.ITEM_PER_PAGE;
		List<Comment> comments = commentDao.getBeforeTime(mid, tmbefore, num);
		
		// comment添加详细信息
		for (int i=0; i<comments.size(); i++)
			CommentIssue.generateDetailComment(comments.get(i));
		
		DynamicFilter fakeFilter = DynamicFilter.getInstance()
				.addFilteFields("user")
				.addFilteFields("uid");
		comments = judgeLikee(CommentIssue.generateFakeCommentList(comments, fakeFilter), currentUser);
		
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
		Message message = messageService.need(mid);
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
		comments = judgeLikee(CommentIssue.generateFakeCommentList(comments, fakeFilter), currentUser);
		
		return Mapper.newInstance(getMap())
				.add(AFTER_TIME, tmafter)
				.add(NUM_PER_PAGE, num)
				.add(NUM_THIS_PAGE, comments.size())
				.add(MESSAGE, message)
				.add(COMMENT_LIST, comments)
				.getMap();
	}
	
	/**
	 *  消息通知
	 */
	private void ntcSend(Comment comment){

		NtcMsg ntcMsg = null;
		boolean sendCmmnt = true;
		// 评论通知
		NtcCmmnt ntcCmmnt = ntcService.getNtcCmmnt(comment);
		if (ntcCmmnt == null) {
			ntcCmmnt = new NtcCmmnt(comment);
			ntcService.create(ntcCmmnt);
		}
		
		
		// 回复评论通知
		Comment rComment = comment.getReplayWhichComment();
		logger.info(comment.toString());
		if (rComment != null) {
			logger.info(rComment.toString());
			NtcRCmmnt ntcRCmmnt = ntcService.getNtcRCmmnt(comment);
			if (ntcRCmmnt == null) {
				ntcRCmmnt = new NtcRCmmnt(comment, rComment);
				ntcService.create(ntcRCmmnt);
			}
			
			if (rComment.getUid() == rComment.getMuid()) {
				sendCmmnt = false;
			}
			
			if (!ntcRCmmnt.getIsRecived()) {
				ntcMsg = new NtcMsg(NtcType.rCmmnt, ntcRCmmnt);
				msgTplt.convertAndSendToUser(ntcRCmmnt.getRcUname(), "/notice", ntcMsg);
				msgTplt.convertAndSendToUser(String.valueOf(ntcRCmmnt.getRcUid()), "/notice", ntcMsg);
			}
		}
		
		if ( (!ntcCmmnt.getIsRecived()) && sendCmmnt) {
			 ntcMsg = new NtcMsg(NtcType.Cmmnt, ntcCmmnt);
			msgTplt.convertAndSendToUser(ntcCmmnt.getmUname(), "/notice", ntcMsg);
			msgTplt.convertAndSendToUser(String.valueOf(ntcCmmnt.getmUid()), "/notice", ntcMsg);
		}
	}
}
