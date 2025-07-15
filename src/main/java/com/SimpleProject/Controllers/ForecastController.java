package com.SimpleProject.Controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.SimpleProject.Model.Location;
import com.SimpleProject.Services.ForecastService;

@RestController
@RequestMapping("/forecast")
public class ForecastController {
	
	@Autowired
	ForecastService forecastService;
	
	@GetMapping("/checkWeek/city={n}&idealTemp={temp_f}")
	@ResponseBody
	public ResponseEntity<String> checkWeek(@PathVariable("n") String city, @PathVariable("temp_f") double f) 
	{
		ForecastService fs = new ForecastService();
		return ResponseEntity.ok(fs.weekSummary(city, f));
	}
	
	
	@GetMapping("/findCityWith/idealTemp={temp_f}&margin={m}")
	@ResponseBody
	public ResponseEntity<ArrayList<Location>> findDestinations(@PathVariable("temp_f") double temp_f, @PathVariable("m") double m) {
		return ResponseEntity.ok(forecastService.findDestination(temp_f, m));
	}
	

}
