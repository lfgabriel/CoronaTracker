package com.coronatracker.demo.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class City implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String state;
	
	@JsonIgnore
	@OneToMany(mappedBy = "city")
	private List<Person> infecteds;
	
	private Integer totalInfected;
	
	
	public City() {};
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getState() {
		return state;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	public List<Person> getInfecteds() {
		return infecteds;
	}


	public void setInfecteds(List<Person> infecteds) {
		this.infecteds = infecteds;
	}
	
	public Integer getTotalInfected() {
		return totalInfected;
	}

	public void setTotalInfected(Integer totalInfected) {
		this.totalInfected = totalInfected;
	}

	@Override
	public String toString() {
		return "City [id=" + id + ", name=" + name + ", state=" + state + ", totalInfected=" + totalInfected + "]";
	}

}
