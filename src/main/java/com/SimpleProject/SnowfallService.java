package com.SimpleProject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;

public class SnowfallService {

	public double weeklySnowTotal(String city) throws JsonMappingException, JsonProcessingException {
		ApiCaller ac = new ApiCaller();
		JsonNode[] jn = ac.weekOutLook(city);
		double snowTotal = 0.0;
		
		for(JsonNode day: jn) {
			snowTotal += day.path("day").path("totalsnow_cm").asDouble();
		}
		return snowTotal;
	}
	
	
}
