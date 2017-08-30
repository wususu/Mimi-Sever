package com.spittr.websocket.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.spittr.dao.BaseDaoHibernate5;
import com.spittr.websocket.model.ChatMsg;

@Repository
public class ChatMsgDaoImpl extends BaseDaoHibernate5<ChatMsg> implements ChatMsgDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<ChatMsg> getNotRecivedChatMsgList(Long reciverId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(ChatMsg.class)
				.add(Restrictions.eq("reciverId", reciverId))
				.add(Restrictions.eq("isRecivered", false))
				.addOrder(Order.asc("time"));
		
		@SuppressWarnings("unchecked")
		List<ChatMsg> chatMsgList = (List<ChatMsg>)criteria.list();
		return chatMsgList;
	}



	@Override
	public ChatMsg getChatMsg(String chatID) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(ChatMsg.class)
		.add(Restrictions.eq("chatID", chatID));
		ChatMsg chatMsg = (ChatMsg)criteria.uniqueResult();
		return chatMsg;
	}
}
