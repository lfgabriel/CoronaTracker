package com.coronatracker.demo.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coronatracker.demo.model.City;
import com.coronatracker.demo.service.ICityService;

@RestController
public class CityController {
	
	@Autowired
	private ICityService cityService;
	
	@RequestMapping("/cities")
    public Iterable<City> cities() {
		return cityService.findAll();
    }
	
	@RequestMapping("/city/add")
	public City addCity(@RequestBody City city) {
		return cityService.save(city);
	}
	
	@RequestMapping("/city/{cityName}")
	public List<City> findbyName(@PathVariable String cityName) {
		return cityService.findByName(cityName);
	}
	
	@RequestMapping("/city/update")
	public City updateCity(City city) {
		return city;
	}
	
	@RequestMapping("city/delete/{cityId}")
	public int deleteCity(@PathVariable String cityId) {
		return cityService.remove(Long.valueOf(cityId));
	}
	
	@RequestMapping("city/mostInfected")
	public HashMap<String, Integer> mostInfected() {
		
		return cityService.mostInfectedCity();
	}

}
