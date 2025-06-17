package com.SimpleProject.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="gen_location")
public class Location {

	
	@Column(name="id")
	public int id;
	
	@Column(name="city")
	public String city;
	
	@Column(name="region")
	public String region;
	
	@Column(name="zip")
	public String zipCode;
	
	@Column(name="country")
	public String country;
	
	@Column(name="continent")
	public String continent;
	
	public Location() {}
	
	public Location(String city, String region, String zipCode, String country, String continent) {
		super();
		this.city = city;
		this.region = region;
		this.zipCode = zipCode;
		this.country = country;
		this.continent = continent;
	}
	
	public Location(int id, String city, String region, String zipCode, String country, String continent) {
		super();
		this.id = id;
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
	
	@Override
	public String toString() {
		return "Location [city=" + city + ", region=" + region + ", country=" + country + ", continent=" + continent
				+ "]";
	}
	
}
