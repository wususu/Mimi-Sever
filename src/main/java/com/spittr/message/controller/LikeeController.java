package com.spittr.message.controller;

import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import com.spittr.authorization.annotation.*;
import com.spittr.message.core.*;
import com.spittr.message.model.*;
import com.spittr.model.ReturnModel;
import com.spittr.user.model.User;

@RestController
@RequestMapping(value="/api/likee")
@CrossOrigin(origins="*", maxAge=3600)
public class LikeeController {

	@Autowired
	@Qualifier("likeeServiceImpl")
	protected LikeeService likeService;
	
	@Autowired
	@Qualifier("messageServiceImpl")
	protected MessageService messageService;
	
	@Autowired
	@Qualifier("commentServiceImpl")
	protected CommentService commentService;
	
	@Authorization
	@RequestMapping(value="/message/{mid}", method=RequestMethod.POST)
	public ReturnModel likeMessage(
			@PathVariable Long mid,
			@AutoCurrentUser User currentUser
			){
		
		Message message = messageService.get(mid);
		
		MLikee like = likeService.get(message, currentUser);
		if (like == null) {
			like = LikeeService.newInstance(message, currentUser);
			likeService.create(like);
		}
		
		return ReturnModel.SUCCESS(likeService.likee(like));
	}
	
	@Authorization
	@RequestMapping(value="/comment/{cid}", method=RequestMethod.POST)
	public ReturnModel likeComment(
			@PathVariable Long cid,
			@AutoCurrentUser User currentUser
			){
		Comment comment = commentService.need(cid);
		
		CLikee cLikee = likeService.get(comment, currentUser);
		if (cLikee == null) {
			cLikee = new CLikee(comment, currentUser);
			likeService.create(cLikee);
		}
		
		return ReturnModel.SUCCESS(likeService.likee(cLikee));
	}
}
