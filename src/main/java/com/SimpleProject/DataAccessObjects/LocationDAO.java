package com.SimpleProject.DataAccessObjects;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.SimpleProject.Model.Location;

@Repository
public interface LocationDAO {

	@Autowired
	public List<Location> getAll();
	
}
