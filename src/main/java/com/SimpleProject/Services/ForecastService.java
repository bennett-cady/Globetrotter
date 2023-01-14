package com.SimpleProject.Services;

import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.SimpleProject.ApiCaller;
import com.SimpleProject.Model.Location;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ForecastService {

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
	
	public ArrayList<Location> findDestination(ArrayList<Location> locs, double ideal, double margin) throws JsonMappingException, JsonProcessingException 
	{
		ArrayList<Location> destinations = new ArrayList<Location>();
		ApiCaller ac = new ApiCaller();
		for(Location loc: locs) 
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
