package com.SimpleProject.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.SimpleProject.SunshineAPICaller;
import com.SimpleProject.Model.IndividualDayReport;
import com.SimpleProject.Model.Location;
import com.SimpleProject.Model.WeeklySunForecastDTO;
import com.SimpleProject.Model.WeeklySunshineReport;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;



@Service
public class SunshineService {
	
	public double calculateDailySunshinePercentage(String city)
	{
		byte sunnyhrs = 0;
		byte totalhrs = 16;
		SunshineAPICaller sunshineApiCaller = new SunshineAPICaller();
		try {
			ArrayList<JsonNode> hourlyForecast = sunshineApiCaller.getDailySunshinePercentage(city);
			for(JsonNode hour: hourlyForecast.subList(6, 22))
			{
				String status = hour.path("condition").path("text").asText().toString();
				if(status.equals("Sunny")) {
					sunnyhrs++;
				}
				
			}	
		} catch(JsonMappingException jme) {
			System.out.println("JsonMappingException");
		} catch(JsonProcessingException jpe) {
			System.out.println("JsonProcessingException");
		}
		return (double) sunnyhrs/totalhrs;
	}
	
	
	public WeeklySunshineReport getWeeklySunshinePercentage(String city) 
	{
		System.out.println("in service...");
		SunshineAPICaller sunshineApiCaller = new SunshineAPICaller();
		WeeklySunshineReport wsr = new WeeklySunshineReport();
		try {
			WeeklySunForecastDTO report = sunshineApiCaller.getWeeklySunshinePercentage(city);
			
			Location loc = new Location( report.getCityName(), report.getRegion(), report.getCountry());
			wsr.setLocation(loc);
			
			List<IndividualDayReport> daily = new ArrayList<IndividualDayReport>();
		} catch(JsonMappingException jme) {
			System.out.println("JsonMappingException");
		} catch(JsonProcessingException jpe) {
			System.out.println("JsonProcessingException");
		}
		return wsr;
	}
	
	/*
	public WeeklySunshineReport getWeeklySunshinePercentage(String city) 
	{
		SunshineAPICaller sunshineApiCaller = new SunshineAPICaller();
		WeeklySunshineReport wsr = new WeeklySunshineReport();
		wsr.setLocation(city); //get more precise details (state/province, region, country) of the location from the API response
		try {
			ArrayList<JsonNode> report = sunshineApiCaller.getWeeklySunshinePercentage(city);
			List<IndividualDayReport> daily = new ArrayList<IndividualDayReport>();
			for(JsonNode day : report) 
			{
				IndividualDayReport idr = new IndividualDayReport();
				idr.setDate(day.get("date").asText());
				idr.setGeneralCondition(day.path("day").path("condition").path("text").asText());
				int idx=0;
				byte sunnyhrs = 0;
				byte totalhrs = 16;
				for(JsonNode hour : day.get("hour").get(idx))
				{
					System.out.println("yoink");
					System.out.println(hour.path("time").asText()); // not working
					if(idx<6 || idx>22) {
						continue;
					}
					String status = hour.path("condition").path("text").asText().toString();
					System.out.println(status);
					if(status.equals("Sunny")) {
						sunnyhrs++;
					}
					idx++;
				}
				idr.setSunshinePercentage( (double) sunnyhrs / totalhrs);
				daily.add(idr);
			}
			wsr.setWeekdays(daily);
			
		}catch(JsonMappingException jme) {
			System.out.println("JsonMappingException");
		} catch(JsonProcessingException jpe) {
			System.out.println("JsonProcessingException");
		}
		return wsr;
	}
	*/
	
	
}
