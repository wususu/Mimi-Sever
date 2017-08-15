package com.spittr.location.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;import org.springframework.web.bind.annotation.ResponseStatus;

import com.spittr.dao.BaseDaoHibernate5;
import com.spittr.location.model.Location;


@Repository
public class LocationDaoImpl extends BaseDaoHibernate5<Location> implements LocationDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Location> getAll() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Location.class)
				.add(Restrictions.eq("isDelete", false))
				.addOrder(Order.asc("lid"));
		List<Location> locations = criteria.list();
		return locations;
	}
	
}

