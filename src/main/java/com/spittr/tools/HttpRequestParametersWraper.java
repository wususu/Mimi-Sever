package com.spittr.tools;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.spittr.authorization.exception.OAuthErrorException;

public class HttpRequestParametersWraper {
	
	public static List<NameValuePair> add(List<NameValuePair> urlParameters, String key, String value){
		System.out.println(key + "   " +value);
		urlParameters.add(new BasicNameValuePair(key, value));
		return urlParameters;
	}
	
	public static List<NameValuePair> add(List<NameValuePair> urlParameter, Map<String, String> keyValue){
		for (String key : keyValue.keySet()) {
			String value = keyValue.get(key);
			add(urlParameter, key, value);
		}
		return urlParameter;
	}
	
	public static <T> List<NameValuePair> add(List<NameValuePair> urlParameters, Class<T> c) {
		
		Field[] fields = c.getFields();
		for (Field field : fields) {
			Object value;
			try {
				value = field.get(null);
				if (String.class.isInstance(value)) {
					add(urlParameters, field.getName(), (String)value);
				}
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			throw new OAuthErrorException();
		}
		return urlParameters;
	}
	
	
}
