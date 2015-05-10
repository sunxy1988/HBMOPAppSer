package com.hbmop.app.dao;

import java.util.List;

import com.hbmop.app.model.Cities;


public class CitiesDAO extends DAOSupport<Cities>{

	
	public List<Cities> findAllCities(){
		List<Cities> list = this.findAll();
		return list;
	}
	
	 
	public Cities findByName(String name){
		String hql = "from Cities where name=?";
		Object[] values={name};
		List<Cities> list = find(hql, values);
		Cities cities = new Cities();
		if(list.size()>0){
			cities =list.get(0);
		}
		return cities;
	}
	
	 
	public List<Cities> findByPcode(String pcode){
		String hql = "from Cities where pcode=?";
		Object[] values={pcode};
		return find(hql, values);
	}
	
	
	public Cities findByCode(String code){
		String hql="from Cities where code = ?";
		Object[] values={code};
		List<Cities> list = find(hql, values);
		Cities city = null;
		if(list.size()>0){
			city = list.get(0);
		}
		return city;
	}
}
