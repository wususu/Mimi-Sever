package com.spittr.message.controller;

import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import com.spittr.authorization.annotation.*;
import com.spittr.message.core.*;
import com.spittr.message.model.*;
import com.spittr.model.ReturnModel;
import com.spittr.user.model.User;

@RestController
@RequestMapping(value="/api/message")
public class LikeController {

	@Autowired
	@Qualifier("likeServiceImpl")
	private LikeService likeService;
	
	@Autowired
	@Qualifier("messageServiceImpl")
	private MessageService messageService;
	
	@Authorization
	@RequestMapping(value="/likee/{mid}", method=RequestMethod.POST)
	public ReturnModel like(
			@PathVariable Long mid,
			@AutoCurrentUser User user
			){
		
		Message message = messageService.get(mid);
		
		Like like = likeService.get(message, user);
		if (like == null) {
			like = LikeService.newInstance(message, user);
			likeService.create(like);
		}
		
		return ReturnModel.SUCCESS(likeService.likee(like));
	}
}
