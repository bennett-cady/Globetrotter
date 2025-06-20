package com.SimpleProject.DataAccessObjects;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.SimpleProject.Model.Location;

@Repository 
public class LocationDAOImpl implements LocationDAO<Location> {
	
	@Autowired
	private EntityManager entityManager;

	public List<Location> findAll() {
		String jpql = "SELECT l FROM  Location l";
        TypedQuery<Location> query = entityManager.createQuery(jpql, Location.class);      
        return query.getResultList();
	}
	
	
	public Location getLocationByCity(String city) {
		String sql = "SELECT * FROM gen_location WHERE city=? ";
		Query query = entityManager.createNativeQuery(sql, Location.class);
		query.setParameter(1, city);
		return (Location) query.getSingleResult();
	}
	

	@Transactional
	public void insert(Location location) {
		String sql = "INSERT INTO gen_location (city, region, zip, country, continent) VALUES (?, ?, ?, ?, ?)";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter(1, location.getCity());
		query.setParameter(2, location.getRegion());
		query.setParameter(3, location.getZipCode());
		query.setParameter(4, location.getCountry());
		query.setParameter(5, location.getContinent());
		query.executeUpdate();
	}

}
