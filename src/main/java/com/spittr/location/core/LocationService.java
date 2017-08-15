package com.spittr.location.core;

import java.util.List;

import com.spittr.location.model.Location;


public interface LocationService {
	
	public static Location getInstance(String locale){
		return new Location(locale);
	}
	
	List<Location> getAll();
	
	Location get(Long id);
	
	void save(Location location);
	
	void update(Location location);
	
	void delete(Location location);
	
}
