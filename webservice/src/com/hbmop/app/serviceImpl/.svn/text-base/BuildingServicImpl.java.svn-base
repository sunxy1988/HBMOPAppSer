package com.hbmop.app.serviceImpl;
import java.util.ArrayList;
import java.util.List;

import com.hbmop.app.dao.BuildingDAO;
import com.hbmop.app.model.Building;
import com.hbmop.app.service.BuildingService;


public class BuildingServicImpl implements BuildingService {
	private BuildingDAO bDAO;
	
	public BuildingDAO getbDAO() {
		
		return bDAO;
	}

	public void setbDAO(BuildingDAO bDAO) {
		System.out.println("BuildingDAO");
		this.bDAO = bDAO;
	}


	@Override
	public List<Building> findAllBuilding() {
		// TODO Auto-generated method stub
		return bDAO.findAll();
	}

	@Override
	public Building findBuildingInformation(String building_code) {
		return bDAO.findBuildingInformation(building_code);
	}


	@Override
	public List<Building> findByChild_property_code(String child_property_code) {
		return bDAO.findByChild_property_code(child_property_code);
	}

	@Override
	public List<Building> findByProperty_code(String property_code) {
		return bDAO.findByProperty_code(property_code);
	}


	/**
	 * 分页查询楼宇信息
	 */
	@Override
	public List<Building> findBuilding(String area, String coverType,
			String keyWord, int currentPage) {
		List<Building> p = new ArrayList<Building>();
		
		int recordCount = bDAO.findByKeytypeAndCovertypeCount( area,  keyWord, coverType);
		if(recordCount==0){
			return p;
		}
		p = bDAO.findByKeytypeAndCovertypePage( area, keyWord, coverType,  recordCount, currentPage);
	return p;
	}


	/**
	 * @author sunxingyang 根据物业点编号查询没被子物业点占用的楼宇
	 */
	@Override
	public List<Building> findByPropertyCode(String involved_property_code) {
		return bDAO.findByPropertyCode(involved_property_code);
	}

	

	
	
}
