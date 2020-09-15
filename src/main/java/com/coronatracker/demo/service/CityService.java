package com.coronatracker.demo.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.coronatracker.demo.model.City;
import com.coronatracker.demo.model.Person;
import com.coronatracker.demo.repository.CityRepository;
import com.coronatracker.demo.repository.PersonRepository;

@Service
public class CityService implements ICityService {

	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private PersonRepository personRepository;
	
	@Override
	public Iterable<City> findAll() {
		return cityRepository.findAll();
	}
	
	@Override
	public City findById(Long id) {
		Optional<City> cityOpt = cityRepository.findById(id);
		City city = cityOpt.get();
		if (city != null)
			return city;
		else
			return null;
	}

	@Override
	public List<City> findByName(String name) {
		
		return cityRepository.findByNameIgnoreCase(name);
	}
	
	
	@Override
	public City save(City city) {
		return cityRepository.save(city);
	}

	@Override
	public int remove(Long id) {
		
		cityRepository.deleteById(id);
		return 1;
	}

	@Override
	public HashMap<String, Integer> mostInfectedCity() {
		HashMap<String, Integer>  infecteds = new HashMap<>();
		
		Iterable<City> cities = findAll();
		for (City city : cities)
			infecteds.put(city.getName(), 0);
		
		System.out.println(infecteds);
		Iterable<Person> people = personRepository.findAll();
		for (Person person : people) {
			Integer cityInfecteds = infecteds.get(person.getCity().getName());
			infecteds.put(person.getCity().getName(), cityInfecteds + 1);
		}
		
		int max = 0;
		String cityName = "";
		for (String key : infecteds.keySet()) {
			if (infecteds.get(key) > max) {
				max = infecteds.get(key);
				cityName = key;
			}
		}
		
		System.out.println("most: " + cityName);
		
		System.out.println(infecteds);
		return infecteds;
		
	}

	@Override
	public void extractCitiesFromFiocruzURL(String body) {
		for (String line : body.split("\n")) {
	    	
	    	if (line.contains("#Mun BR")) {
	    		//System.out.println("line: " + line);
	    		String splitted[] = line.split("#Mun BR:\\s");
	    		//System.out.println("splited: " + Arrays.toString(splitted));
	    		String finalSplit = splitted[2].split("</option>")[0];
	    		//System.out.println("splited: " + finalSplit);
	    		
	    		//save cities (pattern City ST) 
	    		City city = new City();
	    		city.setName(finalSplit);
	    		save(city);
	    	}
	    }
	}

	@Override
	public void extractInfectedsByCityFromFiocruzURL(String body) {
		
		Iterable<City> cities = findAll();
		
		/*
		for (City city : cities) {
			final String nextPage = "https://bigdata-api.fiocruz.br/relatorios/" + city.getName() + ".html";
			System.out.println("next: " + nextPage);
			RestTemplate restTemplate = new RestTemplate();
			String response = restTemplate.getForObject(nextPage, String.class);
			System.out.println("next response: " + response);
			break;
		}
		*/
		
		for (String line : body.split("\n")) {
			
			if (line.contains("href") && !line.contains("h1")) {
				System.out.println("line: " + line);
				String splitted[] = line.split("\"");
				System.out.println("splited: " + Arrays.toString(splitted));
				
				final String nextPage = "https://bigdata-api.fiocruz.br/relatorios/" + splitted[1];
				//final String nextPage = "https://bigdata-api.fiocruz.br/relatorios/Abadia%20de%20Goia%CC%81s%20GO.html";
				
				System.out.println("next: " + nextPage);
				RestTemplate restTemplate = new RestTemplate();
				String response = restTemplate.getForObject(nextPage, String.class);
				System.out.println("next response: " + response);
				break;
			}
		}
		
	}

	@Override
	public void extractInfectedsByCityFromCSV(String csvFile) {
		BufferedReader br = null;
		String line = "";
        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] city = line.split(",");

                System.out.println("City name:" + city[2] + " , deaths=" + city[4] + "]");
                
                List<City> cities = findByName(city[2]);
                if (cities.size() > 0) {
                	System.out.println("found");
                	if (cities.get(0).getTotalInfected() == null) {
                		cities.get(0).setTotalInfected(Integer.valueOf(city[4]));
                		System.out.println("change");
                		save(cities.get(0));
                	}
                }
                else
                	System.out.println("not found");
                
                
            }
            
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
		
	}
	
	

	
}
