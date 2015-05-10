package com.hbmop.app.webserviceImpl;

import java.util.Calendar;
import java.util.List;

import com.hbmop.app.model.ChildProperty;
import com.hbmop.app.service.ChildPropertyService;
import com.hbmop.app.webservice.ChildfPropertyWebService;

public class ChildPropertyWebSerImpl implements ChildfPropertyWebService{
	private ChildPropertyService childPropertyService; 

	public ChildPropertyService getChildPropertyService() {
		return childPropertyService;
	}

	public void setChildPropertyService(ChildPropertyService childPropertyService) {
		this.childPropertyService = childPropertyService;
	}


	public String greeting(String userName){
	       return "Hello " + userName + ", currentTime is " + Calendar.getInstance().getTime(); 
	   }

	@Override
	public List<ChildProperty> findChildProperty(String area, String coverType,
			String keyWord, int currentPage) {
		List<ChildProperty> ss= childPropertyService.findChildProperty( area, coverType, keyWord,  currentPage);
		for (ChildProperty childpProperty : ss) {
			System.out.println(childpProperty.getId());
		}
		return ss;
	}


	@Override
	public ChildProperty findByChildProperty(String childPropertyCode) {
		
		return childPropertyService.findByChildPropertyCode(childPropertyCode);
	}

	@Override
	public List<ChildProperty> findByPropertyCode(String propertyCode) {
		return childPropertyService.findByProperyCode(propertyCode);
	}

}
