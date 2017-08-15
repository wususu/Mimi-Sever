package com.spittr.dao;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public class BaseDaoHibernate5<T> implements BaseDao<T>{
	
	@Autowired
	private SessionFactory sessionFactory;
	
    @SuppressWarnings("unchecked")
	public T get(Class<T > entity, Long id){
		Session session =sessionFactory.getCurrentSession();
		Serializable object = (Serializable) session.get(entity, id);
		return (T) object;
	}
	
	public Serializable save(T entity) {
		Session session = sessionFactory.getCurrentSession();
		Serializable serializable = session.save(entity);
		return serializable;
	}
	
	@SuppressWarnings("unchecked")
	public T load(Class<T> entity, Long id){
		Session session = sessionFactory.getCurrentSession();
		Serializable object = (Serializable)session.load(entity, id);
		return (T) object;
	}
    
	public void update(T entity) {
		Session session = sessionFactory.getCurrentSession();
		session.update(entity);
	}
	
	public void delete(T entity) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(entity);
	}
}
