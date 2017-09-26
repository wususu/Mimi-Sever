package com.spittr.tools;

import java.util.Map;


public class Mapper{
	
	private Map<String, Object> map;
		
	public static Mapper newInstance(Map< String, Object> map){
		return new Mapper(map);
	}
	
	private Mapper(Map<String, Object> map) {
		// TODO Auto-generated constructor stub
		this.map = map;
	}
	
	public Mapper add(String key, Object value){
		map.put(key, value);
		return this;
	}
	
	public Map<String, Object> getMap(){
		return map;
	}
}
