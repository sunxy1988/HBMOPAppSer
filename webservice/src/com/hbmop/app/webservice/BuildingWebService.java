package com.hbmop.app.webservice;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.hbmop.app.model.Building;

@WebService
public interface BuildingWebService {
	@WebMethod
	@WebResult
	public List<Building> findAllBuilding();
	@WebMethod
	@WebResult
	public List<Building> findBuilding(
			@WebParam(name = "area", targetNamespace = "http://webservice.app.hbmop.com/")String area, 
			@WebParam(name = "coverType", targetNamespace = "http://webservice.app.hbmop.com/")String coverType, 
			@WebParam(name = "keyWord", targetNamespace = "http://webservice.app.hbmop.com/")String keyWord,
			@WebParam(name = "currentPage", targetNamespace = "http://webservice.app.hbmop.com/")int currentPage);
	/**
	 * 根据物业点编号查询楼宇
	 * @param propertyCode
	 * @return
	 */
	public List<Building> findByPropertyCode(@WebParam(name = "propertyCode", targetNamespace = "http://webservice.app.hbmop.com/")String propertyCode);
	/**
	 * 根据子物业点编号查询楼宇
	 * @param childPropertyCode
	 * @return
	 */
	public List<Building> findByChildPropertyCode(@WebParam(name = "childPropertyCode", targetNamespace = "http://webservice.app.hbmop.com/")String childPropertyCode);
	/**
	 * 根据楼宇编号查询楼宇
	 * @param buildingCode
	 * @return
	 */
	public Building findByBuildingCode(@WebParam(name = "buildingCode", targetNamespace = "http://webservice.app.hbmop.com/")String buildingCode);
}
