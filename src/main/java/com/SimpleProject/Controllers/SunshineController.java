package com.SimpleProject.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SimpleProject.Model.WeeklySunshineReport;
import com.SimpleProject.Services.SunshineService;

@RestController
@RequestMapping("/sunshine")
public class SunshineController {

	@Autowired
	SunshineService sunshineService;
	
	@GetMapping("/daily/city={n}")
	public ResponseEntity<String> getDailySunshinePercentage(@PathVariable String n) {
		double percentage = sunshineService.calculateDailySunshinePercentage(n);
		return ResponseEntity.ok(String.valueOf(percentage));
	}

	@GetMapping("/weekly/city={location}")
	public ResponseEntity<WeeklySunshineReport> getWeeklySunshinePercentage(@PathVariable String location) {
		WeeklySunshineReport wsr =  sunshineService.getWeeklySunshinePercentage(location);
		
		return ResponseEntity.ok(wsr);
	}
	
	
}
