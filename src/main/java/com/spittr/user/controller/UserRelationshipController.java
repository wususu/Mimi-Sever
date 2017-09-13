package com.spittr.user.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import com.spittr.authorization.annotation.*;
import com.spittr.model.ReturnModel;
import com.spittr.user.core.*;
import com.spittr.user.exception.AddRelationshipErrorException;
import com.spittr.user.model.*;


@RestController
@RequestMapping(value="/api/user/relationship")
@CrossOrigin(origins="*", maxAge=3600)
public class UserRelationshipController {

	@Autowired
	@Qualifier("userRelationshipServiceImpl")
	private UserRelationshipService userRelationshipService;
	
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	
	@Authorization
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public ReturnModel addAttention(
			@AutoCurrentUser User mainUser,
			@RequestParam("objectUname") String objectUname
			){
		User objectUser = userService.get(objectUname);
		UserRelationship userRelationship = userRelationshipService.addAttention(mainUser, objectUser);
		if (userRelationship == null) 
			throw new AddRelationshipErrorException();
		
		return ReturnModel.SUCCESS(userRelationship);
	}
	
	@Authorization
	@RequestMapping(value="/cancel", method=RequestMethod.POST)
	public ReturnModel cancelAttention(
			@AutoCurrentUser User mainUser,
			@RequestParam("objectUname") String objectUname
			){
		User objectUser = userService.get(objectUname);
		
		if (userRelationshipService.cancelAttention(mainUser, objectUser)) 
			return ReturnModel.SUCCESS();
		
		return ReturnModel.ERROR();
	}
	
	@Authorization
	@RequestMapping(value={"/asmain/{tmbefore}", "/asmain"}, method=RequestMethod.GET)
	public ReturnModel getMainUserRelationships(
			@AutoCurrentUser User mainUser,
			@PathVariable(name="tmbefore", required=false) Long tmbefore
			){
		Date bfTime = null;
		bfTime = tmbefore == null? (new Date()) : (new Date(tmbefore));
		Map<String, Object> data = userRelationshipService.attentionsByMainUser(mainUser, bfTime);
		
		return ReturnModel.SUCCESS(data);
	}
	
	@Authorization
	@RequestMapping(value={"/asobject/{tmbefore}", "/asobject"}, method=RequestMethod.GET)
	public ReturnModel getObjectUserRelationships(
			@AutoCurrentUser User objectUser,
			@PathVariable(name="tmbefore", required=false) Long tmbefore
			) {
		Date bfTime = null;
		bfTime = tmbefore == null? (new Date()) : (new Date(tmbefore));
		Map<String, Object> data = userRelationshipService.attentionsByObjectUser(objectUser, bfTime);
		
		return ReturnModel.SUCCESS(data);
	}
	
	
}
