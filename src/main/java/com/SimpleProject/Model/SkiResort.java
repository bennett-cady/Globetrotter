package com.SimpleProject.Model;

public class SkiResort extends Location {
	
	double expectedSnow;

	public SkiResort() {
		super();
		expectedSnow = 0.0;
	}

	public SkiResort(String city, String region, String zipCode, String country, String continent) {
		super(city, region, zipCode, country, continent);
		expectedSnow = 0.0;
	}

	public SkiResort(String city, String region, String country, String continent) {
		super(city, region, country, continent);
		expectedSnow = 0.0;
	}

	public double getExpectedSnow() {
		return expectedSnow;
	}

	public void setExpectedSnow(double expectedSnow) {
		this.expectedSnow = expectedSnow;
	}
	
}
