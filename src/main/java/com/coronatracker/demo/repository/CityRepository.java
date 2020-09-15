package com.coronatracker.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.coronatracker.demo.model.City;

@Repository
public interface CityRepository extends CrudRepository<City, Long> {
	List<City> findByNameIgnoreCase(String name);

}
