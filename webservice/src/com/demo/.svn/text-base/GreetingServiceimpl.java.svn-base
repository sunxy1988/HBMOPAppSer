package com.demo;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.jws.WebService;

import com.demo.GreetingService;
import com.hbmop.app.model.Building;

@WebService(endpointInterface = "com.demo.GreetingService") 
public class GreetingServiceimpl implements GreetingService { 

   public String greeting(String userName){
       return "Hello " + userName + ", currentTime is " + Calendar.getInstance().getTime(); 
   } 
   
   public List<Building> greetings(String userName){
		Building b = new Building();
		List<Building> ls = new ArrayList<Building>();
		b.setBuilding_code(userName);
		ls.add(b);
//	       return "Hello " + userName + ", currentTime is " + Calendar.getInstance().getTime(); 
		return ls;
	   }
} 

