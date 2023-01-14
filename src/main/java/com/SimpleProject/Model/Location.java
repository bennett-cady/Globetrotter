package com.SimpleProject.Model;

import java.util.ArrayList;

public class Location {

	@Override
	public String toString() {
		return "Location [city=" + city + ", region=" + region + ", country=" + country + ", continent=" + continent
				+ "]";
	}

	public String city;
	String region;
	String zipCode;
	String country;
	String continent;
	
	public Location(){}

	public Location(String city, String region, String zipCode, String country, String continent) {
		super();
		this.city = city;
		this.region = region;
		this.zipCode = zipCode;
		this.country = country;
		this.continent = continent;
	}
	
	public Location(String city, String region, String country, String continent) {
		super();
		this.city = city;
		this.region = region;
		this.country = country;
		this.continent = continent;
	}
	
	
	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
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

	public String getContinent() {
		return continent;
	}

	public void setContinent(String continent) {
		this.continent = continent;
	}

	public ArrayList<Location> populateKnown(){
		ArrayList<Location> loc = new ArrayList<Location>();
		
		Location l1 = new Location("Minneapolis", "Midewest", "United States", "North America");
		loc.add(l1);
		
		Location l2 = new Location("New York City", "East Coast", "United States", "North America");
		loc.add(l2);
		
		Location l3 = new Location("Denver", "West Coast", "United States", "North America");
		loc.add(l3);
		
		Location l4 = new Location("Marrakech", "Mediterranean", "Morocco", "Africa");
		loc.add(l4);
		
		Location l5 = new Location("Athens", "Mediterranean", "Greece", "Europe");
		loc.add(l5);
		
		Location l6 = new Location("Bangkok", "Southeast Asia", "Thailand", "Asia");
		loc.add(l6);
		
		Location l7 = new Location("San Pedro", "Caribbean", "Belize", "North America");
		loc.add(l7);
		
		Location l8 = new Location("Santo Domingo", "Caribbean", "Dominican Republic", "North America");
		loc.add(l8);
		
		Location l9 = new Location("Manila", "Southeast Asia", "Philippines", "Asia");
		loc.add(l9);
		
		Location l10 = new Location("Agana", "Pacific Islands", "United States", "Oceania");
		loc.add(l10);
		
		Location l11 = new Location("Sydney", "Australia", "Australia", "Oceania");
		loc.add(l11);
		
		return loc;
	}
	
}
