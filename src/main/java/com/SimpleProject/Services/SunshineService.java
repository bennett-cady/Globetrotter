package com.SimpleProject.Services;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.SimpleProject.SunshineAPICaller;
import com.SimpleProject.Model.WeeklySunshineReport;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;



@Service
public class SunshineService {
	
	public double calculateDailySunshinePercentage(String city)  {
		byte sunnyhrs = 0;
		byte totalhrs = 16;
		SunshineAPICaller sunshineApiCaller = new SunshineAPICaller();
		try {
			ArrayList<JsonNode> hourlyForecast = sunshineApiCaller.getDailySunshinePercentage(city);
			for(JsonNode hour: hourlyForecast.subList(6, 22)) {

				String status = hour.path("condition").path("text").asText().toString();
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
	
	//implement next
	public WeeklySunshineReport getWeeklySunshinePercentage(String city) {
		SunshineAPICaller sunshineApiCaller = new SunshineAPICaller();
		try {
			System.out.println("In Service...");
			sunshineApiCaller.getWeeklySunshinePercentage(city);
		}catch(JsonMappingException jme) {
			System.out.println("JsonMappingException");
		} catch(JsonProcessingException jpe) {
			System.out.println("JsonProcessingException");
		}
		return new WeeklySunshineReport();
	}
}
