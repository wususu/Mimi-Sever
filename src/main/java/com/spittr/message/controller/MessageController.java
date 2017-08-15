package com.spittr.message.controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public Object get(
			@PathVariable Long id
			) throws IOException{
		Message message = messageService.get(id);

		MessageIssues.checkIsDelete(message);

		message = MessageIssues.generateFakeMessage(message);

		return new ResponseEntity<ReturnModel>(ReturnModel.SUCCESS(message), HttpStatus.OK);
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.DELETE)
	@Authorization
	public ResponseEntity<ReturnModel> delete(
			@AutoCurrentUser User user,
			@RequestParam("mid") Long mid
			){
		Message message = messageService.get(mid);
		
		MessageIssues.checkIsDelete(message);
		
		if (MessageIssues.checkAuthority(message, user)){
			messageService.delete(message);
			
			return new ResponseEntity<ReturnModel>(ReturnModel.SUCCESS(), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<ReturnModel>(ReturnModel.ERROR(), HttpStatus.OK);
		}
	}
	
	@RequestMapping(value="/page/{pid}", method=RequestMethod.GET)
	public ResponseEntity<ReturnModel> page(
			@PathVariable Integer pid
			){
		Map<String, Object> result =  messageService.getMessageByPageNumber(pid);
		
		return new ResponseEntity<ReturnModel>(ReturnModel.SUCCESS(result), HttpStatus.OK);
	}
	
}
