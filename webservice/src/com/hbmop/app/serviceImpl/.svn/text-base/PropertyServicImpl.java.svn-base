package com.hbmop.app.serviceImpl;
import java.util.ArrayList;
import java.util.List;

import com.hbmop.app.dao.PropertyDAO;
import com.hbmop.app.dao.UserDAO;
import com.hbmop.app.model.Property;
import com.hbmop.app.service.PropertyService;

public class PropertyServicImpl implements PropertyService {

	private PropertyDAO pDAO;
	private UserDAO userDAO;
	
	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public PropertyDAO getpDAO() {
		return pDAO;
	}

	public void setpDAO(PropertyDAO pDAO) {
		this.pDAO = pDAO;
	}

	/**
	 * 分页查询物业点
	 */
	@Override
	public List<Property> findProperty(String area, String coverType,String keyWord, int currentPage) {
		List<Property> p = new ArrayList<Property>();
			
			int recordCount = pDAO.findByKeytypeAndCovertypeCount( area,  keyWord, coverType);
			if(recordCount==0){
				return p;
			}
			p = pDAO.findByKeytypeAndCovertypePage( area, keyWord, coverType,  recordCount, currentPage);
		return p;
	}
	


	@Override
	public List<Property> findAllProperty() {
		return pDAO.findAll();
	}

	@Override
	public Property findPropertyInformation(String property_code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Property findByPropertyCode(String propertyCode) {
		return pDAO.findPropertyInformation(propertyCode);
	}


	
	
}
