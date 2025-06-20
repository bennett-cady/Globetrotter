package com.SimpleProject.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SimpleProject.ApiCaller;
import com.SimpleProject.DataAccessObjects.LocationDAO;
import com.SimpleProject.Model.Location;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;

@Service
public class ForecastService {
	
	@Autowired
	LocationDAO<Location> locationDAO;

	public String weekSummary(String location, double idealTemp) throws JsonMappingException, JsonProcessingException {
		ApiCaller ac = new ApiCaller();
		JsonNode[] jn = ac.weekOutLook(location);
		double totalAvg = 0.0;
		for(JsonNode day: jn) {
			totalAvg += day.path("day").path("avgtemp_f").asDouble();
		}
		
		if( idealTemp-5.0 > totalAvg/7.0 ) {
			return "It will be too cold for you in "+location;
		} else if ( idealTemp+5.0 < totalAvg/7.0 ) {
			return "It will be too hot for you in "+location;
		}
		
		return "The weather in "+location+" will be just right this week!";
	}
	
	public ArrayList<Location> findDestination(double ideal, double margin) throws JsonMappingException, JsonProcessingException 
	{
		List<Location> allLocs = locationDAO.findAll();
		System.out.println(allLocs.size());
		ArrayList<Location> destinations = new ArrayList<Location>();
		ApiCaller ac = new ApiCaller();
		for(Location loc: allLocs) 
		{
			JsonNode[] jn = ac.weekOutLook(loc.getCity());
			double totalAvg = 0.0;
			for(JsonNode day: jn) {
				totalAvg += day.path("day").path("avgtemp_f").asDouble();
			}
			totalAvg/=7.0;
			if( totalAvg <= ideal+margin && totalAvg >= ideal-margin ) {
				destinations.add(loc);
			}
		}
		return destinations;
	}
	
}
