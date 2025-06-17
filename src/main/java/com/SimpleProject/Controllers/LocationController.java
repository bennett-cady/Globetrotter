package com.SimpleProject.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.SimpleProject.ApiCaller;
import com.SimpleProject.Model.Location;
import com.SimpleProject.Services.LocationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;

@RestController
@RequestMapping("/current")
public class LocationController {
	
	@Autowired
	LocationService service;
	
	@GetMapping("/city={n}")
	@ResponseBody
	public ResponseEntity<String> weatherForCity(@PathVariable("n") String city) throws JsonMappingException, JsonProcessingException 
	{
		ApiCaller ac = new ApiCaller();
		JsonNode jnode = ac.showTempForCity(city);
		double temp = jnode.asDouble();
		return ResponseEntity.ok("It is "+String.valueOf(temp)+" (f) in "+city);
	}
	
	@GetMapping("/getAll")
	@ResponseBody
	public ResponseEntity<List<Location>> getAll () {
		return ResponseEntity.ok(service.findAll());
	}
}
