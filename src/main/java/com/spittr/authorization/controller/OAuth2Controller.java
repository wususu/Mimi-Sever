package com.spittr.authorization.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.spittr.authorization.core.OAuthServiceImpl;
import com.spittr.authorization.model.HMTUserInfoModel;
import com.spittr.model.ReturnModel;


@RestController
@RequestMapping(value="/OAuth")
public class OAuth2Controller {

	@Autowired
	private OAuthServiceImpl oAuthServiceImpl;
	
	@RequestMapping(value="/login")
	public Object hometownOAuth(
			@RequestParam(name="code", required=false) String code
			){
		if (code != null) {
				HMTUserInfoModel userInfoModel = oAuthServiceImpl.doOAuth(code);
				
			return ReturnModel.SUCCESS(userInfoModel);
		}else {
			return ReturnModel.ERROR();
		}
	}
	
}
