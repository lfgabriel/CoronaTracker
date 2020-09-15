package com.coronatracker.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coronatracker.demo.model.City;
import com.coronatracker.demo.model.Person;
import com.coronatracker.demo.service.ICityService;
import com.coronatracker.demo.service.IPersonService;

@RestController
public class PersonController {
	
	@Autowired
	private ICityService cityService;
	
	@Autowired
	private IPersonService personService;
	
	
	@RequestMapping("/people")
	public Iterable<Person> people() {
		return personService.findAll();
	}
	
	
	@RequestMapping("/person/add")
	public Person addPerson(@RequestBody Person person) {
		
		System.out.println("cityId: " + person.getCity().getId());
		try {
			City search = cityService.findById(Long.valueOf(person.getCity().getId()));
			if (search != null) {
				search.setTotalInfected(search.getTotalInfected() + 1);
				return personService.save(person);
			}
			else
				return new Person();
		}
		catch (Exception e) {
			return new Person();
		}
		
		
	}
	
	@RequestMapping("/person/delete/{personId}")
	public int deletePerson(@PathVariable String personId) {
		return personService.remove(Long.valueOf(personId));
	}
	

}
