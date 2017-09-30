package com.spittr.authorization.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import com.spittr.authorization.core.OAuthServiceImpl;
import com.spittr.authorization.model.HMTUserInfoModel;
import com.spittr.authorization.model.Token;
import com.spittr.model.ReturnModel;
import com.spittr.user.core.PasswdAuthenrization;
import com.spittr.user.core.UserService;
import com.spittr.user.model.Passwd;
import com.spittr.user.model.User;


@RestController
@RequestMapping(value="/api/OAuth2")
public class OAuth2Controller {

	@Autowired
	protected OAuthServiceImpl oAuthServiceImpl;

	@Autowired
	@Qualifier("userServiceImpl")
	protected UserService userService;
	
	@Autowired
	private PasswdAuthenrization passwdAuthenrization;
	
	protected Logger logger = LoggerFactory.getLogger(OAuth2Controller.class);
	
	public OAuth2Controller() {
		// TODO Auto-generated constructor stub
	}	
	
	@RequestMapping(value="/request.info", method=RequestMethod.POST)
	public ReturnModel hometownOAuth(
			@RequestParam(name="code") String code,
			@RequestParam(name="redirectUri") String redirectUri
			){
		if (code != null) {
				HMTUserInfoModel userInfoModel = oAuthServiceImpl.doOAuth(code, redirectUri);
				logger.info(userInfoModel.toString());

				Token token = oAuthServiceImpl.login(userInfoModel);
				return token == null?ReturnModel.SUCCESS(userInfoModel):ReturnModel.SUCCESS(token);
		}
			return ReturnModel.ERROR();
	}
	
	@RequestMapping(value="/bind", method=RequestMethod.POST)
	public ReturnModel bindUser(
			@RequestParam(name="hmtUid") Long hmtUid,
			@RequestParam(name="uname") String uname,
			@RequestParam(name="passwd") String passwd
			){
		Passwd passCred = passwdAuthenrization.authenrization(uname, passwd);
		User user = passCred.getUser();
		Token token = oAuthServiceImpl.bind(user, hmtUid);
		
		return ReturnModel.SUCCESS(token);
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public ReturnModel createAndBindUser(
			@RequestParam(name="hmtUid") Long hmtUid,
			@RequestParam(name="uname") String uname,
			@RequestParam(name="nname") String nname,
			@RequestParam(name="passwd") String passwd,
			@RequestParam(name="rpasswd") String rpasswd
			){
		User user = userService.create(uname, nname, rpasswd);
		Token token = oAuthServiceImpl.bind(user, hmtUid);
		return ReturnModel.SUCCESS(token);
	}

	
	@RequestMapping(value="/text")
	public Object text(
			@RequestParam(name="code") String code
			){
		return code;
	}
	
}
