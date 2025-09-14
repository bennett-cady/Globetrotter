package com.SimpleProject.Model;

import java.util.List;

public class WeeklySunshineReport {
	
	byte totalSunnyHrs;
	double totalSunnyPercentage;
	String location;
	List<IndividualDayReport> weekdays;
	String writtenForecast;
	
	
	
	public WeeklySunshineReport() {
		super();
	}
	
	
	
	
	public WeeklySunshineReport(byte totalSunnyHrs, double totalSunnyPercentage, String location,
			List<IndividualDayReport> weekdays, String writtenForecast) {
		super();
		this.totalSunnyHrs = totalSunnyHrs;
		this.totalSunnyPercentage = totalSunnyPercentage;
		this.location = location;
		this.weekdays = weekdays;
		this.writtenForecast = writtenForecast;
	}




	public byte getTotalSunnyHrs() {
		return totalSunnyHrs;
	}
	public void setTotalSunnyHrs(byte totalSunnyHrs) {
		this.totalSunnyHrs = totalSunnyHrs;
	}
	public double getTotalSunnyPercentage() {
		return totalSunnyPercentage;
	}
	public void setTotalSunnyPercentage(double totalSunnyPercentage) {
		this.totalSunnyPercentage = totalSunnyPercentage;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public List<IndividualDayReport> getWeekdays() {
		return weekdays;
	}
	public void setWeekdays(List<IndividualDayReport> weekdays) {
		this.weekdays = weekdays;
	}
	public String getWrittenForecast() {
		return writtenForecast;
	}
	public void setWrittenForecast(String writtenForecast) {
		this.writtenForecast = writtenForecast;
	}
	
	
}
