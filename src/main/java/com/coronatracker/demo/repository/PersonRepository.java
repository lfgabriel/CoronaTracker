package com.coronatracker.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.coronatracker.demo.model.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {

}
