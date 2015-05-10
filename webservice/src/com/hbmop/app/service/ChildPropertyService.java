package com.hbmop.app.service;

import java.util.List;

import com.hbmop.app.model.ChildProperty;


public interface ChildPropertyService {

	List<ChildProperty> findChildProperty(String involved_property_code);

	ChildProperty findChildPropertyById(int child_property_id);

	/**
	 * 通过子物业点编号查询物业点信息
	 * @param child_property_code
	 * @return
	 */
	public ChildProperty findChildPropertyInformation(String child_property_code);
	ChildProperty findByChildPropertyCode(String childPropertyCode);

	
	
	/**
	 * 根据物业点查询子物业点
	 * @param propertyCode
	 * @return
	 */
	public List<ChildProperty> findByProperyCode(String propertyCode);
	




	List<ChildProperty> findChildProperty(String area, String coverType,
			String keyWord, int currentPage);




}
