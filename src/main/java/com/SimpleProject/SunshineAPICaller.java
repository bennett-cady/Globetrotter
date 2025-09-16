package com.SimpleProject;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.SimpleProject.ApiCaller;
import com.SimpleProject.Model.WeeklySunForecastDTO;
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
		// returns a list of hourly forecasts for the day
		return dailyForecasts;
	}
	
	
	public WeeklySunForecastDTO getWeeklySunshinePercentage(String city) throws JsonMappingException, JsonProcessingException {

		String uri = "http://api.weatherapi.com/v1/forecast.json?key="+weatherAPIKey+"&q="+city+"&days=7";
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(response.getBody());
		
		WeeklySunForecastDTO weekDto = new WeeklySunForecastDTO();

		weekDto.setCityName( root.get("location").get("name").asText() );
		weekDto.setRegion( root.get("location").get("region").asText() );
		weekDto.setCountry( root.get("location").get("country").asText() );
		
		JsonNode nodes = root.path("forecast");
		
		ArrayList<String> dates = new ArrayList<String>();
		for(JsonNode day : nodes.get("forecastday") ) {
			dates.add(day.get("date").asText());
		}
		weekDto.setDays(dates);

		ArrayList<ArrayList<JsonNode>> hourlyForecasts = new ArrayList<ArrayList<JsonNode>>();
		for(int i=0; i<7; i++) {
			ArrayList<JsonNode> dailyHrs = new ArrayList<JsonNode>();
			int hr = 0;
			for(JsonNode hour: nodes.get("forecastday").get(i).get("hour")) {
				if( hr>=6 && hr<22 ) {
					dailyHrs.add(hour);
				}
				hr++;
			}
			hourlyForecasts.add( dailyHrs );
		}
		weekDto.setHourly(hourlyForecasts);
		return weekDto;
	}
		
	
}
