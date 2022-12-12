package com.SimpleProject;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;

public class SnowfallService {
	
	ApiCaller ac = new ApiCaller();

	public double weeklySnowTotal(String city) throws JsonMappingException, JsonProcessingException {
		JsonNode[] jn = ac.weekOutLook(city);
		double snowTotal = 0.0;
		for(JsonNode day: jn) {
			snowTotal += day.path("day").path("totalsnow_cm").asDouble();
		}
		return snowTotal;
	}
	
	Location dry = new Location("","","","");
	Location wnpk = new Location("Copper Mountain", "80443", "USA", "North America");
	Location cpmt = new Location("Winter Park", "80482", "USA", "North America"); 	
	Location eldo = new Location("Eldora Ski Resort","80466", "USA", "North America");
	Location taos = new Location("Taos Ski Valley", "87525", "USA", "North America");
	
	Location[] resorts = {dry,wnpk,cpmt,eldo,taos};

	public Location findHighestTotal(int days) 
			throws JsonMappingException, JsonProcessingException
	{
		if(days<1 || days>7) {days=7;}
		
		double highest = 0.0;
		Location maxSnow = resorts[0];
		
		for(int idx=1; idx<resorts.length; idx++) {
			String zip=resorts[idx].getRegion();
			JsonNode[] jn = ac.customOutLook(zip, days);
			double total= 0.0;
			for(JsonNode day: jn) {
				double dailyTotal = day.path("day").path("totalsnow_cm").asDouble();
				total+=dailyTotal;
			}
			if(total>highest) {
				highest=total;
				maxSnow=resorts[idx];
			}
		}
		if(highest==0.0) {
			dry.city="No snow in the next "+String.valueOf(days)+" days";
			return dry;
		}
		return maxSnow;
	}
	
}
