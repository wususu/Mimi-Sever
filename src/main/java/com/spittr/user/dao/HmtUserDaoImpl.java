package com.spittr.user.dao;

import javax.validation.constraints.Null;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spittr.dao.BaseDaoHibernate5;
import com.spittr.user.model.HMTUser;

@Repository
public class HmtUserDaoImpl extends BaseDaoHibernate5<HMTUser> implements HmtUserDao{

	@Autowired
	protected SessionFactory sessionFactory;
	
	@Override
	public HMTUser get(Long uid) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(HMTUser.class)
				.add(Restrictions.eq("hmtUid", uid));
		Object object = criteria.uniqueResult();
		
		return object == null?null:(HMTUser)object;
	}

}
