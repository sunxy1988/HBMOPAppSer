package com.demo;



import java.util.List;

import javax.jws.WebService;

import com.hbmop.app.model.Building;

@WebService 
public interface GreetingService { 
   public String greeting(String userName); 
   public List<Building> greetings(String userName); 
} 
 

