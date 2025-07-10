package com.SimpleProject.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;

import APICaller.SunshineAPICaller;

@Service
public class SunshineService {

	@Autowired
	SunshineAPICaller apiCaller;
	
	public double calculateDailySunshinePercentage(String city)  {
		try {
			JsonNode root = apiCaller.getDailySunshinePercentage(city); //JsonNode = the first item in forecastday: [] ?
			root=root.path("hour");
			byte hr=0;
			byte sunnyhrs = 0;
			for(JsonNode hour: root) {
				if(hr<6 || hr>22) {
					continue;
				}
				
			}
			
			
		} catch(JsonMappingException jme) {
			System.out.println("JsonMappingException");
		} catch(JsonProcessingException jpe) {
			System.out.println("JsonProcessingException");
		}
		return 0.0;
	}
	
}
