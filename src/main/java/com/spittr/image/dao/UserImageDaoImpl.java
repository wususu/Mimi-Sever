package com.spittr.image.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spittr.dao.BaseDaoHibernate5;
import com.spittr.image.model.UserImage;
import com.spittr.user.model.User;

@Repository
public class UserImageDaoImpl extends BaseDaoHibernate5<UserImage> implements UserImageDao{

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public UserImage get(User user) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(UserImage.class)
				.add(Restrictions.eq("user", user));
		UserImage userImage = (UserImage)criteria.uniqueResult();
		return userImage;
	}
	
}
