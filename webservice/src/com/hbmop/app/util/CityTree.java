package com.hbmop.app.util;

import java.util.List;

import com.hbmop.app.model.Cities;



public class CityTree {

	
	public String getCityTree(List<Cities> list){
				
		String json = "[{ id:1, pId:0, name:'湖北省',open:true},";
		for (int i = 0; i < list.size(); i++) {
			Cities cities = list.get(i);
			if(i == list.size()-1){
				String str = "{id:"+cities.getCode()+",pId:"+cities.getPcode()+",name:'"+
						cities.getName()+"',open:false}]";
				json += str;
			}else{
				String str = "{id:"+cities.getCode()+",pId:"+cities.getPcode()+",name:'"+
						cities.getName()+"',open:false},";
				json += str;
			}
			
		}
		
		return json;
	}
}
