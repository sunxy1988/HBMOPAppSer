package com.hbmop.app.webserviceImpl;

import java.util.List;

import com.hbmop.app.model.Property;
import com.hbmop.app.service.PropertyService;
import com.hbmop.app.webservice.PropertyWebService;

public class PropertyWebSerImpl implements PropertyWebService{
	private PropertyService propertyService; 

	public PropertyService getPropertyService() {
		return propertyService;
	}

	public void setPropertyService(PropertyService propertyService) {
		this.propertyService = propertyService;
	}

	public List<Property> findAllProperty() {
		List<Property> bb = propertyService.findAllProperty();
		for (Property property : bb) {
			System.out.println(property.getProperty_code());
		}
		return bb;
	}


	/**
	 * 分页查询物业点
	 */
	public List<Property> findProperty( String area, String coverType, String keyWord,int currentPage) {
		List<Property> ss= propertyService.findProperty( area, coverType, keyWord,  currentPage);
		for (Property property : ss) {
			System.out.println(property.getId());
		}
		return ss;
//		return propertyService.findAllProperty();
	}
	/**
	 * 根据物业点编号查询物业点
	 */
	public Property findByPropertyCode(String propertyCode) {
		return propertyService.findByPropertyCode(propertyCode);
	}

}
