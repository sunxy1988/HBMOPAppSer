package com.hbmop.app.serviceImpl;
import java.util.ArrayList;
import java.util.List;

import com.hbmop.app.dao.ChildPropertyDAO;
import com.hbmop.app.model.ChildProperty;
import com.hbmop.app.service.ChildPropertyService;

public class ChildPropertyServiceImpl implements ChildPropertyService{

	private ChildPropertyDAO childPropertyDAO;
	
	

	public ChildPropertyDAO getChildPropertyDAO() {
		return childPropertyDAO;
	}

	public void setChildPropertyDAO(ChildPropertyDAO childPropertyDAO) {
		this.childPropertyDAO = childPropertyDAO;
	}

	@Override
	public List<ChildProperty> findChildProperty(String involved_property_code) {
		return childPropertyDAO.findByProperyCode(involved_property_code);
	}

	@Override
	public ChildProperty findChildPropertyById(int child_property_id) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public ChildProperty findChildPropertyInformation(String child_property_code) {
		return childPropertyDAO.findByChildPropertyCode(child_property_code);
	}


	@Override
	public ChildProperty findByChildPropertyCode(String childPropertyCode) {
		return childPropertyDAO.findByChildPropertyCode(childPropertyCode);
	}


	@Override
	public List<ChildProperty> findByProperyCode(String propertyCode) {
		return childPropertyDAO.findByProperyCode(propertyCode);
	}






	@Override
	public List<ChildProperty> findChildProperty(String area, String coverType,
			String keyWord, int currentPage) {
		List<ChildProperty> p = new ArrayList<ChildProperty>();
		int recordCount = childPropertyDAO.findByKeytypeAndCovertypeCount( area,  keyWord, coverType);
		if(recordCount==0){
			return p;
		}
		p = childPropertyDAO.findByKeytypeAndCovertypePage( area, keyWord, coverType,  recordCount, currentPage);
	return p;
	}





	



}
