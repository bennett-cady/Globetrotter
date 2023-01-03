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
	
	Location dry = new Location();
	Location wnpk = new Location("Copper Mountain", "80443", "USA", "North America");
	Location cpmt = new Location("Winter Park", "80482", "USA", "North America"); 	
	Location eldo = new Location("Eldora Ski Resort","80466", "USA", "North America");
	Location taos = new Location("Taos Ski Valley", "87525", "USA", "North America");
	
	Location[] resorts = {dry,wnpk,cpmt,eldo,taos};


	public Location findHighestTotal(int days) 
			throws JsonMappingException, JsonProcessingException
	{
		if(days>7) {
			days=7;
		}
		else if(days<1) {
			days=1;
		}
		double highest = 0.0;
		Location maxSnow = resorts[0];
		for(int idx=1; idx<resorts.length; idx++) {
			if(idx==4) {
				System.out.println(resorts[4]);
			}
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
	
	
	public String[][] rankResortSnowfall(int days) 
			throws JsonMappingException, JsonProcessingException 
	{
		if(days>7) {days=7;} else if(days<1) {days=1;}
		String[][] totalsMap = new String[resorts.length-1][2];
		for(int idx=1; idx<resorts.length; idx++) 
		{
			Location current = resorts[idx]; 
			String zipCode= current.getRegion(); 
			JsonNode[] jn = ac.customOutLook(zipCode, days);
			double total= 0.0; 
			int arrIdx = idx-1;
			for(JsonNode day: jn) 
			{
				double dailyTotal = day.path("day").path("totalsnow_cm").asDouble();
				total+=dailyTotal;
			}
			totalsMap[idx-1][0] = current.getCity(); 
			totalsMap[idx-1][1] = String.valueOf(total);
			while(arrIdx>0) 
			{
				if(  Double.valueOf(totalsMap[arrIdx][1]) > Double.valueOf( totalsMap[arrIdx-1][1] )) {
					String[] temp = totalsMap[arrIdx];
					totalsMap[arrIdx] = totalsMap[arrIdx-1];
					totalsMap[arrIdx-1] = temp;
				} else {break;}
				arrIdx--;
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
