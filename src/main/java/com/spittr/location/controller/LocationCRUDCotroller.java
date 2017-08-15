package com.spittr.location.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.loader.custom.Return;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static com.spittr.core.JSONConstants.*;
import com.spittr.location.core.LocationService;
import com.spittr.location.dao.LocationDao;
import com.spittr.location.dao.LocationDaoImpl;
import com.spittr.location.model.Location;
import com.spittr.model.ReturnModel;



@RestController
@RequestMapping(value="/api/locale")
public class LocationCRUDCotroller {

	@Autowired
	@Qualifier("locationServiceImpl")
	private LocationService locationService;
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public ReturnModel create(
			@RequestParam("locale") String locale
			){
		Location location = LocationService.getInstance(locale);
		locationService.save(location);
		return ReturnModel.SUCCESS();
	}
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	@ResponseStatus(value=HttpStatus.OK)
	public ReturnModel list(){
		List<Location> locations = locationService.getAll();
		Map<String, Object> map = getMap();
		map.put(LOCATION_LIST, locations);
		return ReturnModel.SUCCESS(map);
	}
	
	@RequestMapping(value="/get/{id}", method=RequestMethod.GET)
	public ReturnModel get(
			@PathVariable Long id
			){
		Location location = locationService.get(id);
		return ReturnModel.SUCCESS(location);
	}
	
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public ReturnModel update(
			@RequestParam("id") Long lid,
			@RequestParam("locale") String locale
			){
		Location location = locationService.get(lid);
		
		location.setLocale(locale);
		locationService.update(location);
		
		return ReturnModel.SUCCESS();
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.DELETE)
	@Transactional
	public ReturnModel delete(
			@RequestParam("id") Long lid
			){
		Location location = locationService.get(lid);
		
		location.setIsDelete(true);
		location.setTmDelete(new Date());
		
		locationService.update(location);
		
		return ReturnModel.SUCCESS();
	}
	
}
