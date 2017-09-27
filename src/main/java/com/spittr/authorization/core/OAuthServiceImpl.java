package com.spittr.authorization.core;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.spittr.authorization.model.Data;
import com.spittr.authorization.model.HMTOAuthModel;
import com.spittr.authorization.model.HMTUserInfoModel;

@Service
public class OAuthServiceImpl {
	
	public HMTUserInfoModel doOAuth(String code){
		Long uid = null;
		HttpRequest httpRequest = HttpRequest.newInstance();

			String response = httpRequest.setCode(code).doPost().toString();
			HMTOAuthModel hmtoAuthModel = JSON.parseObject(response, HMTOAuthModel.class);

			uid =  hmtoAuthModel.getUid();
			String userInfo = httpRequest.doGet(uid).toString();
			Data userInfoData = JSON.parseObject(userInfo, Data.class);
			return userInfoData.getData();
	}
	
}
