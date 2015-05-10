package com.hbmop.app.service;

import java.util.List;

import com.hbmop.app.model.Building;


public interface BuildingService  {
	
	/**
	 * 查询所有楼宇信息
	 * @return
	 */
	public List<Building> findAllBuilding();
	/**
	 * 通过楼宇编号查询楼宇信息
	 * @param building_code
	 * @return
	 */
	public Building findBuildingInformation(String building_code);

	List<Building> findByChild_property_code(String child_property_code);
	List<Building> findByProperty_code(String property_code);
	/**
	 * 新增子物业点之前查询关联物业点下楼宇
	 * @return
	 */
	public List<Building> findByPropertyCode(String involved_property_code);

	
	public List<Building> findBuilding(String area, String coverType,
			String keyWord, int currentPage);
}
