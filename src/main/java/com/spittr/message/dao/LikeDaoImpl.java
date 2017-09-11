package com.spittr.message.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spittr.dao.BaseDaoHibernate5;
import com.spittr.message.model.Like;
import com.spittr.message.model.Message;
import com.spittr.user.model.User;

@Repository
public class LikeDaoImpl extends BaseDaoHibernate5<Like> implements LikeDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Like get(long mid, long uid) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Like.class)
				.add(Restrictions.eq("mid", mid))
				.add(Restrictions.eq("uid", uid));
		Like userLike = (Like)criteria.uniqueResult();
		return userLike;
	}

	@Override
	public Like get(Message message, User user) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Like.class)
				.add(Restrictions.eq("message", message))
				.add(Restrictions.eq("user", user));
		Like userLike = (Like)criteria.uniqueResult();
		return userLike;
	}

	@Override
	public void create(Like entity) {
		// TODO Auto-generated method stub
		save(entity);
	}

}
