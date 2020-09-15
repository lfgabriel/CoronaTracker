package com.coronatracker.demo.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.coronatracker.demo.model.City;
import com.coronatracker.demo.service.ICityService;


@RestController
public class CoronaTrackerController {
	
	@Autowired
	private ICityService cityService;
	
	@RequestMapping("/corona/getInfectedFromAPI")
	public String getInfectedFromAPI() {
		System.out.println("opi");
		final String uri = "https://bigdata-api.fiocruz.br/relatorios/Abadia%20de%20Goiás%20GO.html";
		//final String uri = "https://bigdata-covid19.icict.fiocruz.br/#shiny-tab-estados";
		//final String uri = "https://www.covidbr.net.br";
	     
	    RestTemplate restTemplate = new RestTemplate();
	    //Global global = restTemplate.getForObject(uri, Global.class);
	    //String response = restTemplate.getForObject(uri, String.class);
	    //System.out.println("response: " + response);
	    
	    //cityService.extractInfectedsByCityFromFiocruzURL(response);
	    String csvFile = "/home/gabriel/Documentos/Gabriel/Aulas/JavaWEB/Juha/demo/caso.csv";
	    cityService.extractInfectedsByCityFromCSV(csvFile);
	    
	    return "Success";
	}
	
	@RequestMapping("/corona/test")
	public String test() {
		
	    return "Success";
	}
	
	

}
