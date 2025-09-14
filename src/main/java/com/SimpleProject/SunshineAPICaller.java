package com.SimpleProject;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.SimpleProject.ApiCaller;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
@PropertySource(value = { "classpath:application.properties" })
public class SunshineAPICaller {

private static String weatherAPIKey;
	
	@Value("${api.key}") 
	public void setKey(String key) {
		SunshineAPICaller.weatherAPIKey = key;
	}
	
	public ArrayList<JsonNode> getDailySunshinePercentage(String city) throws JsonMappingException, JsonProcessingException 
	{		
		String uri = "http://api.weatherapi.com/v1/forecast.json?key="+weatherAPIKey+"&q="+city+"&days=1";
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(response.getBody());
		
		JsonNode nodes = root.path("forecast");
		
		ArrayList<JsonNode> dailyForecasts = new ArrayList<JsonNode>();
		
		for(JsonNode hour : nodes.get("forecastday").get(0).get("hour")) {
			dailyForecasts.add(hour);
		}
		return dailyForecasts;
	}
	
	//To-Do: consolidate getWeeklySunshinePercentage and getDailySunshinePercentage into one method with multiple parameters
	// includes current day in the week outlook - consider bumping up subscription to get the forecast 10 days out
	public ArrayList<JsonNode> getWeeklySunshinePercentage(String city) throws JsonMappingException, JsonProcessingException {
		System.out.println("In SAPIC...");
		String uri = "http://api.weatherapi.com/v1/forecast.json?key="+weatherAPIKey+"&q="+city+"&days=7";
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(response.getBody());
		
		JsonNode nodes = root.path("forecast");
		System.out.println("");
		//return a list of JsonNode for each of the next 7 days
		ArrayList<JsonNode> dailyForecasts = new ArrayList<JsonNode>();
		System.out.println("forecastday.size()="+nodes.get("forecastday").size());
		for(JsonNode day: nodes.get("forecastday")) {
			dailyForecasts.add(day);
		}
		return dailyForecasts;
	}
	
	
}
