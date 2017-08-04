package com.spittr.dao;

import java.io.Serializable;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public class BaseDaoHibernate5<T> implements BaseDao<T>{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Long count(Class<T> entity){
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(entity);
		Long num = (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
		return num;
	}
	
    @SuppressWarnings("unchecked")
	public T get(Class<T > entity, Long id){
    	System.out.println(sessionFactory);
		Session session =sessionFactory.getCurrentSession();
		Serializable object = (Serializable) session.get(entity, id);
		return (T) object;
	}
	
	public Serializable save(T entity) {
		Session session = sessionFactory.getCurrentSession();
		Serializable serializable = session.save(entity);
		return serializable;
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
