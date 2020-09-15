package com.coronatracker.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coronatracker.demo.model.City;
import com.coronatracker.demo.model.Person;
import com.coronatracker.demo.repository.PersonRepository;

@Service
public class PersonService implements IPersonService {
	
	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private CityService cityService;

	@Override
	public Iterable<Person> findAll() {
		return personRepository.findAll();
	}

	@Override
	public Person findById(Long id) {
		Optional<Person> optPerson = personRepository.findById(id);
		
		if (optPerson.get() != null) {
		    return optPerson.get();
		}
		else 
			return null;
	}

	@Override
	public List<Person> findByName(String name) {
		return null;
	}

	@Override
	public Person save(Person person) {
		return personRepository.save(person);
	}

	@Override
	public int remove(Long id) {
		Person person = findById(id);
		
		if (person != null) {
			City city = cityService.findById(person.getCity().getId());
			
			if (city != null ) {
				city.setTotalInfected(city.getTotalInfected() - 1);
				cityService.save(city);
				personRepository.deleteById(id);
				return 1;
			}
			else  
				return 0;
			
		}
		else { 
			return 0;
		}
	}
	

}
