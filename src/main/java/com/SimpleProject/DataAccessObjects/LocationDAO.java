package com.SimpleProject.DataAccessObjects;

import java.util.List;

import com.SimpleProject.Model.Location;

public interface LocationDAO<T>  {

	
	
	public List<Location> findAll();
	
}
