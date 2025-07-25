package com.SimpleProject;


import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
@PropertySource(value = { "classpath:application.properties" })
public class ApiCaller {
	
	private static String weatherAPIKey;
	
	@Value("${api.key}") 
	public void setKey(String key) {
		ApiCaller.weatherAPIKey = key;
	}
	
	
	public JsonNode showTempForCity(String city) throws JsonMappingException, JsonProcessingException 
	{		
		String uri = "http://api.weatherapi.com/v1/current.json?key="+weatherAPIKey+"&q="+city;
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(response.getBody());
		JsonNode nodes = root.path("current").path("temp_f");
		return nodes;
	}
	
	public double[] getCity7Day(String city) throws JsonMappingException, JsonProcessingException 
	{
		String uri = "http://api.weatherapi.com/v1/forecast.json?key="
	    +weatherAPIKey+"&q="+city+ "&days=7";
		
		RestTemplate restTemp = new RestTemplate();
		ResponseEntity<String> response = restTemp.getForEntity(uri, String.class);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(response.getBody());
		JsonNode[] weekForecast = new JsonNode[7];
		JsonNode days = root.path("forecast").path("forecastday");
		
		for(int i=0; i<7; i++) {
			weekForecast[i]=days.get(i);
		}
		
		double[] weekHighs = new double[7];
		int idx=0;
		for(JsonNode node: weekForecast) 
		{
			double temp_f = node.path("day").path("maxtemp_f").asDouble();
			weekHighs[idx]=temp_f;
			idx++;
			
		}
		return weekHighs;
	}
	
	public JsonNode[] weekOutLook(String location) throws JsonMappingException, JsonProcessingException {
		
		//returns a list of the 7 day forecast for the location
	
		String uri = "http://api.weatherapi.com/v1/forecast.json?key="+weatherAPIKey+"&q="+location+ "&days=7";
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(response.getBody());
				
		JsonNode[] weekForecast = new JsonNode[7];
		JsonNode days = root.path("forecast").path("forecastday");
				
		for(int i=0; i<7; i++) {
			weekForecast[i]=days.get(i);
		}
		return weekForecast;
	}
	
	
	public JsonNode[] customOutLook(String location, int daysNo) throws JsonMappingException, JsonProcessingException {

		String uri = "http://api.weatherapi.com/v1/forecast.json?key="+weatherAPIKey+"&q="+location+ "&days="+daysNo;
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(response.getBody());
				
		JsonNode[] forecast = new JsonNode[daysNo];
		JsonNode days = root.path("forecast").path("forecastday");
				
		for(int i=0; i<daysNo; i++) {
			forecast[i]=days.get(i);
		}
		for(JsonNode day: forecast) {
			double total=day.path("day").path("totalsnow_cm").asDouble();
		}
		return forecast;
	}
	
	
}







