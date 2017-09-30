package com.spittr.tools;

import java.io.*;
import java.util.List;

import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.slf4j.*;
import com.spittr.authorization.exception.OAuthErrorException;

/**
 * 简单的线程安全的http请求单例
 * @author janke
 *
 */
public class HttpRequest {
	
	protected Logger logger = LoggerFactory.getLogger(HttpRequest.class);	
	
	private HttpClient httpClient;
 	
	private static class Inner{
		public static HttpRequest instance = new HttpRequest();
	}
	
	public static HttpRequest getInstance(){
		return Inner.instance;
	}
	
	private PoolingHttpClientConnectionManager cm;	
	
	private HttpRequest() {
		// TODO Auto-generated constructor stub
		this.cm = new PoolingHttpClientConnectionManager();
		this.httpClient = HttpClients.custom().setConnectionManager(cm).build();
	}
	
	public String doPost(String url, List<NameValuePair> urlParameters){
		HttpPost httpPost = new HttpPost(url);
		
		try {
			if (urlParameters!=null && urlParameters.size() > 0) 
				httpPost.setEntity(new UrlEncodedFormEntity(urlParameters));
						
				HttpResponse response = httpClient.execute(httpPost);
				httpPost.releaseConnection();
				HttpEntity httpEntity = response.getEntity();
			try{
				int responseCode = response.getStatusLine().getStatusCode();
				if (responseCode != 200) 
					throw new OAuthErrorException();
				
				return getConentBody(httpEntity.getContent()).toString()
						;

			}finally {
				// 释放资源
				EntityUtils.consume(httpEntity);
			}
		}catch (OAuthErrorException e) {
			// TODO: handle exception
			throw e;
		}catch (Exception e) {
			// TODO Auto-generated catch block
			logger.warn("HttpPost Fail:  " + e.getMessage());
			logger.debug(e.toString());
		}
		return null;
	}
	
	public String doGet(String url){
		HttpGet httpGet = new HttpGet(url);
		
		try {
			HttpResponse response = httpClient.execute(httpGet);
			HttpEntity httpEntity = response.getEntity();
			
				try{
						int responseCode = response.getStatusLine().getStatusCode();
						if (responseCode != 200) 
							throw new OAuthErrorException();	
									
						return getConentBody(httpEntity.getContent()).toString();
				}finally {
						EntityUtils.consume(httpEntity);
						httpGet.releaseConnection();
				}		
		}catch (OAuthErrorException e) {
			// TODO: handle exception
			throw e;
		}catch (Exception e) {
			logger.warn(e.toString());
			logger.warn("HttpPost Fail:  " + e.getMessage());
		}
		return null;
	}

	
	private StringBuffer getConentBody(InputStream inputStream){
		BufferedReader rd;
		try {
			rd = new BufferedReader( new InputStreamReader(inputStream));
			StringBuffer result = new StringBuffer();
			String line = "";
			while((line = rd.readLine()) != null)
				result.append(line);
			return result;
		} catch (UnsupportedOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return null;
	}
}
