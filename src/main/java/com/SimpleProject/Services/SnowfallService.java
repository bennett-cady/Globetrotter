package com.SimpleProject.Services;

import com.SimpleProject.ApiCaller;
import com.SimpleProject.Model.Location;
import com.SimpleProject.Model.SkiResort;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;

public class SnowfallService 
{	
	ApiCaller apiCaller = new ApiCaller();

	public double weeklySnowTotal(String city) throws JsonMappingException, JsonProcessingException 
	{
		JsonNode[] jsonNode = apiCaller.weekOutLook(city);
		double snowTotal = 0.0;
		for(JsonNode day: jsonNode) {
			snowTotal += day.path("day").path("totalsnow_cm").asDouble();
		}
		return snowTotal;
	}
	
	SkiResort noneExpected = new SkiResort();
	SkiResort winterPark = new SkiResort("Copper Mountain", "Rocky Mountains", "80443", "USA", "North America");
	SkiResort copperMountain = new SkiResort("Winter Park", "Rocky Mountains", "80482", "USA", "North America"); 	
	SkiResort eldora = new SkiResort("Eldora Ski Resort", "Rocky Mountains", "80466", "USA", "North America");
	SkiResort taosSkiValley = new SkiResort("Taos Ski Valley", "Rocky Mountains", "87525", "USA", "North America");
	
	SkiResort[] resorts = {noneExpected, winterPark, copperMountain, eldora, taosSkiValley};

	public Location findHighestTotal(int days) 
			throws JsonMappingException, JsonProcessingException
	{
		if(days>7) {
			days=7;
		}
		else if(days<1) {
			days=1;
		}
		double highestTotal = 0.0;
		Location resort = resorts[0];
		for(int idx=1; idx<resorts.length; idx++)
		{
			String zipcode=resorts[idx].getZipCode();
			JsonNode[] jsonNode = apiCaller.customOutLook(zipcode, days);
			double total= 0.0;
			for(JsonNode day: jsonNode) {
				double dailyTotal = day.path("day").path("totalsnow_cm").asDouble();
				total+=dailyTotal;
			}
			if(total>highestTotal) {
				highestTotal=total;
				resort=resorts[idx];
			}
		}
		if(highestTotal==0.0) {
			noneExpected.city="No snow in the next "+String.valueOf(days)+" days";
			return noneExpected;
		}
		return resort;
	}
	
	public SkiResort[] rankResortSnowfall(int days) 
			throws JsonMappingException, JsonProcessingException 
	{
		if(days>7) {
			days=7;
		} else if(days<1) {
			days=1;
		}
		SkiResort[] resortsRanked = new SkiResort[resorts.length-1];
		
		for(int idx=1; idx<resorts.length; idx++) {
			SkiResort current = resorts[idx];
			JsonNode[] nodes = apiCaller.customOutLook(current.getZipCode(), days);
			double currentTotal = 0.0;
			int arrayIdx = idx-1;
			for(JsonNode day: nodes) {
				double dailyTotal = day.path("day").path("totalsnow_cm").asDouble();
				currentTotal+=dailyTotal;
			}
			current.setExpectedSnow(Math.ceil(currentTotal*100)/100);
			resortsRanked[arrayIdx]=current;
			while(arrayIdx>0) {
				if( resortsRanked[arrayIdx].getExpectedSnow() > resortsRanked[arrayIdx-1].getExpectedSnow() ) {
					SkiResort temp = resortsRanked[arrayIdx];
					resortsRanked[arrayIdx] = resortsRanked[arrayIdx-1];
					resortsRanked[arrayIdx-1] = temp;
				} else {
					break;
				}
				arrayIdx--;
			}
			
		}

		return resortsRanked; 
	}	
	
}
