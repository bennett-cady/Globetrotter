package com.SimpleProject.DataAccessObjects;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.SimpleProject.Model.Location;

@Repository 
public class LocationDAOImpl implements LocationDAO<Location> {
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public List<Location> findAll() {
		String jpql = "SELECT l FROM  Location l";
        TypedQuery<Location> query = entityManager.createQuery(jpql, Location.class);      
        return query.getResultList();
	}



}
