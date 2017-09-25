package com.spittr.user.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spittr.dao.BaseDaoHibernate5;
import com.spittr.user.model.User;
import com.spittr.user.model.UserRelationship;

@Repository
public class UserRelationshipDaoImpl extends BaseDaoHibernate5<UserRelationship> implements UserRelationshipDao{

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public UserRelationship get(long id) {
		// TODO Auto-generated method stub
		return get(UserRelationship.class, id);
	}

	/**
	 * get UserRelationship include isDelete
	 */
	@Override
	public UserRelationship get(User mainUser, User objectUser) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(UserRelationship.class)
				.add(Restrictions.eq("mainUser", mainUser))
				.add(Restrictions.eq("objectUser", objectUser));
		
		UserRelationship userRelationship = (UserRelationship)criteria.uniqueResult();
		return userRelationship;
	}
	
	@Override
	public List<UserRelationship> getMainUserRelationship(User mainUser, Date bfTime, int limit) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(UserRelationship.class)
				.add(Restrictions.eq("isDelete", false))
				.add(Restrictions.eq("mainUser", mainUser))
				.addOrder(Order.desc("tmCreated"))
				.add(Restrictions.le("tmCreated", bfTime))
				.setFirstResult(0)
				.setMaxResults(limit);
		
		@SuppressWarnings("unchecked")
		List<UserRelationship> userRelationships = (List<UserRelationship>)criteria.list();

		return userRelationships;
	}

	@Override
	public List<UserRelationship> getObjectUserRelationship(User objectUser, Date bfTime, int limit) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(UserRelationship.class)
				.add(Restrictions.eq("isDelete", false))
				.add(Restrictions.eq("objectUser", objectUser))
				.addOrder(Order.desc("tmCreated"))
				.add(Restrictions.le("tmCreated", bfTime))
				.setFirstResult(0)
				.setMaxResults(limit);
		
		@SuppressWarnings("unchecked")
		List<UserRelationship> userRelationships = (List<UserRelationship>)criteria.list();

		return userRelationships;
	}

}
