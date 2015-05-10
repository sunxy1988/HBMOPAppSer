package com.hbmop.app.service;

import java.util.List;

import com.hbmop.app.model.Property;


public interface PropertyService{
	/**
	 * 分页查询物业点
	 * @param city
	 * @param coverType
	 * @param keyWord
	 * @param pageSize
	 * @param recordCount
	 * @param currentPage
	 */
	public List<Property> findProperty(String city, String coverType, String keyWord,int currentPage);
	/**
	 * 根据物业点编号查询物业点
	 * @param propertyCode
	 * @return
	 */
	public Property findByPropertyCode(String propertyCode);
	/**
	 * 通过物业点编号查询物业点信息
	 * @param property_code
	 * @return
	 */
	public Property findPropertyInformation(String property_code);
	/**
	 * 查询所有物业点信息
	 * @return
	 */
	public List<Property> findAllProperty();

	

}
