package com.spittr.websocket.dao;

import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spittr.dao.BaseDaoHibernate5;

import com.spittr.websocket.model.*;

@Repository
public class NtcDaoImpl extends BaseDaoHibernate5<NtcBody> implements NtcDao{

	@Autowired
	protected SessionFactory sessionFactory;

	@Override
	public NtcMLikee getNtcMLikee(long id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(NtcMLikee.class)
				.add(Restrictions.eq("id", id));
		Object object = criteria.uniqueResult();
		
		return object==null?null:(NtcMLikee)object;
	}	
	
	@Override
	public NtcCLikee getNtcCLikee(long id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(NtcCLikee.class)
				.add(Restrictions.eq("id", id));
		Object object = criteria.uniqueResult();
		
		return object==null?null:(NtcCLikee)object;
	}	
	
	@Override
	public NtcCmmnt getNtcCmmnt(long id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(NtcCmmnt.class)
				.add(Restrictions.eq("id", id));
		Object object = criteria.uniqueResult();
		
		return object==null?null:(NtcCmmnt)object;
	}	
	
	@Override
	public NtcMLikee getNtcMLikee(Long mid, Long lkUid) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(NtcMLikee.class)
				.add(Restrictions.eq("mid", mid))
				.add(Restrictions.eq("lkUid", lkUid));
		Object object = criteria.uniqueResult();
		
		return object ==null?null:(NtcMLikee)object;
	}
	
	@Override
	public NtcCLikee getNtcCLikee(Long cid, Long lkUid) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(NtcCLikee.class)
				.add(Restrictions.eq("cid", cid))
				.add(Restrictions.eq("lkUid", lkUid));
		Object object = criteria.uniqueResult();
		
		return object ==null?null:(NtcCLikee)object;
	}
	
	@Override
	public NtcCmmnt getNtcCmmnt(Long cid, Long lkUid) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(NtcCmmnt.class)
				.add(Restrictions.eq("cid", cid))
				.add(Restrictions.eq("lkUid", lkUid));
		Object object = criteria.uniqueResult();
		
		return object ==null?null:(NtcCmmnt)object;
	}

	
}
