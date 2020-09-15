package com.coronatracker.demo.service;

import java.util.HashMap;
import java.util.List;

import com.coronatracker.demo.model.City;

public interface ICityService {

	Iterable<City> findAll();
	
	City findById(Long id);
	
	List<City> findByName(String name);

	City save(City city);
	
	int remove(Long id);

	HashMap<String, Integer> mostInfectedCity();
	
	void extractCitiesFromFiocruzURL(String body);
	
	void extractInfectedsByCityFromFiocruzURL(String body);

	void extractInfectedsByCityFromCSV(String csvFile);
	
}
