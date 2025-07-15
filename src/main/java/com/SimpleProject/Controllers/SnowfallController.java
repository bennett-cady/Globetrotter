package com.SimpleProject.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.SimpleProject.Model.Location;
import com.SimpleProject.Model.SkiResort;
import com.SimpleProject.Services.SnowfallService;

@RestController
@RequestMapping("/snowfall")
public class SnowfallController 
{
	
	SnowfallService sfs = new SnowfallService();

	@GetMapping("/city={n}")
	@ResponseBody
	public ResponseEntity<String> weeklySnowTotal(@PathVariable("n") String city) {
		double weeklyTotal = sfs.weeklySnowTotal(city);
		return ResponseEntity.ok().body("It will snow "+String.valueOf(weeklyTotal)+" cm in "+city+" this week.");
	}
	
	@GetMapping("/city={n}&inches")
	@ResponseBody
	public ResponseEntity<String> weeklySnowTotalIn(@PathVariable("n") String city) {
		double weeklyTotalInches = sfs.weeklySnowTotal(city) * 0.39;
		return ResponseEntity.ok().body("It will snow "+String.valueOf(weeklyTotalInches)+" inches in "+city+" this week.");
	}
	
	@GetMapping("/largestTotals&days={days}")
	@ResponseBody
	public ResponseEntity<Location> bestSkiResort(@PathVariable("days") int days) {
		Location maxL = sfs.findHighestTotal(days);
		return ResponseEntity.ok().body(maxL);
	}
	
	@GetMapping("/rankResorts&days={days}")
	@ResponseBody
	public ResponseEntity<SkiResort[]> rankByTotal(@PathVariable("days") int days) {
		SkiResort[] res = sfs.rankResortSnowfall(days);
		return ResponseEntity.ok().body(res);
	}
	
	
	
}
