package com.spittr.message.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spittr.dao.BaseDaoHibernate5;
import com.spittr.message.model.Comment;
import com.spittr.tools.page.Page;


@Repository
public class CommentDaoImpl extends BaseDaoHibernate5<Comment> implements CommentDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Comment> get(Long mid){
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Comment.class)
				.add(Restrictions.eq("mid", mid))
				.add(Restrictions.eq("isDelete", false));
		
		@SuppressWarnings("unchecked")
		List<Comment> commentList = (List<Comment>)criteria.list();
		return commentList;
	}
	
	@Override
	public List<Comment> get(Long mid, Page page){
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Comment.class)
				.add(Restrictions.eq("mid", mid))
				.add(Restrictions.eq("isDelete", false))
				.addOrder(Order.asc("tmCreated"))
				.setFirstResult(page.getFirst())
				.setMaxResults(page.getItemPerPage());
		
		@SuppressWarnings("unchecked")
		List<Comment> commentList = (List<Comment>)criteria.list();
		return commentList;
	}
	
	@Override
	public Long count(Long mid){
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Comment.class)
				.add(Restrictions.eq("isDelete", false))
				.add(Restrictions.eq("mid", mid))
				.setProjection(Projections.rowCount());
		
		Long count = (Long)criteria.uniqueResult();
		return count;
	}

}
