package com.hbmop.app.webservice;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.hbmop.app.model.IndoorSite;

@WebService
public interface IndoorSiteWebService {
	@WebMethod
	@WebResult
	public List<IndoorSite> findAllIndoorSites();
	public List<IndoorSite> findByPropertyCode(@WebParam(name = "propertyCode", targetNamespace = "http://webservice.app.hbmop.com/")String propertyCode);
	public List<IndoorSite> findByChildPropertyCode(@WebParam(name = "childPropertyCode", targetNamespace = "http://webservice.app.hbmop.com/")String ChildPropertyCode);
	@WebMethod
	@WebResult
	public List<IndoorSite> findIndoorSite(
			@WebParam(name = "area", targetNamespace = "http://webservice.app.hbmop.com/")String area, 
			@WebParam(name = "networkType", targetNamespace = "http://webservice.app.hbmop.com/")String networkType, 
			@WebParam(name = "keyWord", targetNamespace = "http://webservice.app.hbmop.com/")String keyWord,
			@WebParam(name = "currentPage", targetNamespace = "http://webservice.app.hbmop.com/")int currentPage);
}
