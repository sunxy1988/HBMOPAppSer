package com.hbmop.app.webservice;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.hbmop.app.model.Property;

@WebService
public interface PropertyWebService {
	@WebMethod
	@WebResult
	public List<Property> findAllProperty();
	public Property findByPropertyCode(@WebParam(name = "propertyCode", targetNamespace = "http://webservice.app.hbmop.com/")String propertyCode);
	@WebMethod
	@WebResult
	public List<Property> findProperty(
			@WebParam(name = "area", targetNamespace = "http://webservice.app.hbmop.com/")String area, 
			@WebParam(name = "coverType", targetNamespace = "http://webservice.app.hbmop.com/")String coverType, 
			@WebParam(name = "keyWord", targetNamespace = "http://webservice.app.hbmop.com/")String keyWord,
			@WebParam(name = "currentPage", targetNamespace = "http://webservice.app.hbmop.com/")int currentPage);
}
