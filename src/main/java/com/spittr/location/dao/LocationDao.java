package com.spittr.location.dao;

import java.util.List;
import java.util.Set;

import com.spittr.dao.BaseDao;
import com.spittr.location.model.Location;

public interface LocationDao extends BaseDao<Location>{
	
	List<Location> getAll();
}
