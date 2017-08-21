package com.spittr.user.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spittr.dao.BaseDaoHibernate5;
import com.spittr.user.model.User;

@Repository
@Transactional
public class UserDaoImpl extends BaseDaoHibernate5<User> implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public User get(String uname) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(User.class).add(Restrictions.eq("uname", uname));
		Object user = criteria.uniqueResult();
		return user == null? null : (User)user;
	}

	@Override
	public User getByNname(String nname) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(User.class).add(Restrictions.eq("nname", nname));
		Object user = criteria.uniqueResult();
		return user == null? null : (User)user;
	}
	
}
