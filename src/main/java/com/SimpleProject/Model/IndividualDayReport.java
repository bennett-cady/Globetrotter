package com.SimpleProject.Model;

public class IndividualDayReport {
	
	String date;
	String generalCondition;
	double sunshinePercentage;
	
	public IndividualDayReport() {
		super();
	}
	public IndividualDayReport(String date, String generalCondition, double sunshinePercentage) {
		super();
		this.date = date;
		this.generalCondition = generalCondition;
		this.sunshinePercentage = sunshinePercentage;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getGeneralCondition() {
		return generalCondition;
	}
	public void setGeneralCondition(String generalCondition) {
		this.generalCondition = generalCondition;
	}
	public double getSunshinePercentage() {
		return sunshinePercentage;
	}
	public void setSunshinePercentage(double sunshinePercentage) {
		this.sunshinePercentage = sunshinePercentage;
	}
	
	
}
