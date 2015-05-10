package com.hbmop.app.serviceImpl;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hbmop.app.dao.IndoorSiteDAO;
import com.hbmop.app.model.IndoorSite;
import com.hbmop.app.service.IndoorSiteService;
@Service("indoorSiteService")
public class IndoorSiteServiceImpl implements IndoorSiteService {
	IndoorSiteDAO indoorSiteDAO;

	

	public IndoorSiteDAO getIndoorSiteDAO() {
		return indoorSiteDAO;
	}

	public void setIndoorSiteDAO(IndoorSiteDAO indoorSiteDAO) {
		this.indoorSiteDAO = indoorSiteDAO;
	}


	@Override
	public List<IndoorSite> findIndoorSiteProperty(String property_code) {
		// TODO Auto-generated method stub
		return indoorSiteDAO.findIndoorSiteAllProperty(property_code);
	}


	@Override
	public List<IndoorSite> findAllIndoorSite() {
		return indoorSiteDAO.findAll();
	}


	@Override
	public List<IndoorSite> findIndoorSite(String area, String networkType,
			String keyWord, int currentPage) {
		List<IndoorSite> p = new ArrayList<IndoorSite>();
		
		int recordCount = indoorSiteDAO.findByKeytypeAndNetworkTypeCount( area,  keyWord, networkType);
		if(recordCount==0){
			return p;
		}
		p = indoorSiteDAO.findByKeytypeAndNetworkTypePage( area, keyWord, networkType,  recordCount, currentPage);
	return p;
	}

	@Override
	public List<IndoorSite> findIndoorSiteByChildProperty(
			String childPropertyCode) {
		return indoorSiteDAO.findByChildProperty(childPropertyCode);
	}
	

		
		
}