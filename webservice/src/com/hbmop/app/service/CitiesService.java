package com.hbmop.app.service;

import java.util.List;

import com.hbmop.app.model.Cities;


public interface CitiesService {

	List<Cities> findAllCities();

	Cities findByName(String name);

	List<Cities> findByPcode(String pcode);

	Cities findByCode(String code);

}
