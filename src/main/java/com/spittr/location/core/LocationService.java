package com.spittr.location.core;

import com.spittr.location.model.Location;


public interface LocationService {
	
	Location get(Long id);
	
	void save(Location location);
	
	void update(Location location);
	
	void delete(Location location);
	
}
