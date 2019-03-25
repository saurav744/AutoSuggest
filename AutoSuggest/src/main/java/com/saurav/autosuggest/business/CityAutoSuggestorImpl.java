package com.saurav.autosuggest.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saurav.autosuggest.dao.CityDao;
import com.saurav.autosuggest.model.City;

@Service("cityAutoSuggestor")
public class CityAutoSuggestorImpl implements CityAutoSuggestor{

	private SortedMap<String, City> cityMap = new TreeMap<>();

	@Autowired
	private CityDao cityDao;
	
	
    @PostConstruct
    public void init() {
    	List<City> cities = cityDao.findAll();
    	
    	for (City city : cities) {
    		cityMap.put(city.getName().toLowerCase(), city);
    	}
    }

	@Override
	public List<String> suggestCities(String prefix, int lim) {
		
		List<String> suggestions = new ArrayList<String>();
		int count= 1;
		
		for(Map.Entry<String, City> entry : filterCities(cityMap, prefix).entrySet()) {
			if(count > lim)
				break;
			suggestions.add(entry.getValue().getName() +", "+ entry.getValue().getState());
			count ++;
		}
		
		return suggestions;
	}
	

	private SortedMap<String, City> filterCities(SortedMap<String, City> baseMap, String prefix) {
		if(prefix.length() > 0) {
			char nextLetter = (char) (prefix.charAt(prefix.length() -1) + 1);
			String end = prefix.substring(0, prefix.length()-1) + nextLetter;
			return baseMap.subMap(prefix.toLowerCase(), end.toLowerCase());
		}
		return baseMap;
	}

}
