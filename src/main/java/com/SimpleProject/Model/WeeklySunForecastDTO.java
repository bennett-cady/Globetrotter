package com.SimpleProject.Model;

import java.util.ArrayList;

import com.fasterxml.jackson.databind.JsonNode;

public class WeeklySunForecastDTO {
	
	String cityName;
	String region;
	String country;
	ArrayList<String> days;
	ArrayList<ArrayList<JsonNode>> hourly;
	
	public WeeklySunForecastDTO() {
		super();
	}

	public WeeklySunForecastDTO(String cityName, String region, String country, ArrayList<String> days,
			ArrayList<ArrayList<JsonNode>> hourly) {
		super();
		this.cityName = cityName;
		this.region = region;
		this.country = country;
		this.days = days;
		this.hourly = hourly;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public ArrayList<String> getDays() {
		return days;
	}

	public void setDays(ArrayList<String> days) {
		this.days = days;
	}

	public ArrayList<ArrayList<JsonNode>> getHourly() {
		return hourly;
	}

	public void setHourly(ArrayList<ArrayList<JsonNode>> hourly) {
		this.hourly = hourly;
	}
	
	
}
