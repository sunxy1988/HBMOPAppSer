package com.hbmop.app.service;

import java.util.List;

import com.hbmop.app.model.IndoorSite;


public interface IndoorSiteService {
	
	/**
	 * 分页查询室分站点
	 * @param city
	 * @param networkType
	 * @param keyWord
	 * @param recordCount
	 * @param currentPage
	 */
	public List<IndoorSite> findIndoorSite(String city, String networkType, String keyWord,int currentPage);

	public List<IndoorSite> findAllIndoorSite();

	public List<IndoorSite> findIndoorSiteProperty(String propertyCode);

	public List<IndoorSite> findIndoorSiteByChildProperty(
			String childPropertyCode);
	
}
