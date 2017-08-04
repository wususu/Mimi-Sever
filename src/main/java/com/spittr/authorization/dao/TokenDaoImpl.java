package com.spittr.authorization.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spittr.authorization.model.Token;
import com.spittr.dao.BaseDaoHibernate5;


@Repository
@Transactional
public class TokenDaoImpl extends BaseDaoHibernate5<Token> implements TokenDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Token get(String secret) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Token.class).add(Restrictions.eq("secret", secret));
		 List<?> list= criteria.list();
		if (list.size() > 0) {
			return (Token)list.get(0);
		}
		return null;
	}
	
	@Override
	public List<Token> get(Long uid){
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Token.class).add(Restrictions.eq("uid", uid));
		@SuppressWarnings("unchecked")
		List<Token> list = (List<Token>)criteria.list();
		if (list.size() > 0)
			return list;
		return null;
	}

}
