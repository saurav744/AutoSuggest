package com.saurav.autosuggest.business;

import java.util.List;

public interface CityAutoSuggestor {
	
	public List<String> suggestCities(String str, int lim);

}
