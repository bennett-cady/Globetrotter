package com.SimpleProject.Services;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.SimpleProject.ApiCaller;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;

import APICaller.SunshineAPICaller;

@Service
public class SunshineService {
	
	public double calculateDailySunshinePercentage(String city)  {
		byte sunnyhrs = 0;
		byte totalhrs = 16;
		ApiCaller apiCaller = new ApiCaller();
		try {
			ArrayList<JsonNode> hourlyForecast = apiCaller.getDailySunshinePercentage(city); //JsonNode = the first item in forecastday: [] ?

			for(JsonNode hour: hourlyForecast.subList(6, 22)) {

				String status = hour.path("condition").path("text").asText().toString();
				System.out.println(status);
				if(status.equals("Sunny")) {
					sunnyhrs++;
				}
				
			}	
		} catch(JsonMappingException jme) {
			System.out.println("JsonMappingException");
		} catch(JsonProcessingException jpe) {
			System.out.println("JsonProcessingException");
		}
		return (double) sunnyhrs/totalhrs;
	}
	
}
