package com.spittr.location.core;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spittr.exception.LocationNotFoundException;
import com.spittr.location.dao.LocationDao;
import com.spittr.location.model.Location;

@Service
public class LocationServiceImpl implements LocationService{

	@Autowired
	@Qualifier("locationDaoImpl")
	private LocationDao locationDao;
	
	@Override
	public Location get(Long id) {
		// TODO Auto-generated method stub
		Location location = locationDao.get(Location.class, id);
		if ( location.getIsDelete()) {
			throw new LocationNotFoundException();
		}
		return location;
	}

	@Override
	public void save(Location location) {
		// TODO Auto-generated method stub
		locationDao.save(location);
	}

	@Override
	public void update(Location location) {
		// TODO Auto-generated method stub
		locationDao.update(location);
	}

	@Override
	@Transactional
	public void delete(Location location) {
		// TODO Auto-generated method stub
		location.setIsDelete(true);
		location.setTmDelete(new Date());
		locationDao.update(location);
	}
}
