package com.saurav.autosuggest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saurav.autosuggest.model.City;

public interface CityDao extends JpaRepository<City, Long> {

}
