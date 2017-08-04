package com.spittr.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spittr.authorization.annotation.Authorization;
import com.spittr.authorization.annotation.AutoCurrentUser;
import com.spittr.authorization.model.Token;
import com.spittr.model.ReturnModel;
import com.spittr.user.core.UserService;
import com.spittr.user.dao.UserDao;
import com.spittr.user.exception.PasswdNotEqualsException;
import com.spittr.user.model.User;


@RestController
@RequestMapping(value="/api/user")
public class UserController {

	@Autowired
	@Qualifier("userDaoImpl")
	private UserDao userDao;
	
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public ResponseEntity<ReturnModel> create(
			@RequestParam("uname") String uname, 
			@RequestParam("passwd") String passwd,
			@RequestParam("rpasswd") String rpasswd,
			@RequestParam("nname") String nname
			){
		// 密码确认
		if (!passwd.equals(rpasswd)) {
			throw new PasswdNotEqualsException();
		}
		userService.create(uname, nname, passwd);
		return new ResponseEntity<>(ReturnModel.SUCCESS(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ResponseEntity<ReturnModel> logIn(
			@RequestParam("uname") String uname,
			@RequestParam("passwd") String passwd
			){
		
		Token token = userService.login(uname, passwd);
		return new ResponseEntity<>(ReturnModel.SUCCESS(token), HttpStatus.OK);
	}
	
	@Authorization
	@RequestMapping(value="/logout", method=RequestMethod.POST)
	public ResponseEntity<ReturnModel> logOut(@AutoCurrentUser User user){
		
		userService.logout(user);
		
		return new ResponseEntity<>(ReturnModel.SUCCESS(), HttpStatus.OK);
	}
	
	@Authorization
	@RequestMapping(value="/me", method=RequestMethod.GET)
	public ResponseEntity<ReturnModel> get(@AutoCurrentUser User user){
		return new ResponseEntity<>(ReturnModel.SUCCESS(user), HttpStatus.OK);
	}

	
//	@RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
//	public ResponseEntity<ReturnModel> delete(
//			@PathVariable Long id
//			){
//		User user = userDao.get(User.class, id);
//		user.setPlogin(false);
//		return new ResponseEntity<>(ReturnModel.SUCCESS(), HttpStatus.OK);
//	}
	
}
