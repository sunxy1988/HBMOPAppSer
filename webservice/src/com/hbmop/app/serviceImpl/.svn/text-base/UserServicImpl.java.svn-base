package com.hbmop.app.serviceImpl;

import com.hbmop.app.dao.UserDAO;
import com.hbmop.app.model.User;
import com.hbmop.app.service.UserService;

public class UserServicImpl implements UserService {

	UserDAO userDAO;
	
	

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Override
	public User findByAccountName(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User login(String userName, String passWord) {
		return userDAO.login(userName, passWord);
		
	}

	

	

	
	
}
