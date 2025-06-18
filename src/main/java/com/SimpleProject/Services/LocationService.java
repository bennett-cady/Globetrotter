package com.SimpleProject.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SimpleProject.DataAccessObjects.LocationDAO;
import com.SimpleProject.Model.Location;


@Service
public class LocationService {

	@Autowired(required=false)
	LocationDAO<?> locationDao;
	
	public List<Location> findAll() {
		return locationDao.findAll();
	}
	
	public void insertLocation(Location location) {
		locationDao.insert(location);
	}
}
