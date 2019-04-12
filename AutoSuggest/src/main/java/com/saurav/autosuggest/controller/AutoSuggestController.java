package com.saurav.autosuggest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.saurav.autosuggest.business.CityAutoSuggestor;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value = "City names auto suggestion APIs")
public class AutoSuggestController {
	
	@Autowired
	private CityAutoSuggestor cityAutoSuggestor;
	
	
	@ApiOperation(value = "Home", response = String.class)
	@GetMapping("/")
	public String welcome() {
		String msg = "Welcome, you are awesome!";
		return msg;
	}
	
	
	@ApiOperation(value = "Returns auto suggestions for city names", response = List.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved suggestions"),
			@ApiResponse(code = 401, message = "You are not authorized to view this resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	@GetMapping("/suggest_cities")
	public List<String> getCityAutoSuggestions(
			@ApiParam(value = "Prefix of the city name", required = true)
			@RequestParam("start") String prefix, 
			@ApiParam(value = "Count limit of suggestion results", required = true) 
			@RequestParam("atmost") String limit) {
		
		return cityAutoSuggestor.suggestCities(prefix, Integer.parseInt(limit));
	}

}
