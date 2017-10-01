package com.spittr.message.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spittr.dao.BaseDaoHibernate5;
import com.spittr.message.model.CLikee;
import com.spittr.message.model.Comment;
import com.spittr.user.model.User;

@Repository
public class ClikeeDaoImpl extends BaseDaoHibernate5<CLikee> implements ClikeeDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public CLikee get(long cid, long uid) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(CLikee.class)
		.add(Restrictions.eq("cid", cid))
		.add(Restrictions.eq("uid", uid));
		Object object = criteria.uniqueResult();
		if (object == null) 
			return null;
		return (CLikee)object;
	}
	
	@Override
	public CLikee get(Comment comment, User user) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(CLikee.class)
		.add(Restrictions.eq("comment", comment))
		.add(Restrictions.eq("user", user));
		Object object = criteria.uniqueResult();
		if (object == null) 
			return null;
		return (CLikee)object;
	}
	
	@Override
	public void create(CLikee entity){
		save(entity);
	}
}
