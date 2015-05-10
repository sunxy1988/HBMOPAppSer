package com.hbmop.app.serviceImpl;

import java.util.List;

import com.hbmop.app.dao.CitiesDAO;
import com.hbmop.app.model.Cities;
import com.hbmop.app.service.CitiesService;

public class CitiesSerciceImpl implements CitiesService{

	CitiesDAO cDAO;

	
	public CitiesDAO getcDAO() {
		return cDAO;
	}

	public void setcDAO(CitiesDAO cDAO) {
		this.cDAO = cDAO;
	}

	@Override
	public List<Cities> findAllCities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cities findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cities> findByPcode(String pcode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cities findByCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
