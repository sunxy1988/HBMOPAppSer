package com.hbmop.app.webserviceImpl;

import com.hbmop.app.model.User;
import com.hbmop.app.service.UserService;
import com.hbmop.app.webservice.LoginWebService;







public class LoginWebSerImpl implements LoginWebService{
	private UserService userService; 

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public User login(String userName, String passWord) {
		return userService.login(userName,passWord);
	}





}
