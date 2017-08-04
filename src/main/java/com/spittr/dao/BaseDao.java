package com.spittr.dao;

import java.io.Serializable;


public interface BaseDao <T>{
	
	Long count(Class<T> entity);
	
	T get(Class<T> entity,Long id);
	
	Serializable save(T entity);
	
	void update(T entity);
	
	void delete(T entity);

}
