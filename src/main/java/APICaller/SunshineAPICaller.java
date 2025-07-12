package APICaller;

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
public class SunshineAPICaller {

	private static String weatherAPIKey;
	
	@Value("${api.key}") 
	public void setKey(String key) {
		SunshineAPICaller.weatherAPIKey = key;
	}
	
	public JsonNode getDailySunshinePercentage(String city) throws JsonMappingException, JsonProcessingException 
	{		
		String uri = "http://api.weatherapi.com/v1/forecast.json?key="+weatherAPIKey+"&q="+city+"&days=1";
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(response.getBody());
		JsonNode nodes = root.path("forecast").path("forecastday").get(0);
		return nodes;
	}
	
	
}
