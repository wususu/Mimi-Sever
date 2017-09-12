package com.spittr.user.controller;

import org.hibernate.loader.custom.Return;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
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
import com.sun.org.apache.bcel.internal.generic.I2F;


@RestController
@RequestMapping(value="/api/user")
@CrossOrigin(origins="*", maxAge=3600)
public class UserController {

	@Autowired
	@Qualifier("userDaoImpl")
	private UserDao userDao;
	
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public ReturnModel create(
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
		return ReturnModel.SUCCESS();
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ReturnModel logIn(
			@RequestParam("uname") String uname,
			@RequestParam("passwd") String passwd
			){
		
		Token token = userService.login(uname, passwd);
		return ReturnModel.SUCCESS(token);
	}
	
	@Authorization
	@RequestMapping(value="/logout", method=RequestMethod.POST)
	public ReturnModel logOut(@AutoCurrentUser User user){
		
		userService.logout(user);
		
		return ReturnModel.SUCCESS();
	}
	
	@Authorization
	@RequestMapping(value="/me", method=RequestMethod.GET)
	public ReturnModel get(@AutoCurrentUser User user){
		return ReturnModel.SUCCESS(user);
	}

	@Authorization
	@RequestMapping(value="/profile/{uname}", method=RequestMethod.GET)
	public ReturnModel get(@PathVariable String uname){
		// 头像
		
		User user = userService.get(uname);
		return ReturnModel.SUCCESS(user);
	}
	
	@Authorization
	@RequestMapping(value="/profile/edit", method=RequestMethod.POST)
	public ReturnModel editProfile(
			@AutoCurrentUser User user,
			@RequestParam(name="gender", required=false) Boolean gender,
			@RequestParam(name="site", required=false) String site,
			@RequestParam(name="signature", required=false) String signature
			){
		if ( (user.getGender() == null? user.getGender() == gender: user.getGender().equals(gender)) &&
			 (user.getSite() == null? user.getSite() == site: user.getSite().equals(site)) && 
			 (user.getSignature() == null? user.getSignature() == signature: user.getSignature().equals(signature)) )
			return ReturnModel.SUCCESS(user);
		
		user.setGender(gender);
		user.setSignature(signature);
		user.setSite(site);
		userService.update(user);
		return ReturnModel.SUCCESS(user);
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
