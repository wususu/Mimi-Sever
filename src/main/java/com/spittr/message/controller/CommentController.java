package com.spittr.message.controller;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.spittr.authorization.annotation.Authorization;
import com.spittr.authorization.annotation.AutoCurrentUser;
import com.spittr.message.core.CommentIssue;
import com.spittr.message.core.CommentService;
import com.spittr.message.core.MessageIssues;
import com.spittr.message.core.MessageService;
import com.spittr.message.model.Comment;
import com.spittr.message.model.Message;
import com.spittr.model.ReturnModel;
import com.spittr.user.core.UserService;
import com.spittr.user.model.User;

@RestController
@RequestMapping(value="/api/comment")
@CrossOrigin(origins="*", maxAge=3600)
public class CommentController {
	
	@Autowired
	@Qualifier("messageServiceImpl")
	private MessageService messageService;
	
	@Autowired
	@Qualifier("commentServiceImpl")
	private CommentService commentService;
	
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;

	
	@Authorization
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public ReturnModel create(
			@AutoCurrentUser User user,
			@RequestParam("mid") Long mid,
			@RequestParam(value="rcid", required=false) Long replayCommentId,
			@RequestParam("content") String content,
			@RequestParam(value="isFake", defaultValue="false", required=false) Boolean isFake
			){
		
		Comment replayComment = null;
		Message underMessage = messageService.get(mid);
		
		MessageIssues.checkIsDelete(underMessage);
		if (replayCommentId != null) {
			replayComment = commentService.get(replayCommentId);

			CommentIssue.checkReplayComment(replayComment, underMessage);
		}
		
		commentService.create( underMessage, user, content, replayComment, isFake );
		
		return ReturnModel.SUCCESS();
	}
	
	@RequestMapping(value="/get/{cid}", method=RequestMethod.GET)
	public ReturnModel get(
			@PathVariable("cid") Long cid
			) throws JsonParseException, JsonMappingException, IOException{
		
		Comment comment = commentService.get(cid);

		CommentIssue.checkIsDelete(comment);
		comment = CommentIssue.generateFakeComment(comment);		
		
		return ReturnModel.SUCCESS(comment);
	}
	
	@Authorization
	@RequestMapping(value="/delete", method=RequestMethod.DELETE)
	public ReturnModel delete(
			@AutoCurrentUser User user,
			@RequestParam("cid") Long cid
			){
		Comment comment = commentService.get(cid);
		
		if (CommentIssue.checkAuthority(comment, user))
			commentService.delete(comment);
		
		return ReturnModel.SUCCESS();
	}
	
	@RequestMapping(value={"/message/{mid}/page/{pid}", "/message/{mid}"}, method=RequestMethod.GET)
	public ReturnModel page(
			@PathVariable("mid") Long mid,
			@PathVariable(value = "pid", required=false) Integer pid
			){
		Message message = messageService.get(mid);
		MessageIssues.checkIsDelete(message);
		
		Map<String, Object> result = commentService.getByMidAndPageNumber(mid, pid);
				
		return ReturnModel.SUCCESS(result);
	}
	

}
