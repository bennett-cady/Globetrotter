package com.SimpleProject;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
@RequestMapping("/snowfall")
public class SnowfallController {

	@GetMapping("/city={n}")
	@ResponseBody
	public ResponseEntity<String> weeklySnowTotal(@PathVariable("n") String city) throws JsonMappingException, JsonProcessingException 
	{
		SnowfallService sfs = new SnowfallService();
		double weeklyTotal = sfs.weeklySnowTotal(city);
		return ResponseEntity.ok().body("It will snow "+String.valueOf(weeklyTotal)+" cm in "+city+" this week.");
		
	}
	
	@GetMapping("/city={n}&inches")
	@ResponseBody
	public ResponseEntity<String> weeklySnowTotalIn(@PathVariable("n") String city) throws JsonMappingException, JsonProcessingException 
	{
		SnowfallService sfs = new SnowfallService();
		double weeklyTotalInches = sfs.weeklySnowTotal(city) * 0.39;
		return ResponseEntity.ok().body("It will snow "+String.valueOf(weeklyTotalInches)+" inches in "+city+" this week.");
		
	}
	
}