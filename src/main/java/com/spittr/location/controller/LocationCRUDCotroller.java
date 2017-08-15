package com.spittr.location.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spittr.location.dao.LocationDao;
import com.spittr.location.dao.LocationDaoImpl;
import com.spittr.location.model.Location;
import com.spittr.model.ReturnModel;


@RestController
@RequestMapping(value="/api/locale")
public class LocationCRUDCotroller {

	@Autowired
	@Qualifier("locationDaoImpl")
	private LocationDao locationDao;
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public ResponseEntity<ReturnModel> create(
			@RequestParam("locale") String locale
			){
		Location location = LocationDaoImpl.getInstance(locale);
		locationDao.save(location);
		return new ResponseEntity<ReturnModel>(ReturnModel.SUCCESS(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/get/{id}", method=RequestMethod.GET)
	public ResponseEntity<ReturnModel> get(
			@PathVariable Long id
			){
		Location location = locationDao.get(Location.class, id);
		return new ResponseEntity<ReturnModel>(ReturnModel.SUCCESS(location), HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public ResponseEntity<ReturnModel> update(
			@RequestParam("id") Long lid,
			@RequestParam("locale") String locale
			){
		Location location = locationDao.get(Location.class, lid);
		
		location.setLocale(locale);
		locationDao.update(location);
		
		return new ResponseEntity<ReturnModel>(ReturnModel.SUCCESS(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.DELETE)
	@Transactional
	public ResponseEntity<ReturnModel> delete(
			@RequestParam("id") Long lid
			){
		Location location = locationDao.get(Location.class, lid);
		
		System.out.println(location);
		
		location.setIsDelete(true);
		location.setTmDelete(new Date());
		
		locationDao.update(location);
		
		return new ResponseEntity<ReturnModel>(ReturnModel.SUCCESS(), HttpStatus.OK);
	}
	
}
