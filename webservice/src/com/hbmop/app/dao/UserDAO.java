package com.hbmop.app.dao;

import java.util.List;

import com.hbmop.app.model.User;

public class UserDAO extends DAOSupport<User>{

	public User findUserInformation(String account_name) {
		String hql="from User where account_name = ?";
		Object[] values={account_name};
		List<User> user = find(hql, values);
		User u = null;
		if(user.size()>0){
			u = user.get(0);
		}
		return u;
	}
	public User login(String account_name,String passWord) {
		String hql="from User where account_name=? and password = ?";
		Object[] values={account_name,passWord};
		List<User> user = find(hql, values);
		User u = null;
		if(user.size()>0){
			u = user.get(0);
		}
		return u;
	}
}
