package com.coronatracker.demo.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties()
public class Global {
	
	private String newConfirmed;
	
	
	@JsonProperty("TotalConfirmed")
	private String totalConfirmed;
	
	@JsonProperty("Countries")
	List<Country> countries;
	

	public Global() {}

	public String getNewConfirmed() {
		return newConfirmed;
	}

	public void setNewConfirmed(String newConfirmed) {
		this.newConfirmed = newConfirmed;
	}

	public String getTotalConfirmed() {
		return totalConfirmed;
	}

	public void setTotalConfirmed(String totalConfirmed) {
		this.totalConfirmed = totalConfirmed;
	}

	public List<Country> getCountries() {
		return countries;
	}

	public void setCountries(List<Country> countries) {
		this.countries = countries;
	}

	@Override
	public String toString() {
		return "Global [newConfirmed=" + newConfirmed + ", totalConfirmed=" + totalConfirmed + ", countries="
				+ countries + "]";
	}
	

}
