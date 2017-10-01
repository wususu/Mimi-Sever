package com.spittr.message.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spittr.dao.BaseDaoHibernate5;
import com.spittr.message.model.MLikee;
import com.spittr.message.model.Message;
import com.spittr.user.model.User;

@Repository
public class MlikeeDaoImpl extends BaseDaoHibernate5<MLikee> implements MlikeeDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public MLikee get(long mid, long uid) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(MLikee.class)
				.add(Restrictions.eq("mid", mid))
				.add(Restrictions.eq("uid", uid));
		MLikee userLike = (MLikee)criteria.uniqueResult();
		return userLike;
	}

	@Override
	public MLikee get(Message message, User user) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(MLikee.class)
				.add(Restrictions.eq("message", message))
				.add(Restrictions.eq("user", user));
		MLikee userLike = (MLikee)criteria.uniqueResult();
		return userLike;
	}

	@Override
	public void create(MLikee entity) {
		// TODO Auto-generated method stub
		save(entity);
	}
}
