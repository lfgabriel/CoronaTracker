package com.coronatracker.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties()
public class Country {
	
	@JsonProperty("Country")
	private String name;
	
	@JsonProperty("CountryCode")
	private String code;
	
	@JsonProperty("TotalConfirmed")
	private String totalConfirmed;
	
	@JsonProperty("TotalDeaths")
	private String totalDeath;
	

	public Country() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTotalConfirmed() {
		return totalConfirmed;
	}

	public void setTotalConfirmed(String totalConfirmed) {
		this.totalConfirmed = totalConfirmed;
	}

	public String getTotalDeath() {
		return totalDeath;
	}

	public void setTotalDeath(String totalDeath) {
		this.totalDeath = totalDeath;
	}

	@Override
	public String toString() {
		return "Country [name=" + name + ", code=" + code + ", totalConfirmed=" + totalConfirmed + ", totalDeath="
				+ totalDeath + "]";
	}
	
}
