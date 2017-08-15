package com.spittr.image.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spittr.dao.BaseDaoHibernate5;
import com.spittr.image.model.MessageImage;

@Repository
public class MessageImageDaoImpl extends BaseDaoHibernate5<MessageImage> implements MessageImageDao{

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public MessageImage get(String webPath){
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(MessageImage.class)
				.add(Restrictions.eq("webPath", webPath))
				.add(Restrictions.eq("isDelete", false));
		
		MessageImage messageImage = (MessageImage)criteria.uniqueResult();
		
		return messageImage;
	}
	
	@Override
	public List<MessageImage> getByMid(Long mid) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(MessageImage.class)
				.add(Restrictions.eq("mid", mid))
				.add(Restrictions.eq("isDelete", false))
				.addOrder(Order.asc("tmCreated"));
		
		@SuppressWarnings("unchecked")
		List<MessageImage> messageImageList = (List<MessageImage>) criteria.list();
		
		return messageImageList;
	}
	
	
	
	
}
