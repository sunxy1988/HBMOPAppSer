package com.hbmop.app.webservice;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.hbmop.app.model.ChildProperty;

@WebService
public interface ChildfPropertyWebService {
	@WebMethod
	@WebResult
	public String greeting(@WebParam(name = "userName", targetNamespace = "http://webservice.app.hbmop.com/")String userName); 
	public List<ChildProperty> findChildProperty(
			@WebParam(name = "area", targetNamespace = "http://webservice.app.hbmop.com/")String area, 
			@WebParam(name = "coverType", targetNamespace = "http://webservice.app.hbmop.com/")String coverType, 
			@WebParam(name = "keyWord", targetNamespace = "http://webservice.app.hbmop.com/")String keyWord,
			@WebParam(name = "currentPage", targetNamespace = "http://webservice.app.hbmop.com/")int currentPage);
	public ChildProperty findByChildProperty(@WebParam(name = "childPropertyCode", targetNamespace = "http://webservice.app.hbmop.com/")String childPropertyCode);
	public List<ChildProperty> findByPropertyCode(@WebParam(name = "propertyCode", targetNamespace = "http://webservice.app.hbmop.com/")String propertyCode);
}
