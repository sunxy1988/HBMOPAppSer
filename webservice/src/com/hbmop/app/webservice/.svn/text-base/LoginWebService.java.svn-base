package com.hbmop.app.webservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.hbmop.app.model.User;


@WebService
public interface LoginWebService {
	@WebMethod
	@WebResult
	public User login(@WebParam(name = "userName", targetNamespace = "http://webservice.app.hbmop.com/")String userName,
			@WebParam(name = "passWord", targetNamespace = "http://webservice.app.hbmop.com/")String passWord);
}
