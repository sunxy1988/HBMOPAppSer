package com.hbmop.app.webserviceImpl;

import java.util.List;

import com.hbmop.app.model.IndoorSite;
import com.hbmop.app.service.IndoorSiteService;
import com.hbmop.app.webservice.IndoorSiteWebService;

public class IndoorSiteWebSerImpl implements IndoorSiteWebService{
	private IndoorSiteService indoorSiteService; 
	



	public IndoorSiteService getIndoorSiteService() {
		return indoorSiteService;
	}

	public void setIndoorSiteService(IndoorSiteService indoorSiteService) {
		this.indoorSiteService = indoorSiteService;
	}

	public List<IndoorSite> findAllIndoorSites() {
		List<IndoorSite> bb = indoorSiteService.findAllIndoorSite();
		for (IndoorSite indoorSite : bb) {
			System.out.println(indoorSite.getIndoor_site_code());
		}
		return bb;
	}

	@Override
	public List<IndoorSite> findByPropertyCode(String propertyCode) {
		return indoorSiteService.findIndoorSiteProperty(propertyCode);
	}

	@Override
	public List<IndoorSite> findIndoorSite(String area, String networkType,
			String keyWord, int currentPage) {
			List<IndoorSite> ss= indoorSiteService.findIndoorSite( area, networkType, keyWord,  currentPage);
			for (IndoorSite indoorSite : ss) {
				System.out.println(indoorSite.getId());
			}
			return ss;
		}

	@Override
	public List<IndoorSite> findByChildPropertyCode(String ChildPropertyCode) {
		return indoorSiteService.findIndoorSiteByChildProperty(ChildPropertyCode);
	}

}
