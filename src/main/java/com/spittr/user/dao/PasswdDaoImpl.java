package com.spittr.user.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spittr.dao.BaseDaoHibernate5;
import com.spittr.user.model.Passwd;
import com.spittr.user.model.User;


@Repository
public class PasswdDaoImpl extends BaseDaoHibernate5<Passwd> implements PasswdDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired 
	private UserDao userDao;
	
	@Override
	public Passwd isPasswd(String loginName) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Passwd.class).add(Restrictions.eq("login", loginName));
		List<?>  list = criteria.list();
		if (list.size() > 0) {
			return (Passwd)list.get(0);
		}
		return null;
	}

	@Override
	public Passwd passwdCreat(String loginName) {
		// TODO Auto-generated method stub
		Passwd passwd = isPasswd(loginName);

		if (passwd  == null) 
			passwd = this.create(loginName);
		
		return passwd;
	}
	
	private Passwd create(String login){
		User user = userDao.get(login);
		Passwd passwd = Passwd.newInstance(login);
		passwd.setUser(user);
		return passwd;
	}
	
}
