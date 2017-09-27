package com.spittr.message.controller;

import java.io.IOException;
import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.spittr.authorization.annotation.*;
import com.spittr.image.core.*;
import com.spittr.image.model.MessageImage;
import com.spittr.location.core.LocationService;
import com.spittr.location.model.Location;
import com.spittr.message.core.*;
import com.spittr.message.model.Message;
import com.spittr.model.ReturnModel;
import com.spittr.user.core.UserService;
import com.spittr.user.exception.UserNotFoundException;
import com.spittr.user.model.User;


@RestController
@RequestMapping(value="/api/message")
@CrossOrigin(origins="*", maxAge=3600)
public class MessageController {

	@Autowired
	@Qualifier("locationServiceImpl")
	private LocationService locationService;
	
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	
	@Autowired
	@Qualifier("messageServiceImpl")
	private MessageService messageService;
	
	@Autowired
	@Qualifier("commentServiceImpl")
	private CommentService commentService;
	
	@Autowired
	@Qualifier("likeeServiceImpl")
	private LikeeService likeeService;
	
	@Autowired
	@Qualifier("messageImageServiceImpl")
	private MessageImageService messageImageService;
	
	@Authorization
	@RequestMapping(value="/create", method=RequestMethod.POST)
	@ResponseStatus(value=HttpStatus.OK)
	public ReturnModel create(
			@AutoCurrentUser User user,
			@RequestParam("lid") Long lid, 
			@RequestParam("content") String content,
			@RequestParam(value="isFake", defaultValue="false") Boolean isFake,
			@RequestParam(value="imageidList", required=false) List<Long> imageidList
			){
		Set<MessageImage> imageSet = new HashSet<>();
		
		if ( imageidList != null && imageidList.size() > 0 ) 
			for (Long imageid : imageidList) {
				MessageImage messageImage = messageImageService.get(imageid);
				
				if (ImageIssues.isImageCanUse(messageImage)) 
					imageSet.add(messageImage);
				
			}
		
		Location location = locationService.get(lid);
		Message message = MessageService.newInstance(content, user, location);
		
		message = MessageIssues.generateFakeName(isFake, message);
		
		if (imageidList == null || imageidList.size() == 0) 
			messageService.create(message);
		else 
			messageService.createWhichImage(message, imageSet);		
		
		return ReturnModel.SUCCESS();
	}

	@RequestMapping(value="/get/{id}", method=RequestMethod.GET)
	public ReturnModel get(
			@PathVariable Long id,
			@AutoCurrentUser User user
			) throws IOException{
		Message message = messageService.get(id);

		MessageIssues.checkIsDelete(message);

		message = MessageIssues.generateFakeMessage(message);
		likeeService.generateLikee(message, user);
		
		return  ReturnModel.SUCCESS(message);
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.DELETE)
	@Authorization
	public ReturnModel delete(
			@AutoCurrentUser User user,
			@RequestParam("mid") Long mid
			){
		Message message = messageService.get(mid);
		
		MessageIssues.checkIsDelete(message);
		
		if (MessageIssues.checkAuthority(message, user)){
			messageService.delete(message);
			
			return ReturnModel.SUCCESS();
		}
		else {
			return ReturnModel.ERROR();
		}
	}
	
	@Deprecated
	@RequestMapping(value="/page/{pid}", method=RequestMethod.GET)
	@ResponseStatus(value=HttpStatus.OK)
	public ReturnModel page(
			@PathVariable Integer pid
			){
		Map<String, Object> result =  messageService.getMessageByPageNumber(pid);
		
		return ReturnModel.SUCCESS(result);
	}
	
	@Deprecated
	@RequestMapping(value="/lid/{lid}/page/{pid}")
	@ResponseStatus(value=HttpStatus.OK)
	public ReturnModel pageByLid(
			@PathVariable Long lid,
			@PathVariable Integer pid
			){
		Map<String, Object> result = messageService.getLocaleMessageByPageNumber(lid, pid);
		
		return ReturnModel.SUCCESS(result);
	}
	
	@RequestMapping(value={"/lid/{lid}/tmbefore/{time}", "/lid/{lid}/tmbefore"}, method=RequestMethod.GET)
	@Authorization
	public ReturnModel getMessagesByLidBeforeTime(
			@AutoCurrentUser User user,
			@PathVariable("lid") Long lid,
			@PathVariable(name="time", required=false) Long tmbefore
			){
		tmbefore = tmbefore == null? (new Date()).getTime() : tmbefore;
		Map<String, Object> data = messageService.beforeTimeMessages(new Date(tmbefore), user, lid);
		
		return ReturnModel.SUCCESS(data);
	}
	
	@RequestMapping(value={"/lid/{lid}/tmafter/{time}", "/lid/{lid}/tmafter"}, method=RequestMethod.GET)
	@Authorization
	public ReturnModel getMessagesByLidAfterTime(
			@AutoCurrentUser User user,
			@PathVariable("lid") Long lid,
			@PathVariable(name="time", required=false) Long tmafter
			){
		tmafter = tmafter == null? (new Date()).getTime() : tmafter;
		Map<String, Object> data = messageService.afterTimeMessages(new Date(tmafter), user, lid);
		
		return ReturnModel.SUCCESS(data);
	}
	
	@RequestMapping(value={"/tmbefore/{time}", "/tmbefore"}, method=RequestMethod.GET)
	@ResponseStatus(value=HttpStatus.OK)
	public ReturnModel getMessagesBeforeTime(
			@PathVariable(name="time",required=false) Long tmbefore,
			@AutoCurrentUser User user
			){
		tmbefore = tmbefore == null? (new Date()).getTime() : tmbefore;
		Map<String, Object> data = messageService.beforeTimeMessages(new Date(tmbefore), user);
		
		return ReturnModel.SUCCESS(data);
	}
	
	@RequestMapping(value="/tmafter/{time}", method=RequestMethod.GET)
	@ResponseStatus(value=HttpStatus.OK)
	public ReturnModel getMessagesAfterTime(
			@PathVariable(name="time") Long tmbefore,
			@AutoCurrentUser User user
			) {
		Map<String, Object> data = messageService.afterTimeMessages(new Date(tmbefore), user);
		return ReturnModel.SUCCESS(data);
	}
	
	@Authorization
	@RequestMapping(value={"/me/{time}", "/me"})
	public ReturnModel getSelfMessages(
			@AutoCurrentUser User currentUser,
			@PathVariable(name="time", required=false) Long tmbefore
			){
		tmbefore = tmbefore == null? (new Date()).getTime() : tmbefore;
		Map<String, Object> data = messageService.userMessages(new Date(tmbefore), currentUser, currentUser);
		return ReturnModel.SUCCESS(data);
	}
	
	@RequestMapping(value={"/user/{uname}/{time}", "/user/{uname}"})
	public ReturnModel getUserMessages(
			@AutoCurrentUser User currentUser,
			@PathVariable(name="uname") String uName,
			@PathVariable(name="time", required=false) Long tmbefore
			){
		tmbefore = tmbefore == null? (new Date()).getTime() : tmbefore;
		User objectUser = userService.get(uName);
		if (objectUser == null) 
			throw new UserNotFoundException(uName);		
		
		Map<String, Object> data = messageService.userMessages(new Date(tmbefore), objectUser, currentUser);
		return ReturnModel.SUCCESS(data);
	}
	
	@RequestMapping(value={"/attention/tmbefore/{time}", "/attention/tmbefore"}, method=RequestMethod.GET)
	@Authorization
	public ReturnModel getMyAttemtionMessages(
			@AutoCurrentUser User currentUser,
			@PathVariable(name="time", required=false) Long tmbefore
			){
		tmbefore = tmbefore == null? (new Date()).getTime() : tmbefore;

		Map<String, Object> data = messageService.myAttentionMessages(new Date(tmbefore),currentUser);
		
		return ReturnModel.SUCCESS(data);
	}
	
}
