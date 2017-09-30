package com.spittr.authorization.core;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.spittr.authorization.exception.ElementNotFoundException;
import com.spittr.authorization.exception.OAuthErrorException;
import com.spittr.authorization.manager.TokenManager;
import com.spittr.authorization.model.Data;
import com.spittr.authorization.model.HMTOAuthModel;
import com.spittr.authorization.model.HMTUserInfoModel;
import com.spittr.authorization.model.Token;
import com.spittr.sys.config.Configurator;
import com.spittr.tools.CacheUtils;
import com.spittr.tools.HttpRequest;
import com.spittr.user.dao.HmtUserDao;
import com.spittr.user.model.HMTUser;
import com.spittr.user.model.User;

import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

import static com.spittr.tools.HttpRequestParametersWraper.*;


@Service
public class OAuthServiceImpl {
	
	public final static String KEY_HMT_USER_CACHE_NAME
	= "Mimi.cache.user.HMT";
	public final static String KEY_AUTH_URI
	= "Mimi.authorization.OAuth2.HMT.url.auth";
	public final static String KEY_USER_INFO_URI
	= "Mimi.authorization.OAuth2.HMT.url.user_info";
	public final static String KEY_K_REDIRECT_URI
	= "Mimi.authorization.OAuth2.HMT.key.redirect_uri";
	public final static String KEY_K_CODE
	= "Mimi.authorization.OAuth2.HMT.key.code";
	public final static String KEY_K_CLIENT_ID
	= "Mimi.authorization.OAuth2.HMT.key.client_id";	
	public final static String KEY_K_CLIENT_SECRET
	= "Mimi.authorization.OAuth2.HMT.key.client_secret";
	public final static String KEY_K_GRANT_TYPE
	= "Mimi.authorization.OAuth2.HMT.key.grant_type";
	public final static String KEY_V_CLIENT_ID
	= "Mimi.authorization.OAuth2.HMT.value.client_id";	
	public final static String KEY_V_CLIENT_SECRET
	= "Mimi.authorization.OAuth2.HMT.value.client_secret";
	public final static String KEY_V_GRANT_TYPE
	= "Mimi.authorization.OAuth2.HMT.value.grant_type";
	
	protected String hmtUserCacheName;
	protected String authUri;
	protected String userInfoUri;
	protected String kRedirectUri;
	protected String kCode;
	protected String kClientId;
	protected String kClientSecret;
	protected String kGrantType;
	protected String vClientId;
	protected String vClientSecret;
	protected String vGrantType;
 	
	protected Logger logger = LoggerFactory.getLogger(OAuthServiceImpl.class);
	
	protected HttpRequest httpRequest;
	
	@Autowired
	@Qualifier("mySQLTokenManager")
	protected TokenManager tokenManager;
	
	@Autowired
	@Qualifier("hmtUserDaoImpl")
	protected HmtUserDao hmtUserDao;
	
	protected Ehcache cache;
	
	public OAuthServiceImpl() {
		// TODO Auto-generated constructor stub
		this.httpRequest = HttpRequest.getInstance();
		Configurator configurator = Configurator.getInstance();
		
		this.hmtUserCacheName = configurator.get(KEY_HMT_USER_CACHE_NAME);
		this.authUri = configurator.get(KEY_AUTH_URI);
		this.userInfoUri = configurator.get(KEY_USER_INFO_URI);
		this.kRedirectUri = configurator.get(KEY_K_REDIRECT_URI);
		this.kCode = configurator.get(KEY_K_CODE);
		this.kClientId = configurator.get(KEY_K_CLIENT_ID);
		this.kClientSecret = configurator.get(KEY_K_CLIENT_SECRET);
		this.kGrantType = configurator.get(KEY_K_GRANT_TYPE);
		this.vClientId = configurator.get(KEY_V_CLIENT_ID);
		this.vClientSecret = configurator.get(KEY_V_CLIENT_SECRET);
		this.vGrantType = configurator.get(KEY_V_GRANT_TYPE);
		
		this.cache = CacheUtils.getCache(hmtUserCacheName);
		
	}
	
	public HMTUserInfoModel doOAuth(String code, String redirectUri) {
		HttpRequest httpRequest = HttpRequest.getInstance();

		Long uid = null;
		List<NameValuePair> urlParameters = new ArrayList<>();
		urlParameters = add(urlParameters, this.kClientId, this.vClientId);
		urlParameters = add(urlParameters, this.kClientSecret,this.vClientSecret);
		urlParameters = add(urlParameters, this.kGrantType, this.vGrantType);
		urlParameters = add(urlParameters, this.kCode, code);
		urlParameters = add(urlParameters, this.kRedirectUri, redirectUri);
						
		String response = httpRequest.doPost(this.authUri, urlParameters);
		logger.debug(response);

		HMTOAuthModel hmtoAuthModel = JSON.parseObject(response, HMTOAuthModel.class);

		uid =  hmtoAuthModel.getUid();
		if (uid == null) 
			throw new OAuthErrorException();
		
		String userInfo = httpRequest.doGet(userInfoUri + uid);
		Data userInfoData = JSON.parseObject(userInfo.toString(), Data.class);
		
		HMTUserInfoModel hmtUserInfoModel = userInfoData.getData();
		if (!hmtUserInfoModel.check()) 
			throw new OAuthErrorException();
		
		return hmtUserInfoModel;
	}
	
	public Token login(HMTUserInfoModel hmtUserInfoModel){
			HMTUser hmtUser = hmtUserDao.get(hmtUserInfoModel.getUid());
			if (hmtUser == null) {
			cache.put(
					new Element(hmtUserInfoModel.getUid(), hmtUserInfoModel)
					);
				return null;
			}
			User user = hmtUser.getUser();
			Token token = tokenManager.createToken(user);
			return token;
	}
	
	public Token bind(User user, Long hmtUid){
		Element hmtUserInfoElement = cache.get(hmtUid);
		if (hmtUserInfoElement == null) 
			throw new ElementNotFoundException();
		
		Token token = tokenManager.createToken(user);
		HMTUserInfoModel hmtUserInfo = (HMTUserInfoModel)hmtUserInfoElement.getObjectValue();
		hmtUserDao.save(new HMTUser(hmtUserInfo, user));
		return token;
	}
	
	@Override
	public String toString() {
		return "OAuthServiceImpl [hmtUserCacheName=" + hmtUserCacheName + ", authUri=" + authUri + ", userInfoUri="
				+ userInfoUri + ", kRedirectUri=" + kRedirectUri + ", kCode=" + kCode + ", kClientId=" + kClientId
				+ ", kClientSecret=" + kClientSecret + ", kGrantType=" + kGrantType + ", vClientId=" + vClientId
				+ ", vClientSecret=" + vClientSecret + ", vGrantType=" + vGrantType + "]";
	}
	
	
	
}
