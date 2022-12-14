package com.SimpleProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


@Configuration
public class ApiCaller {
	/*
	@Value("${API_KEY}")
	private String apiKey;
	*/
	/*
	@Autowired
	private Environment env;
	String apiKey = env.getProperty("API_KEY");
	*/

	public JsonNode showTempForCity(String city) throws JsonMappingException, JsonProcessingException 
	{
		String uri = "http://api.weatherapi.com/v1/current.json?key="+System.getenv("API_KEY")+"&q="+city;
		RestTemplate restTemp = new RestTemplate();
		ResponseEntity<String> response = restTemp.getForEntity(uri, String.class);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(response.getBody());
		JsonNode name = root.path("current").path("temp_f");
		return name;
	}
	
	public double[] getCity7Day(String city) throws JsonMappingException, JsonProcessingException 
	{
		String uri = "http://api.weatherapi.com/v1/forecast.json?key="
	    +System.getenv("API_KEY")+"&q="+city+ "&days=7";
		RestTemplate restTemp = new RestTemplate();
		ResponseEntity<String> response = restTemp.getForEntity(uri, String.class);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(response.getBody());
		
		JsonNode[] arr = new JsonNode[7];
		JsonNode days = root.path("forecast").path("forecastday");
		
		for(int i=0; i<7; i++) 
		{
			arr[i]=days.get(i);
		}
		
		double[] highs = new double[7];
		int idx=0;
		for(JsonNode jn: arr) 
		{
			double tf= jn.path("day").path("maxtemp_f").asDouble();
			highs[idx]=tf;
			idx++;
			
		}
		return highs;
	}
	
	public JsonNode[] weekOutLook(String location) throws JsonMappingException, JsonProcessingException {
		
		//returns a list of the 7 day forecast for the location
		
		String uri = "http://api.weatherapi.com/v1/forecast.json?key="+System.getenv("API_KEY")+"&q="+location+ "&days=7";
		RestTemplate restTemp = new RestTemplate();
		ResponseEntity<String> response = restTemp.getForEntity(uri, String.class);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(response.getBody());
				
		JsonNode[] arr = new JsonNode[7];
		JsonNode days = root.path("forecast").path("forecastday");
				
		for(int i=0; i<7; i++) {
			arr[i]=days.get(i);
		}
		return arr;
	}
	
	
	public JsonNode[] customOutLook(String location, int daysNo) throws JsonMappingException, JsonProcessingException {
		
		String uri = "http://api.weatherapi.com/v1/forecast.json?key="+System.getenv("API_KEY")+"&q="+location+ "&days="+daysNo;
		RestTemplate restTemp = new RestTemplate();
		ResponseEntity<String> response = restTemp.getForEntity(uri, String.class);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(response.getBody());
				
		JsonNode[] arr = new JsonNode[daysNo];
		JsonNode days = root.path("forecast").path("forecastday");
				
		for(int i=0; i<daysNo; i++) {
			arr[i]=days.get(i);
		}
		for(JsonNode day: arr) {
			double total=day.path("day").path("totalsnow_cm").asDouble();
		}
		return arr;
	}
	
	
	
}
