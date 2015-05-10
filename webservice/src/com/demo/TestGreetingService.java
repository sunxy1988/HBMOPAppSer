package com.demo;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import com.hbmop.app.model.Building;
import com.hbmop.app.model.Property;
import com.hbmop.app.webservice.BuildingWebService;
import com.hbmop.app.webservice.PropertyWebService;

public class TestGreetingService {
	public static void main(String[] args) throws MalformedURLException {
		URL url=new URL("http://localhost:8080/webservice/PropertyService?wsdl");
		QName qname=new QName("http://webserviceImpl.app.hbmop.com/","PropertyWebSerImplService");
		Service service=Service.create(url, qname);
//		BuildingWebService buildingWebService = service.getPort(BuildingWebService.class);
		PropertyWebService buildingWebService = service.getPort(PropertyWebService.class);
//		List<Building> list = buildingWebService.findAllBuilding();
//		List<Property> list = buildingWebService.findAllProperty();
		List<Property> list = buildingWebService.findProperty("fdf", "", "", 2);
		for(Property building : list){
			System.out.println(building.getId());
		}   
		System.out.println(list.size());
	}
}

