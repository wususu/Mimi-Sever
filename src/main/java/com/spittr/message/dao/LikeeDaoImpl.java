package com.spittr.message.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spittr.dao.BaseDaoHibernate5;
import com.spittr.message.model.Likee;
import com.spittr.message.model.Message;
import com.spittr.user.model.User;

@Repository
public class LikeeDaoImpl extends BaseDaoHibernate5<Likee> implements LikeeDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Likee get(long mid, long uid) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Likee.class)
				.add(Restrictions.eq("mid", mid))
				.add(Restrictions.eq("uid", uid));
		Likee userLike = (Likee)criteria.uniqueResult();
		return userLike;
	}

	@Override
	public Likee get(Message message, User user) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Likee.class)
				.add(Restrictions.eq("message", message))
				.add(Restrictions.eq("user", user));
		Likee userLike = (Likee)criteria.uniqueResult();
		return userLike;
	}

	@Override
	public void create(Likee entity) {
		// TODO Auto-generated method stub
		save(entity);
	}

}
