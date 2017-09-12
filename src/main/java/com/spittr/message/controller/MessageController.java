package com.spittr.message.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.spittr.authorization.annotation.Authorization;
import com.spittr.authorization.annotation.AutoCurrentUser;
import com.spittr.image.core.ImageIssues;
import com.spittr.image.core.MessageImageService;
import com.spittr.image.model.MessageImage;
import com.spittr.location.core.LocationService;
import com.spittr.location.model.Location;
import com.spittr.message.core.CommentService;
import com.spittr.message.core.MessageIssues;
import com.spittr.message.core.MessageService;
import com.spittr.message.model.Message;
import com.spittr.model.ReturnModel;
import com.spittr.user.model.User;


@RestController
@RequestMapping(value="/api/message")
@CrossOrigin(origins="*", maxAge=3600)
public class MessageController {

	@Autowired
	@Qualifier("locationServiceImpl")
	private LocationService locationService;

	@Autowired
	@Qualifier("messageServiceImpl")
	private MessageService messageService;
	
	@Autowired
	@Qualifier("commentServiceImpl")
	private CommentService commentService;
	
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
			@PathVariable Long id
			) throws IOException{
		Message message = messageService.get(id);

		MessageIssues.checkIsDelete(message);

		message = MessageIssues.generateFakeMessage(message);

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
	
	@RequestMapping(value="/page/{pid}", method=RequestMethod.GET)
	@ResponseStatus(value=HttpStatus.OK)
	public ReturnModel page(
			@PathVariable Integer pid
			){
		Map<String, Object> result =  messageService.getMessageByPageNumber(pid);
		
		return ReturnModel.SUCCESS(result);
	}
	
	@RequestMapping(value="/lid/{lid}/page/{pid}")
	@ResponseStatus(value=HttpStatus.OK)
	public ReturnModel pageByLid(
			@PathVariable Long lid,
			@PathVariable Integer pid
			){
		Map<String, Object> result = messageService.getLocaleMessageByPageNumber(lid, pid);
		
		return ReturnModel.SUCCESS(result);
	}
	
	@RequestMapping(value="/tmbefore/{time}", method=RequestMethod.GET)
	@ResponseStatus(value=HttpStatus.OK)
	public ReturnModel getMessageBeforeTime(
			@PathVariable Long time,
			@AutoCurrentUser User user
			){
		Map<String, Object> data = messageService.beforeTimeMessage(new Date(time), user);
		return ReturnModel.SUCCESS(data);
	}
	
	@RequestMapping(value="/tmafter/{time}", method=RequestMethod.GET)
	@ResponseStatus(value=HttpStatus.OK)
	public ReturnModel getMessageAfterTime(
			@PathVariable(required=false) Long time,
			@AutoCurrentUser User user
			) {
		time = time == null? (new Date()).getTime() : time;
		Map<String, Object> data = messageService.afterTimeMessage(new Date(time), user);
		return ReturnModel.SUCCESS(data);
	}
	
	@Authorization
	@RequestMapping(value="/me/{time}")
	public ReturnModel getSelfMessage(
			@AutoCurrentUser User user,
			@PathVariable Long time
			){
		time = time == null? (new Date()).getTime() : time;
		Map<String, Object> data = messageService.userMessage(new Date(time), user);
		return ReturnModel.SUCCESS(data);
	}
	
}
