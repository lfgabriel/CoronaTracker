package com.coronatracker.demo.service;

import java.util.List;

import com.coronatracker.demo.model.Person;

public interface IPersonService {
	
	Iterable<Person> findAll();
	
	Person findById(Long id);
	
	List<Person> findByName(String name);

	Person save(Person person);
	
	int remove(Long id);

}
