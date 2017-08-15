package com.spittr.location.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spittr.dao.BaseDaoHibernate5;
import com.spittr.location.model.Location;


@Repository
public class LocationDaoImpl extends BaseDaoHibernate5<Location> implements LocationDao{

	public static Location getInstance(String locale){
		return new Location(locale);
	}
	
}

