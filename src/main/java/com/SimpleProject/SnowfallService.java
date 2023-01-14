package com.SimpleProject;

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
	
	Location noneExpected = new Location();
	Location winterPark = new Location("Copper Mountain", "Rocky Mountains", "80443", "USA", "North America");
	Location copperMountain = new Location("Winter Park", "Rocky Mountains", "80482", "USA", "North America"); 	
	Location eldora = new Location("Eldora Ski Resort", "Rocky Mountains", "80466", "USA", "North America");
	Location taosSkiValley = new Location("Taos Ski Valley", "Rocky Mountains", "87525", "USA", "North America");
	
	Location[] resorts = {noneExpected, winterPark, copperMountain, eldora, taosSkiValley};

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
	
	public String[][] rankResortSnowfall(int days) 
			throws JsonMappingException, JsonProcessingException 
	{
		if(days>7) {
			days=7;
		} else if(days<1) {
			days=1;
		}
		String[][] totalsMap = new String[resorts.length-1][2];
		
		for(int idx=1; idx<resorts.length; idx++) 
		{
			Location currentResort = resorts[idx]; 
			String zipcode= currentResort.getZipCode(); 
			JsonNode[] jn = apiCaller.customOutLook(zipcode, days);
			double currentTotal= 0.0; 
			int arrayIdx = idx-1;
			for(JsonNode day: jn) 
			{
				double dailyTotal = day.path("day").path("totalsnow_cm").asDouble();
				currentTotal+=dailyTotal;
			}
			totalsMap[idx-1][0] = currentResort.getCity(); 
			totalsMap[idx-1][1] = String.valueOf(currentTotal);
			
			while(arrayIdx>0) {
				if(  Double.valueOf(totalsMap[arrayIdx][1]) > Double.valueOf( totalsMap[arrayIdx-1][1] )) {
					String[] temp = totalsMap[arrayIdx];
					totalsMap[arrayIdx] = totalsMap[arrayIdx-1];
					totalsMap[arrayIdx-1] = temp;
				} else {break;}
				arrayIdx--;
			}
		}
		for(String[] location: totalsMap) {
			double total=Double.valueOf(location[1]);
			total=Math.ceil(total*100)/100;
			location[1]=String.valueOf(total);
		}
		return totalsMap; 
	}	
	
}
