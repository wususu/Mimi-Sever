package com.spittr.authorization.core;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import com.spittr.authorization.exception.OAuthErrorException;


public class HttpRequest {
	
	private static final String HMT_OAUTH_URL =  "http://hometown.scau.edu.cn/open/OAuth/access_token";
	
	private static final String HMT_USER_URL = "http://hometown.scau.edu.cn/bbs/plugin.php?id=iltc_open:userinfo&uid=";
	
	private static String K_CLIENT_SRECT = "client_secret";
	
	private static String K_REDIRECT_URI = "redirect_uri";
	
	private static String K_CLIENT_ID = "client_id";
	
	private static String K_GRANT_TYPE = "grant_type";
	
	private static String K_CODE = "code";
		
	private static String V_CLIENT_SECRET = "XB6Q3z3cPXp04xe58XEh157FEFum3vP7";
	
	private static String V_REDIRECT_URI = "http://localhost:8080/Mimi/OAuth/login";
	
	private static String V_CLIENT_ID = "8";
	
	private static String V_GRANT_TYPE = "authorization_code";
		
	private HttpClient  client = HttpClientBuilder.create().build();
	
	private HttpPost post = new HttpPost(HMT_OAUTH_URL);
	
	private HttpGet get  = null;
	
	private List<NameValuePair> urlParameters = new ArrayList<>();
	
	public static HttpRequest newInstance(){
		return new HttpRequest();
	}
	
	private HttpRequest() {
		// TODO Auto-generated constructor stub
		urlParameters.add(new BasicNameValuePair(K_REDIRECT_URI, V_REDIRECT_URI));
		urlParameters.add(new BasicNameValuePair(K_CLIENT_SRECT, V_CLIENT_SECRET));
		urlParameters.add(new BasicNameValuePair(K_CLIENT_ID, V_CLIENT_ID));
		urlParameters.add(new BasicNameValuePair(K_GRANT_TYPE, V_GRANT_TYPE));
	}
	
	
	public HttpRequest setCode(String code){
		urlParameters.add(new BasicNameValuePair(K_CODE, code));
		return this;
	}
	
	public StringBuffer doPost(){
		try {
			post.setEntity(new UrlEncodedFormEntity(urlParameters));
			
			HttpResponse response = client.execute(post);
			
			int responseCode = response.getStatusLine().getStatusCode();
			if (responseCode != 200) 
				throw new OAuthErrorException();
			
			BufferedReader rd = new BufferedReader( new InputStreamReader(response.getEntity().getContent()));		
			StringBuffer result = new StringBuffer();
			String line = "";
			while((line = rd.readLine()) != null)
				result.append(line);
			
			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		throw new NullPointerException();
	}
	
	public StringBuffer doGet(Long uid){
		try {
			HttpResponse response = client.execute(new HttpGet(HMT_USER_URL + String.valueOf(uid)));
			
			int responseCode = response.getStatusLine().getStatusCode();
			if (responseCode != 200) 
				throw new OAuthErrorException();
			
			BufferedReader rd = new BufferedReader( new InputStreamReader(response.getEntity().getContent()));		
			StringBuffer result = new StringBuffer();
			String line = "";
			while((line = rd.readLine()) != null)
				result.append(line);
			
			System.out.println(result);
			
			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		throw new NullPointerException();

	}

	
	
	public static void main(String[] args) {
		HttpRequest.newInstance().setCode("aab91ef05ea42a642b1f7398f16334eb").doPost();
	}
}
